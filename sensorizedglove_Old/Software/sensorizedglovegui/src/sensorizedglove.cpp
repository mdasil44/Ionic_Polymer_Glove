#include "sensorizedglove.h"
#include "ui_sensorizedglove.h"
#include <QSerialPort>
#include <QSerialPortInfo>
#include <string>
#include <sstream>
#include <QDebug>
#include <QDesktopServices>
#include <QMessageBox>

using namespace std;

#define LB_FORCE_TO_NEWTONS 0.224808942443


SensorizedGlove::SensorizedGlove(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::SensorizedGlove), recordData(0), readyArduRead(0), calibrating(0), loadCellForce(0),
    loadCellMaxForce(10), sensorToCalibrate(0), flashON(0), assessCalibra(0),
    tempBiasloadCellForCalibration(0), tempBiasFSRForCalibration(0), firstGoodValueCalibrating(true),
    tempSlopeGlobal(0), tempIntersecGlobal(0)

{
    ui->setupUi(this);
    setWindowIcon(QIcon(":/images/WearMe.ico"));
    renderSceneElements();
    timerDataReceived = new QTimer(this);
    flashGloveColourMap = new QTimer(this);
    serialBuffer = "";
    AcquiredDataPath = "./data";
    arduino = new QSerialPort(this);
    loadCell = new LoadCellPPS();

    //initialize loadCell
    loadCell->initialize(ui->logger);
    if(!loadCell->start()){
        ui->logger->append("PPS load cell initialized properly");
    }
    ui->maxCalibForceSpinBox->setValue(loadCellMaxForce);
    std::stringstream msg;
    msg << loadCellMaxForce << " N";
    ui->maxForceLabel->setText(msg.str().c_str());


    //load FSRs calibration values
    loadFSRCalibration();

    if(connectArduino())
        connect(arduino, SIGNAL(readyRead()), this, SLOT(readSerial()));

    //Connect signals and slots
    connect(timerDataReceived, SIGNAL(timeout()), this, SLOT(dataNotReceived()));
    connect (flashGloveColourMap, SIGNAL(timeout()), this, SLOT(changeFlashColour()));
    connect(ui->actionCalibrate, SIGNAL(triggered(bool)), this, SLOT(activeCalibrationPanel(bool)));
    connect(ui->actionAssess_Calibration, SIGNAL(triggered(bool)), this, SLOT(assessCalibration(bool)));


    //for finding pixels points in the image. False when not used
    setMouseTracking(false);
}


SensorizedGlove::~SensorizedGlove()
{
    recordData = false;
    if(acquiredDataFile.is_open()){
        acquiredDataFile.close();
        ui->logger->append("Closing file.");
    }
    timerDataReceived->stop();
    if(arduino->isOpen()){
        ui->logger->append("Closing arduino.");
        arduino->close();
    }

    delete ui;
}


/****************************
 * opens and connects Arduino
 * *************************/
bool SensorizedGlove::connectArduino(){
    bool arduino_is_available = false;
    QString arduino_port_name;

    if(QSerialPortInfo::availablePorts().count()>0){
        //check if the arduino is connected to any of the ports available
        foreach(const QSerialPortInfo & serialPortInfo, QSerialPortInfo::availablePorts()){            
            if(serialPortInfo.hasVendorIdentifier() && serialPortInfo.hasProductIdentifier()){
                if(serialPortInfo.vendorIdentifier() == arduino_mega_vendor_id){
                    if(serialPortInfo.productIdentifier() == arduino_mega_product_id){
                        arduino_is_available = true;
                        arduino_port_name = serialPortInfo.portName();
                    }
                }
            }
        }

        if (arduino_is_available) {
            ui->logger->append("Arduino available on port " + arduino_port_name);
            //open and configure the serialport
            arduino->setPortName(arduino_port_name);
            if(arduino->open(QSerialPort::ReadOnly)){
                ui->l_arduino->setText("<font color='Black'>Connected");
                arduino->setBaudRate(QSerialPort::Baud115200);
                arduino->setDataBits(QSerialPort::Data8);
                arduino->setParity(QSerialPort::NoParity);
                arduino->setStopBits(QSerialPort::OneStop);
                arduino->setFlowControl(QSerialPort::NoFlowControl);
                return true;
            }
            else{
                std::stringstream errormsg;
                errormsg << "<span style=\"color: #F10000; font-weight: bold;\">";
                errormsg << "Could not connect arduino.";
                errormsg << "</span>";
                ui->logger->append(errormsg.str().c_str());
                return false;
            }
        }
        else{
            std::stringstream errormsg;
            errormsg << "<span style=\"color: #F10000; font-weight: bold;\">";
            errormsg << "The Arduino is not connected to any of the available COM ports. Ensure the Arduino is connected and restart the application.";
            errormsg << "</span>";
            ui->logger->append(errormsg.str().c_str());
            return false;
        }
    }
    else{
        std::stringstream errormsg;
        errormsg << "<span style=\"color: #F10000; font-weight: bold;\">";
        errormsg << "There are no serial ports available. Ensure the Arduino is connected and restart the application.";
        errormsg << "</span>";
        ui->logger->append(errormsg.str().c_str());//"There are no serial ports available. Ensure the Arduino is connected and restart the application. \n");
        return false;
    }    
}


