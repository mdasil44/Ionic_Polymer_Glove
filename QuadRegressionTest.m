close all;
clear all;

TempCapVal = [0,1,2,3];
LoadCellTempForce = [1,3,6,14];
plot(TempCapVal,LoadCellTempForce,'*');
grid on;
hold on;

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
numberOfSamples = length(TempCapVal);
        
%calculating parameters to find linear regression equation
for i = 1:numberOfSamples
%    x+= loadCellvalues(i);
%    y+= FSRvalues(i);
%    xy+= loadCellvalues(i) * FSRvalues(i);
%    x2+= loadCellvalues(i) * loadCellvalues(i);
%    y2+= FSRvalues(i) * FSRvalues(i);
    y = y + LoadCellTempForce(i);
    x = x + TempCapVal(i);
    xy = xy + LoadCellTempForce(i)*TempCapVal(i);
    x2y = x2y + LoadCellTempForce(i)*TempCapVal(i)*TempCapVal(i);
    y2 = y2 + LoadCellTempForce(i)*LoadCellTempForce(i);
    x2 = x2 + TempCapVal(i)*TempCapVal(i);
    x3 = x3 + TempCapVal(i)*TempCapVal(i)*TempCapVal(i);
    x4 = x4 + TempCapVal(i)*TempCapVal(i)*TempCapVal(i)*TempCapVal(i);
end
    
%calculating linear regression equation
temp1 = [x4,x3,x2;x3,x2,x;x2,x,numberOfSamples];
temp2 = [x2y;xy;y];
temp3 = temp1\temp2;

a = temp3(1,1);
b = temp3(2,1);
c = temp3(3,1);
%calculating the coefficient of determination
for i = 1:numberOfSamples
%    ss_tot+= pow(FSRvalues(i) - y/numberOfSamples,2);
%    ss_res+= pow(FSRvalues(i) - (a + b*loadCellvalues(i)),2);
    ss_tot = ss_tot + power(LoadCellTempForce(i) - (y/numberOfSamples),2);
    ss_res = ss_res + power((LoadCellTempForce(i) - a*TempCapVal(i)*TempCapVal(i) - b*TempCapVal(i) - c),2);

end
r2_deterCoeff = 1 - (ss_res / ss_tot);
% disp(y)
% disp(x)
% disp(xy)
% disp(y2)
% disp(x2)
% disp(a)
% disp(b)
% disp(ss_tot)
% disp(ss_res)

%the calculated values are for a linear regression where the x axis is the load cell force
%and the y axis is the FSR conductance.
%In order to use this regression for our calibration, we need to inverse the linear equation to
%related measured FSR conductance to calculated Newtons.
tempQuadrC = c;
tempQuadrB = b;
tempQuadrA = a;

xout = 0:0.1:3;
yout = zeros(1,length(xout));
for i = 1:length(xout)
    yout(i) = a*xout(i)*xout(i) + b*xout(i) + c;
end

plot(xout,yout);

for i = 1:length(TempCapVal)
    TempCapVal(i) = a*TempCapVal(i)*TempCapVal(i)+b*TempCapVal(i)+c;
end
figure();
plot(TempCapVal,LoadCellTempForce);