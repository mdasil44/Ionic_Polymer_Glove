/*  RCTiming_capacitance_meter
 *   Paul Badger 2008
 *  Demonstrates use of RC time constants to measure the value of a capacitor 
 *
 * Theory   A capcitor will charge, through a resistor, in one time constant, defined as T seconds where
 *    TC = R * C
 * 
 *    TC = time constant period in seconds
 *    R = resistance in ohms
 *    C = capacitance in farads (1 microfarad (ufd) = .0000001 farad = 10^-6 farads ) 
 *
 *    The capacitor's voltage at one time constant is defined as 63.2% of the charging voltage.
 *
 *  Hardware setup:
 *  Test Capacitor between common point and ground (positive side of an electrolytic capacitor  to common)
 *  Test Resistor between chargePin and common point
 *  220 ohm resistor between dischargePin and common point
 *  Wire between common point and analogPin (A/D input)
 */

#define analogPin      0          // analog pin for measuring capacitor voltage
#define chargePin      13         // pin to charge the capacitor - connected to one end of the charging resistor
#define dischargePin   11         // pin to discharge the capacitor
                                  // F formatter tells compliler it's a floating point value
#define avg 20

unsigned long chargeTime[avg];
unsigned long sumTime;
float microFarads;                // floating point variable to preserve precision, make calculations
float nanoFarads;

void setup(){
  pinMode(chargePin, OUTPUT);     // set chargePin to output
  digitalWrite(chargePin, LOW); 

  Serial.begin(9600);             // initialize serial transmission for debugging
}

void loop(){

  sumTime = 0;
  for ( int count = 0; count < avg; count ++ )
  {
     chargeTime[count] = chargeCap();
     sumTime = sumTime + chargeTime[count];
     dischargeCap();
  }

  
  Serial.print("Charge Time: ");       // print the value to serial port
  Serial.println(sumTime/avg); 

  
  delay(50);
}

unsigned long chargeCap()
{
  unsigned long startTime;
  unsigned long elapsedTime;
  digitalWrite(chargePin, HIGH);  // set chargePin HIGH and capacitor charging
  startTime = micros();

  while(analogRead(analogPin) < 648){       // 647 is 63.2% of 1023, which corresponds to full-scale voltage 
  }

  elapsedTime= micros() - startTime;

  return elapsedTime;
}

unsigned long dischargeCap()
{
    /* dicharge the capacitor  */
  digitalWrite(chargePin, LOW);             // set charge pin to  LOW 
  pinMode(dischargePin, OUTPUT);            // set discharge pin to output 
  digitalWrite(dischargePin, LOW);          // set discharge pin LOW 
  while(analogRead(analogPin) > 0){         // wait until capacitor is completely discharged
  }

  pinMode(dischargePin, INPUT);            // set discharge pin back to input
}

