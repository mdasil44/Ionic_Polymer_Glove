function calibSensor(obj,event,app)
    LBF_TO_N = 0.224808942443;  % (lbf / LBF_TO_N) = N

    data = zeros((app.BufferSize),1);
    dataPtr =  libpointer('singlePtr',data);
    times = zeros(app.BufferSize,1);
    timesPtr = libpointer('uint32Ptr',times);
    
    [app.nReady,times,data] = calllib('ppsRef','ppsGetData',...
        app.BufferSize,timesPtr,dataPtr);
    if app.nReady ~= app.BufferSize % First ppsGetData fills buffer with garbage
        for i = 1:app.nReady
            app.totalTime = [app.totalTime times(i)];
            if app.UnitDropDown.Value == 'lbf'
                app.totalData = [app.totalData data(i)];
            elseif app.UnitDropDown.Value == 'N'
                app.totalData = [app.totalData (data(i)/LBF_TO_N)];
            end
        end
        
        app.ReadingLamp.Enable = 'on';

        app.RefGauge.Value = app.totalData(end);
        switch app.SensorDropDown.Value
            case 'I1'
                app.calibCap = app.cap5AdjData;
            case 'I2'
                disp('I2 sensor not connected')
                app.I2.Enable = 'on';
            case 'I3'
                app.calibCap = app.cap6AdjData;
            case 'M1'
                app.calibCap = app.cap7AdjData;
            case 'M3'
                app.calibCap = app.cap8AdjData;
            case 'R1'
                app.calibCap = app.cap9AdjData;
            case 'R3'
                app.calibCap = app.cap11AdjData;
            case 'S1'
                app.calibCap = app.cap10AdjData;
            case 'S3'
                app.calibCap = app.cap12AdjData;
            case 'T1'
                app.calibCap = app.cap3AdjData;
            case 'T2'
                app.calibCap = app.cap4AdjData;
            case 'P1'
                disp('P1 sensor not connected')
                app.P1.Enable = 'on';
            case 'P2'
                app.calibCap = app.cap2AdjData;
            case 'P3'
                app.calibCap = app.cap14AdjData;
            case 'P4'
                app.calibCap = app.cap13AdjData;
            case 'P5'
                app.calibCap = app.cap15AdjData;
        end
        
        %detects when pressure is being applied on the load cell
        if app.totalData(end) > 0.01
            if app.firstGoodValueCalibrating
                app.tempBiasloadCellForCalibration = app.totalData(end);  %for bias during calibration
                app.tempBiasCapForCalibration = app.calibCap(end);
                app.firstGoodValueCalibrating = false;
            else
                app.TempCapVal = [app.TempCapVal (app.calibCap(end) - app.tempBiasCapForCalibration)];
                app.LoadCellTempForce = [app.LoadCellTempForce (app.totalData(end) - app.tempBiasloadCellForCalibration);


                if app.totalData(end) > app.maxForceEditField.Value
                    app.r2_deterCoeff = 0;
                    app.tempSlope = 1;
                    app.tempIntersec = 0;

                    %For debugging
                %    for int i = 0; i<FSRTempResistance.size(); i++)
                %    qDebug() << FSRTempResistance[i] << "   " << LoadCellTempForce[i];
                    %%%%%%%%%%%%%

                    app.r2_deterCoeff = calculateLinearRegressionForCalibration();%app.TempCapVal, app.LoadCellTempForce, app.tempSlope, app.tempIntersec
                    if abs(app.r2_deterCoeff) >= 0.97  % && abs(app.tempIntersec) <= 0.5
                        disp('Sensor calibrated properly. Proceed with the next sensor.');    % r2: " << r2_deterCoeff << " m: " << tempSlope << " b: " << tempIntersec;
                        ui->calibrationResultLabel->setText(QString::fromStdString(msg.str()));
                        %saves new calibration parameters
                        FSRCalibrationValues[sensorToCalibrate][0] = tempSlope;
                        FSRCalibrationValues[sensorToCalibrate][1] = 0;%tempIntersec;
                        sensorToCalibrate++;

                        %calibration completed when the sensorToCalibrate counter equals 16
                        if (sensorToCalibrate == 16)
                            emit ui->actionCalibrate->triggered(false);
                            ui->actionCalibrate->setChecked(false);
                            QMessageBox calibrationMsgBox;
                            calibrationMsgBox.setText("Calibration of the FSR pressure sensors completed.");
                            calibrationMsgBox.exec();
                            ui->logger->append("Calibration completed.");
                            saveCalibrationValues();
                        end
                    else
                        std::stringstream msg;
                        msg << "<font color='Red'>Sensor was NOT calibrated properly. Please repeat calibration.<br> r2: " << r2_deterCoeff << " m: " << tempSlope << " b: " << tempIntersec;
                        ui->calibrationResultLabel->setText(QString::fromStdString(msg.str()));
                    end
                    FSRTempResistance.clear();
                    LoadCellTempForce.clear();
                    ::Sleep(1500);
                end
            end
        else
            %to ensure calibration works in case it is restarted
            FSRTempResistance.clear();
            LoadCellTempForce.clear();
            tempBiasloadCellForCalibration=0;
            tempBiasFSRForCalibration=0;
            firstGoodValueCalibrating = true;
        end
    end
end