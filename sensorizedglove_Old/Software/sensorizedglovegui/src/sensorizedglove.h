#ifndef SENSORIZEDGLOVE_H
#define SENSORIZEDGLOVE_H

#include <QMainWindow>
#include <QSerialPort>
#include <QByteArray>
#include <QTimer>
#include <QGraphicsScene>
#include <QGraphicsView>
#include <QGraphicsItem>
#include <QDir>
#include <QDateTime>
#include <fstream>
#include <QMouseEvent>
#include <QVector>
#include "loadcellpps.h"



namespace Ui {
class SensorizedGlove;
}

class SensorizedGlove : public QMainWindow
{
    Q_OBJECT

public:
    explicit SensorizedGlove(QWidget *parent = 0);
    ~SensorizedGlove();

typedef struct {
    double r,g,b;
} COLOUR;

private slots:
    void readSerial();    
    void updateGloveColourMap(COLOUR []);
    void saveAcquiredData (double [], QStringList, double );
    void dataNotReceived();
    void changeFlashColour();
    void saveCalibrationValues();
    void setTempCalibrationValues (double slope, double intersection);

    //GUI slots
    void on_StartRecordingB_clicked();
    void on_StopRecordingB_clicked();
    void on_logger_anchorClicked(const QUrl &arg1);
    void activeCalibrationPanel(bool);
    void assessCalibration(bool);
    void on_loadCellBiasButton_clicked();
    void on_maxCalibForceSpinBox_valueChanged(int maxCalForce);

    void on_acceptCalibButton_clicked();

    void on_skipCalibButton_clicked();

private:
    bool connectArduino();
    void renderSceneElements();
    COLOUR GetColour(double v, double vmin, double vmax);
    void loadFSRCalibration();    
    double calculateLinearRegressionForCalibration(QVector<double> FSRvalues, QVector<double> loadCellvalues, double &slope, double &intersec);

    Ui::SensorizedGlove *ui;
    QSerialPort *arduino;
    static const quint16 arduino_mega_vendor_id = 9025;
    static const quint16 arduino_mega_product_id = 66;
    QByteArray serialData;
    QString serialBuffer;
    bool recordData;
    bool readyArduRead;
    bool calibrating;
    bool assessCalibra;    
    bool flashON;
    bool firstGoodValueCalibrating;
    double loadCellForce;
    double FSRCalibrationValues[16][2];   // m and b values for each FRS    
    int sensorToCalibrate;
    int loadCellMaxForce;
    double tempBiasloadCellForCalibration;
    double tempBiasFSRForCalibration;
    double tempSlopeGlobal;
    double tempIntersecGlobal;
    QVector<double> FSRTempResistance;
    QVector<double> LoadCellTempForce;





    QGraphicsScene *scene;
    QGraphicsPolygonItem *T1_polygon;
    QGraphicsPolygonItem *T2_polygon;
    QGraphicsPolygonItem *P1_polygon;
    QGraphicsPolygonItem *P2_polygon;
    QGraphicsPolygonItem *P3_polygon;
    QGraphicsPolygonItem *P4_polygon;
    QGraphicsPolygonItem *P5_polygon;
    QGraphicsPolygonItem *I1_polygon;
    QGraphicsPolygonItem *I2_polygon;
    QGraphicsPolygonItem *I3_polygon;
    QGraphicsPolygonItem *M1_polygon;
    QGraphicsPolygonItem *M2_polygon;
    QGraphicsPolygonItem *M3_polygon;
    QGraphicsPolygonItem *R1_polygon;
    QGraphicsPolygonItem *R2_polygon;
    QGraphicsPolygonItem *R3_polygon;
    QGraphicsPolygonItem *S1_polygon;
    QGraphicsPolygonItem *S2_polygon;
    QGraphicsPolygonItem *S3_polygon;

    //for testing connectivity with Arduino
    QTimer *timerDataReceived;
    QTimer *flashGloveColourMap;

    //for files functionality
    std::ofstream acquiredDataFile;
    QString  AcquiredDataPath;
    QString AcquiredDataFileName;



    LoadCellPPS *loadCell;
    void mousePressEvent(QMouseEvent *event);
};

#endif // SENSORIZEDGLOVE_H
