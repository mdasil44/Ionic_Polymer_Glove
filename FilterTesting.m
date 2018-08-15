close all;
clear all;

load 'TempCapVal';

array = xlsread('D:\Ionic_Polymer_Glove\sensorized-glove\Code\GUI\Matlab\TempCapVal.xlsx');
array = array(1800:2300);

figure('Name','Data','NumberTitle','off')
plot(array);

figure('Name','Envelope','NumberTitle','off')
[up,lo] = envelope(array,1,'peak');
hold on;
plot(array);
plot(up);
plot(lo);

mid = (up+lo);
mid = mid/2;

% mid = zeros(1,length(lo));
% for i = 1:length(lo)
%     mid(i) = (up(i)+lo(i))/2;
% end

plot(mid);
