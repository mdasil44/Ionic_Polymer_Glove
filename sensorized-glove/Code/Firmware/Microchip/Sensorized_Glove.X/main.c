/**
  Generated Main Source File

  Company:
    Microchip Technology Inc.

  File Name:
    main.c

  Summary:
    This is the main file generated using PIC24 / dsPIC33 / PIC32MM MCUs

  Description:
    This header file provides implementations for driver APIs for all modules selected in the GUI.
    Generation Information :
        Product Revision  :  PIC24 / dsPIC33 / PIC32MM MCUs - pic24-dspic-pic32mm : v1.35
        Device            :  PIC24FV16KM202
    The generated drivers are tested against the following:
        Compiler          :  XC16 1.31
        MPLAB             :  MPLAB X 3.60
*/

/*
    (c) 2016 Microchip Technology Inc. and its subsidiaries. You may use this
    software and any derivatives exclusively with Microchip products.

    THIS SOFTWARE IS SUPPLIED BY MICROCHIP "AS IS". NO WARRANTIES, WHETHER
    EXPRESS, IMPLIED OR STATUTORY, APPLY TO THIS SOFTWARE, INCLUDING ANY IMPLIED
    WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY, AND FITNESS FOR A
    PARTICULAR PURPOSE, OR ITS INTERACTION WITH MICROCHIP PRODUCTS, COMBINATION
    WITH ANY OTHER PRODUCTS, OR USE IN ANY APPLICATION.

    IN NO EVENT WILL MICROCHIP BE LIABLE FOR ANY INDIRECT, SPECIAL, PUNITIVE,
    INCIDENTAL OR CONSEQUENTIAL LOSS, DAMAGE, COST OR EXPENSE OF ANY KIND
    WHATSOEVER RELATED TO THE SOFTWARE, HOWEVER CAUSED, EVEN IF MICROCHIP HAS
    BEEN ADVISED OF THE POSSIBILITY OR THE DAMAGES ARE FORESEEABLE. TO THE
    FULLEST EXTENT ALLOWED BY LAW, MICROCHIP'S TOTAL LIABILITY ON ALL CLAIMS IN
    ANY WAY RELATED TO THIS SOFTWARE WILL NOT EXCEED THE AMOUNT OF FEES, IF ANY,
    THAT YOU HAVE PAID DIRECTLY TO MICROCHIP FOR THIS SOFTWARE.

    MICROCHIP PROVIDES THIS SOFTWARE CONDITIONALLY UPON YOUR ACCEPTANCE OF THESE
    TERMS.
*/

#include "mcc_generated_files/mcc.h"
/*
                         Main application
 */

uint8_t sensorChannels[16] = { ADC1_SENSOR_0, ADC1_SENSOR_1, ADC1_SENSOR_2, ADC1_SENSOR_3, ADC1_SENSOR_4, ADC1_SENSOR_5, ADC1_SENSOR_6, ADC1_SENSOR_7, ADC1_SENSOR_8, ADC1_SENSOR_9, ADC1_SENSOR_10, ADC1_SENSOR_11, ADC1_SENSOR_12, ADC1_SENSOR_13, ADC1_SENSOR_14, ADC1_SENSOR_15};


int main(void)
{
    // initialize the device
    SYSTEM_Initialize();
    CtmuCapTouchConfig(CTMU_MODE_EDGE, RANGE_0_550uA, 0);
    
    uint8_t count = 0;
    
    while(1)
    {
        for ( count = 0; count < 16; count++)
        {
           sampleCapSensor(count);
           //__delay_ms(5);
        }
    }

    return -1;
}

void sampleCapSensor (uint8_t channel)
{
    uint16_t sample = 0;
    
    // Redirect the ADC to the desired channel
    ADC1_ChannelSelect( sensorChannels[channel] );
    
    uint8_t sampleCount = 0;
    // Sample the ADC Channel
    for ( sampleCount = 0; sampleCount < 1; sampleCount++)
    {
        sample = sample + CtmuReturnSample();
    }

    sample = sample / 1;
    
    // Send a start byte
    while(UART1_StatusGet() & UART1_TX_FULL) {}
    UART1_Write(0x61);
    
    // Send the channel number
    while(UART1_StatusGet() & UART1_TX_FULL) {}
    UART1_Write(channel);

    // Send the MSB of the data
    while(UART1_StatusGet() & UART1_TX_FULL) {}
    UART1_Write( (sample >> 8) & 0x00FF);
    
    // Send the LSB of the data
    while(UART1_StatusGet() & UART1_TX_FULL) {}
    UART1_Write( sample & 0x00FF);
}
/**
 End of File
*/