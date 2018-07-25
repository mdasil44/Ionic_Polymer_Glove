//---------------------------------------------------------------------------
//
// Pressure Profile Systems API Demo Application
//
// Copyright (c) 2009-2014 by Pressure Profile Systems, Inc.
//
//---------------------------------------------------------------------------
#include "PPSDaq.h"
//---------------------------------------------------------------------------
#include <iostream>
#include <sstream>
#include <fstream>
#include <vector>
//---------------------------------------------------------------------------
using namespace std;
//---------------------------------------------------------------------------
// Location of the PPS DLL
string_t LibraryPath       = L"PPSDaqAPI.dll";

// Standard configuration options that must always be specified
//string_t ConfigFileDef     = L"16x16_emulator.cfg";
string_t ConfigFileDef = L"FingerTPS+AuxRefSensor.cfg";
int const   LogLevel       = 2;                 // 0 to disable

// Settings specific to our demo application
string_t OutputFile        = L"pps_output.csv";
int const BufferSize       = 500;               // max frames we can read
int const ReadDuration     = 10000;             // in milliseconds
int const ReadInterval     = 500;               // in milliseconds
int const StartupTimeout   = 60000;             // max to wait for daq to start (ms)

// Some constants to make saving easier
string_t RowDelim          = L"\n";
string_t ColDelim          = L", ";

// Typedefs to save us typing later
typedef vector<output_t>      data_t;
typedef vector<time_stamp_t>  times_t;

//---------------------------------------------------------------------------
// exitProgram() lets us close the program without letting Windows
//    immediately close our command prompt window.
void exitProgram(int exitCode = 1);

// saveBufferToFile() saves the requested number of frames of
//    data to the given, already open, file.
bool saveToFile(wofstream& out, data_t const& data, times_t const& times,
                int nFrames, int frameSize, bool writeHeader);
//---------------------------------------------------------------------------
// This macro saves us quite a bit of typing to do if...else with every
//    function call.
#define CheckApiCall(desc, func) \
   wcout << desc << "..."; \
   if ( !func ) \
      exitProgram(); \
   else \
      wcout << "done." << endl; 
//---------------------------------------------------------------------------
// Main entry point for the demo application.
int main(int argc, wchar_t* argv[])
{
   wcout << "Pressure Profile Systems Dynamic-Static API Demo Application" << endl;
   wcout << "Copyright (c) 2009-2014 by Pressure Profile Systems, Inc." << endl;
   wcout << endl;

   // Print our current directory
   wchar_t pathBuf[_MAX_PATH];
   ::GetCurrentDirectory(_MAX_PATH, pathBuf);
   wcout << "Working directory is " << pathBuf << endl << endl;

#ifdef PPS_DYNAMIC_LINK
   CheckApiCall( "Connecting to API Library", ppsConnectToApi(LibraryPath) );
#endif

   // Read config file as argument if specified
   wstring configFile = (argc > 1) ? argv[1] : ConfigFileDef;

   // Setup PPS Hardware
   wcout << "Using configuration file " << configFile.c_str() << endl;
   CheckApiCall( "Initializing connection to API",
                 ppsInitialize(configFile.c_str(), LogLevel) );

   // Allocate storage for incoming data
   int const FrameSize = ppsGetRecordSize();
   data_t data(BufferSize * FrameSize);
   times_t times(BufferSize);
   wcout << "RecordSize is " << FrameSize << " elements." << endl;
   wcout << "Output values are" << ( ppsIsCalibrated() ? " " : " not " );
   wcout << "calibrated." << endl;

   // Create our output file and start data acquisition
   wofstream out( OutputFile );
   CheckApiCall( "Creating output file " << OutputFile, out.is_open() );
   CheckApiCall( "Starting acquisition", ppsStart() );

   // Wait for acquisition to actually get going
   // (ie. there's data in the buffer)
   wcout << "Waiting for data...";
   for (int i = 0, n = StartupTimeout / ReadInterval;
        i < n && 0 == ppsFramesReady();
        ++i)
   {
      ::Sleep(ReadInterval);
      wcout << ".";
   }

   // If there's still no data, then something is wrong
   if ( 0 == ppsFramesReady() )
   {
      CheckApiCall( endl << "Timeout waiting for data. Shutting down",
                    ppsStop() );
      exitProgram(1);
   }
   else if (ppsFramesReady() < 0)
   {
      exitProgram(1);
   }
   else
   {
      wcout << "done" << endl;
   }

   // Read chunks of data and output to our file
   for (int i = 0, n = ReadDuration / ReadInterval; i < n; ++i)
   {
      if ( (n / 2) == i )
      {
         // At the halfway point, set a new baseline just to show
         // how it works.
         wcout << "Resetting baseline with current values." << endl;
         ppsSetBaseline();
      }
      // Keep things running at a reasonable pace
      ::Sleep(ReadInterval);

      // This construct alternates between requesting the
      // number of frames actually available and the maximum
      // amount we can fit in our buffer.
      int nReady = ppsFramesReady();
      if (nReady < 0)
      {
         exitProgram(1);
      }

      int nRead = (i % 2) ? nReady : BufferSize;
      wcout << "Trying to read " << nRead << " frames of data; ";

      // This function only reads what is available, so we can
      // safely request more data if required.
      nRead = ppsGetData(nRead, &times[0], &data[0]);
      CheckApiCall( "Saving " << nRead << " frames to file",
                    saveToFile(out, data, times, nRead, FrameSize, 0 == i) );
   }

   // It's always best to stop acquisition when exiting or you can
   // run into problems when starting the next time.
   CheckApiCall( "Stopping acquisition", ppsStop() );

   // Close our file so we can open it before exiting this little demo
   out.close();

   exitProgram(0);
}
//---------------------------------------------------------------------------
bool saveToFile(wofstream& out, data_t const& data, times_t const& times,
                int nFrames, int frameSize, bool writeHeader)
{
   if ( !out.is_open() )
      return false;

   // On the first iteration, write headers to the file
   if (writeHeader)
   {
      for (int j = 0, m = frameSize; j < m; ++j)
      {
         // Before the sensor data we print the time
         if (0 == j)
            out << "Time [ms]" << ColDelim;

         out << "Elem" << j << ((m - 1) == j ? RowDelim : ColDelim);
      }
   }

   // Write the requested number of frames to the file
   for (int i = 0; i < nFrames; ++i)
   {
      // Write all of the data
      for (int j = 0, m = frameSize; j < m; ++j)
      {
         // Before the sensor data we print the time
         if (0 == j)
            out << times[i] << ColDelim;

         // Data is returned collated (i.e. one complete frame at a time)
         int index = j + (i * frameSize);
         out << data[index] << ((m - 1) == j ? RowDelim : ColDelim);
      }
   }
   return true;
}
//---------------------------------------------------------------------------
void exitProgram(int exitCode)
{
   if ( 0 != exitCode )
   {
      wcout << endl << "ERROR: Program shutting down." << endl;
      wcout << ppsGetLastError() << endl;
   }

   wcout << endl << "Press [return] to exit." << endl;
   cin.get();

#ifdef PPS_DYNAMIC_LINK
   ppsDisconnectApi();
#endif

   ppsStop();
   exit(exitCode);
}
//---------------------------------------------------------------------------