/**************************
 * renders the GUI elements
 * ***********************/
void SensorizedGlove::renderSceneElements(){
    scene = new QGraphicsScene(this);
    ui->graphicsView->setScene(scene);
    QPen outlinePen(Qt::black);
    outlinePen.setWidth(2);
    QPolygonF myPolygon;

    //draw the Right Hand image
    QPixmap RHpixmap(":/images/RightHand.png");
    scene->addPixmap(RHpixmap);

    //draw polygons on the phalanges, representing the location of the FSR
    myPolygon << QPointF(71, 406) << QPointF(102, 392) << QPointF(141, 399) << QPointF(180, 445) << QPointF(146, 476);
    T1_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(158, 490) << QPointF(193, 456) << QPointF(238, 476) << QPointF(187, 526);
    T2_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(212, 138) << QPointF(254, 126) << QPointF(270, 176) << QPointF(225, 192);
    I1_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(231, 213) << QPointF(276, 198) << QPointF(296, 253) << QPointF(247, 271);
    I2_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(254, 303) << QPointF(304, 283) << QPointF(327, 341) << QPointF(272, 364);
    I3_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(371, 89) << QPointF(423, 89) << QPointF(421, 157) << QPointF(371, 157);
    M1_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(373, 179) << QPointF(420, 179) << QPointF(420, 245) << QPointF(374, 245);
    M2_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(374, 270) << QPointF(420, 270) << QPointF(420, 341) << QPointF(374, 341);
    M3_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(531, 119) << QPointF(576, 136) << QPointF(561, 188) << QPointF(517, 173);
    R1_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(507, 203) << QPointF(554, 217) << QPointF(537, 271) << QPointF(492, 258);
    R2_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(483, 291) << QPointF(529, 305) << QPointF(512, 366) << QPointF(467, 353);
    R3_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(661, 219) << QPointF(692, 237) << QPointF(673, 273) << QPointF(640, 254);
    S1_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(630, 268) << QPointF(664, 289) << QPointF(633, 341) << QPointF(599, 319);
    S2_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(592, 329) << QPointF(628, 351) << QPointF(597, 405) << QPointF(560, 383);
    S3_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(217, 565) << QPointF(278, 503) << QPointF(350, 578) << QPointF(289, 639);
    P1_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(276, 454) << QPointF(283, 407) << QPointF(339, 416) << QPointF(329, 463);
    P2_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(526, 513) << QPointF(545, 463) << QPointF(588, 479) << QPointF(571, 531);
    P3_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(501, 588) << QPointF(544, 604) << QPointF(563, 554) << QPointF(518, 538);
    P4_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

    myPolygon.clear();
    myPolygon << QPointF(497, 601) << QPointF(539, 616) << QPointF(521, 663) << QPointF(480, 647);
    P5_polygon = scene->addPolygon(myPolygon, outlinePen, Qt::blue);

}


/*****************************************
 * reads the text file that contains
 * the calibration information of the FSRs
 * **************************************/
