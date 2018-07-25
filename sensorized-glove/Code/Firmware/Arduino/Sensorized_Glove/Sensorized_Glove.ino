// Sensorized Glove Firmware

#include <SoftwareSerial.h>

#include "I2Cdev.h"
#include "MPU6050.h"

// Pin Definitions
#define PIC_SERIAL_RX         10
#define PIC_SERIAL_TX         11
#define LED1_PIN              13
#define LED2_PIN              A1

// Arduino Wire library is required if I2Cdev I2CDEV_ARDUINO_WIRE implementation
// is used in I2Cdev.h
#if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
    #include "Wire.h"
#endif

SoftwareSerial PICSerial(PIC_SERIAL_RX, PIC_SERIAL_RX);

int16_t ax, ay, az;
int16_t gx, gy, gz;

MPU6050 accelgyro;

void setup() {
  
  // Pin configuration
  pinMode(LED1_PIN, OUTPUT);
  pinMode(LED2_PIN, OUTPUT);
  digitalWrite(LED1_PIN, LOW);
  digitalWrite(LED2_PIN, LOW);
  
  // Communication Setup
    // Serial communication to PC
  Serial.begin(115200);
    // Serial Communication to PIC controller
  PICSerial.begin(38400);

  // IMU Initialization
    // join I2C bus (I2Cdev library doesn't do this automatically)
  #if I2CDEV_IMPLEMENTATION == I2CDEV_ARDUINO_WIRE
      Wire.begin();
  #elif I2CDEV_IMPLEMENTATION == I2CDEV_BUILTIN_FASTWIRE
      Fastwire::setup(400, true);
  #endif
  
  accelgyro.initialize();
  if( accelgyro.testConnection() ) 
  {
    digitalWrite(LED1_PIN, HIGH);
  }
}

void loop() {
  if (PICSerial.available()) {
    char serialData = PICSerial.read();

    // Wait for the beginning of the transmission
    if (serialData == 'a')
    {
      // Wait for the channel indicator
      while (PICSerial.available() < 1) {}

      byte sensorChannel = PICSerial.read();

      // Wait for 2 bytes (16 bit data) to come in
      while (PICSerial.available() < 2) {}

      // Receive the MSB
      byte capDataMSB = PICSerial.read();

      // Receive the LSB
      byte capDataLSB = PICSerial.read();

      unsigned int capData = (capDataMSB << 8) | capDataLSB;

      // Send the data identifier
      switch (sensorChannel)
      {
        case 0:
          // Send the start character
          Serial.print("a");
          Serial.print("b");
          break;

        case 1:
          Serial.print("c");
          break;

        case 2:
          Serial.print("d");
          break;

        case 3:
          Serial.print("e");
          break;

        case 4:
          Serial.print("f");
          break;

        case 5:
          Serial.print("g");
          break;

        case 6:
          Serial.print("h");
          break;

        case 7:
          Serial.print("i");
          break;

        case 8:
          Serial.print("j");
          break;

        case 9:
          Serial.print("k");
          break;

        case 10:
          Serial.print("l");
          break;

        case 11:
          Serial.print("m");
          break;

        case 12:
          Serial.print("n");
          break;

        case 13:
          Serial.print("o");
          break;

        case 14:
          Serial.print("p");
          break;

        case 15:
          Serial.print("q");
          break;
        
      }

      // Send the capacitance data
      Serial.print(capData); 
      
      if (sensorChannel == 15)
      {
        // read raw accel/gyro measurements from device
        accelgyro.getMotion6(&ax, &ay, &az, &gx, &gy, &gz);
        
        Serial.print("r"); Serial.print(ax); 
        Serial.print("s"); Serial.print(ay); 
        Serial.print("t"); Serial.print(az); 
        Serial.print("u"); Serial.print(gx); 
        Serial.print("v"); Serial.print(gy); 
        Serial.print("w"); Serial.print(gz); 
        
        // Send the terminating character
        Serial.print("z");
      }
    }
    serialData = 0;
  }
  
}
