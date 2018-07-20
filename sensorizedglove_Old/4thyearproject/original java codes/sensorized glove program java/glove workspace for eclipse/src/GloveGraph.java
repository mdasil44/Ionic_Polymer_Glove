/*
 * The GloveGraph Class utilises the JFreechart class to convert the ArrayLists saved to the system into DataSets
 * These Datasets are converted into Charts and these Charts are converted into Chart Panels
 * These chart panels can then be output for use in the GUI
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.Series;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * A simple demonstration application showing how to create a line chart using data from an
 * {@link XYDataset}.
 *
 */
public class GloveGraph extends JPanel{

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
	
	private XYDataset Acceldataset;
	private XYDataset Jerkdataset;
	private XYDataset Angledataset;
	private XYDataset[] Pressuredataset;
    private JFreeChart Accelchart; 
    private JFreeChart Jerkchart;
    private JFreeChart Anglechart;
    private JFreeChart Pressurechart;
    private ChartPanel AccelchartPanel;
    private ChartPanel JerkchartPanel;
    private ChartPanel AnglechartPanel;
    private ChartPanel SensorchartPanel1;
    private ChartPanel SensorchartPanel2;
    private ChartPanel SensorchartPanel3;
    private ChartPanel SensorchartPanel4;
    private ChartPanel SensorchartPanel5;
    private ChartPanel SensorchartPanel6;
    private ChartPanel SensorchartPanel7;
    private ChartPanel SensorchartPanel8;
    private ChartPanel SensorchartPanel9;
    private ChartPanel SensorchartPanel10;
    private ChartPanel SensorchartPanel11;
    private ChartPanel SensorchartPanel12;
    private ChartPanel SensorchartPanel13;
    private ChartPanel SensorchartPanel14;
    private ChartPanel SensorchartPanel15;
    private ChartPanel SensorchartPanel16;
    private ChartPanel SensorchartPanel17;
    private ChartPanel SensorchartPanel18;
    private ChartPanel SensorchartPanel19;
    private ArrayList<Double> Accelerations;
    private ArrayList<Double> Jerks;
    private ArrayList<Double> Angles;
    private ArrayList<Double> Times;
    private ArrayList<ArrayList<Double>> Pressures;
    private ArrayList<Double> Press1;
    private ArrayList<Double> Press2;
    private ArrayList<Double> Press3;
    private ArrayList<Double> Press4;
    private ArrayList<Double> Press5;
    private ArrayList<Double> Press6;
    private ArrayList<Double> Press7;
    private ArrayList<Double> Press8;
    private ArrayList<Double> Press9;
    private ArrayList<Double> Press10;
    private ArrayList<Double> Press11;
    private ArrayList<Double> Press12;
    private ArrayList<Double> Press13;
    private ArrayList<Double> Press14;
    private ArrayList<Double> Press15;
    private ArrayList<Double> Press16;
    private ArrayList<Double> Press17;
    private ArrayList<Double> Press18;
    private ArrayList<Double> Press19;
    
    
    
