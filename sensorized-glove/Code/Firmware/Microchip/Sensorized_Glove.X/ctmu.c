/**
  CTMU Driver File

  @Company
    Microchip Technology Inc.

  @File Name
    ctmu.c

  @Summary
    This is the driver implementation file for the CTMU driver using PIC24 / dsPIC33 / PIC32MM MCUs

  @Description
    This header file provides implementations for driver APIs for CTMU.
    Generation Information :
        Product Revision  :  PIC24 / dsPIC33 / PIC32MM MCUs - pic24-dspic-pic32mm : v1.35
        Device            :  PIC24FV16KM202
    The generated drivers are tested against the following:
        Compiler          :  XC16 1.31
        MPLAB             :  MPLAB X 3.60
*/

/**
  Section: Included Files
*/
#include "ctmu.h"
#include "mcc_generated_files/adc1.h"

/**
  Section: CTMU APIs
*/
void CtmuCapTouchConfig(unsigned int mode, unsigned int range, signed int trim)
{
    // step 1 Configure the CTMU
    CTMUCON1L = 0x0000; // Disable CTMU
    CTMUCON1Lbits.TGEN = mode; // Enable/Disable Time Generation mode
    CTMUCON1Lbits.EDGEN = 0; // Edges are disabled
    CTMUCON1Lbits.ITRIM = trim; // Set trim
    CTMUCON1Lbits.CTTRIG = 0; // Trigger output disabled
    CTMUCON1Lbits.IRNG = (range & 3); // Set range

    CTMUCON1H = 0; // Edges are disabled, edge controls ignored
    // This line does not apply to all devices
    //CTMUCON1Hbits.IRNGH = (range>>2); // set high bit of range

    CTMUCON2Lbits.IRSTEN = 0; // Current source reset disabled
    CTMUCON2Lbits.DISCHS = 0; // Discharge source disabled
    // Step 2 Configure the port Ports
    //TRISB = TRISB | (1<<2); // Set channel 2
    //ANSBbits.ANSB2 = 1; // Make AN2 as analog

    // Step 3 configure the ADC
    ADC1_ChannelSelect( ADC1_SENSOR_1 ); // Select the analog channel(2)

    //AD1CON1 = 0x8000; // Turn On A/D Converter,
    // Unsigned fractional format, Clear SAMP bit to
    // start conversion, Sample when SAMP bit is set
    //AD1CON2 = 0x0000; // VR+ = AVDD, V- = AVSS, Don't scan,
    //AD1CON3 = 0x0000; // ADC uses system clock
    //AD1CON3bits.ADCS = 0; // conversion clock = 1xTcy
    //AD1CON5 = 0x0000; // Auto-Scan disabled
    
}

unsigned int CtmuReturnSample()
{
    unsigned int result, x;

    // Step 4 - 7 Enable the current source and start sampling
    CTMUCON1Lbits.CTMUEN = 1; // Enable the CTMU
    CTMUCON1Hbits.EDG1STAT = 1; // Enable current source
    CTMUCON1Lbits.IDISSEN = 1; // Enable discharge 
    
    ADC1_Start(); // Start Sampling
    
    // step 8 1500us delay to discharge sample cap
    for (x = 0; x < 2000; x++); // ~6 cycles * 2000
    
    // step 9 Disable the discharge circuit
    CTMUCON1Lbits.IDISSEN = 0; // Disable discharge (start charging)

    // step 10 allow the sample cap to partially charge
    for (x = 0; x < 200; x++); // ~6 cycles * 250 ~ 670 cnts
    
    // step 11 Convert the analog sample
    ADC1_Stop(); // Stop sampling
    
    while(!ADC1_IsConversionComplete()) // Wait for the conversion to complete
    {
        ADC1_Tasks();   
    }
    
    // Step 12 Disable the current source
    CTMUCON1Hbits.EDG1STAT = 0; // Disable current source
    IFS0bits.AD1IF = 0; // Clear ADC interrupt flag
    CTMUCON1Lbits.CTMUEN = 0; // Disable the CTMU
    result = ADC1_ConversionResultGet();
    return (result);
}

/**
  End of File
*/
