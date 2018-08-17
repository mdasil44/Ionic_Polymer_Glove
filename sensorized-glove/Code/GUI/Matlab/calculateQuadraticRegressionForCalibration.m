function calculateQuadraticRegressionForCalibration(obj, event, app)
    x = 0;
    y = 0;
    xy = 0;
    x2y = 0;
    x2 = 0;
    y2 = 0;
    x3 = 0;
    x4 = 0;
    a = 0;
    b = 0;
    c = 0;
    ss_tot = 0;
    ss_res = 0;
    numberOfSamples = length(app.TempCapVal)-1;
        
    %calculating parameters to find linear regression equation
    for i = 2:(numberOfSamples+1)
%        x+= loadCellvalues(i);
%        y+= FSRvalues(i);
%        xy+= loadCellvalues(i) * FSRvalues(i);
%        x2+= loadCellvalues(i) * loadCellvalues(i);
%        y2+= FSRvalues(i) * FSRvalues(i);
        y = y + app.LoadCellTempForce(i);
        x = x + app.TempCapVal(i);
        xy = xy + app.LoadCellTempForce(i)*app.TempCapVal(i);
        x2y = x2y + app.LoadCellTempForce(i)*app.TempCapVal(i)*app.TempCapVal(i);
        y2 = y2 + app.LoadCellTempForce(i)*app.LoadCellTempForce(i);
        x2 = x2 + app.TempCapVal(i)*app.TempCapVal(i);
        x3 = x3 + app.TempCapVal(i)*app.TempCapVal(i)*app.TempCapVal(i);
        x4 = x4 + app.TempCapVal(i)*app.TempCapVal(i)*app.TempCapVal(i)*app.TempCapVal(i);
    end
    
    %calculating linear regression equation
    temp1 = [x4,x3,x2;x3,x2,x;x2,x,numberOfSamples];
    temp2 = [x2y;xy;y];
    temp3 = temp1\temp2;
    
    a = temp3(1,1);
    b = temp3(2,1);
    c = temp3(3,1);

    %calculating the coefficient of determination
    for i = 2:(numberOfSamples+1)
%        ss_tot+= pow(FSRvalues(i) - y/numberOfSamples,2);
%        ss_res+= pow(FSRvalues(i) - (a + b*loadCellvalues(i)),2);
        ss_tot = ss_tot + power(app.LoadCellTempForce(i) - (y/numberOfSamples),2);
        ss_res = ss_res + power((app.LoadCellTempForce(i) - a*app.TempCapVal(i)*app.TempCapVal(i) - b*app.TempCapVal(i) - c),2);

    end
    app.r2_deterCoeff = 1 - (ss_res / ss_tot);
%     disp(y)
%     disp(x)
%     disp(xy)
%     disp(x2y)
%     disp(y2)
%     disp(x2)
%     disp(x3)
%     disp(x4)
%     disp(a)
%     disp(b)
%     disp(c)
%     disp(ss_tot)
%     disp(ss_res)

    %the calculated values are for a linear regression where the x axis is the load cell force
    %and the y axis is the FSR conductance.
    %In order to use this regression for our calibration, we need to inverse the linear equation to
    %related measured FSR conductance to calculated Newtons.

    app.tempQuadrC = c;
    app.tempQuadrB = b;
    app.tempQuadrA = a;
end