void SensorizedGlove::loadFSRCalibration(){
    ifstream inFSRCalibrationFile ("setup/FSR_calibration_values.txt");
    string token;

    for (int i = 0; i<16; i++){
        for (int j = 0; j<2; j++ ){
            inFSRCalibrationFile >> token;
            FSRCalibrationValues[i][j] = atof(token.c_str());


        }        
    }
    inFSRCalibrationFile.close();
}


/********************************
 * Reads serial data from Arduino
********************************/
void SensorizedGlove::readSerial() {
    double FSRvoltages[16] = {0};
    double FSRforces[16] = {0};
    double FSRConductance[16] = {0};
    double FSRResistance = 0;

    COLOUR FSRcolours[16] = {0};    

    //for first time reading
    if(!readyArduRead){
        readyArduRead = true;
        ui->StartRecordingB->setEnabled(true);
        timerDataReceived->start(2500);
    }

    /*
     * readyRead() doesn't guarantee that the entire message will be received all at once.
     * The message can arrive split into parts.  Need to buffer the serial data and then parse to look for the force values
     * The complete message received should be until a \n is received"
     *
     */

    serialData = arduino->readAll();
    serialBuffer += QString::fromStdString(serialData.toStdString());
    timerDataReceived->start();     //for checking data are received

    //only proceed if full message received
    if (serialBuffer.contains("f") && serialBuffer.contains("\n")){        
        //check data are in first force value
        if(!serialBuffer.indexOf("f") == 0){
            serialBuffer.remove(0, serialBuffer.indexOf("f"));
        }
        else {
            //removing bits extra
            serialBuffer.remove(serialBuffer.indexOf("\n"),serialBuffer.size() - serialBuffer.indexOf("\n"));
            QStringList buffer_split = serialBuffer.split(",");
            //extra layer of safety
            if(buffer_split.size() == 25){    //ensures a full package is received
                //transform voltage value to resistance value. Loads a voltage array in the order:
                //T1, T2, I1, I2, M1, M3, R1, R3, S1, S2, S3, P1, P2, P3, P4, P5 (See documentation for details of the sensors IDs)
                FSRvoltages[0] = buffer_split[3].toDouble();
                FSRvoltages[1] = buffer_split[2].toDouble();
                FSRvoltages[2] = buffer_split[14].toDouble();
                FSRvoltages[3] = buffer_split[8].toDouble();
                FSRvoltages[4] = buffer_split[10].toDouble();
                FSRvoltages[5] = buffer_split[9].toDouble();
                FSRvoltages[6] = buffer_split[11].toDouble();
                FSRvoltages[7] = buffer_split[5].toDouble();
                FSRvoltages[8] = buffer_split[7].toDouble();
                FSRvoltages[9] = buffer_split[12].toDouble();
                FSRvoltages[10] = buffer_split[6].toDouble();
                FSRvoltages[11] = buffer_split[1].toDouble();
                FSRvoltages[12] = buffer_split[4].toDouble();
                FSRvoltages[13] = buffer_split[15].toDouble();
                FSRvoltages[14] = buffer_split[16].toDouble();
                FSRvoltages[15] = buffer_split[13].toDouble();

                //updates glove colour map and loads a resistance array
                for (int i = 0; i<16; i++){
                    if (FSRvoltages[i]!=0){
                        // The voltage = Vcc * R / (R + FSR) where R = 10K and Vcc = 5V
                        // so FSR = ((Vcc - V) * R) / V
                        FSRResistance = ((5000 - FSRvoltages[i]) * 10000) / FSRvoltages[i];     // fsrVoltage is in millivolts so 5V = 5000mV, 10K resistor
                        FSRConductance[i] = 1000000  / FSRResistance ;   // we measure in micromhos, so 1000000.
                    }
                    //For debugging
                    //qDebug() << "Volt : " << FSRvoltages[i] << " Resis : " << FSRResistance << " Conduc : " << FSRConductance << " Force : " << FSRforces[i];
                    //FSRConductance = 0;
                    FSRResistance = 0;
                }

                if(calibrating){

                    loadCell->acquireData(loadCellForce);
                    loadCellForce = loadCellForce / LB_FORCE_TO_NEWTONS;   //changes LB Force to Newtons
                    std::stringstream currentCellForceload;
                    currentCellForceload << "Current load: " << loadCellForce << " N";
                    ui->currentLoadLabel->setText(currentCellForceload.str().c_str());
                    ui->loadCellProgressBar->setValue(int(loadCellForce/loadCellMaxForce*100));
                    std::stringstream currentFSRConductance;
                    currentFSRConductance << "Current FSR Conductance: " << FSRConductance[sensorToCalibrate] << " S";
                    ui->FSRConductanceLabel->setText(currentFSRConductance.str().c_str());
                    //detects when pressure is being applied on the load cell
                    if (loadCellForce > 0.01 && FSRConductance[sensorToCalibrate] > 0.1 ){
                        if(firstGoodValueCalibrating){
                            tempBiasloadCellForCalibration = loadCellForce;  //for bias during calibration
                            tempBiasFSRForCalibration = FSRConductance[sensorToCalibrate];
                            firstGoodValueCalibrating = false;
                        }
                        else{
                            FSRTempResistance.push_back(FSRConductance[sensorToCalibrate] - tempBiasFSRForCalibration);
                            LoadCellTempForce.push_back(loadCellForce - tempBiasloadCellForCalibration);


                            if(loadCellForce > loadCellMaxForce){
                                double r2_deterCoeff = 0, tempSlope = 1, tempIntersec = 0;


                                //For debugging
                            //    for (int i = 0; i<FSRTempResistance.size(); i++)
                            //    qDebug() << FSRTempResistance[i] << "   " << LoadCellTempForce[i];
                                //////////////////////////



                                r2_deterCoeff = calculateLinearRegressionForCalibration(FSRTempResistance, LoadCellTempForce, tempSlope, tempIntersec);
                                if (fabs(r2_deterCoeff) >= 0.97 /*&& fabs(tempIntersec) <= 0.5*/ ){
                                    std::stringstream msg;
                                    msg << "<font color='Green'>Sensor calibrated properly. Proceed with the next sensor. <br> r2: " << r2_deterCoeff << " m: " << tempSlope << " b: " << tempIntersec;
                                    ui->calibrationResultLabel->setText(QString::fromStdString(msg.str()));
                                    //saves new calibration parameters
                                    FSRCalibrationValues[sensorToCalibrate][0] = tempSlope;
                                    FSRCalibrationValues[sensorToCalibrate][1] = 0;//tempIntersec;
                                    sensorToCalibrate++;

                                    //calibration completed when the sensorToCalibrate counter equals 16
                                    if (sensorToCalibrate == 16){
                                        emit ui->actionCalibrate->triggered(false);
                                        ui->actionCalibrate->setChecked(false);
                                        QMessageBox calibrationMsgBox;
                                        calibrationMsgBox.setText("Calibration of the FSR pressure sensors completed.");
                                        calibrationMsgBox.exec();
                                        ui->logger->append("Calibration completed.");
                                        saveCalibrationValues();
                                    }
                                }
                                else{
                                    std::stringstream msg;
                                    msg << "<font color='Red'>Sensor was NOT calibrated properly. Please repeat calibration.<br> r2: " << r2_deterCoeff << " m: " << tempSlope << " b: " << tempIntersec;
                                    ui->calibrationResultLabel->setText(QString::fromStdString(msg.str()));
                                }
                                FSRTempResistance.clear();
                                LoadCellTempForce.clear();
                                ::Sleep(1500);
                            }
                        }
                    }
                    else{
                        //to ensure calibration works in case it is restarted
                        FSRTempResistance.clear();
                        LoadCellTempForce.clear();
                        tempBiasloadCellForCalibration=0;
                        tempBiasFSRForCalibration=0;
                        firstGoodValueCalibrating = true;
                    }
                }
                else{
                    //if calibration is being assessed, stores the load cell values at any point in time
                    if (assessCalibra){
                        loadCell->acquireData(loadCellForce);
                        loadCellForce = loadCellForce / LB_FORCE_TO_NEWTONS;   //changes LB Force to Newtons

                        std::stringstream msg;
                        msg << "Current load: " << loadCellForce;
                        ui->currentLoadLabel->setText(msg.str().c_str());
                        ui->loadCellProgressBar->setValue(int(loadCellForce/loadCellMaxForce*100));
                    }

                    //Use the calculated Conductance in conjunction with the calibrated values to determine the actual force measured
                    for (int i = 0; i<16; i++){
                        if (FSRConductance[i]!=0)
                            FSRforces[i] = FSRConductance[i] * FSRCalibrationValues[i][0] /*+ FSRCalibrationValues[i][1]*/;

                        else
                            FSRforces[i] = 0;

                        FSRcolours[i] = GetColour(FSRforces[i], 0, loadCellMaxForce);
                    }


                    //for (int i = 0; i<16; i++){

                       // qDebug() << FSRforces[4] << "  " << FSRConductance[0] << "  " << FSRCalibrationValues[0][0] << "  " <<  FSRCalibrationValues[0][1];;
                    //}



                    //update color map of the glove
                    updateGloveColourMap(FSRcolours);

                    if (recordData)
                        saveAcquiredData(FSRforces, buffer_split, loadCellForce);
                }


                //checks accelerometer status, error if all values equals -1
                if (buffer_split[17].toDouble() == -1 && buffer_split[18].toDouble() == -1 && buffer_split[19].toDouble() == -1
                    && buffer_split[21].toDouble() == -1 && buffer_split[22].toDouble() == -1 && buffer_split[23].toDouble() == -1)
                    ui->l_accelerometer->setText("<font color='Red'>NOT WORKING");
                else
                    ui->l_accelerometer->setText("<font color='Black'> Z Acc: " + QString::number(buffer_split[19].toDouble()/16384));




            }//else
            serialBuffer = "";
        }
    }    
}


