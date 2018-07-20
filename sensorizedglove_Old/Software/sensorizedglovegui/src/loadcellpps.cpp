#include "loadcellpps.h"
#include <Qdebug>
#include <ctime>
#include <vector>
#include <sstream>

#define LOG_MESSAGE(msg) if(log) log->message(msg)
#define LOG_ALERT(msg)   if(log) log->alert(msg)
#define LOG_ERROR(msg)   if(log) log->error(msg)
#define LOG_DEBUG(msg)   if(log) log->debug(msg)

using namespace std;

// Typedefs to save us typing later
typedef vector<output_t>      data_t;
typedef vector<time_stamp_t>  times_t;

LoadCellPPS::LoadCellPPS() : LogLevel(2), ConfigFileDef(L"Reference.cfg"),
    log(NULL), cellInitialized(false), BufferSize(500)
{

}


LoadCellPPS::~LoadCellPPS(){
    delete log;
}


/********************************
 * initialize the PPS load cell
 * Note: the PPS API is not detecting wheter the loadCell is connected,
 * initialized or started correctly. The only check we can do is at
 * the data acquisition phase.
 * The code below and it other functions does not have error checks
 * for the stages described above.
 * *****************************/

int LoadCellPPS::initialize(Logger *log){
    //PLEAE READ NOTES IN THE DESCRIPTION ABOVE
    this->log = log;
    wstring configFile = ConfigFileDef;
    ppsInitialize(configFile.c_str(), LogLevel);

    return 0;
}


/********************************
 * start the PPS load cell
 * *****************************/

int LoadCellPPS::start(){
    //The API only detects if data is not acquired properly
    //This step is the one used to catch any initialization errors
    ppsStart();


    for (int i = 0; i < 100 && 0 == ppsFramesReady(); ++i)
    {
        ::Sleep(500);
    }

    // If there's still no data, then something is wrong
    if (ppsFramesReady() <= 0)
    {
        ppsStop();
        LOG_ERROR("PPS load cell did not initialized properly. Please check that it is connected to the USB cable.");
    }

    ppsSetBaseline();
    cellInitialized = true;
    return 0;
}


/******************************
 * stop the PPS LoadCell
 * ***************************/

int LoadCellPPS::stop(){
    if(cellInitialized){
        if(!ppsStop())
            LOG_ALERT("PPS cell could not be stopped properly.");
        cellInitialized = false;
    }
    return 0;
}


/*********************************
 * query the loadcell
 * ******************************/

int LoadCellPPS::acquireData(double &cellForce){
    if (!cellInitialized)
    {
        LOG_ERROR("Load cell not initialized.");
        return 1;
    }
    ::Sleep(10);        //to ensure some data is acquired

    // Allocate storage for incoming data
    //the record size is 1
    int const FrameSize = ppsGetRecordSize();
    data_t cellForceValues(BufferSize * FrameSize);
    times_t times(BufferSize);

    int nFramesReady = ppsFramesReady();    
    if (nFramesReady <= 0)
    {
        LOG_ERROR("Load cell values cannot be acquired properly.");
        return 1;
    }

    if (nFramesReady > BufferSize)
        nFramesReady = BufferSize;

    // safely request more data if required. It returns the number of frames in the buffer
    nFramesReady = ppsGetData(nFramesReady, &times[0], &cellForceValues[0]);
    cellForce = cellForceValues[nFramesReady-1];

            //FOR DEBUGGING
        //    // Prints to screen the value of the load cell
        //    std::stringstream msg;
        //    msg << cellForceValues[nFramesReady-1];
        //    LOG_MESSAGE(msg.str().c_str());
        ////////////////

    return 0;
}


/*********************************
 * Bias the loadcell
 * ******************************/

void LoadCellPPS::biasLoadCell(){
    ppsSetBaseline();
}