    private String title;


    
    
    
    //For sonstructor Use arrays for each variable and an int array with a 1 in slots that correspond to each one.
    //Maybe make a class 
    public GloveGraph(String INtitle, DataGroup x) {
    	
    	title = INtitle;
    	Accelerations = new ArrayList<Double>();
    	Accelerations.add(new Double(3.05));
    	Accelerations.add(new Double(4.21));
    	Accelerations.add(new Double(2.45));
    	Accelerations.add(new Double(1.55));
    	Accelerations.add(new Double(6.25));
    	
    	
    	Times = new ArrayList<Double>();
    	Times.add(new Double(1));
    	Times.add(new Double(2));
    	Times.add(new Double(3));
    	Times.add(new Double(4));
    	Times.add(new Double(5));
    	//data.setAccelerations(Accelerations);
    	Jerks = new ArrayList<Double>();
    	Jerks.add(new Double(9.05));
    	Jerks.add(new Double(5.21));
    	Jerks.add(new Double(8.45));
    	Jerks.add(new Double(2.55));
    	Jerks.add(new Double(15.25));
    	//data.setJerks(Jerks);
    	Angles = new ArrayList<Double>();
    	Angles.add(new Double(5.05));
    	Angles.add(new Double(4.21));
    	Angles.add(new Double(0.45));
    	Angles.add(new Double(10.55));
    	Angles.add(new Double(7.25));
    	
    	Pressures = new ArrayList<ArrayList<Double>>();
    	Press1 = new ArrayList<Double>();
    	Press1.add(10.3);
    	Press1.add(5.6);
    	Press1.add(15.4);
    	Press1.add(21.8);
    	
    	Pressures.add(Press1);


    	Press2 = new ArrayList<Double>();
    	Press2.add(11.3);
    	Press2.add(1.6);
    	Press2.add(12.4);
    	Press2.add(22.8);
    	
    	Pressures.add(Press2);
    	
    	Press3 = new ArrayList<Double>();
    	Press3.add(13.3);
    	Press3.add(3.6);
    	Press3.add(13.4);
    	Press3.add(23.8);
    	
    	Pressures.add(Press3);
    	
    	Press4 = new ArrayList<Double>();
    	Press4.add(14.3);
    	Press4.add(4.6);
    	Press4.add(45.4);
    	Press4.add(24.8);
    	
    	Pressures.add(Press4);
    	
    	Press5 = new ArrayList<Double>();
    	Press5.add(15.3);
    	Press5.add(5.6);
    	Press5.add(15.4);
    	Press5.add(25.8);
    	
    	Pressures.add(Press5);

    	
    	Press6 = new ArrayList<Double>();
    	Press6.add(16.3);
    	Press6.add(6.6);
    	Press6.add(16.4);
    	Press6.add(26.8);
    	
    	Pressures.add(Press6);

    	
    	Press7 = new ArrayList<Double>();
    	Press7.add(17.3);
    	Press7.add(7.6);
    	Press7.add(17.4);
    	Press7.add(27.8);
    	
    	Pressures.add(Press7);

    	Press8 = new ArrayList<Double>();
    	Press8.add(18.3);
    	Press8.add(9.6);
    	Press8.add(19.4);
    	Press8.add(29.8);
    	
    	Pressures.add(Press8);

    	Press9 = new ArrayList<Double>();
    	Press9.add(10.3);
    	Press9.add(50.6);
    	Press9.add(10.4);
    	Press9.add(20.8);
    	
    	Pressures.add(Press9);

    	Press10 = new ArrayList<Double>();
    	Press10.add(10.3);
    	Press10.add(5.6);
    	Press10.add(15.4);
    	Press10.add(21.8);
    	
    	Pressures.add(Press10);
    	
    	Press11 = new ArrayList<Double>();
    	Press11.add(11.3);
    	Press11.add(51.6);
    	Press11.add(11.4);
    	Press11.add(22.8);
    	
    	Pressures.add(Press11);

    	Press12 = new ArrayList<Double>();
    	Press12.add(12.3);
    	Press12.add(2.6);
    	Press12.add(12.4);
    	Press12.add(23.8);
    	
    	Pressures.add(Press12);

    	Press13 = new ArrayList<Double>();
    	Press13.add(13.3);
    	Press13.add(3.6);
    	Press13.add(13.4);
    	Press13.add(23.8);
    	
    	Pressures.add(Press13);

    	Press14 = new ArrayList<Double>();
    	Press14.add(14.3);
    	Press14.add(4.6);
    	Press14.add(15.4);
    	Press14.add(26.8);
    	
    	Pressures.add(Press14);
    	
    	Press15= new ArrayList<Double>();
    	Press15.add(12.3);
    	Press15.add(3.6);
    	Press15.add(11.4);
    	Press15.add(21.8);
    	
    	Pressures.add(Press15);

    	Press16 = new ArrayList<Double>();
    	Press16.add(12.3);
    	Press16.add(5.6);
    	Press16.add(13.4);
    	Press16.add(24.8);
    	
    	Pressures.add(Press16);

    	Press17 = new ArrayList<Double>();
    	Press17.add(15.3);
    	Press17.add(6.6);
    	Press17.add(17.4);
    	Press17.add(23.8);
    	
    	Pressures.add(Press17);

    	Press18 = new ArrayList<Double>();
    	Press18.add(12.3);
    	Press18.add(12.6);
    	Press18.add(14.4);
    	Press18.add(12.8);
    	
    	Pressures.add(Press18);

    	Press19 = new ArrayList<Double>();
    	Press19.add(14.3);
    	Press19.add(21.6);
    	Press19.add(1.4);
    	Press19.add(11.8);
    	
    	Pressures.add(Press19);
    	
    	Acceldataset= createDataset(Accelerations,Times);
    	Jerkdataset= createDataset(Jerks,Times);
    	Angledataset= createDataset(Angles,Times);
    	
    	Pressuredataset = new XYDataset[19];
    	Pressuredataset= createPressureDataset(Pressures,Times);
        JFreeChart Accelchart = createChart(Acceldataset, INtitle + " Acceleration", "Acceleration (m/s^2)" ); 
        JFreeChart Jerkchart= createChart(Jerkdataset, INtitle + " Jerk", "Jerk (m/s^3)" );
        JFreeChart Anglechart= createChart(Angledataset, INtitle + " Angle", "Angle (degree)" );
        JFreeChart Sensor1chart = createChart(Pressuredataset[0], INtitle + " Sensor 1", "Force (N)");
        JFreeChart Sensor2chart = createChart(Pressuredataset[1], INtitle + " Sensor 2", "Force (N)");
        JFreeChart Sensor3chart = createChart(Pressuredataset[2], INtitle + " Sensor 3", "Force (N)");
        JFreeChart Sensor4chart = createChart(Pressuredataset[3], INtitle + " Sensor 4", "Force (N)");
        JFreeChart Sensor5chart = createChart(Pressuredataset[4], INtitle + " Sensor 5", "Force (N)");
        JFreeChart Sensor6chart = createChart(Pressuredataset[5], INtitle + " Sensor 6", "Force (N)");
        JFreeChart Sensor7chart = createChart(Pressuredataset[6], INtitle + " Sensor 7", "Force (N)");
        JFreeChart Sensor8chart = createChart(Pressuredataset[7], INtitle + " Sensor 8", "Force (N)");
        JFreeChart Sensor9chart = createChart(Pressuredataset[8], INtitle + " Sensor 9", "Force (N)");
        JFreeChart Sensor10chart = createChart(Pressuredataset[9], INtitle + " Sensor 10", "Force (N)");
        JFreeChart Sensor11chart = createChart(Pressuredataset[10], INtitle + " Sensor 11", "Force (N)");
        JFreeChart Sensor12chart = createChart(Pressuredataset[11], INtitle + " Sensor 12", "Force (N)");
        JFreeChart Sensor13chart = createChart(Pressuredataset[12], INtitle + " Sensor 13", "Force (N)");
        JFreeChart Sensor14chart = createChart(Pressuredataset[13], INtitle + " Sensor 14", "Force (N)");
        JFreeChart Sensor15chart = createChart(Pressuredataset[14], INtitle + " Sensor 15", "Force (N)");
        JFreeChart Sensor16chart = createChart(Pressuredataset[15], INtitle + " Sensor 16", "Force (N)");
        JFreeChart Sensor17chart = createChart(Pressuredataset[16], INtitle + " Sensor 17", "Force (N)");
        JFreeChart Sensor18chart = createChart(Pressuredataset[17], INtitle + " Sensor 18", "Force (N)");
        JFreeChart Sensor19chart = createChart(Pressuredataset[18], INtitle + " Sensor 19", "Force (N)");
        

        AccelchartPanel = new ChartPanel(Accelchart);
        JerkchartPanel = new ChartPanel(Jerkchart);
        AnglechartPanel = new ChartPanel(Anglechart);
        SensorchartPanel1 = new ChartPanel(Sensor1chart);
        SensorchartPanel2 = new ChartPanel(Sensor2chart);
        SensorchartPanel3 = new ChartPanel(Sensor3chart);
        SensorchartPanel4 = new ChartPanel(Sensor4chart);
        SensorchartPanel5 = new ChartPanel(Sensor5chart);
        SensorchartPanel6 = new ChartPanel(Sensor6chart);
        SensorchartPanel7 = new ChartPanel(Sensor7chart);
        SensorchartPanel8 = new ChartPanel(Sensor8chart);
        SensorchartPanel9 = new ChartPanel(Sensor9chart);
        SensorchartPanel10 = new ChartPanel(Sensor10chart);
        SensorchartPanel11 = new ChartPanel(Sensor11chart);
        SensorchartPanel12 = new ChartPanel(Sensor12chart);
        SensorchartPanel13 = new ChartPanel(Sensor13chart);
        SensorchartPanel14 = new ChartPanel(Sensor14chart);
        SensorchartPanel15 = new ChartPanel(Sensor15chart);
        SensorchartPanel16 = new ChartPanel(Sensor16chart);
        SensorchartPanel17 = new ChartPanel(Sensor17chart);
        SensorchartPanel18 = new ChartPanel(Sensor18chart);
        SensorchartPanel19 = new ChartPanel(Sensor19chart);
        
    }
    