/*****************************
 * Records data to a text file
 * **************************/

void SensorizedGlove::saveAcquiredData(double FSRForceValues[16], QStringList gloveSensorValues, double loadCellValue){
    //the acceleration and gyro resolution are based on the data-sheet of the MPU-6050 and the information found at
    // http://samselectronicsprojects.blogspot.ca/2014/07/processing-data-from-mpu-6050.html
    //it uses default setting in the I2Cdevlib class is +/- 2g for the accel and +/- 250 deg/sec for the gyro.
    double accelResol = 16384, gyroResol = 131, gravity = 9.80665;

    //saves timestamp
    acquiredDataFile << QDateTime::currentDateTime().toMSecsSinceEpoch() << ",";

    //saves forces
    for (int i = 0; i<16; i++){
        acquiredDataFile << FSRForceValues[i] << ",";
    }
    //saves load cell value if calibration is being assessed
    if (assessCalibra)
        acquiredDataFile << loadCellValue << ",";

    //saves accelerometer
    for (int i = 17; i<20; i++){
        acquiredDataFile << gloveSensorValues[i].toDouble()/accelResol*gravity << ",";
    }
    //saves temperature
    acquiredDataFile << gloveSensorValues[20].toDouble()*gravity << ",";
    //saves gyroscope
    for (int i = 21; i<24; i++){
        acquiredDataFile << gloveSensorValues[i].toDouble()/gyroResol*gravity << ",";
    }

    acquiredDataFile << "\n";
}


