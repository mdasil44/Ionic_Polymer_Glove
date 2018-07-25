close all;
if libisloaded('PPSRef')
   unloadlibrary('PPSRef') 
end

if ~libisloaded('PPSRef')
    [notfound,warnings] = loadlibrary('PPSDaqAPI.dll','PPSDaq.h','alias','PPSRef');
end

libfunctions('PPSRef');
configStr = 'Reference.cfg';
configPtr = libpointer('uint16Ptr',[int8(configStr) 0]);
logLvl = 2;
calllib('PPSRef','ppsInitialize',configPtr.Value,logLvl);
FrameSize = 1;

BufferSize = 250;
ReadDuration = 5000;    %in ms
ReadInterval = 100;     %in ms
StartupTimeout = 60000; %in ms

data = zeros((BufferSize*FrameSize),1);
dataPtr =  libpointer('singlePtr',data);
times = zeros(BufferSize,1);
timesPtr = libpointer('uint32Ptr',times);

totalData = nan;
totalTime = nan;

calllib('PPSRef','ppsStart');

for i = 1:(StartupTimeout/ReadInterval)
    if calllib('PPSRef','ppsFramesReady') > 0
        break
    end
    pause(ReadInterval/1000)
end

disp('Reading Sensor...');
for i = 1:(ReadDuration/ReadInterval)
    if i == 1
        calllib('PPSRef','ppsSetBaseline');
    end
    pause(ReadInterval/1000)
    
    nReady = calllib('PPSRef','ppsFramesReady');
    [nRead,times,data] = calllib('PPSRef','ppsGetData',BufferSize,timesPtr,dataPtr); %Asking to get max data points, but will only recieve max number of frames available
    
    if i ~= 1   %first ppsGetData fills buffer with garbage values
        for j = 1:nRead
%             data(j)  %Debugging the live load cell value 
            totalTime = [totalTime times(j)];
            totalData = [totalData data(j)];
            %Figure out how Andrew initialized the Capacitance Data arrays and
            %then added values to them constantly
        end
    end
    plot(totalTime,totalData);
end

calllib('PPSRef','ppsStop');