//---------------------------------------------------------------------------
//
// Pressure Profile Systems API Demo C# Application
//
// Copyright (c) 2012 by Pressure Profile Systems, Inc.
//
//---------------------------------------------------------------------------
using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using PPSDaq;

namespace SampleAppCSharp
{
   public class Program
   {
      // Some constants to make saving easier
      private const string RowDelim       = "\n";
      private const string ColDelim       = ",";

      public static void Main(string[] args)
      {
         // Standard configuration options that must always be specified
          const string ConfigFileDef = "FingerTPS+AuxRefSensor.cfg";
         const int LogLevel          = 2;                 // 0 to disable

         // Settings specific to our demo application
         const string OutputFile     = "pps_output.csv";
         const int BufferSize        = 500;               // max frames we can read
         const int ReadDuration      = 10000;             // in milliseconds
         const int ReadInterval      = 500;               // in milliseconds
         const int StartupTimeout    = 60000;             // max to wait for daq to start (ms)

         Console.WriteLine("Pressure Profile Systems CSharp API Demo Application");
         Console.WriteLine("Copyright (c) 2012-2014 by Pressure Profile Systems, Inc.");
         Console.WriteLine();

         // Print our current directory
         Console.WriteLine("Working directory is " + Environment.CurrentDirectory);
         Console.WriteLine();

         // Read config file as argument if specified
         string configFile = (args.Length > 1) ? args[1] : ConfigFileDef;

         // Setup PPS Hardware
         Console.WriteLine("Using configuration file " + configFile);
         Console.Write("Initializing connection to API...");
         if (!PPS.Initialize(configFile, LogLevel))
         {
            exitProgram(1);
         }
         Console.WriteLine("done.");
               
         // Allocate storage for incoming data
         int frameSize = PPS.GetRecordSize();
         float[] data = new float[BufferSize * frameSize];
         int[] times = new int[BufferSize];
         Console.WriteLine("RecordSize is " + frameSize + " elements.");
         Console.WriteLine("Output values are" + ( PPS.IsCalibrated() ? " " : " not " ) + "calibrated.");

         // Create our output file and start data acquisition
         Console.WriteLine("Creating output file " + OutputFile);
         try
         {
            using (StreamWriter sw = new StreamWriter(OutputFile))
            {
               Console.Write("Starting acquisition...");
               if (!PPS.Start())
               {
                  exitProgram(1);
               }
               Console.WriteLine("done.");

               // Wait for acquisition to actually get going
               // (ie. there's data in the buffer)
               Console.Write("Waiting for data...");
                    int z = PPS.FramesReady();
               for (int i = 0, n = StartupTimeout / ReadInterval; i < n && 0 >= z; ++i)
               {
                        z = PPS.FramesReady();
                        PPS.FramesReady();
                  System.Threading.Thread.Sleep(ReadInterval);
                  Console.Write(".");
               }

               // If there's still no data, then something is wrong
               if (0 == PPS.FramesReady())
               {
                  Console.Write("Timeout waiting for data. Shutting down...");
                  PPS.Stop();
                  exitProgram(1);
               }
               else if (PPS.FramesReady() < 0)
               {
                  exitProgram(1);
               }
               else
               {
                  Console.WriteLine("done.");
               }

               // Read chunks of data and output to our file
               for (int i = 0, n = ReadDuration / ReadInterval; i < n; ++i)
               {
                  if ((n / 2) == i)
                  {
                     // At the halfway point, set a new baseline just to show
                     // how it works.
                     Console.WriteLine("Resetting baseline with current values.");
                     PPS.SetBaseline();
                  }
                  // Keep things running at a reasonable pace
                  System.Threading.Thread.Sleep(ReadInterval);

                  // This construct alternates between requesting the
                  // number of frames actually available and the maximum
                  // amount we can fit in our buffer.
                  int nReady = PPS.FramesReady();
                  if (nReady < 0)
                  {
                     exitProgram(1);
                  }

                  int nRead = (i % 2 != 0) ? nReady : BufferSize;
                  Console.Write("Trying to read " + nRead + " frames of data; ");

                  // This function only reads what is available, so we can
                  // safely request more data if required.
                  nRead = PPS.GetData(nRead, times, data);
                  Console.Write("Saving " + nRead + " frames to file...");
                  saveToFile(sw, data, times, nRead, frameSize, 0 == i);
                  Console.WriteLine("done.");
               }
            }
         }
         catch (Exception ex)
         {
            Console.WriteLine(ex.Message);
         }
         finally
         {
            // It's always best to stop acquisition when exiting or you can
            // run into problems when starting the next time.
            Console.Write("Stopping acquisition...");
            if (!PPS.Stop())
            {
               exitProgram(1);
            }

            exitProgram(0);
         }
      }

      private static void saveToFile(StreamWriter sw, float[] data, int[] times, int nFrames, int frameSize, bool writeHeader)
      {
         // On the first iteration, write headers to the file
         if (writeHeader)
         {
            for (int j = 0, m = frameSize; j < m; ++j)
            {
               // Before the sensor data we print the time
               if (0 == j)
               {
                  sw.Write("Time [ms]" + ColDelim);
               }

               sw.Write("Elem" + j + ((m - 1) == j ? RowDelim : ColDelim));
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
               {
                  sw.Write(times[i] + ColDelim);
               }

               // Data is returned collated (i.e. one complete frame at a time)
               int index = j + (i * frameSize);
               sw.Write(data[index] + ((m - 1) == j ? RowDelim : ColDelim));
            }
         }
      }

      private static void exitProgram(int exitCode)
      {
         if (0 != exitCode)
         {
            Console.WriteLine();
            Console.WriteLine("ERROR: Program shutting down.");
            Console.WriteLine(PPS.GetLastError());
         }

         Console.WriteLine();
         Console.WriteLine("Press [return] to exit.");
         Console.ReadKey();

         PPS.Stop();

         Environment.Exit(exitCode);
      }

   }
}
