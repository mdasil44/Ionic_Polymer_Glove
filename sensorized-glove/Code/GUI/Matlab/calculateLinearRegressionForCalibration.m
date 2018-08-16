function calculateLinearRegressionForCalibration(obj, event, app)
    x = 0;
    y = 0;
    xy = 0;
    x2 = 0;
    y2 = 0;
    a = 0;
    b = 0;
    ss_tot = 0;
    ss_res = 0;
    numberOfSamples = length(app.TempCapVal);
        
    %calculating parameters to find linear regression equation
    for i = 2:numberOfSamples
%        x+= loadCellvalues(i);
%        y+= FSRvalues(i);
%        xy+= loadCellvalues(i) * FSRvalues(i);
%        x2+= loadCellvalues(i) * loadCellvalues(i);
%        y2+= FSRvalues(i) * FSRvalues(i);
        y = y + app.LoadCellTempForce(i);
        x = x + app.TempCapVal(i);
        xy = xy + app.LoadCellTempForce(i)*app.TempCapVal(i);
        y2 = y2 + app.LoadCellTempForce(i)*app.LoadCellTempForce(i);
        x2 = x2 + app.TempCapVal(i)*app.TempCapVal(i);
    end
    
    %calculating linear regression equation
    a = (y*x2 - x*xy)/(numberOfSamples*x2 - x*x);
    b = (numberOfSamples*xy - x*y)/(numberOfSamples*x2 - x*x);

    %calculating the coefficient of determination
    for i = 2:numberOfSamples
%        ss_tot+= pow(FSRvalues(i) - y/numberOfSamples,2);
%        ss_res+= pow(FSRvalues(i) - (a + b*loadCellvalues(i)),2);
        ss_tot = ss_tot + power(app.LoadCellTempForce(i) - y/numberOfSamples,2);
        ss_res = ss_res + power(app.LoadCellTempForce(i) - (a + b*app.TempCapVal(i)),2);

    end
    app.r2_deterCoeff = 1 - ss_res / ss_tot;
%     disp(y)
%     disp(x)
%     disp(xy)
%     disp(y2)
%     disp(x2)
%     disp(a)
%     disp(b)
%     disp(ss_tot)
%     disp(ss_res)

    %the calculated values are for a linear regression where the x axis is the load cell force
    %and the y axis is the FSR conductance.
    %In order to use this regression for our calibration, we need to inverse the linear equation to
    %related measured FSR conductance to calculated Newtons.

    app.tempSlope = b;
    app.tempIntersec = a;
end