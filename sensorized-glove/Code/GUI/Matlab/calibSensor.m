function calibSensor(obj,event,app)
    LBF_TO_N = 0.224808942443;  % (lbf / LBF_TO_N) = N
    
    enable = 'off';
    
    sensorToCalibrate = 0;
    
    if app.Calibrating
        switch app.SensorDropDown.Value
            case 'I1'
                app.calibCap = app.cap5FiltData;
                sensorToCalibrate = 5;
            case 'I2'
                disp('I2 sensor not connected')
                app.I2.Enable = 'on';
                app.Calibrating = false;
            case 'I3'
                app.calibCap = app.cap6FiltData;
                sensorToCalibrate = 6;
            case 'M1'
                app.calibCap = app.cap7FiltData;
                sensorToCalibrate = 7;
            case 'M3'
                app.calibCap = app.cap8FiltData;
                sensorToCalibrate = 8;
            case 'R1'
                app.calibCap = app.cap12FiltData;
                sensorToCalibrate = 12;
            case 'R3'
                app.calibCap = app.cap11FiltData;
                sensorToCalibrate = 11;
            case 'S1'
                app.calibCap = app.cap10FiltData;
                sensorToCalibrate = 10;
            case 'S3'
                app.calibCap = app.cap9FiltData;
                sensorToCalibrate = 9;
            case 'T1'
                app.calibCap = app.cap3FiltData;
                sensorToCalibrate = 3;
            case 'T2'
                app.calibCap = app.cap4FiltData;
                sensorToCalibrate = 4;
            case 'P1'
                disp('P1 sensor not connected')
                app.P1.Enable = 'on';
                app.Calibrating = false;
            case 'P2'
                app.calibCap = app.cap2FiltData;
                sensorToCalibrate = 2;
            case 'P3'
                app.calibCap = app.cap14FiltData;
                sensorToCalibrate = 14;
            case 'P4'
                app.calibCap = app.cap13FiltData;
                sensorToCalibrate = 13;
            case 'P5'
                app.calibCap = app.cap15FiltData;
                sensorToCalibrate = 15;
        end
    
        [app.nReady,app.times,app.data] = calllib('PPSRef','ppsGetData',...
            app.BufferSize,app.timesPtr,app.dataPtr);
    
       if app.nReady ~= app.BufferSize && app.nReady > 0 && app.Calibrating % First ppsGetData fills buffer with garbage
            for i = 1:app.nReady
                app.totalTime = [app.totalTime app.times(i)];
                if app.UnitDropDown.Value == 'lbf'
                    app.totalData = [app.totalData app.data(i)];
                elseif app.UnitDropDown.Value == 'N'
                    app.totalData = [app.totalData (app.data(i)/LBF_TO_N)];
                end
            end
        
            app.ReadyLamp.Enable = 'on';

            app.CurrentValueEditField_2.Value = app.totalData(end);
            app.RefGauge.Value = app.totalData(end);

            %detects when pressure is being applied on the load cell
%             temp = size(app.totalData);
            if app.totalData(end) > 0.01 %&& temp(2) > 10
                if app.firstGoodValueCalibrating
                    app.tempBiasloadCellForCalibration = app.totalData(end);  %for bias during calibration
                    app.tempBiasCapForCalibration = app.calibCap(end);
                    app.firstGoodValueCalibrating = false;
                else
                    app.TempCapVal = [app.TempCapVal (app.calibCap(end) - app.tempBiasCapForCalibration)];
                    app.LoadCellTempForce = [app.LoadCellTempForce (app.totalData(end) - app.tempBiasloadCellForCalibration)];
                    
                    if app.totalData(end) > app.MaxForceEditField.Value
                       app.r2_deterCoeff = 0;
                       app.tempSlope = 1;
                       app.tempIntersec = 0;
                    
                        %For debugging
                    %    for int i = 0; i<FSRTempResistance.size(); i++)
                    %    qDebug() << FSRTempResistance[i] << "   " << LoadCellTempForce[i];
                        %%%%%%%%%%%%%
                    
%                         figure('Name','A')
%                         plot(app.TempCapVal)
%                         hold on
%                         xlswrite('TempCapVal.xlsx',app.TempCapVal);
                    
%                         app.TempCapVal = smooth(app.TempCapVal(:));
                    
%                         plot(app.TempCapVal)
                    
                        figure('Name','Test')
                        plot(app.TempCapVal,app.LoadCellTempForce)
                        hold on;
                    