/*******************************
 * update the GUI glove elements
 * ****************************/

void SensorizedGlove::updateGloveColourMap(COLOUR GloveColours[16]){
    T1_polygon->setBrush(QColor(GloveColours[0].r, GloveColours[0].g, GloveColours[0].b));
    T2_polygon->setBrush(QColor(GloveColours[1].r, GloveColours[1].g, GloveColours[1].b));
    I1_polygon->setBrush(QColor(GloveColours[2].r, GloveColours[2].g, GloveColours[2].b));
    I2_polygon->setBrush(QColor(GloveColours[3].r, GloveColours[3].g, GloveColours[3].b));
    //I3_polygon->setBrush(QColor(GloveColours[3].r, GloveColours[3].g, GloveColours[3].b));
    M1_polygon->setBrush(QColor(GloveColours[4].r, GloveColours[4].g, GloveColours[4].b));
    //M2_polygon->setBrush(QColor(GloveColours[14].r, GloveColours[14].g, GloveColours[14].b));
    M3_polygon->setBrush(QColor(GloveColours[5].r, GloveColours[5].g, GloveColours[5].b));
    R1_polygon->setBrush(QColor(GloveColours[6].r, GloveColours[6].g, GloveColours[6].b));
    //R2_polygon->setBrush(QColor(GloveColours[15].r, GloveColours[15].g, GloveColours[15].b));
    R3_polygon->setBrush(QColor(GloveColours[7].r, GloveColours[7].g, GloveColours[7].b));
    S1_polygon->setBrush(QColor(GloveColours[8].r, GloveColours[8].g, GloveColours[8].b));
    S2_polygon->setBrush(QColor(GloveColours[9].r, GloveColours[9].g, GloveColours[9].b));
    S3_polygon->setBrush(QColor(GloveColours[10].r, GloveColours[10].g, GloveColours[10].b));
    P1_polygon->setBrush(QColor(GloveColours[11].r, GloveColours[11].g, GloveColours[11].b));
    P2_polygon->setBrush(QColor(GloveColours[12].r, GloveColours[12].g, GloveColours[12].b));
    P3_polygon->setBrush(QColor(GloveColours[13].r, GloveColours[13].g, GloveColours[13].b));
    P4_polygon->setBrush(QColor(GloveColours[14].r, GloveColours[14].g, GloveColours[14].b));
    P5_polygon->setBrush(QColor(GloveColours[15].r, GloveColours[15].g, GloveColours[15].b));
}


