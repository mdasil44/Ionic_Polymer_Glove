function dataCallback(obj, event, app)
    time = toc;
    
    indicators = ['a' 'b' 'c' 'd' 'e' 'f' 'g' 'h' 'i' 'j' 'k' 'l' 'm' 'n' 'o' 'p' 'q' 'r' 's' 't' 'u' 'v' 'w'];
    indIndexes = zeros(size(indicators));
    
    % Read the whole packet of information 
    sData = fgetl(app.serialCOM);
    
    % search for the location of each data start indicator 
    for index = 1:23
        % look for the indicator
        location = find(sData == indicators(index), 1 );
        
        % if the indicator wasn't found, scrap everything
        if location
            indIndexes(index) = location;
        else
            return;
        end
    end
        
    % break up the data in the string to the appropriate variables
    app.timeData = [ app.timeData time ];
    app.cap1Data = [ app.cap1Data str2num(sData(indIndexes(2)+1 : indIndexes(3)-1)) ];
    app.cap2Data = [ app.cap2Data str2num(sData(indIndexes(3)+1 : indIndexes(4)-1)) ];
    app.cap3Data = [ app.cap3Data str2num(sData(indIndexes(4)+1 : indIndexes(5)-1)) ];
    app.cap4Data = [ app.cap4Data str2num(sData(indIndexes(5)+1 : indIndexes(6)-1)) ];
    app.cap5Data = [ app.cap5Data str2num(sData(indIndexes(6)+1 : indIndexes(7)-1)) ];
    app.cap6Data = [ app.cap6Data str2num(sData(indIndexes(7)+1 : indIndexes(8)-1)) ];
    app.cap7Data = [ app.cap7Data str2num(sData(indIndexes(8)+1 : indIndexes(9)-1)) ];
    app.cap8Data = [ app.cap8Data str2num(sData(indIndexes(9)+1 : indIndexes(10)-1)) ];
    app.cap9Data = [ app.cap9Data str2num(sData(indIndexes(10)+1 : indIndexes(11)-1)) ];
    app.cap10Data = [ app.cap10Data str2num(sData(indIndexes(11)+1 : indIndexes(12)-1)) ];
    app.cap11Data = [ app.cap11Data str2num(sData(indIndexes(12)+1 : indIndexes(13)-1)) ];
    app.cap12Data = [ app.cap12Data str2num(sData(indIndexes(13)+1 : indIndexes(14)-1)) ];
    app.cap13Data = [ app.cap13Data str2num(sData(indIndexes(14)+1 : indIndexes(15)-1)) ];
    app.cap14Data = [ app.cap14Data str2num(sData(indIndexes(15)+1 : indIndexes(16)-1)) ];
    app.cap15Data = [ app.cap15Data str2num(sData(indIndexes(16)+1 : indIndexes(17)-1)) ];
    app.cap16Data = [ app.cap16Data str2num(sData(indIndexes(17)+1 : indIndexes(18)-1)) ];
    app.accelXData = [ app.accelXData str2num(sData(indIndexes(18)+1 : indIndexes(19)-1)) ];
    app.accelYData = [ app.accelYData str2num(sData(indIndexes(19)+1 : indIndexes(20)-1)) ];
    app.accelZData = [ app.accelZData str2num(sData(indIndexes(20)+1 : indIndexes(21)-1)) ];
    app.gyroXData = [ app.gyroXData str2num(sData(indIndexes(21)+1 : indIndexes(22)-1)) ];
    app.gyroYData = [ app.gyroYData str2num(sData(indIndexes(22)+1 : indIndexes(23)-1)) ];
    app.gyroZData = [ app.gyroZData str2num(sData(indIndexes(23)+1 : end)) ];
    
    %Adjusted Data
    app.cap1AdjData = [ app.cap1AdjData AdjustCap((str2num(sData(indIndexes(2)+1 : indIndexes(3)-1)) - app.capRef(1)),app.CapCalibrationValues(1,:),app.CapRegrChoice(1)) ];
    app.cap2AdjData = [ app.cap2AdjData AdjustCap((str2num(sData(indIndexes(3)+1 : indIndexes(4)-1)) - app.capRef(2)),app.CapCalibrationValues(2,:),app.CapRegrChoice(2)) ];
    app.cap3AdjData = [ app.cap3AdjData AdjustCap((str2num(sData(indIndexes(4)+1 : indIndexes(5)-1)) - app.capRef(3)),app.CapCalibrationValues(3,:),app.CapRegrChoice(3)) ];
    app.cap4AdjData = [ app.cap4AdjData AdjustCap((str2num(sData(indIndexes(5)+1 : indIndexes(6)-1)) - app.capRef(4)),app.CapCalibrationValues(4,:),app.CapRegrChoice(4)) ];
    app.cap5AdjData = [ app.cap5AdjData AdjustCap((str2num(sData(indIndexes(6)+1 : indIndexes(7)-1)) - app.capRef(5)),app.CapCalibrationValues(5,:),app.CapRegrChoice(5)) ];
    app.cap6AdjData = [ app.cap6AdjData AdjustCap((str2num(sData(indIndexes(7)+1 : indIndexes(8)-1)) - app.capRef(6)),app.CapCalibrationValues(6,:),app.CapRegrChoice(6)) ];
    app.cap7AdjData = [ app.cap7AdjData AdjustCap((str2num(sData(indIndexes(8)+1 : indIndexes(9)-1)) - app.capRef(7)),app.CapCalibrationValues(7,:),app.CapRegrChoice(7)) ];
    app.cap8AdjData = [ app.cap8AdjData AdjustCap((str2num(sData(indIndexes(9)+1 : indIndexes(10)-1)) - app.capRef(8)),app.CapCalibrationValues(8,:),app.CapRegrChoice(8)) ];
    app.cap9AdjData = [ app.cap9AdjData AdjustCap((str2num(sData(indIndexes(10)+1 : indIndexes(11)-1)) - app.capRef(9)),app.CapCalibrationValues(9,:),app.CapRegrChoice(9)) ];
    app.cap10AdjData = [ app.cap10AdjData AdjustCap((str2num(sData(indIndexes(11)+1 : indIndexes(12)-1)) - app.capRef(10)),app.CapCalibrationValues(10,:),app.CapRegrChoice(10)) ];
    app.cap11AdjData = [ app.cap11AdjData AdjustCap((str2num(sData(indIndexes(12)+1 : indIndexes(13)-1)) - app.capRef(11)),app.CapCalibrationValues(11,:),app.CapRegrChoice(11)) ];
    app.cap12AdjData = [ app.cap12AdjData AdjustCap((str2num(sData(indIndexes(13)+1 : indIndexes(14)-1)) - app.capRef(12)),app.CapCalibrationValues(12,:),app.CapRegrChoice(12)) ];
    app.cap13AdjData = [ app.cap13AdjData AdjustCap((str2num(sData(indIndexes(14)+1 : indIndexes(15)-1)) - app.capRef(13)),app.CapCalibrationValues(13,:),app.CapRegrChoice(13)) ];
    app.cap14AdjData = [ app.cap14AdjData AdjustCap((str2num(sData(indIndexes(15)+1 : indIndexes(16)-1)) - app.capRef(14)),app.CapCalibrationValues(14,:),app.CapRegrChoice(14)) ];
    app.cap15AdjData = [ app.cap15AdjData AdjustCap((str2num(sData(indIndexes(16)+1 : indIndexes(17)-1)) - app.capRef(15)),app.CapCalibrationValues(15,:),app.CapRegrChoice(15)) ];
    app.cap16AdjData = [ app.cap16AdjData AdjustCap((str2num(sData(indIndexes(17)+1 : indIndexes(18)-1)) - app.capRef(16)),app.CapCalibrationValues(16,:),app.CapRegrChoice(16)) ];
    
