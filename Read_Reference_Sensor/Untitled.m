close all;

system('F:\Read_Reference_Sensor\TestPPS_API\bin\x64\TestPPS_API.exe');

referenceData = csvread('pps_output.csv',1,0)
% plot(referenceData(:,1),referenceData(:,2));