/*************************************
 * start recording data to a text file
*************************************/

void SensorizedGlove::on_StartRecordingB_clicked()
{
    ui->StartRecordingB->setDisabled(true);
    ui->StopRecordingB->setEnabled(true);

    QDir qdir;
    if(!qdir.exists(AcquiredDataPath))
        qdir.mkdir(AcquiredDataPath);
    AcquiredDataPath = AcquiredDataPath + QString("/");

    QString timeStamp = QDateTime::currentDateTime().toString("yyyy-MM-dd hh-mm-ss-zzz");

    if (ui->trialIDText->text() == "")
        AcquiredDataFileName = QString("New Trial " + timeStamp + ".csv");
    else
        AcquiredDataFileName = QString("Trial " + ui->trialIDText->text() + " " + timeStamp + ".csv");

    acquiredDataFile.open(QString(AcquiredDataPath + AcquiredDataFileName).toStdString());
    recordData = true;

    ui->logger->append("Saving data in text file...");
}


/*************************************
 * stop recording data to a text file
*************************************/

void SensorizedGlove::on_StopRecordingB_clicked()
{
    recordData = false;
    acquiredDataFile.close();
    ui->logger->append("Data saved in file <a href=\"file:" + QString(AcquiredDataPath + AcquiredDataFileName) + "\">" + AcquiredDataFileName + "</a>\n");
    ui->StopRecordingB->setDisabled(true);
    ui->StartRecordingB->setEnabled(true);
}


/********************************
 * Save calibration values to
 * the calibration text file
********************************/

void SensorizedGlove::saveCalibrationValues(){
    QFile calibrationFile ("setup/FSR_calibration_values.txt");
    if (calibrationFile.open(QFile::WriteOnly | QIODevice::Text)){
        QTextStream calOut(&calibrationFile);

        //print calibration values
        for (int i=0; i<16; i++)
        {
            calOut << QString::number(FSRCalibrationValues[i][0]);
            calOut << " ";
            calOut << QString::number(FSRCalibrationValues[i][1]) << endl;
        }
        ui->logger->append("Calibration Saved"); //updates log
    }
    else
        ui->logger->append("<font color='red'>Calibrations file did not open properly. Calibration values could not be saved.</font>");

    calibrationFile.close();
}


/************************************
 * Opens the links of the saved files
 * *********************************/


void SensorizedGlove::on_logger_anchorClicked(const QUrl &link)
{
    //qDebug()<<"opening " << link;
    QDesktopServices::openUrl(QUrl(link));
}


/**************************************
 * Executed if data is not received
 * ***********************************/

void SensorizedGlove::dataNotReceived(){
    //if data is being recorded
    recordData = false;
    if(acquiredDataFile.is_open()){
        acquiredDataFile.close();
        ui->StopRecordingB->setDisabled(true);
        ui->logger->append("closing file\n");
    }

    //if the FSRs are being calibrated
    if (calibrating)
        activeCalibrationPanel(false);

    //Notification to the user
    ui->l_arduino->setText("<font color='Red'>NOT CONNECTED");
    ui->l_accelerometer->setText("<font color='Red'>NOT WORKING");
    ui->logger->append("Data is not being received. Arduino may have disconnected, reconnecting...\n");     
    arduino->close();
    readyArduRead = false;

    //reconnecting
    connectArduino();

}