%                         figure('Name','B')
%                         plot(app.LoadCellTempForce)
%                         xlswrite('LoadCellTempForce.xlsx',app.LoadCellTempForce);

                        calculateLinearRegressionForCalibration(obj,event,app);%app.TempCapVal, app.LoadCellTempForce, app.tempSlope, app.tempIntersec
                        calculateQuadraticRegressionForCalibration(obj,event,app);
                        
%                         disp(app.r2_deterCoeff);
%                         disp(app.quadr2_deterCoeff);
                        
                        if app.r2_deterCoeff > app.quadr2_deterCoeff || app.r2_deterCoeff >= 0.92
                            app.r2EditField.Value = app.r2_deterCoeff;
                            app.SlopeEditField.Value = app.tempSlope;
                            app.CapRegrChoice(sensorToCalibrate) = 0;
                        else
                            app.r2EditField.Value = app.quadr2_deterCoeff;
                            app.SlopeEditField.Value = Inf;
                            app.CapRegrChoice(sensorToCalibrate) = 1;
                        end
                    
                        if abs(app.r2_deterCoeff) >= 0.92 || abs(app.quadr2_deterCoeff) >= 0.92  % && abs(app.tempIntersec) <= 0.5
                            app.ResultsTextArea.Value = 'Sensor calibrated properly. Proceed with the next sensor.';    % r2: " << r2_deterCoeff << " m: " << tempSlope << " b: " << tempIntersec;
                            enable = 'on';
                        
                            %saves new calibration parameters
                            if app.CapRegrChoice(sensorToCalibrate) == 0
                                if app.tempSlope > 0    %Capacitance values only decrease with applied pressure, so a positive slope means that the sensor was previously calibrated
                                    app.CapCalibrationValues(sensorToCalibrate, 1) = app.CapCalibrationValues(sensorToCalibrate, 1) * app.tempSlope;
                                    x = -1:0.2:(max(app.TempCapVal)+1);
                                else
                                    app.CapCalibrationValues(sensorToCalibrate, 1) = app.tempSlope;
                                    x = (min(app.TempCapVal)-1):0.2:0;
                                end
                                app.CapCalibrationValues(sensorToCalibrate, 2) = app.tempIntersec;
                                
                                disp(app.tempSlope);
                                disp(app.tempIntersec);
                                
                                y = zeros(length(x),1);
                                for i = 1:length(x)
                                    y(i) = app.tempSlope*x(i)+app.tempIntersec;
                                end
                                plot(x,y)
                            else
                                app.CapCalibrationValues(sensorToCalibrate, 1) = app.tempQuadrA;
                                app.CapCalibrationValues(sensorToCalibrate, 2) = app.tempQuadrB;
                                
                                disp(app.tempQuadrA);
                                disp(app.tempQuadrB);
                                disp(app.tempQuadrC);
                                
                                x = (min(app.TempCapVal)-1):0.2:0;
                                y = zeros(length(x),1);
                                for i = 1:length(x)
                                    y(i) = app.tempQuadrA*x(i)*x(i)+app.tempQuadrB*x(i)+app.tempQuadrC;
                                end
                                plot(x,y)
                                
                                temp = zeros(length(app.TempCapVal),1);
                                temp(1) = nan;
                                for i = 2:length(app.TempCapVal)
                                    temp(i) = app.tempQuadrA*app.TempCapVal(i)*app.TempCapVal(i)+app.tempQuadrB*app.TempCapVal(i)+app.tempQuadrC;
                                end
                                
                                figure()
                                plot(temp,app.LoadCellTempForce);
                            end
                        
                            app.Calibrating = false;
                        else
                            app.ResultsTextArea.Value = 'Sensor was NOT calibrated properly. Please repeat calibration.';   % r2: " << r2_deterCoeff << " m: " << tempSlope << " b: " << tempIntersec;
                            enable = 'off';
                            
                            app.CapRegrChoice(sensorToCalibrate) = 0;
                            app.Calibrating = false;
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
    
        switch app.SensorDropDown.Value
            case 'I1'
                app.I1.Enable = enable;
            case 'I3'
                app.I3.Enable = enable;
            case 'M1'
                app.M1.Enable = enable;
            case 'M3'
                app.M3.Enable = enable;
            case 'R1'
                app.R1.Enable = enable;
            case 'R3'
                app.R3.Enable = enable;
            case 'S1'
                app.S1.Enable = enable;
            case 'S3'
                app.S3.Enable = enable;
            case 'T1'
                app.T1.Enable = enable;
            case 'T2'
                app.T2.Enable = enable;
            case 'P2'
                app.P2.Enable = enable;
            case 'P3'
                app.P3.Enable = enable;
            case 'P4'
                app.P4.Enable = enable;
            case 'P5'
                app.P5.Enable = enable;
        end
    end
end