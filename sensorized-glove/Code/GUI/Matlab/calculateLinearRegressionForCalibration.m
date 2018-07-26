function z = calculateLinearRegressionForCalibration(obj, event, app)
    x = 0;
    y = 0;
    xy = 0;
    x2 = 0;
    y2 = 0;
    a = 0;
    b = 0;
    ss_tot = 0;
    ss_res = 0;
    r2 = 0;
    numberOfSamples = size(app.TempCapVal);

    %calculating parameters to find linear regression equation
    for i = 1:numberOfSamples
%        x+= loadCellvalues(i);
%        y+= FSRvalues(i);
%        xy+= loadCellvalues(i) * FSRvalues(i);
%        x2+= loadCellvalues(i) * loadCellvalues(i);
%        y2+= FSRvalues(i) * FSRvalues(i);
        y = y + app.LoadCellTempForce(i);
        x = x + FSRvalues(i);
        xy = xy + loadCellvalues(i)*FSRvalues(i);
        y2 = y2 + loadCellvalues(i)*loadCellvalues(i);
        x2 = x2 + FSRvalues(i)*FSRvalues(i);
    end

    %calculating linear regression equation
    a = (y*x2 - x*xy)/(numberOfSamples*x2 - x*x);
    b = (numberOfSamples*xy - x*y)/(numberOfSamples*x2 - x*x);

    %calculating the coefficient of determination
    for i = 0:numberOfSamples
%        ss_tot+= pow(FSRvalues(i) - y/numberOfSamples,2);
%        ss_res+= pow(FSRvalues(i) - (a + b*loadCellvalues(i)),2);
        ss_tot = ss_tot + power(loadCellvalues(i) - y/numberOfSamples,2);
        ss_res = ss_res + power(loadCellvalues(i) - (a + b*FSRvalues(i)),2);

    end
    r2 = 1 - ss_res / ss_tot;    

    %the calculated values are for a linear regression where the x axis is the load cell force
    %and the y axis is the FSR conductance.
    %In order to use this regression for our calibration, we need to inverse the linear equation to
    %related measured FSR conductance to calculated Newtons.

    app.tempSlope = b;
    app.tempIntersec = a;

    %these values are used if the user decides to accept the current calibration values
    setTempCalibrationValues (app.tempSlope, app.tempIntersec);

    z = r2;
end