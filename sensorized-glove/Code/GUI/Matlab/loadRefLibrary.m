function loadRefLibrary(obj, event, app)
    if libisloaded('PPSRef')
        unloadlibrary('PPSRef') 
    end
    if ~libisloaded('PPSRef')
        [notfound,warnings] = loadlibrary('PPSDaqAPI.dll','PPSDaq.h','alias','PPSRef');
    end
    
    configStr = 'Reference.cfg';
    configPtr = libpointer('uint16Ptr',[int8(configStr) 0]);
    logLvl = 2;
    calllib('PPSRef','ppsInitialize',configPtr.Value,logLvl);

    calllib('PPSRef','ppsStart');
    calllib('PPSRef','ppsClearBaseline');
end