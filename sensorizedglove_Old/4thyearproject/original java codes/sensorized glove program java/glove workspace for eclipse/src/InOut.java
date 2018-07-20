import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.csvreader.CsvWriter;
/*The InOut Data Class is used as input and outputs of the sytem.
 * It consolidates the data of both hands into one variable and 
* calls read an write methods using this new variable.
*/
/*-------------Save File Order-----------
 * Accelerations
 * Angles
 * Jerks
 * Pressures1-19
 ---------------------------------------*/
public class InOut {

	private DataGroup RightHand;
	private DataGroup LeftHand;
	
	public InOut(){
		RightHand = new DataGroup();
		LeftHand = new DataGroup();
	}
	
	public void setData(DataGroup inRight, DataGroup inLeft)
	{
		RightHand = inRight;
		LeftHand = inLeft;
	}
	
	public void saveFile()
	{
		
		final String DATE_FORMAT_NOW = "yyyy-MM-dd-HH-mm-ss";
		
		final String outputFile = getDateTime(DATE_FORMAT_NOW) + ".csv";
		
		
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
			
		try {
			// use FileWriter constructor that specifies open for appending
			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
			// if the file didn't already exist then we need to write out the header line
			if (!alreadyExists)
			{
				//--------------------------Right Hand--------------------------
				for (int i = 0; i < RightHand.getAccelerations().size(); i++)
				{
					csvOutput.write(Double.toString(RightHand.getAccelerations().get(i)));
				
				
				}
				csvOutput.endRecord();

				for (int i = 0; i < RightHand.getAngles().size(); i++)
				{
					csvOutput.write(Double.toString(RightHand.getAngles().get(i)));
				
				}
				csvOutput.endRecord();
				
				for (int i = 0; i < RightHand.getJerks().size(); i++)
				{
					csvOutput.write(Double.toString(RightHand.getJerks().get(i)));
				
				}
				csvOutput.endRecord();
				
				for (int i = 0; i < RightHand.getPressures().size(); i++)
				{
					
						for (int j = 0; j < RightHand.getPressures().get(i).size(); j++)
						{
							
							csvOutput.write(Double.toString(RightHand.getPressures().get(i).get(j)));
						}
				
						csvOutput.endRecord();
				}
				
				//--------------------------Left Hand---------------------
				for (int i = 0; i < LeftHand.getAccelerations().size(); i++)
				{
					csvOutput.write(Double.toString(LeftHand.getAccelerations().get(i)));
				
				
				}
				csvOutput.endRecord();

				for (int i = 0; i < LeftHand.getAngles().size(); i++)
				{
					csvOutput.write(Double.toString(LeftHand.getAngles().get(i)));
				
				}
				csvOutput.endRecord();
				
				for (int i = 0; i < LeftHand.getJerks().size(); i++)
				{
					csvOutput.write(Double.toString(LeftHand.getJerks().get(i)));
				
				}
				csvOutput.endRecord();
				
				for (int i = 0; i < LeftHand.getPressures().size(); i++)
				{
						for (int j = 0; j < LeftHand.getPressures().get(i).size(); j++)
						{
							csvOutput.write(Double.toString(LeftHand.getPressures().get(i).get(j)));
						}
				
						csvOutput.endRecord();
				}
			}
			
			csvOutput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public void ReadFile(String FileName) throws IOException
	{
		try {
			BufferedReader Loader = new BufferedReader(new FileReader(FileName));
			
		      String data = Loader.readLine();

		     //while (data != null){
		     //if (data != null){
		    	  
		    	  //-----------------Right Hand Accelerations-------------
		    	  String DataArray[] = data.split(",");
		    	  ArrayList<Double> tempList = new ArrayList<Double>();

		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  RightHand.setAccelerations(tempList);
		    	  
		    	  
		          //---------------Right Hand Angle---------- 
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList2 = new ArrayList<Double>();
		    	  if(DataArray.length==0)
		    	  {
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    	  tempList2.add(Double.parseDouble(DataArray[i]));
		    	  }
		    	  RightHand.setAngles(tempList2);
		    	  }
		    	  
		    	//---------------Right Hand Jerks---------- 

		    	  data = Loader.readLine();
		    	  
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList3 = new ArrayList<Double>();
		    	  if(DataArray.length==0)
		    	  {
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    	  tempList3.add(Double.parseDouble(DataArray[i]));
		    	  }
		    	  RightHand.setAngles(tempList3);
		    	  }
		    	  
		    	//-------------Right Hand Pressures-----------------
		    	  
		    	//-------------------------1------------------------
		    	
		    	  ArrayList<ArrayList<Double>> PressArraytemp = new ArrayList<ArrayList<Double>>();
		    	  data = Loader.readLine();
		    	  //System.out.println(data);
		          DataArray = data.split(",");
		          //System.out.println(DataArray);
		          
		          ArrayList<Double> tempList4 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	  //{
		    		  tempList4.add(Double.parseDouble(DataArray[i]));
		    		  
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList4);
		    	
		    	  
		    	  
		    	  //-----------------------2------------------------
		    	  
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList5 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	  //{
		    		  tempList5.add(Double.parseDouble(DataArray[i]));
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList5);
		    	  
		    	  
		    	  //-----------------------3----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList6 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList6.add(Double.parseDouble(DataArray[i]));
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList6);
	
		    	  
		    	  //-----------------------4----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList7 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList7.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList7);

		    	  
		    	  //-----------------------5----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList8 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	 // {
		    		  tempList8.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList8);
		    	  
		    	  //-----------------------6----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList9 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	  //{
		    		  tempList9.add(Double.parseDouble(DataArray[i]));
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList9);
		    	  
		    	  //-----------------------7----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList10 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	  //{
		    		  tempList10.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList10);
		    	  
		    	  //-----------------------8----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList11 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList11.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList11);
		    	  
		    	  //-----------------------9----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList12 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList12.add(Double.parseDouble(DataArray[i]));
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList12);
		    	  
		    	  //-----------------------10----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList13 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	  //{
		    		  tempList13.add(Double.parseDouble(DataArray[i]));
			    	 //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList13);
		    	  
		    	  //-----------------------11----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList14 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	  //{
		    		  tempList14.add(Double.parseDouble(DataArray[i]));
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList14);
		    	  
		    	  //-----------------------12----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList15 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	  //{
		    		  tempList15.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList15);
		    	  
		    	  //-----------------------13----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList16 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	  //{
		    		  tempList16.add(Double.parseDouble(DataArray[i]));
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList16);
		    	  
		    	  //-----------------------14----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList17 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList17.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList17);
		    	  
		    	  
		    	  //-----------------------15----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList18 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList18.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList18);
		    	  //-----------------------16----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList19 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList19.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList19);
		    	  
		    	  //-----------------------17----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList20 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList20.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList20);
		    	  

		    	  
		    	  //-----------------------18----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList21 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		 // if(DataArray.length==0)
			    	 // {
		    		  tempList21.add(Double.parseDouble(DataArray[i]));
			    	 // }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList21);
		    	  
		    	  
		    	  //-----------------------19----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList22 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  //if(DataArray.length==0)
			    	 // {
		    		  tempList22.add(Double.parseDouble(DataArray[i]));
			    	  //}
		    	  }
		    	  
		    	  PressArraytemp.add(tempList22);
		    	  
		    	  //---------------Final----------------------------
		    	  
		    	  RightHand.setPressures(PressArraytemp);
		  		System.out.println(RightHand.getPressures());
		    	  
		    	 /* //----------------Left Hand-----------------------
		    	  data = Loader.readLine();
		    	  PressArraytemp.clear();
		    	 
		    	  System.out.println(data);
		    	  //-----------------Left Hand Accelerations-------------
		    	  DataArray= data.split(",");
		    	  System.out.println(RightHand.getPressures());
		    	  ArrayList<Double> tempList23 = new ArrayList<Double>();
		    	  
	    		  if(!DataArray[0].isEmpty())
		    	  {
	    		 
	    			  for(int i = 0; i < DataArray.length; i++)
	    			  {
	    				  tempList23.add(Double.parseDouble(DataArray[i]));
	    			  }
		    	  }
		    	  LeftHand.setAccelerations(tempList23);
		    	  
		          
		          //---------------Left Hand Angle---------- 
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList24= new ArrayList<Double>();
		    	  if(!DataArray[0].isEmpty())
		    	  {
		    		  for(int i = 0; i < DataArray.length; i++)
		    		  {
		    			  tempList24.add(Double.parseDouble(DataArray[i]));
		    		  }
		    	  }
		    	  LeftHand.setAngles(tempList24);
		    	  
		    	//---------------Left Hand Jerks---------- 
		    	  
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList25 = new ArrayList<Double>();
		    	  if(!DataArray[0].isEmpty())
		    	  {
		    		  for(int i = 0; i < DataArray.length; i++)
		    		  {
		    			  tempList25.add(Double.parseDouble(DataArray[i]));
		    		  }
		    	  LeftHand.setAngles(tempList25);
		    	  }
		    	  
		    	//-------------Left Hand Pressures-----------------
		    	  
		    	//-------------------------1------------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList26 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(DataArray.length==0)
			    	  {
		    		  tempList26.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList26);
		    	  //-----------------------2------------------------
		    	  
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList27 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList27.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList27);
		    	  
		    	  //-----------------------3----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		         
		          ArrayList<Double> tempList28 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList28.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList28);
		    	  
		    	  //-----------------------4----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList29 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList29.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList29);
		    	  
		    	  //-----------------------5----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList30 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList30.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList30);
		    	  
		    	  //-----------------------6----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList31 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList31.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList31);
		    	  
		    	  //-----------------------7----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList32 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList32.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList32);
		    	  
		    	  //-----------------------8----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList33 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList33.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList33);
		    	  
		    	  //-----------------------9----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList34 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList34.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList34);
		    	  
		    	  //-----------------------10----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList35 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList35.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList35);
		    	  
		    	  //-----------------------11----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList36 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList36.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList36);
		    	  
		    	  //-----------------------12----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList37 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList37.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList37);
		    	  
		    	  //-----------------------13----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList38 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList38.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList38);
		    	  
		    	  //-----------------------14----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList39 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList39.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList39);
		    	  
		    	  //-----------------------15----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList40 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList40.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList40);
		    	  
		    	  //-----------------------16----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList41 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList41.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList41);
		    	  
		    	  //-----------------------17----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList42 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList42.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList42);
		    	  

		    	  
		    	  //-----------------------18----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList43 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList43.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList43);
		    	  
		    	  
		    	  //-----------------------19----------------------
		    	  data = Loader.readLine();
		          DataArray = data.split(",");
		          
		          ArrayList<Double> tempList44 = new ArrayList<Double>();
		    	  for(int i = 0; i < DataArray.length; i++)
		    	  {
		    		  if(!DataArray[i].isEmpty())
			    	  {
		    		  tempList44.add(Double.parseDouble(DataArray[i]));
			    	  }
		    	  }
		    	  
		    	  PressArraytemp.add(tempList44);
		    	  
		    	  //---------------Final----------------------------
		    	  
		    	  LeftHand.setPressures(PressArraytemp);
		    	  
		    	
		    	  Loader.close();
		        //}*/
		     
		      


		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public DataGroup getLeftHand()
	{
		return LeftHand;
	}
	
	public DataGroup getRightHand()
	{

		return RightHand;
	}
	
	private static String getDateTime(String datetime) {
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(datetime);
	return sdf.format(cal.getTime());
	}
}

