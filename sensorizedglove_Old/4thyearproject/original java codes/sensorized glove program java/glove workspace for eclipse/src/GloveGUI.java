import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.jfree.chart.ChartPanel;
import org.jfree.ui.RefineryUtilities;

import net.miginfocom.swing.MigLayout;

/*
 * Sensorized Glove GUI
 * 
 * By: Tim Nicholson Undergraduate Student studying Electrical Engineering at the University of Western Ontario
 * 
 * Final Design project 2015
 * 
 * This program is used to accept the input from the Arduino Microcontroller using the serial port.
 * This data is then displayed using a GUI designed to be intuitive and straightforward for the user.
 * 
 */









/*
 * The GloveGUI class is responsible for initializing the GUI, handling the input from the glove
 * through the serial port (COM 6), and inputting this data into the GUI to be displayed
 */

public class GloveGUI implements SerialPortEventListener{
	SerialPort serialPort;
	
	private static Double oldtime = new Double (0);
	private static Double firsttime = new Double (0);
	private Double RightOldAccel = new Double (0);
	private static GloveFrame window= new GloveFrame();

    //The port the program will use. 
private static final String PORT_NAMES[] = { 

		"COM6", 
};

private BufferedReader input;

private OutputStream output;

private static final int TIME_OUT = 2000;
/** Default bits per second for COM port. */
private static final int DATA_RATE = 9600;

public void initialize() {


	CommPortIdentifier portId = null;
	Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

	//Find an instance of serial port
	while (portEnum.hasMoreElements()) {
		CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
		for (String portName : PORT_NAMES) {
			if (currPortId.getName().equals(portName)) {
				portId = currPortId;
				break;
			}
		}
	}
	if (portId == null) {
		System.out.println("Could not find COM port.");
		return;
	}

	try {
		// open serial port
		serialPort = (SerialPort) portId.open(this.getClass().getName(),
				TIME_OUT);

		// set port parameters
		serialPort.setSerialPortParams(DATA_RATE,
				SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1,
				SerialPort.PARITY_NONE);

		// open serial port streams
		input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		output = serialPort.getOutputStream();

		// add event listeners
		serialPort.addEventListener(this);
		serialPort.notifyOnDataAvailable(true);
	} catch (Exception e) {
		System.err.println(e.toString());
	}
}

private String[] getSensorStrings(String[] AllStrings)
{
	int NumSensors = AllStrings.length-3;
	String[] SensorStrings = new String[NumSensors];
	
	for (int i = 0; i < NumSensors; i++)
	{
		SensorStrings[i] = AllStrings[i];
	}
	
	
	return SensorStrings;
	
}

private Double[] ConvertSensorValues(String[] inStrings)
{
	int numValues = inStrings.length;
	Double[] SensorDoubles = new Double[numValues];
	DecimalFormat df = new DecimalFormat("00.00000");
	
	for (int i = 0; i < numValues; i++)
	{
		SensorDoubles[i] = 38.22/(Double.parseDouble(inStrings[i])) -0.001;
		df.format(SensorDoubles[i]);
	}
	
	
	
	return SensorDoubles;
	
}

private Double AccelMag(String x, String y, String z)
{
	Double ArrayX = Double.parseDouble(x) -1796; //1796,1464,-4856,
	Double ArrayY = Double.parseDouble(y) -1464;
	Double ArrayZ = Double.parseDouble(z) +4856;
	DecimalFormat df2 = new DecimalFormat("0000.00");
	Double AccelMag = ((2*9.80665)/32767)*Math.sqrt(ArrayX*ArrayX + ArrayY*ArrayY + ArrayZ*ArrayZ);
	df2.format(AccelMag);
	return AccelMag;
}

/**
 * This should be called when you stop using the port.
 * This will prevent port locking on platforms like Linux.
 */
public synchronized void close() {
	if (serialPort != null) {
		serialPort.removeEventListener();
		serialPort.close();
	}
}

/**
 * Handle an event on the serial port. Read the data and print it.
 */
public synchronized void serialEvent(SerialPortEvent oEvent) {
	if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
		try {
			String inputLine=input.readLine();
			String ValuesArray[] = inputLine.split(",");			//Data of the form Accelx, Accely, Accel Z, Sensors1-10
			int NumInputs = ValuesArray.length;
			String SensorStrings[] = new String[NumInputs-3];
			Double[] SensorNumeric = new Double[NumInputs-3];
			Calendar cal = Calendar.getInstance();
			Double newtime = (double) cal.getTimeInMillis();
			firsttime = window.getTime();
			newtime = newtime - firsttime;
			Double RightNewAccel = AccelMag(ValuesArray[NumInputs-3], ValuesArray[NumInputs-2], ValuesArray[NumInputs-1]);
			Double RightJerk = Math.abs(RightNewAccel - RightOldAccel) / (Math.abs(newtime-oldtime)/1000);
			RightOldAccel = RightNewAccel;
			oldtime = newtime;
			SensorStrings = getSensorStrings(ValuesArray);
			
			SensorNumeric = ConvertSensorValues(SensorStrings);
			
			window.currentTime(newtime);
			window.currentRightAccel(RightNewAccel);
			window.currentRightJerk(RightJerk);
			

			window.currentRightSensor13(SensorNumeric[0]);
			window.currentRightSensor15(SensorNumeric[1]);
			window.currentRightSensor19(SensorNumeric[2]);
			window.currentRightSensor1(SensorNumeric[3]);
			window.currentRightSensor4(SensorNumeric[4]);
			window.currentRightSensor7(SensorNumeric[5]);
			window.currentRightSensor16(SensorNumeric[6]);
			window.currentRightSensor8(SensorNumeric[7]);
			window.currentRightSensor18(SensorNumeric[8]);
			window.currentRightSensor17(SensorNumeric[9]);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
}
	
	
	/**
	 * Launch the GUI
	 */
	public static void main(String[] args) {
		GloveGUI main = new GloveGUI();
		firsttime = window.getTime();
		oldtime = firsttime;
		main.initialize();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.setVisible(true);
					window.setResizable(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


				
				
				
				
				
	}
