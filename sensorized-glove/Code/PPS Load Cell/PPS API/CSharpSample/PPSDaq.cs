using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace PPSDaq
{
   public class PPS
   {
      public const string LibraryFile = "PPSDaqAPI.dll";

      /// <summary>
      /// Initializes a connection to the PPS hardware using the provided configuration
      /// file and at the specified log level. NOTE: Logging can affect system stability
      /// and should not be used without first consulting PPS.
      /// </summary>
      /// <param name="configFile">Path to the main configuration file for your system (provided by PPS)</param>
      /// <param name="logLevel">Verbosity level for logs from 0 (disabled) to 5 (max. verbosity)</param>
      /// <returns>True on success</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsInitialize", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern bool Initialize(string configFile, int logLevel);

      /// <summary>
      /// Starts the PPS hardware collecting and streaming data.
      /// </summary>
      /// <returns>True on success</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsStart", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern bool Start();

      /// <summary>
      /// Halts acquisition from PPS hardware.
      /// </summary>
      /// <returns>True on success</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsStop", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern bool Stop();

      /// <summary>
      /// Retrieves the Record Size, which consists of one reading from each of the elements in all
      /// connected sensors.
      /// </summary>
      /// <returns>Size of a single frame of sensor data</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsGetRecordSize", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern int GetRecordSize();

      /// <summary>
      /// Retrieves the number of frames available in the buffer to be read.
      /// </summary>
      /// <returns>Number of available frames</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsFramesReady", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern int FramesReady();

      /// <summary>
      /// Retrieves at most the specified number of frames into the provided vectors
      /// for time and data. The system will at most return the maximum number of
      /// available frames. It is the calling program's responsibility to ensure
      /// that sufficient memory is already allocated for the maximum number of
      /// frames to be returned.
      /// </summary>
      /// <param name="nFrames">The maximum number of frames to read</param>
      /// <param name="times">Destination for the list of times, one per frame</param>
      /// <param name="data">Destination for the sensor data</param>
      /// <returns>The number of frames actually written to times and data</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsGetData", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern int GetData(int nFrames, int[] times, float[] data);

      /// <summary>
      /// When applicable, used to provide hardware-specific commands for certain systems
      /// in advanced application. Requires additional documentation from PPS for your
      /// specific configuration and is not needed for normal operation.
      /// </summary>
      /// <param name="command">Command string for low-level command</param>
      /// <returns>Result of low-level command (empty on failure)</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsDirectCommand", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      private static extern IntPtr directCommand(string command);
      public static string DirectCommand(string command)
      {
         return Marshal.PtrToStringUni(directCommand(command));
      }

      /// <summary>
      /// Retrieves the maximum raw signal value that may be returned by the attached hardware.
      /// </summary>
      /// <returns>Maximum raw signal value</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsGetMaxSignal", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern int GetMaxSignal();

      /// <summary>
      /// Resets the sensor zero output value based on the current sensor data.
      /// </summary>
      [DllImport(LibraryFile, EntryPoint = "ppsSetBaseline", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern void SetBaseline();

      /// <summary>
      /// For calibrated systems, resets the baseline to the default value found during calibration.
      /// For uncalibrated systems, clears the baseline by setting all values to zero.
      /// </summary>
      [DllImport(LibraryFile, EntryPoint = "ppsClearBaseline", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern void ClearBaseline();

      /// <summary>
      /// Use to see if the system is provided calibrated or raw data.
      /// </summary>
      /// <returns>True for calibrated data</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsIsCalibrated", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      public static extern bool IsCalibrated();

      /// <summary>
      /// Retrieves the most recent error message generated by the attached PPS system and clears
      /// the error flag.
      /// </summary>
      /// <returns>Error message</returns>
      [DllImport(LibraryFile, EntryPoint = "ppsGetLastError", CharSet = CharSet.Unicode, CallingConvention = CallingConvention.Cdecl)]
      private static extern IntPtr getLastError();
      public static string GetLastError()
      {
         return Marshal.PtrToStringUni(getLastError());
      }
   }
}