/**************************************
 * Activates Calibration Panel
 * ***********************************/

void SensorizedGlove::activeCalibrationPanel(bool state){
    ui->calibrationGB->setEnabled(state);
    if(state){
        calibrating = true;
        flashGloveColourMap->start(500);
        ui->logger->append("Calibration of the FSR pressure sensors started.");
    }
    else{
        calibrating = false;
        sensorToCalibrate = 0;
        saveCalibrationValues();
        ui->calibrationResultLabel->setText("");        
        std::stringstream msg;
        msg << "Current load: 0 N";
        ui->currentLoadLabel->setText(msg.str().c_str());
        ui->loadCellProgressBar->setValue(0);
        flashGloveColourMap->stop();
        ui->logger->append("Calibration stopped.");
    }    

}


/**************************************************
 * Assess the calibration by activating a variable
 * ***********************************************/

void SensorizedGlove::assessCalibration(bool state){
    if(state){
        loadCell->biasLoadCell();
        assessCalibra = true;
        ui->calibrationGB->setEnabled(true);

    }
    else
        assessCalibra = false;
}


/**********************************************************
 * Calculates the linear regression
 * Updates the calibration graph
 * updates the calibration value if a new value is captured
 * ********************************************************/

double SensorizedGlove::calculateLinearRegressionForCalibration(QVector<double> FSRvalues, QVector<double> loadCellvalues, double &slope, double &intersec){
    double x = 0;
    double y = 0;
    double xy = 0;
    double x2 = 0;
    double y2 = 0;
    double a = 0;
    double b = 0;
    double ss_tot = 0;
    double ss_res = 0;
    double r2 = 0;
    double numberOfSamples = FSRvalues.size();

    //calculating parameters to find linear regression equation
    for (int i=0; i<numberOfSamples; i++)
    {
//        x+= loadCellvalues[i];
//        y+= FSRvalues[i];
//        xy+= loadCellvalues[i] * FSRvalues[i];
//        x2+= loadCellvalues[i] * loadCellvalues[i];
//        y2+= FSRvalues[i] * FSRvalues[i];
        y+= loadCellvalues[i];
        x+= FSRvalues[i];
        xy+= loadCellvalues[i] * FSRvalues[i];
        y2+= loadCellvalues[i] * loadCellvalues[i];
        x2+= FSRvalues[i] * FSRvalues[i];
    }

    //calculating linear regression equation
    a= (y*x2 - x*xy)/(numberOfSamples*x2 - x*x);
    b= (numberOfSamples*xy - x*y)/(numberOfSamples*x2 - x*x);

    //calculating the coefficient of determination
    for (int i=0; i<numberOfSamples; i++)
    {
//        ss_tot+= pow(FSRvalues[i] - y/numberOfSamples,2);
//        ss_res+= pow(FSRvalues[i] - (a + b*loadCellvalues[i]),2);
        ss_tot+= pow(loadCellvalues[i] - y/numberOfSamples,2);
        ss_res+= pow(loadCellvalues[i] - (a + b*FSRvalues[i]),2);

    }
    r2 = 1 - ss_res / ss_tot;    

    //the calculated values are for a linear regression where the x axis is the load cell force
    //and the y axis is the FSR conductance.
    //In order to use this regression for our calibration, we need to inverse the linear equation to
    //related measured FSR conductance to calculated Newtons.

    slope = b;
    intersec = a;

    //these values are used if the user decides to accept the current calibration values
    setTempCalibrationValues (slope, intersec);

    return r2;
}


/***************************************
 * toggles the flashing of the glove
 * colour during calibration
 * ************************************/

void SensorizedGlove::changeFlashColour(){
    COLOUR FSRcolours[16] = {{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255},{0,0,255}};

    if(flashON){
        flashON = false;
        FSRcolours[sensorToCalibrate] = {255, 0, 0};
    }
    else{
        flashON = true;
    }

    //update color map of the glove
    updateGloveColourMap(FSRcolours);
}



