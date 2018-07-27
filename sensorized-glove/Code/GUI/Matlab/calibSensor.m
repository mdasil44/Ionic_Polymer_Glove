function calibSensor(obj,event,app)
    LBF_TO_N = 0.224808942443;  % (lbf / LBF_TO_N) = N
    
    Lamp = nan;
    
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
                Lamp = app.I1.Enable;
            case 'I2'
                disp('I2 sensor not connected')
                app.I2.Enable = 'on';
            case 'I3'
                app.calibCap = app.cap6AdjData;
                Lamp = app.I3.Enable;
            case 'M1'
                app.calibCap = app.cap7AdjData;
                Lamp = app.M1.Enable;
            case 'M3'
                app.calibCap = app.cap8AdjData;
                Lamp = app.M3.Enable;
            case 'R1'
                app.calibCap = app.cap9AdjData;
                Lamp = app.R1.Enable;
            case 'R3'
                app.calibCap = app.cap11AdjData;
                Lamp = app.R3.Enable;
            case 'S1'
                app.calibCap = app.cap10AdjData;
                Lamp = app.S1.Enable;
            case 'S3'
                app.calibCap = app.cap12AdjData;
                Lamp = app.S3.Enable;
            case 'T1'
                app.calibCap = app.cap3AdjData;
                Lamp = app.T1.Enable;
            case 'T2'
                app.calibCap = app.cap4AdjData;
                Lamp = app.T2.Enable;
            case 'P1'
                disp('P1 sensor not connected')
                app.P1.Enable = 'on';
            case 'P2'
                app.calibCap = app.cap2AdjData;
                Lamp = app.P2.Enable;
            case 'P3'
                app.calibCap = app.cap14AdjData;
                Lamp = app.P3.Enable;
            case 'P4'
                app.calibCap = app.cap13AdjData;
                Lamp = app.P4.Enable;
            case 'P5'
                app.calibCap = app.cap15AdjData;
                Lamp = app.P5.Enable;
        end
        
        %detects when pressure is being applied on the load cell
        if app.totalData(end) > 0.01
            if app.firstGoodValueCalibrating
                app.tempBiasloadCellForCalibration = app.totalData(end);  %for bias during calibration
                app.tempBiasCapForCalibration = app.calibCap(end);
                app.firstGoodValueCalibrating = false;
            else
                app.TempCapVal = [app.TempCapVal (app.calibCap(end) - app.tempBiasCapForCalibration)];
                app.LoadCellTempForce = [app.LoadCellTempForce (app.totalData(end) - app.tempBiasloadCellForCalibration)];


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
                        Lamp = 'on';
                        
                        %saves new calibration parameters
                        app.CapCalibrationValues(sensorToCalibrate, 1) = tempSlope;
                        app.CapCalibrationValues(sensorToCalibrate, 2) = 0;%tempIntersec;
                    else
                        disp('Sensor was NOT calibrated properly. Please repeat calibration.'   % r2: " << r2_deterCoeff << " m: " << tempSlope << " b: " << tempIntersec;
                        Lamp = 'off';
                    end
                    app.TempCapVal = nan;
                    app.LoadCellTempForce = nan;
                end
            end
        else
            %to ensure calibration works in case it is restarted
            app.TempCapVal = nan;
            app.LoadCellTempForce = nan;
            app.tempBiasloadCellForCalibration = 0;
            app.tempBiasCapForCalibration = 0;
            app.firstGoodValueCalibrating = true;
        end
    end
end