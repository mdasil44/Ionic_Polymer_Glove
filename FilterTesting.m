% close all;

% load 'TempCapVal';
% 
% array = xlsread('D:\Ionic_Polymer_Glove\sensorized-glove\Code\GUI\Matlab\TempCapVal.xlsx');
% array = array(1000:end);
% 
% figure('Name','Data','NumberTitle','off')
% plot(array);
% hold on;
% 
% order = 2;
% sampling_freq = 1000;
% cut_off_freq = 10;
% passband_peak_to_peak_db = 0.5;
% stopband_attenuation = 20;
% [B,A] = ellip(order, passband_peak_to_peak_db, stopband_attenuation, cut_off_freq/(0.5*sampling_freq), 'low');
% 
% y = filter(B,A,array);
% 
% plot(y);
% plot(smooth(y(:)));

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

cap = xlsread('D:\Ionic_Polymer_Glove\sensorized-glove\Code\GUI\Matlab\PowerSpecCap.xlsx');
cap = cap(15:end);
time = xlsread('D:\Ionic_Polymer_Glove\sensorized-glove\Code\GUI\Matlab\PowerSpecTime.xlsx');

% sum = 0;
% for i = 1:length(time)-1
%     sum = sum + time(i+1)-time(i);
% end
% sum = sum/(length(time)-1)

Fs = 18; % ~18Hz obtained from 1/(average time difference between samples) using the time array

% figure('Name','Data','NumberTitle','off')
% plot(time,cap);

N = length(cap);
xdft = fft(cap);
xdft = xdft(1:N/2+1);
psdx = (1/(Fs*N)) * abs(xdft).^2;
psdx(2:end-1) = 2*psdx(2:end-1);
freq = 0:Fs/length(cap):Fs/2;


plot(freq,10*log10(psdx))
grid on
title('Periodogram Using FFT')
xlabel('Frequency (Hz)')
ylabel('Power/Frequency (dB/Hz)')
hold on