/**************************************
 * Bias PPS Load Cell
 * ***********************************/

void SensorizedGlove::on_loadCellBiasButton_clicked()
{
    loadCell->biasLoadCell();
}


/**********************************************
 * Set temporary calibration values
 * *******************************************/

void SensorizedGlove::setTempCalibrationValues (double slope, double intersection){
    tempSlopeGlobal = slope;
    tempIntersecGlobal = intersection;

}


/**********************************************
 * accepts calibration values for a particular
 * sensor, after regression was calculated.
 *  Decision made by user
 * *******************************************/

void SensorizedGlove::on_acceptCalibButton_clicked()
{
    //saves new calibration parameters
    FSRCalibrationValues[sensorToCalibrate][0] = tempSlopeGlobal;
    FSRCalibrationValues[sensorToCalibrate][1] = tempIntersecGlobal;
    sensorToCalibrate++;

    //calibration completed when the sensorToCalibrate counter equals 16
    if (sensorToCalibrate == 16){
        emit ui->actionCalibrate->triggered(false);
        ui->actionCalibrate->setChecked(false);
        QMessageBox calibrationMsgBox;
        calibrationMsgBox.setText("Calibration of the FSR pressure sensors completed.");
        calibrationMsgBox.exec();
        ui->logger->append("Calibration completed.");
        saveCalibrationValues();
    }
}


/**********************************************
 * Skips the calibration of a particular
 * sensor.
 *  Decision made by user
 * *******************************************/

void SensorizedGlove::on_skipCalibButton_clicked()
{
    sensorToCalibrate++;

    //calibration completed when the sensorToCalibrate counter equals 16
    if (sensorToCalibrate == 16){
        emit ui->actionCalibrate->triggered(false);
        ui->actionCalibrate->setChecked(false);
        QMessageBox calibrationMsgBox;
        calibrationMsgBox.setText("Calibration of the FSR pressure sensors completed.");
        calibrationMsgBox.exec();
        ui->logger->append("Calibration completed.");
        saveCalibrationValues();
    }

}



/************************************************
 * Modifies the maximum calibration force
 * *********************************************/

void SensorizedGlove::on_maxCalibForceSpinBox_valueChanged(int maxCalForce)
{
    loadCellMaxForce = maxCalForce;
    std::stringstream msg;
    msg << loadCellMaxForce << " N";
    ui->maxForceLabel->setText(msg.str().c_str());
}



/**********************************************************************
   Return a RGB colour value given a scalar v in the range [vmin,vmax]
   In this case each colour component ranges from 0 (no contribution) to
   1 (fully saturated), modifications for other ranges is trivial.
   The colour is clipped at the end of the scales if v is outside
   the range [vmin,vmax]
   TAKEN FROM http://stackoverflow.com/questions/7706339/grayscale-to-red-green-blue-matlab-jet-color-scale
***********************************************************************/

SensorizedGlove::COLOUR SensorizedGlove::GetColour(double v, double vmin, double vmax)
{
   COLOUR c = {255, 255, 255}; // white
   double dv;

   if (v < vmin)
      v = vmin;
   if (v > vmax)
      v = vmax;
   dv = vmax - vmin;

   if (v < (vmin + 0.25 * dv)) {
      c.r = 0;
      c.g = 4 * (v - vmin) / dv;
   } else if (v < (vmin + 0.5 * dv)) {
      c.r = 0;
      c.b = 255 * (1 + 4 * (vmin + 0.25 * dv - v) / dv);
   } else if (v < (vmin + 0.75 * dv)) {
      c.r = 4 * (v - vmin - 0.5 * dv) / dv;
      c.b = 0;
   } else {
      c.g = 255 * (1 + 4 * (vmin + 0.75 * dv - v) / dv);
      c.b = 0;
   }

   return(c);
}


void SensorizedGlove::mousePressEvent(QMouseEvent *event){
    QPoint pos = event->pos();
    //this position is for the glove widget, it needs to be corrected in order to match the (0,0) of the main window with the (0,0) of the graphics scene
    qDebug() << pos.x() - 12 << ", " << pos.y() - 22;
}



