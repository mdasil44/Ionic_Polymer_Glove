#include <SoftwareSerial.h>

#define BT_SERIAL_RX          9
#define BT_SERIAL_TX          A2
 

SoftwareSerial BTSerial(BT_SERIAL_RX, BT_SERIAL_TX);
 
char c=' ';
boolean NL = true;
 
void setup() 
{
    Serial.begin(9600);
 
    BTSerial.begin(115200);  

}
 
void loop()
{
    // Read from the Bluetooth module and send to the Arduino Serial Monitor
    if (BTSerial.available())
    {
        c = BTSerial.read();
        Serial.write(c);
    }
 
 
    // Read from the Serial Monitor and send to the Bluetooth module
    if (Serial.available())
    {
        c = Serial.read();
  
        BTSerial.write(c);

 
        // Echo the user input to the main window. 
        // If there is a new line print the ">" character.
        if (NL) { Serial.print("\r\n>");  NL = false; }
        Serial.write(c);
        if (c==10) { NL = true; }
    }
}