    /**
     * Creates a sample dataset.
     * 
     * @return a sample dataset.
     */
    
    //since series just adds can use a loop and the arrays used in the other program
    private XYDataset createDataset(ArrayList<Double> indata, ArrayList<Double> inTimes) {
        
        final XYSeries outSeries = new XYSeries("Acceleration");
        
        for (int i = 0; i < indata.size(); i++ )
        {
        outSeries.add(inTimes.get(i), indata.get(i));
        }
        



        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(outSeries);
        
        return dataset;
        
    }
    

    
    
    
    
    /**
     * Creates a chart.
     * 
     * @param dataset  the data for the chart.
     * 
     * @return a chart.
     */
    private JFreeChart createChart(final XYDataset dataset, String title, String Ytitle ) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
            title,      // chart title
            "Time (ms)",                      // x axis label
            Ytitle,                      // y axis label
            dataset,                  // data
            PlotOrientation.VERTICAL,
            false,                     // include legend
            true,                     // tooltips
            false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
        
        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        
        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.
                
        return chart;
        
    }
    
    
    private XYDataset[] createPressureDataset(ArrayList<ArrayList<Double>> inArray, ArrayList<Double> inTimes ){
    	
    	XYSeries outSeries;
    	final XYDataset[] retArray = new XYDataset[inArray.size()]; 
    	
    	for (int i = 0; i<inArray.size(); i++)
    	{
    	
    		 outSeries = new XYSeries("Pressure " + i);
        
    		//ArrayList<Double> tempArray = new ArrayList<Double>();
        
    		//tempArray.addAll(inArray.get(i));
        
    		for (int j = 0; j < inArray.get(i).size(); j++ )
    		{
    			outSeries.add(inTimes.get(j), inArray.get(i).get(j));
    			
    		}
        
    		final XYSeriesCollection dataset = new XYSeriesCollection();
    		//System.out.println(outSeries);
    		dataset.addSeries(outSeries);
        
    		retArray[i] = dataset;
    	}
    	return retArray;
    }
    
    
   /* private XYPlot[] setPressureplots(XYDataset insets[])
    {
    	final XYPlot[] ArrayPlots = new XYPlot[insets.length];
    	
    	for (int i = 0; i<insets.length;i++)
    	{
    		final XYPlot oneplot = new XYPlot();
    		
    		oneplot.setDataset(i,insets[i]);
            oneplot.setDomainGridlinePaint(Color.white);
            oneplot.setRangeGridlinePaint(Color.white);
            
            final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
            oneplot.setRenderer(renderer);

            final NumberAxis rangeAxis = (NumberAxis) oneplot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            
            ArrayPlots[i] = oneplot;
            
            
    	}
		return ArrayPlots;
    	
        

    }*/
    /*private JFreeChart createPressureChart(final XYDataset[] dataset) {
    	
    	
    	
    	
    	
    	
    	
    	final JFreeChart chart = ChartFactory.createXYLineChart(
                "TITLE",      // chart title
                "Time",                      // x axis label
                "Pressure",                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
            );
    	
    }*/
    
    
    public JPanel getAccelChartPanel()
    {

    	return AccelchartPanel;
    }
    
    public JPanel getJerkChartPanel()
    {
		return JerkchartPanel;
    	
    }
    
    public JPanel getAngleChartPanel()
    {
		return AnglechartPanel;
    	
    }
    
    public JPanel getSensorChartPanel1()
    {
		return SensorchartPanel1;
    	
    }
    
    public JPanel getSensorChartPanel2()
    {
		return SensorchartPanel2;
    	
    }
    public JPanel getSensorChartPanel3()
    {
		return SensorchartPanel3;
    	
    }
    public JPanel getSensorChartPanel4()
    {
		return SensorchartPanel4;
    	
    }
    public JPanel getSensorChartPanel5()
    {
		return SensorchartPanel5;
    	
    }
    public JPanel getSensorChartPanel6()
    {
		return SensorchartPanel6;
    	
    }
    public JPanel getSensorChartPanel7()
    {
		return SensorchartPanel7;
    	
    }
    public JPanel getSensorChartPanel8()
    {
		return SensorchartPanel8;
    	
    }
    public JPanel getSensorChartPanel9()
    {
		return SensorchartPanel9;
    	
    }
    
    public JPanel getSensorChartPanel10()
    {
		return SensorchartPanel10;
    	
    }
    
    public JPanel getSensorChartPanel11()
    {
		return SensorchartPanel11;
    	
    }
    
    public JPanel getSensorChartPanel12()
    {
		return SensorchartPanel12;
    	
    }
    
    public JPanel getSensorChartPanel13()
    {
		return SensorchartPanel13;
    	
    }
    
    public JPanel getSensorChartPanel14()
    {
		return SensorchartPanel14;
    	
    }
    
    public JPanel getSensorChartPanel15()
    {
		return SensorchartPanel15;
    	
    }
    
    public JPanel getSensorChartPanel16()
    {
		return SensorchartPanel16;
    	
    }
    
    public JPanel getSensorChartPanel17()
    {
		return SensorchartPanel17;
    	
    }
    
    public JPanel getSensorChartPanel18()
    {
		return SensorchartPanel18;
    	
    }
    
    public JPanel getSensorChartPanel19()
    {
		return SensorchartPanel19;
    	
    }
    
    public void setAccelGraph()
    
    {
    	//Accelerations.clear();
    	//Accelerations.add(new Double(10.05));
    	//Accelerations.add(new Double(14.21));
    	//Accelerations.add(new Double(12.45));
    	//Accelerations.add(new Double(11.55));
    	//Accelerations.add(new Double(16.25));
    	Acceldataset= createDataset(Accelerations, Times);
    	Accelchart = createChart(Acceldataset, title + " Acceleration", "Acceleration (m/s^2)" ); 
    	 AccelchartPanel = new ChartPanel(Accelchart);
    	 
    	//MakeGraphs();
    }
    
   public void setJerkGraph()
    
    {
	   	
    	
    	Jerkdataset= createDataset(Jerks, Times);
    	Jerkchart= createChart(Jerkdataset, title + " Jerk", "Jerk (m/s^3)" );
    	 JerkchartPanel = new ChartPanel(Jerkchart);
    	 
    	MakeGraphs();
    }
   
   public void setAngleGraph()
   
   {

   	Angledataset= createDataset(Angles, Times);
   	Anglechart= createChart(Angledataset, title + " Angle", "Angle (degree)" );
   	AnglechartPanel = new ChartPanel(Anglechart);
   	 
   	//MakeGraphs();
   }
    
    public void setData(DataGroup InData){
    	Accelerations.clear();
    	Angles.clear();
    	Jerks.clear();
    	Pressures.clear();
    	Times.clear();
    	Accelerations.addAll(InData.getAccelerations());
    	Angles.addAll(InData.getAngles());
    	Jerks.addAll(InData.getJerks());
    	//System.out.println("InData Pressures");
    	//System.out.println(InData.getPressures());
    	Pressures.addAll(InData.getPressures());
    	Times.addAll(InData.getTimes());
    	//System.out.println("VARIABLE PRESSURE");
    	//System.out.println(Pressures);
    	
    }
    
    public void MakeGraphs(){

        //System.out.println("Pressures");
    	//System.out.println(Pressures);
    	Pressuredataset = new XYDataset[19];
    	Pressuredataset = createPressureDataset(Pressures, Times);
    	//System.out.println("PRESS ME");
    	//System.out.println(Pressures);
        
        JFreeChart Sensor1chart = createChart(Pressuredataset[0], title + " Sensor 1", "Force (N)");
        JFreeChart Sensor2chart = createChart(Pressuredataset[1], title + " Sensor 2", "Force (N)");
        JFreeChart Sensor3chart = createChart(Pressuredataset[2], title + " Sensor 3", "Force (N)");
        JFreeChart Sensor4chart = createChart(Pressuredataset[3], title + " Sensor 4", "Force (N)");
        JFreeChart Sensor5chart = createChart(Pressuredataset[4], title + " Sensor 5", "Force (N)");
        JFreeChart Sensor6chart = createChart(Pressuredataset[5], title + " Sensor 6", "Force (N)");
        JFreeChart Sensor7chart = createChart(Pressuredataset[6], title + " Sensor 7", "Force (N)");
        JFreeChart Sensor8chart = createChart(Pressuredataset[7], title + " Sensor 8", "Force (N)");
        JFreeChart Sensor9chart = createChart(Pressuredataset[8], title + " Sensor 9", "Force (N)");
        JFreeChart Sensor10chart = createChart(Pressuredataset[9], title + " Sensor 10", "Force (N)");
        JFreeChart Sensor11chart = createChart(Pressuredataset[10], title + " Sensor 11", "Force (N)");
        JFreeChart Sensor12chart = createChart(Pressuredataset[11], title + " Sensor 12", "Force (N)");
        JFreeChart Sensor13chart = createChart(Pressuredataset[12], title + " Sensor 13", "Force (N)");
        JFreeChart Sensor14chart = createChart(Pressuredataset[13], title + " Sensor 14", "Force (N)");
        JFreeChart Sensor15chart = createChart(Pressuredataset[14], title + " Sensor 15", "Force (N)");
        JFreeChart Sensor16chart = createChart(Pressuredataset[15], title + " Sensor 16", "Force (N)");
        JFreeChart Sensor17chart = createChart(Pressuredataset[16], title + " Sensor 17", "Force (N)");
        JFreeChart Sensor18chart = createChart(Pressuredataset[17], title + " Sensor 18", "Force (N)");
        JFreeChart Sensor19chart = createChart(Pressuredataset[18], title + " Sensor 19", "Force (N)");
        
        //JFreeChart Pressurechart= createPressureChart(Pressuredataset);
        //final XYDataset dataset = createDataset();
       // final JFreeChart chart = createChart(Acceldataset);
       
       
        
       SensorchartPanel1 = new ChartPanel(Sensor1chart);
        SensorchartPanel2 = new ChartPanel(Sensor2chart);
        SensorchartPanel3 = new ChartPanel(Sensor3chart);
        SensorchartPanel4 = new ChartPanel(Sensor4chart);
        SensorchartPanel5 = new ChartPanel(Sensor5chart);
        SensorchartPanel6 = new ChartPanel(Sensor6chart);
        SensorchartPanel7 = new ChartPanel(Sensor7chart);
        SensorchartPanel8 = new ChartPanel(Sensor8chart);
        SensorchartPanel9 = new ChartPanel(Sensor9chart);
        SensorchartPanel10 = new ChartPanel(Sensor10chart);
        SensorchartPanel11 = new ChartPanel(Sensor11chart);
        SensorchartPanel12 = new ChartPanel(Sensor12chart);
        SensorchartPanel13 = new ChartPanel(Sensor13chart);
        SensorchartPanel14 = new ChartPanel(Sensor14chart);
        SensorchartPanel15 = new ChartPanel(Sensor15chart);
        SensorchartPanel16 = new ChartPanel(Sensor16chart);
        SensorchartPanel17 = new ChartPanel(Sensor17chart);
        SensorchartPanel18 = new ChartPanel(Sensor18chart);
        SensorchartPanel19 = new ChartPanel(Sensor19chart);
        
       	Angledataset= createDataset(Angles, Times);
       	Anglechart= createChart(Angledataset, title + " Angle", "Angle (degree)" );
       	AnglechartPanel = new ChartPanel(Anglechart);
       	
    	Jerkdataset= createDataset(Jerks, Times);
    	Jerkchart= createChart(Jerkdataset, title + " Jerk", "Jerk (m/s^3)" );
    	 JerkchartPanel = new ChartPanel(Jerkchart);
       	 
     	Acceldataset= createDataset(Accelerations, Times);
     	Accelchart = createChart(Acceldataset, title + " Acceleration", "Acceleration (m/s^2)" ); 
     	 AccelchartPanel = new ChartPanel(Accelchart);
    	 
    }
    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
//Write add series to manioulate PRessure data sets for pressure. will need alot of them. also use remove series

}
 