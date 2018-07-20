if libisloaded('PPSRef')
   unloadlibrary('PPSRef') 
end

[notfound,warnings] = loadlibrary('PPSDaqAPI.dll','PPSDaq.h','alias','PPSRef');

libfunctions('PPSRef')
str = 'Reference.cfg';
vp = libpointer('voidPtr',[int8(str) 0]);
logLvl = 2;
calllib('PPSRef','ppsInitialize',char(vp.Value),logLvl)