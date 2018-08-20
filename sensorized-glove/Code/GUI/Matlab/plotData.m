function plotData(obj, event, app)

    if isnan(app.timeData)
        return;
    end
    
    %Error checking because sometimes data doesn't get read
    if(std([size(app.timeData,2), size(app.cap1Data,2), size(app.cap2Data,2), size(app.cap3Data,2),...
             size(app.cap4Data,2), size(app.cap5Data,2), size(app.cap6Data,2), size(app.cap7Data,2),...
             size(app.cap8Data,2), size(app.cap9Data,2), size(app.cap10Data,2), size(app.cap11Data,2),...
             size(app.cap12Data,2), size(app.cap13Data,2), size(app.cap14Data,2), size(app.cap15Data,2),...
             size(app.cap16Data,2), size(app.accelXData,2), size(app.accelYData,2), size(app.accelZData,2),...
             size(app.gyroXData,2), size(app.gyroYData,2), size(app.gyroZData,2)])>0)
        
        disp("Error Continued");
        
    end

    % Check if there is enough data to fill the plot time
    if app.timeData(end) < app.plotLength
        
        % Capacitance data plotting 
        plot(app.CapacitanceAxes,   app.timeData, app.cap1Data, ...
                                    app.timeData, app.cap2Data, ...
                                    app.timeData, app.cap3Data, ...
                                    app.timeData, app.cap4Data, ...
                                    app.timeData, app.cap5Data, ...
                                    app.timeData, app.cap6Data, ...
                                    app.timeData, app.cap7Data, ...
                                    app.timeData, app.cap8Data, ...
                                    app.timeData, app.cap9Data, ...
                                    app.timeData, app.cap10Data, ...
                                    app.timeData, app.cap11Data, ...
                                    app.timeData, app.cap12Data, ...
                                    app.timeData, app.cap13Data, ...
                                    app.timeData, app.cap14Data, ...
                                    app.timeData, app.cap15Data, ...
                                    app.timeData, app.cap16Data);

        app.CapacitanceAxes.XLim = [0 app.plotLength];
        
        plottedData = [ app.cap1Data  app.cap2Data  app.cap3Data  app.cap4Data ...
                        app.cap5Data  app.cap6Data  app.cap7Data  app.cap8Data ...
                        app.cap9Data  app.cap10Data app.cap11Data app.cap12Data ...
                        app.cap13Data app.cap14Data app.cap15Data app.cap16Data ];

        app.CapacitanceAxes.YLim = [min(plottedData)*0.9 max(plottedData)*1.1];   
        
        % Gyroscope data plotting
        plot(app.GyroAxes,  app.timeData, app.gyroXData, ...
                            app.timeData, app.gyroYData, ...
                            app.timeData, app.gyroZData);

        app.GyroAxes.XLim = [0 app.plotLength];
        
        plottedData = [ app.gyroXData  app.gyroYData  app.gyroZData ];
        
        minData = min(plottedData);
        
        if minData < 0
            minData = minData*1.1;
        else
            minData = minData*0.9;
        end
        
        maxData = max(plottedData);
        
        if maxData < 0
            maxData = maxData*0.9;
        else
            maxData = maxData*1.1;
        end
        

        app.GyroAxes.YLim = [minData maxData]; 
        
        % Acceleration data plotting
        plot(app.AccelerationAxes,  app.timeData, app.accelXData, ...
                                    app.timeData, app.accelYData, ...
                                    app.timeData, app.accelZData);

        app.AccelerationAxes.XLim = [0 app.plotLength];
        
        plottedData = [ app.accelXData  app.accelYData  app.accelZData ];
        
        minData = min(plottedData);
        
        if minData < 0
            minData = minData*1.1;
        else
            minData = minData*0.9;
        end
        
        maxData = max(plottedData);
        
        if maxData < 0
            maxData = maxData*0.9;
        else
            maxData = maxData*1.1;
        end
        

        app.AccelerationAxes.YLim = [minData maxData]; 
    
    else
        % find the index that is the current time minus the plot length 
        startTime = find(app.timeData > max(app.timeData)-app.plotLength, 1, 'first' );
        
        % just in case there isn't data that far back
        if isempty(startTime)
           startTime = 2; 
        end
        
        % Capacitance data plotting 
            % Plot the resistance Axes
            
        plot(app.CapacitanceAxes,   app.timeData(startTime:end), app.cap8FiltData(startTime:end));%, ...
%                                     app.timeData(startTime:end), app.cap2FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap3FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap4FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap5FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap6FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap7FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap8FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap9FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap10FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap11FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap12FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap13FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap14FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap15FiltData(startTime:end), ...
%                                     app.timeData(startTime:end), app.cap16FiltData(startTime:end));

        app.CapacitanceAxes.XLim = [app.timeData(startTime) app.timeData(end)];
        
        plottedData = [ app.cap1FiltData(startTime:end)  app.cap2FiltData(startTime:end)  app.cap3FiltData(startTime:end)  app.cap4FiltData(startTime:end) ...
                        app.cap5FiltData(startTime:end)  app.cap6FiltData(startTime:end)  app.cap7FiltData(startTime:end)  app.cap8FiltData(startTime:end) ...
                        app.cap9FiltData(startTime:end)  app.cap10FiltData(startTime:end) app.cap11FiltData(startTime:end) app.cap12FiltData(startTime:end) ...
                        app.cap13FiltData(startTime:end) app.cap14FiltData(startTime:end) app.cap15FiltData(startTime:end) app.cap16FiltData(startTime:end) ];
        
        minData = min(plottedData);
        
        if minData < 0
            minData = minData*1.1;
        else
            minData = minData*0.9;
        end
        
        maxData = max(plottedData);
        
        if maxData < 0
            maxData = maxData*0.9;
        else
            maxData = maxData*1.1;
        end
        
        app.CapacitanceAxes.YLim = [minData maxData];
        
        % Gyroscope data plotting
        plot(app.GyroAxes,  app.timeData(startTime:end), app.gyroXData(startTime:end), ...
                            app.timeData(startTime:end), app.gyroYData(startTime:end), ...
                            app.timeData(startTime:end), app.gyroZData(startTime:end));

        app.GyroAxes.XLim = [app.timeData(startTime) app.timeData(end)];
        
        plottedData = [ app.gyroXData(startTime:end)  app.gyroYData(startTime:end)  app.gyroZData(startTime:end) ];
        
        minData = min(plottedData);
        
        if minData < 0
            minData = minData*1.1;
        else
            minData = minData*0.9;
        end
        
        maxData = max(plottedData);
        
        if maxData < 0
            maxData = maxData*0.9;
        else
            maxData = maxData*1.1;
        end
        

        app.GyroAxes.YLim = [minData maxData]; 
        
        
        % Acceleration data plotting
        plot(app.AccelerationAxes,  app.timeData(startTime:end), app.accelXData(startTime:end), ...
                                    app.timeData(startTime:end), app.accelYData(startTime:end), ...
                                    app.timeData(startTime:end), app.accelZData(startTime:end));

        app.AccelerationAxes.XLim = [app.timeData(startTime) app.timeData(end)];
        
        plottedData = [ app.accelXData(startTime:end)  app.accelYData(startTime:end)  app.accelZData(startTime:end) ];
        
        minData = min(plottedData);
        
        if minData < 0
            minData = minData*1.1;
        else
            minData = minData*0.9;
        end
        
        maxData = max(plottedData);
        
        if maxData < 0
            maxData = maxData*0.9;
        else
            maxData = maxData*1.1;
        end
        
        app.AccelerationAxes.YLim = [minData maxData]; 
        
    end
       
end