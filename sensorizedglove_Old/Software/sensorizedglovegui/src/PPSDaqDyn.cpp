//---------------------------------------------------------------------------
// PPSDaqDyn.cpp
//
// Dynamic Linking for PPS Data Acquisition API
// Copyright (c) 2009-2012 by Pressure Profile Systems, Inc.
// All Rights Reserved
//
// This Header/Source file combo demonstrates how to dynamically link
// to the PPS API DLL for cases where static linking is either 
// undesirable or overly difficult. (E.g. when compiling using Borland
// or compilers other than VisualStudio 2005.)
//
// To include a dynamically-linked version of the PPS API in your
// application, simple add this file to your project and compile with
// the constant PPS_DYNAMIC_LINK defined. See PPSSampleApp.cpp for 
// examples of how to call and use the PPS API functions.
//
//---------------------------------------------------------------------------
#include "PPSDaq.h"
//---------------------------------------------------------------------------
namespace
{
   // Count for number of instances of API on this DLL
   int nDll_(0);

   // Handle for DLL access
   HINSTANCE dll_(0);
}
//---------------------------------------------------------------------------
// Definition of function pointers declared in header file.
ppsInitialize_t         ppsInitialize(0);
ppsStart_t              ppsStart(0);
ppsStop_t               ppsStop(0);
ppsGetRecordSize_t      ppsGetRecordSize(0);
ppsFramesReady_t        ppsFramesReady(0);
ppsGetData_t            ppsGetData(0);
ppsDirectCommand_t      ppsDirectCommand(0);
ppsGetMaxSignal_t       ppsGetMaxSignal(0);
ppsSetBaseline_t        ppsSetBaseline(0);
ppsClearBaseline_t      ppsClearBaseline(0);
ppsIsCalibrated_t       ppsIsCalibrated(0);
ppsGetLastError_t       ppsGetLastError(0);
//---------------------------------------------------------------------------
// Macro to simplify loading DLL functions
#define LoadDllFunction(dll, type, dest) \
{ \
   dest = (type) ::GetProcAddress(dll, #dest); \
   if (0 == dest) \
      return false; \
}
//---------------------------------------------------------------------------
BOOL ppsConnectToApi(string_t filepath)
{
   // If we've already loaded, we're set and don't need to do it again
   if (0 != nDll_)
      return true;

   // Connect to DLL
   dll_ = ::LoadLibrary(filepath);
   if (0 == dll_)
      return false;

   // Connect to each API function in the DLL
   LoadDllFunction(dll_, ppsInitialize_t, ppsInitialize);
   LoadDllFunction(dll_, ppsStart_t, ppsStart);
   LoadDllFunction(dll_, ppsStop_t, ppsStop);
   LoadDllFunction(dll_, ppsGetRecordSize_t, ppsGetRecordSize);
   LoadDllFunction(dll_, ppsFramesReady_t, ppsFramesReady);
   LoadDllFunction(dll_, ppsGetData_t, ppsGetData);
   LoadDllFunction(dll_, ppsDirectCommand_t, ppsDirectCommand);
   LoadDllFunction(dll_, ppsGetMaxSignal_t, ppsGetMaxSignal);
   LoadDllFunction(dll_, ppsSetBaseline_t, ppsSetBaseline);
   LoadDllFunction(dll_, ppsClearBaseline_t, ppsClearBaseline);
   LoadDllFunction(dll_, ppsIsCalibrated_t, ppsIsCalibrated);
   LoadDllFunction(dll_, ppsGetLastError_t, ppsGetLastError);

   return true;
}
//---------------------------------------------------------------------------
void ppsDisconnectApi()
{
   // Make sure we don't delete if anything is still using this API
   if (0 == --nDll_)
   {
      // Make sure our handle is valid before releasing it
      if (0 != dll_)
      {
         ::FreeLibrary(dll_);

         // Reset all function pointers
         ppsInitialize = 0;
         ppsStart = 0;
         ppsStop = 0;
         ppsGetRecordSize = 0;
         ppsFramesReady = 0;
         ppsGetData = 0;
         ppsDirectCommand = 0;
         ppsGetMaxSignal = 0;
         ppsSetBaseline = 0;
         ppsClearBaseline = 0;
         ppsIsCalibrated = 0;
         ppsGetLastError = 0;
      }
   }
}
//---------------------------------------------------------------------------