%     if app.count4 == 0
%         app.x = 1000000;
%     end
%     
%     if app.capRef(2) ~= 0 && app.count4 == 0
%         app.x = length(app.cap2AdjData);
%         app.count4 = app.count4 + 1;
%     end
%     disp(length(app.cap2AdjData))
%     disp(app.x)
%     if length(app.cap2AdjData) > app.x && app.timeData(end) > 30 && app.timeData(end) < 30.1
%         xlswrite('PowerSpecCap.xlsx',app.cap2FiltData(app.x:end));
%         xlswrite('PowerSpecTime.xlsx',app.timeData(app.x:end));
%     end

    %Error checking because sometimes data doesn't get read
    if(std([size(app.timeData,2), size(app.cap1Data,2), size(app.cap2Data,2), size(app.cap3Data,2),...
             size(app.cap4Data,2), size(app.cap5Data,2), size(app.cap6Data,2), size(app.cap7Data,2),...
             size(app.cap8Data,2), size(app.cap9Data,2), size(app.cap10Data,2), size(app.cap11Data,2),...
             size(app.cap12Data,2), size(app.cap13Data,2), size(app.cap14Data,2), size(app.cap15Data,2),...
             size(app.cap16Data,2), size(app.accelXData,2), size(app.accelYData,2), size(app.accelZData,2),...
             size(app.gyroXData,2), size(app.gyroYData,2), size(app.gyroZData,2)])>0)
        
        disp("Data Correction");
       
        % if for some reason the lengths of the arrays dont match, trash
        % that data set
        
        minData = min([size(app.timeData,2), size(app.cap1Data,2), size(app.cap2Data,2), size(app.cap3Data,2),...
                       size(app.cap4Data,2), size(app.cap5Data,2), size(app.cap6Data,2), size(app.cap7Data,2),...
                       size(app.cap8Data,2), size(app.cap9Data,2), size(app.cap10Data,2), size(app.cap11Data,2),...
                       size(app.cap12Data,2), size(app.cap13Data,2), size(app.cap14Data,2), size(app.cap15Data,2),...
                       size(app.cap16Data,2), size(app.accelXData,2), size(app.accelYData,2), size(app.accelZData,2),...
                       size(app.gyroXData,2), size(app.gyroYData,2), size(app.gyroZData,2)]);
        
        app.timeData = app.timeData(1:minData);           
        app.cap1Data = app.cap1Data(1:minData);
        app.cap2Data = app.cap2Data(1:minData);
        app.cap3Data = app.cap3Data(1:minData);
        app.cap4Data = app.cap4Data(1:minData);
        app.cap5Data = app.cap5Data(1:minData);
        app.cap6Data = app.cap6Data(1:minData);
        app.cap7Data = app.cap7Data(1:minData);
        app.cap8Data = app.cap8Data(1:minData);
        app.cap9Data = app.cap9Data(1:minData);
        app.cap10Data = app.cap10Data(1:minData);
        app.cap11Data = app.cap11Data(1:minData);
        app.cap12Data = app.cap12Data(1:minData);
        app.cap13Data = app.cap13Data(1:minData);
        app.cap14Data = app.cap14Data(1:minData);
        app.cap15Data = app.cap15Data(1:minData);
        app.cap16Data = app.cap16Data(1:minData);
        app.cap1AdjData = app.cap1AdjData(1:minData);
        app.cap2AdjData = app.cap2AdjData(1:minData);
        app.cap3AdjData = app.cap3AdjData(1:minData);
        app.cap4AdjData = app.cap4AdjData(1:minData);
        app.cap5AdjData = app.cap5AdjData(1:minData);
        app.cap6AdjData = app.cap6AdjData(1:minData);
        app.cap7AdjData = app.cap7AdjData(1:minData);
        app.cap8AdjData = app.cap8AdjData(1:minData);
        app.cap9AdjData = app.cap9AdjData(1:minData);
        app.cap10AdjData = app.cap10AdjData(1:minData);
        app.cap11AdjData = app.cap11AdjData(1:minData);
        app.cap12AdjData = app.cap12AdjData(1:minData);
        app.cap13AdjData = app.cap13AdjData(1:minData);
        app.cap14AdjData = app.cap14AdjData(1:minData);
        app.cap15AdjData = app.cap15AdjData(1:minData);
        app.cap16AdjData = app.cap16AdjData(1:minData);
        app.accelXData = app.accelXData(1:minData);
        app.accelYData = app.accelYData(1:minData);
        app.accelZData = app.accelZData(1:minData);
        app.gyroXData = app.gyroXData(1:minData);
        app.gyroYData = app.gyroYData(1:minData);
        app.gyroZData = app.gyroZData(1:minData);

    end
    
    app.cap1FiltData = [nan, filter(app.B,app.A,app.cap1AdjData(2:end))];
    app.cap2FiltData = [nan, filter(app.B,app.A,app.cap2AdjData(2:end))];
    app.cap3FiltData = [nan, filter(app.B,app.A,app.cap3AdjData(2:end))];
    app.cap4FiltData = [nan, filter(app.B,app.A,app.cap4AdjData(2:end))];
    app.cap5FiltData = [nan, filter(app.B,app.A,app.cap5AdjData(2:end))];
    app.cap6FiltData = [nan, filter(app.B,app.A,app.cap6AdjData(2:end))];
    app.cap7FiltData = [nan, filter(app.B,app.A,app.cap7AdjData(2:end))];
    app.cap8FiltData = [nan, filter(app.B,app.A,app.cap8AdjData(2:end))];
    app.cap9FiltData = [nan, filter(app.B,app.A,app.cap9AdjData(2:end))];
    app.cap10FiltData = [nan, filter(app.B,app.A,app.cap10AdjData(2:end))];
    app.cap11FiltData = [nan, filter(app.B,app.A,app.cap11AdjData(2:end))];
    app.cap12FiltData = [nan, filter(app.B,app.A,app.cap12AdjData(2:end))];
    app.cap13FiltData = [nan, filter(app.B,app.A,app.cap13AdjData(2:end))];
    app.cap14FiltData = [nan, filter(app.B,app.A,app.cap14AdjData(2:end))];
    app.cap15FiltData = [nan, filter(app.B,app.A,app.cap15AdjData(2:end))];
    app.cap16FiltData = [nan, filter(app.B,app.A,app.cap16AdjData(2:end))];
end

function x = AdjustCap(tempCapVal,calibVal,regrChoice)
    if regrChoice == 1
        x = calibVal(1)*tempCapVal*tempCapVal + tempCapVal*calibVal(2);
    else
        x = tempCapVal*calibVal(1);
    end
end
