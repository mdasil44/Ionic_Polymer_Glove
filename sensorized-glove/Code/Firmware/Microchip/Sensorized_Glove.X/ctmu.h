/**
  CTMU Header File

  @Company
    Microchip Technology Inc.

  @File Name
    ctmu.h

  @Summary
    This is the header file for the CTMU driver using PIC24 / dsPIC33 / PIC32MM MCUs

  @Description
    This header file provides APIs for driver for UART1.
    Generation Information :
        Product Revision  :  PIC24 / dsPIC33 / PIC32MM MCUs - pic24-dspic-pic32mm : v1.35
        Device            :  PIC24FV16KM202
    The generated drivers are tested against the following:
        Compiler          :  XC16 1.31
        MPLAB             :  MPLAB X 3.60
*/

#ifndef _CTMU_H
#define _CTMU_H

/**
  Section: Included Files
*/

#include <xc.h>
#include <stdbool.h>
#include <stdint.h>
#define CTMU_MODE_EDGE 0
#define RANGE_0_550uA 1 // .550uA

#ifdef __cplusplus  // Provide C++ Compatibility

    extern "C" {

#endif

/**
  Section: Data Types
*/

/**
  Section: Macro Declarations
*/

/**
  Section: CTMU APIs
*/

/**
  @Summary
 Configuration of the CMTU module

  @Description
    This routine initializes the CTMU driver.
    This routine must be called before any other CTMU routine is called.

  @Preconditions
    None

  @Param
    unsigned int mode   - Enable/Disable Time Generation mode
    unsigned int range  - current source range
    signed int trim     - Current trim

  @Returns
    None

  @Comment
    
*/
void CtmuCapTouchConfig(unsigned int mode, unsigned int range, signed int trim);

/**
  @Summary
 Preforms the capacitance measurement and returns the ADC value

  @Description
 Preforms the capacitance measurement and returns the ADC value

  @Preconditions
    CtmuCapTouchConfig() function should have been called
    before calling this function. 

  @Param
    None

  @Returns
    ADC value which is related to the capacitance.
*/
unsigned int CtmuReturnSample(void);

#ifdef __cplusplus  // Provide C++ Compatibility

    }

#endif

#endif  // _CTMU_H
/**
 End of File
*/