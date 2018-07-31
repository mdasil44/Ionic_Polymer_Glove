function plotRefData(obj, event, app)
    LBF_TO_N = 0.224808942443;  % (lbf / lbf_to_N) = N
    
    if calllib('PPSRef','ppsFramesReady') <= 0
        return;
    end
    

    if app.UnitSwitch.Value == 'Pound-Force'
        app.RefSensorAxes.YLabel.String = 'Force (lbf)';
    end
    if app.UnitSwitch.Value == 'Newtons    '
        app.RefSensorAxes.YLabel.String = 'Force (N)';
    end
    
    [app.nReady,app.times,app.data] = calllib('PPSRef','ppsGetData',...
        app.BufferSize,app.timesPtr,app.dataPtr);
    if app.nReady ~= app.BufferSize % First ppsGetData fills buffer with garbage
        for i = 1:app.nReady
            app.totalTime = [app.totalTime app.times(i)];
            if app.UnitSwitch.Value == 'Pound-Force'
                app.totalData = [app.totalData app.data(i)];
            elseif app.UnitSwitch.Value == 'Newtons    '
                app.totalData = [app.totalData (app.data(i)/LBF_TO_N)];
            end
        end
        
        app.ReadingLamp.Enable = 'on';
        app.NotReadingLamp.Enable = 'off';
        
        app.plotData = plot(app.RefSensorAxes, app.totalTime, app.totalData);
        
        app.CurrentValueEditField.Value = app.totalData(end);
        
        
        minData = min(app.totalData);
        
        if minData < 0
            minData = minData*1.1;
        else
            minData = minData*0.9;
        end
        
        maxData = max(app.totalData);
        
        if maxData < 0
            maxData = maxData*0.9;
        else
            maxData = maxData*1.1;
        end
        
        if maxData == 0 && minData == 0
            app.RefSensorAxes.YLim = [0 1];
        else
            app.RefSensorAxes.YLim = [minData maxData];
        end
        
        
        if app.totalTime(end) < app.plotLength*1000
            app.RefSensorAxes.XLim = [0 app.plotLength*1000];
        else
            startTime = find(app.totalTime > max(app.totalTime)-(app.plotLength*1000), 1, 'first' );

            if isempty(startTime)
                startTime = 2000; 
            end

            app.RefSensorAxes.XLim = [app.totalTime(startTime) app.totalTime(end)];
        end
    end
end