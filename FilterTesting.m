close all;

load 'TempCapVal';

array = xlsread('D:\Ionic_Polymer_Glove\sensorized-glove\Code\GUI\Matlab\TempCapVal.xlsx');
array = array(1000:end);

figure('Name','Data','NumberTitle','off')
plot(array);
hold on;

order = 2;
sampling_freq = 1000;
cut_off_freq = 10;
passband_peak_to_peak_db = 0.5;
stopband_attenuation = 20;
[B,A] = ellip(order, passband_peak_to_peak_db, stopband_attenuation, cut_off_freq/(0.5*sampling_freq), 'low');

y = filter(B,A,array);

plot(y);

% figure('Name','Envelope','NumberTitle','off')
% [up,lo] = envelope(array,1,'peak');
% hold on;
% plot(array);
% plot(up);
% plot(lo);
% 
% mid = (up+lo);
% mid = mid/2;
% 
% mid = zeros(1,length(lo));
% for i = 1:length(lo)
%     mid(i) = (up(i)+lo(i))/2;
% end
% 
% plot(mid);
