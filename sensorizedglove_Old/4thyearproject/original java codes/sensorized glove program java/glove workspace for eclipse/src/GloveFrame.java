import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * The GloveFrame is the GUI class for this program. It controls all visual components
 * of the program and implements appropriate action listeners.
 */

public class GloveFrame extends JFrame {

	
	public GloveFrame() {
		initComponents();
	}

	private void initComponents() {

		jTabbedPane1 = new JTabbedPane();
		SavedDataPanel = new JPanel();
		GraphAnalysisTitle = new JLabel();
		LeftGraphPanel1 = new JPanel();
		LeftGraphPanel2 = new JPanel();
		LeftGraphPanel3 = new JPanel();
		LeftGraphPanel4 = new JPanel();
		LeftGraphPanel5 = new JPanel();
		LeftGraphPanel6 = new JPanel();
		LeftGraphPanel7 = new JPanel();
		LeftGraphPanel8 = new JPanel();
		LeftGraphPanel9 = new JPanel();
		LeftGraphPanel10 = new JPanel();
		LeftGraphPanel11 = new JPanel();
		LeftGraphPanel12 = new JPanel();
		LeftGraphPanel13 = new JPanel();
		LeftGraphPanel14 = new JPanel();
		LeftGraphPanel15 = new JPanel();
		LeftGraphPanel16 = new JPanel();
		LeftGraphPanel17 = new JPanel();
		LeftGraphPanel18 = new JPanel();
		LeftGraphPanel19 = new JPanel();
		LeftGraphPanel20 = new JPanel();
		LeftGraphPanel21 = new JPanel();
		LeftGraphPanel22 = new JPanel();

		RightGraphPanel1 = new JPanel();
		RightGraphPanel2 = new JPanel();
		RightGraphPanel3 = new JPanel();
		RightGraphPanel4 = new JPanel();
		RightGraphPanel5 = new JPanel();
		RightGraphPanel6 = new JPanel();
		RightGraphPanel7 = new JPanel();
		RightGraphPanel8 = new JPanel();
		RightGraphPanel9 = new JPanel();
		RightGraphPanel10 = new JPanel();
		RightGraphPanel11 = new JPanel();
		RightGraphPanel12 = new JPanel();
		RightGraphPanel13 = new JPanel();
		RightGraphPanel14 = new JPanel();
		RightGraphPanel15 = new JPanel();
		RightGraphPanel16 = new JPanel();
		RightGraphPanel17 = new JPanel();
		RightGraphPanel18 = new JPanel();
		RightGraphPanel19 = new JPanel();
		RightGraphPanel20 = new JPanel();
		RightGraphPanel21 = new JPanel();
		RightGraphPanel22 = new JPanel();

		Lcard1 = new JPanel();

		LeftGraphCards = new JPanel();
		RightGraphCards = new JPanel();
		GraphTimeLabel = new JLabel();
		GraphAccelButton = new JButton();
		GraphJerkButton = new JButton();
		GraphAngleButton = new JButton();
		lblGraphPressure = new JLabel();
		jScrollPane1 = new JScrollPane();
		GraphPressureList = new JList();
		LiveDataPanel = new JPanel();
		LeftHandPressurePanel = new JPanel();
		LHLayerPane = new JLayeredPane();
		LHImage = new JLabel();
		LeftHandPressureTablePane = new JScrollPane();
		LHPTable = new JTable();
		RHPPanel = new JPanel();
		RightHandPressureTablePane = new JScrollPane();
		RHPTable = new JTable();
		RHLayerPane = new JLayeredPane();
		RHImage = new JLabel();
		RHAPanel = new JPanel();
		RHAccelLabel = new JLabel();
		RHVelLabel = new JLabel();
		RHAngLabel = new JLabel();
		tfRightAccel = new JTextField();
		tfRightJerk = new JTextField();
		tfRightAngle = new JTextField();
		Western_Label = new JLabel();
		LHAPanel = new JPanel();
		lblLeftAccel = new JLabel();
		tfLeftAccel = new JTextField();
		lblLeftJerk = new JLabel();
		tfLeftJerk = new JTextField();
		tfLeftAngle = new JTextField();
		lblLeftAngle = new JLabel();
		W_E_Label = new JLabel();
		TabbedTitle = new JLabel();
		LeftHandTitle = new JLabel();
		RightHandTitle = new JLabel();
		StartButton = new JButton();
		StopButton = new JButton();
		jMenuBar1 = new JMenuBar();
		FileMenu = new JMenu();
		FileMenuSave = new JMenuItem();
		FileMenuLoad = new JMenuItem();
		FileMenuClose = new JMenuItem();
		EditMenu = new JMenu();
		HelpMenu = new JMenu();
		HelpMenuHelp = new JMenuItem();
		HelpMenuAbout = new JMenuItem();
		RightHandData = new DataGroup();
		LeftHandData = new DataGroup();
		LeftGraph = new GloveGraph("Left Hand", LeftHandData);
		RightGraph = new GloveGraph("Right Hand", RightHandData);
		LHSensorPanel = new JPanel();
		LSensor1 = new JLabel();
		LSensor2 = new JLabel();
		LSensor3 = new JLabel();
		LSensor4 = new JLabel();
		LSensor5 = new JLabel();
		LSensor6 = new JLabel();
		LSensor7 = new JLabel();
		LSensor8 = new JLabel();
		LSensor9 = new JLabel();
		LSensor10 = new JLabel();
		LSensor11 = new JLabel();
		LSensor12 = new JLabel();
		LSensor13 = new JLabel();
		LSensor14 = new JLabel();
		LSensor15 = new JLabel();
		LSensor16 = new JLabel();
		LSensor17 = new JLabel();
		LSensor18 = new JLabel();
		LSensor19 = new JLabel();
		RHSensorPanel = new JPanel();
		RSensor1 = new JLabel();
		RSensor2 = new JLabel();
		RSensor3 = new JLabel();
		RSensor4 = new JLabel();
		RSensor5 = new JLabel();
		RSensor6 = new JLabel();
		RSensor7 = new JLabel();
		RSensor8 = new JLabel();
		RSensor9 = new JLabel();
		RSensor10 = new JLabel();
		RSensor11 = new JLabel();
		RSensor12 = new JLabel();
		RSensor13 = new JLabel();
		RSensor14 = new JLabel();
		RSensor15 = new JLabel();
		RSensor16 = new JLabel();
		RSensor17 = new JLabel();
		RSensor18 = new JLabel();
		RSensor19 = new JLabel();
		LeftCardLayout = new CardLayout();
		RightCardLayout = new CardLayout();
		LeftAccel = new Double(0.0);
		df2.format(LeftAccel);
		LeftJerk = new Double(0.0);
		df2.format(LeftJerk);
		LeftAngle = new Double(0.0);
		df2.format(LeftAngle);
		LeftPressures = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		LeftPressures = formatDouble(LeftPressures, df);
		RightAccel = new Double(0.0);
		df2.format(RightAccel);
		RightJerk = new Double(0.0);
		df2.format(RightJerk);
		RightAngle = new Double(0.0);
		df2.format(RightAngle);
		RightPressures = new Double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		RightPressures = formatDouble(RightPressures, df);
		Writable = false;
		TimeArray = new ArrayList<Double>();
		RAccelArray = new ArrayList<Double>();
		RJerkArray = new ArrayList<Double>();
		RAngleArray = new ArrayList<Double>();
		RSensor1Array = new ArrayList<Double>();
		RSensor2Array = new ArrayList<Double>();
		RSensor3Array = new ArrayList<Double>();
		RSensor4Array = new ArrayList<Double>();
		RSensor5Array = new ArrayList<Double>();
		RSensor6Array = new ArrayList<Double>();
		RSensor7Array = new ArrayList<Double>();
		RSensor8Array = new ArrayList<Double>();
		RSensor9Array = new ArrayList<Double>();
		RSensor10Array = new ArrayList<Double>();
		RSensor11Array = new ArrayList<Double>();
		RSensor12Array = new ArrayList<Double>();
		RSensor13Array = new ArrayList<Double>();
		RSensor14Array = new ArrayList<Double>();
		RSensor15Array = new ArrayList<Double>();
		RSensor16Array = new ArrayList<Double>();
		RSensor17Array = new ArrayList<Double>();
		RSensor18Array = new ArrayList<Double>();
		RSensor19Array = new ArrayList<Double>();
		RightHandPressures = new ArrayList<ArrayList<Double>>();

		LAccelArray = new ArrayList<Double>();
		LJerkArray = new ArrayList<Double>();
		LAngleArray = new ArrayList<Double>();
		LSensor1Array = new ArrayList<Double>();
		LSensor2Array = new ArrayList<Double>();
		LSensor3Array = new ArrayList<Double>();
		LSensor4Array = new ArrayList<Double>();
		LSensor5Array = new ArrayList<Double>();
		LSensor6Array = new ArrayList<Double>();
		LSensor7Array = new ArrayList<Double>();
		LSensor8Array = new ArrayList<Double>();
		LSensor9Array = new ArrayList<Double>();
		LSensor10Array = new ArrayList<Double>();
		LSensor11Array = new ArrayList<Double>();
		LSensor12Array = new ArrayList<Double>();
		LSensor13Array = new ArrayList<Double>();
		LSensor14Array = new ArrayList<Double>();
		LSensor15Array = new ArrayList<Double>();
		LSensor16Array = new ArrayList<Double>();
		LSensor17Array = new ArrayList<Double>();
		LSensor18Array = new ArrayList<Double>();
		LSensor19Array = new ArrayList<Double>();
		LeftHandPressures = new ArrayList<ArrayList<Double>>();
		doneonce = false;

		Rcard1 = new JPanel();
		Rcard2 = new JPanel();
		Rcard3 = new JPanel();
		Rcard4 = new JPanel();
		Rcard5 = new JPanel();
		Rcard6 = new JPanel();
		Rcard7 = new JPanel();
		Rcard8 = new JPanel();
		Rcard9 = new JPanel();
		Rcard10 = new JPanel();
		Rcard11 = new JPanel();
		Rcard12 = new JPanel();
		Rcard13 = new JPanel();
		Rcard14 = new JPanel();
		Rcard15 = new JPanel();
		Rcard16 = new JPanel();
		Rcard17 = new JPanel();
		Rcard18 = new JPanel();
		Rcard19 = new JPanel();
		Rcard20 = new JPanel();
		Rcard21 = new JPanel();
		Rcard22 = new JPanel();

		Lcard1 = new JPanel();
		Lcard2 = new JPanel();
		Lcard3 = new JPanel();
		Lcard4 = new JPanel();
		Lcard5 = new JPanel();
		Lcard6 = new JPanel();
		Lcard7 = new JPanel();
		Lcard8 = new JPanel();
		Lcard9 = new JPanel();
		Lcard10 = new JPanel();
		Lcard11 = new JPanel();
		Lcard12 = new JPanel();
		Lcard13 = new JPanel();
		Lcard14 = new JPanel();
		Lcard15 = new JPanel();
		Lcard16 = new JPanel();
		Lcard17 = new JPanel();
		Lcard18 = new JPanel();
		Lcard19 = new JPanel();
		Lcard20 = new JPanel();
		Lcard21 = new JPanel();
		Lcard22 = new JPanel();
		setTime();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		jTabbedPane1.setBackground(new java.awt.Color(102, 102, 102));

		SavedDataPanel.setBackground(new java.awt.Color(153, 0, 255));

		GraphAnalysisTitle.setFont(new java.awt.Font("Tahoma", 1, 48)); 
		GraphAnalysisTitle.setForeground(new java.awt.Color(204, 204, 204));
		GraphAnalysisTitle.setText("Graph Analysis");

		LeftGraphPanel1.setBackground(new java.awt.Color(255, 255, 255));
		LeftGraphPanel2.setBackground(new java.awt.Color(255, 255, 255));
		LeftGraphPanel3.setBackground(new java.awt.Color(255, 255, 255));

		GraphTimeLabel.setFont(new java.awt.Font("Tahoma", 1, 36)); 
		GraphTimeLabel.setForeground(new java.awt.Color(204, 204, 204));
		GraphTimeLabel.setText("Time");

		GraphAccelButton.setFont(new java.awt.Font("Tahoma", 0, 24)); 
		GraphAccelButton.setText("Acceleration");
		GraphAccelButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GraphAccelButtonActionPerformed(evt);
			}
		});

		GraphJerkButton.setFont(new Font("Tahoma", 0, 24)); 
		GraphJerkButton.setText("Jerk");

		GraphJerkButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GraphJerkButtonActionPerformed(evt);
			}
		});

		GraphAngleButton.setFont(new Font("Tahoma", 0, 24)); 
		GraphAngleButton.setText("Angle");
		GraphAngleButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				GraphAngleButtonActionPerformed(evt);
			}
		});

		lblGraphPressure.setFont(new Font("Tahoma", 1, 48)); 
		lblGraphPressure.setText("Pressures");
		lblGraphPressure.setForeground(new java.awt.Color(204, 204, 204));

		GraphPressureList.setFont(new Font("Tahoma", 0, 18)); 
		GraphPressureList.setModel(new AbstractListModel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] strings = { "Index Top", "Index Middle", "Index Bottom",
					"Long Top", "Long Middle", "Long Bottom", "Ring Top",
					"Ring Middle", "Ring Bottom", "Small Top", "Small Middle",
					"Small Bottom", "Thumb Top", "Thumb Bottom",
					"Lateral Distal P.C.", "Medial Distal  P.C",
					"Medial Proximal  P.C", "Hypothenar Eminence",
					"Palmaris Brevis" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});

		ListSelectionListener s1 = new ListSelectionListener() {


			public void valueChanged(ListSelectionEvent e) {
		
				int selindex = GraphPressureList.getSelectedIndex() + 1;

				switch (selindex) {

				case 1:
					LeftCardLayout.show(LeftGraphCards, "Sensor1");
					RightCardLayout.show(RightGraphCards, "Sensor1");
					break;
				case 2:
					LeftCardLayout.show(LeftGraphCards, "Sensor2");
					RightCardLayout.show(RightGraphCards, "Sensor2");
					break;
				case 3:
					LeftCardLayout.show(LeftGraphCards, "Sensor3");
					RightCardLayout.show(RightGraphCards, "Sensor3");
					break;
				case 4:
					LeftCardLayout.show(LeftGraphCards, "Sensor4");
					RightCardLayout.show(RightGraphCards, "Sensor4");
					break;
				case 5:
					LeftCardLayout.show(LeftGraphCards, "Sensor5");
					RightCardLayout.show(RightGraphCards, "Sensor5");
					break;
				case 6:
					LeftCardLayout.show(LeftGraphCards, "Sensor6");
					RightCardLayout.show(RightGraphCards, "Sensor6");
					break;
				case 7:
					LeftCardLayout.show(LeftGraphCards, "Sensor7");
					RightCardLayout.show(RightGraphCards, "Sensor7");
					break;
				case 8:
					LeftCardLayout.show(LeftGraphCards, "Sensor8");
					RightCardLayout.show(RightGraphCards, "Sensor8");
					break;
				case 9:
					LeftCardLayout.show(LeftGraphCards, "Sensor9");
					RightCardLayout.show(RightGraphCards, "Sensor9");
					break;
				case 10:
					LeftCardLayout.show(LeftGraphCards, "Sensor10");
					RightCardLayout.show(RightGraphCards, "Sensor10");
					break;
				case 11:
					LeftCardLayout.show(LeftGraphCards, "Sensor11");
					RightCardLayout.show(RightGraphCards, "Sensor11");
					break;
				case 12:
					LeftCardLayout.show(LeftGraphCards, "Sensor12");
					RightCardLayout.show(RightGraphCards, "Sensor12");
					break;
				case 13:
					LeftCardLayout.show(LeftGraphCards, "Sensor13");
					RightCardLayout.show(RightGraphCards, "Sensor13");
					break;
				case 14:
					LeftCardLayout.show(LeftGraphCards, "Sensor14");
					RightCardLayout.show(RightGraphCards, "Sensor14");
					break;
				case 15:
					LeftCardLayout.show(LeftGraphCards, "Sensor15");
					RightCardLayout.show(RightGraphCards, "Sensor15");
					break;
				case 16:
					LeftCardLayout.show(LeftGraphCards, "Sensor16");
					RightCardLayout.show(RightGraphCards, "Sensor16");
					break;
				case 17:
					LeftCardLayout.show(LeftGraphCards, "Sensor17");
					RightCardLayout.show(RightGraphCards, "Sensor17");
					break;
				case 18:
					LeftCardLayout.show(LeftGraphCards, "Sensor18");
					RightCardLayout.show(RightGraphCards, "Sensor18");
					break;
				case 19:
					LeftCardLayout.show(LeftGraphCards, "Sensor19");
					RightCardLayout.show(RightGraphCards, "Sensor19");
					break;

				}

			}

		};

		GraphPressureList.addListSelectionListener(s1);

		jScrollPane1.setViewportView(GraphPressureList);

		RightGraphPanel1.setBackground(new java.awt.Color(255, 255, 255));
		RightGraphPanel2.setBackground(new java.awt.Color(255, 255, 255));
		RightGraphPanel3.setBackground(new java.awt.Color(255, 255, 255));

		setCards();

		SavedDataPanelLayout = new GroupLayout(SavedDataPanel);
		SavedDataPanel.setLayout(SavedDataPanelLayout);
		SavedDataPanelLayout
				.setHorizontalGroup(SavedDataPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								SavedDataPanelLayout
										.createSequentialGroup()
										.addGap(61, 61, 61)
										.addGroup(
												SavedDataPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																GraphJerkButton,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																GraphAccelButton,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																GraphAngleButton,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblGraphPressure,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jScrollPane1,
																GroupLayout.DEFAULT_SIZE,
																194,
																Short.MAX_VALUE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												SavedDataPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																RightGraphCards,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																LeftGraphCards,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(94, 94, 94))
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								SavedDataPanelLayout.createSequentialGroup()
										.addContainerGap(649, Short.MAX_VALUE)
										.addComponent(GraphAnalysisTitle)
										.addGap(642, 642, 642)));
		SavedDataPanelLayout.setVerticalGroup(SavedDataPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						SavedDataPanelLayout
								.createSequentialGroup()
								.addGap(10, 10, 10)
								.addComponent(GraphAnalysisTitle)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(LeftGraphCards,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10)
								.addComponent(RightGraphCards,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(50, 50, 50))
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						SavedDataPanelLayout
								.createSequentialGroup()
								.addGap(125, 125, 125)
								.addComponent(GraphAccelButton)
								.addGap(45, 45, 45)
								.addComponent(GraphJerkButton)
								.addGap(39, 39, 39)
								.addComponent(GraphAngleButton)
								.addGap(38, 38, 38)
								.addComponent(lblGraphPressure)
								.addGap(18, 18, 18)
								.addComponent(jScrollPane1,
										GroupLayout.PREFERRED_SIZE, 470,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(90, Short.MAX_VALUE)));

		LiveDataPanel.setBackground(new Color(153, 0, 255));

		LeftHandPressurePanel.setBackground(new Color(204, 204, 204));

		LHImage.setIcon(new ImageIcon(getClass().getResource(
				"/Images/Left Palm.jpg"))); 
		LHImage.setBounds(20, 20, 300, 300);

		LHSensorPanel.setLayout(null);
		LHSensorPanel.setBounds(10, 10, 400, 300);
		LHSensorPanel.setBackground(Color.RED);
		LHSensorPanel.setOpaque(false);

		LSensor1.setSize(15, 15);
		LSensor1.setLocation(68, 45);
		LSensor1.setBackground(Color.RED);
		LSensor1.setOpaque(true);
		LHSensorPanel.add(LSensor1);

		LSensor2.setSize(15, 15);
		LSensor2.setLocation(77, 75);
		LSensor2.setBackground(Color.RED);
		LSensor2.setOpaque(true);
		LHSensorPanel.add(LSensor2);

		LSensor3.setSize(15, 15);
		LSensor3.setLocation(87, 107);
		LSensor3.setBackground(Color.RED);
		LSensor3.setOpaque(true);
		LHSensorPanel.add(LSensor3);

		LSensor4.setSize(15, 15);
		LSensor4.setLocation(124, 30);
		LSensor4.setBackground(Color.RED);
		LSensor4.setOpaque(true);
		LHSensorPanel.add(LSensor4);

		LSensor5.setSize(15, 15);
		LSensor5.setLocation(122, 62);
		LSensor5.setBackground(Color.RED);
		LSensor5.setOpaque(true);
		LHSensorPanel.add(LSensor5);

		LSensor6.setSize(15, 15);
		LSensor6.setLocation(123, 94);
		LSensor6.setBackground(Color.RED);
		LSensor6.setOpaque(true);
		LHSensorPanel.add(LSensor6);

		LSensor7.setSize(15, 15);
		LSensor7.setLocation(168, 38);
		LSensor7.setBackground(Color.RED);
		LSensor7.setOpaque(true);
		LHSensorPanel.add(LSensor7);

		LSensor8.setSize(15, 15);
		LSensor8.setLocation(163, 72);
		LSensor8.setBackground(Color.RED);
		LSensor8.setOpaque(true);
		LHSensorPanel.add(LSensor8);

		LSensor9.setSize(15, 15);
		LSensor9.setLocation(157, 100);
		LSensor9.setBackground(Color.RED);
		LSensor9.setOpaque(true);
		LHSensorPanel.add(LSensor9);

		LSensor10.setSize(13, 13);
		LSensor10.setLocation(208, 82);
		LSensor10.setBackground(Color.RED);
		LSensor10.setOpaque(true);
		LHSensorPanel.add(LSensor10);

		LSensor11.setSize(13, 13);
		LSensor11.setLocation(197, 104);
		LSensor11.setBackground(Color.RED);
		LSensor11.setOpaque(true);
		LHSensorPanel.add(LSensor11);

		LSensor12.setSize(13, 13);
		LSensor12.setLocation(186, 125);
		LSensor12.setBackground(Color.RED);
		LSensor12.setOpaque(true);
		LHSensorPanel.add(LSensor12);

		LSensor13.setSize(15, 15);
		LSensor13.setLocation(25, 145);
		LSensor13.setBackground(Color.RED);
		LSensor13.setOpaque(true);
		LHSensorPanel.add(LSensor13);

		LSensor14.setSize(15, 15);
		LSensor14.setLocation(50, 168);
		LSensor14.setBackground(Color.RED);
		LSensor14.setOpaque(true);
		LHSensorPanel.add(LSensor14);

		LSensor15.setSize(20, 20);
		LSensor15.setLocation(95, 135);
		LSensor15.setBackground(Color.RED);
		LSensor15.setOpaque(true);
		LHSensorPanel.add(LSensor15);

		LSensor16.setSize(20, 20);
		LSensor16.setLocation(165, 148);
		LSensor16.setBackground(Color.RED);
		LSensor16.setOpaque(true);
		LHSensorPanel.add(LSensor16);

		LSensor17.setSize(20, 20);
		LSensor17.setLocation(165, 180);
		LSensor17.setBackground(Color.RED);
		LSensor17.setOpaque(true);
		LHSensorPanel.add(LSensor17);

		LSensor18.setSize(20, 20);
		LSensor18.setLocation(160, 212);
		LSensor18.setBackground(Color.RED);
		LSensor18.setOpaque(true);
		LHSensorPanel.add(LSensor18);

		LSensor19.setSize(20, 20);
		LSensor19.setLocation(95, 200);
		LSensor19.setBackground(Color.RED);
		LSensor19.setOpaque(true);
		LHSensorPanel.add(LSensor19);

		Dimension LPSize = new Dimension(300, 300);

		LHLayerPane.setOpaque(true);
		LHLayerPane.setPreferredSize(LPSize);
		LHLayerPane.add(LHImage, JLayeredPane.DEFAULT_LAYER);
		LHLayerPane.add(LHSensorPanel, JLayeredPane.PALETTE_LAYER);

		LHPTable.setFont(new Font("Tahoma", 0, 12)); 
		LHPTable.setModel(new DefaultTableModel(new Object[][] {

		{ "Index Top", LeftPressures[0] },
				{ "Index Middle", LeftPressures[1] },
				{ "Index Bottom", LeftPressures[2] },
				{ "Long Top", LeftPressures[3] },
				{ "Long Middle", LeftPressures[4] },
				{ "Long Bottom", LeftPressures[5] },
				{ "Ring Top", LeftPressures[6] },
				{ "Ring Middle", LeftPressures[7] },
				{ "Ring Bottom", LeftPressures[8] },
				{ "Short Top", LeftPressures[9] },
				{ "Short Middle", LeftPressures[10] },
				{ "Short Bottom", LeftPressures[11] },
				{ "Thumb Top", LeftPressures[12] },
				{ "Thumb Bottom", LeftPressures[13] },
				{ "Medial Distal P.C.", LeftPressures[14] },
				{ "Lateral Distal P.C.", LeftPressures[15] },
				{ "Palm Middle", LeftPressures[16] },
				{ "Hypothenal Eminence", LeftPressures[17] },
				{ "Palmeris Brevis", LeftPressures[18] }, }, new String[] {
				"Sensor", "Force (N)" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.Double.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});

		LHPTable.setEnabled(false);
		LHPTable.getTableHeader().setReorderingAllowed(false);
		LeftHandPressureTablePane.setViewportView(LHPTable);

		GroupLayout LeftHandPressurePanelLayout = new GroupLayout(
				LeftHandPressurePanel);
		LeftHandPressurePanel.setLayout(LeftHandPressurePanelLayout);
		LeftHandPressurePanelLayout
				.setHorizontalGroup(LeftHandPressurePanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								LeftHandPressurePanelLayout
										.createSequentialGroup()
										.addContainerGap(28, Short.MAX_VALUE)
										.addComponent(LHLayerPane,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												LeftHandPressureTablePane,
												GroupLayout.PREFERRED_SIZE,
												271, GroupLayout.PREFERRED_SIZE)));
		LeftHandPressurePanelLayout
				.setVerticalGroup(LeftHandPressurePanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(LHLayerPane)
						.addGroup(
								LeftHandPressurePanelLayout
										.createSequentialGroup()
										.addComponent(
												LeftHandPressureTablePane,
												GroupLayout.PREFERRED_SIZE,
												333, GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)));

		RHPPanel.setBackground(new Color(204, 204, 204));

		RHPTable.setFont(new Font("Tahoma", 0, 12)); 
		RHPTable.setModel(new DefaultTableModel(new Object[][] {
				{ "Index Top", RightPressures[0] },
				{ "Index Middle", RightPressures[1] },
				{ "Index Bottom", RightPressures[2] },
				{ "Long Top", RightPressures[3] },
				{ "Long Middle", RightPressures[4] },
				{ "Long Bottom", RightPressures[5] },
				{ "Ring Top", RightPressures[6] },
				{ "Ring Middle", RightPressures[7] },
				{ "Ring Bottom", RightPressures[8] },
				{ "Short Top", RightPressures[9] },
				{ "Short Middle", RightPressures[10] },
				{ "Short Bottom", RightPressures[11] },
				{ "Thumb Top", RightPressures[12] },
				{ "Thumb Bottom", RightPressures[13] },
				{ "Medial Distal P.C.", RightPressures[14] },
				{ "Lateral Distal P.C.", RightPressures[15] },
				{ "Palm Middle", RightPressures[16] },
				{ "Hypothenal Eminence", RightPressures[17] },
				{ "Palmeris Brevis", RightPressures[18] }, }, new String[] {
				"Sensor", "Force (N)" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.Double.class };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
		});
		RHPTable.setEnabled(false);
		RHPTable.getTableHeader().setReorderingAllowed(false);
		RightHandPressureTablePane.setViewportView(RHPTable);

		RHLayerPane.setBackground(new Color(204, 204, 204));

		RHImage.setIcon(new ImageIcon(getClass().getResource(
				"/Images/Right Palm.jpg"))); 

		RHImage.setBounds(20, 20, 300, 300);

		RHSensorPanel.setLayout(null);
		RHSensorPanel.setBounds(10, 10, 400, 300);
		RHSensorPanel.setBackground(Color.RED);
		RHSensorPanel.setOpaque(false);

		RSensor1.setSize(15, 15);
		RSensor1.setLocation(156, 45);
		RSensor1.setBackground(Color.RED);
		RSensor1.setOpaque(true);
		RHSensorPanel.add(RSensor1);

		RSensor2.setSize(15, 15);
		RSensor2.setLocation(148, 75);
		RSensor2.setBackground(Color.RED);
		RSensor2.setOpaque(true);
		RHSensorPanel.add(RSensor2);

		RSensor3.setSize(15, 15);
		RSensor3.setLocation(138, 105);
		RSensor3.setBackground(Color.RED);
		RSensor3.setOpaque(true);
		RHSensorPanel.add(RSensor3);

		RSensor4.setSize(15, 15);
		RSensor4.setLocation(101, 30);
		RSensor4.setBackground(Color.RED);
		RSensor4.setOpaque(true);
		RHSensorPanel.add(RSensor4);

		RSensor5.setSize(15, 15);
		RSensor5.setLocation(102, 62);
		RSensor5.setBackground(Color.RED);
		RSensor5.setOpaque(true);
		RHSensorPanel.add(RSensor5);

		RSensor6.setSize(15, 15);
		RSensor6.setLocation(101, 94);
		RSensor6.setBackground(Color.RED);
		RSensor6.setOpaque(true);
		RHSensorPanel.add(RSensor6);

		RSensor7.setSize(15, 15);
		RSensor7.setLocation(57, 38);
		RSensor7.setBackground(Color.RED);
		RSensor7.setOpaque(true);
		RHSensorPanel.add(RSensor7);

		RSensor8.setSize(15, 15);
		RSensor8.setLocation(62, 72);
		RSensor8.setBackground(Color.RED);
		RSensor8.setOpaque(true);
		RHSensorPanel.add(RSensor8);

		RSensor9.setSize(15, 15);
		RSensor9.setLocation(67, 100);
		RSensor9.setBackground(Color.RED);
		RSensor9.setOpaque(true);
		RHSensorPanel.add(RSensor9);

		RSensor10.setSize(13, 13);
		RSensor10.setLocation(21, 81);
		RSensor10.setBackground(Color.RED);
		RSensor10.setOpaque(true);
		RHSensorPanel.add(RSensor10);

		RSensor11.setSize(13, 13);
		RSensor11.setLocation(29, 104);
		RSensor11.setBackground(Color.RED);
		RSensor11.setOpaque(true);
		RHSensorPanel.add(RSensor11);

		RSensor12.setSize(13, 13);
		RSensor12.setLocation(41, 125);
		RSensor12.setBackground(Color.RED);
		RSensor12.setOpaque(true);
		RHSensorPanel.add(RSensor12);

		RSensor13.setSize(15, 15);
		RSensor13.setLocation(199, 145);
		RSensor13.setBackground(Color.RED);
		RSensor13.setOpaque(true);
		RHSensorPanel.add(RSensor13);

		RSensor14.setSize(15, 15);
		RSensor14.setLocation(174, 168);
		RSensor14.setBackground(Color.RED);
		RSensor14.setOpaque(true);
		RHSensorPanel.add(RSensor14);

		RSensor15.setSize(20, 20);
		RSensor15.setLocation(125, 135);
		RSensor15.setBackground(Color.RED);
		RSensor15.setOpaque(true);
		RHSensorPanel.add(RSensor15);

		RSensor16.setSize(20, 20);
		RSensor16.setLocation(55, 148);
		RSensor16.setBackground(Color.RED);
		RSensor16.setOpaque(true);
		RHSensorPanel.add(RSensor16);

		RSensor17.setSize(20, 20);
		RSensor17.setLocation(55, 180);
		RSensor17.setBackground(Color.RED);
		RSensor17.setOpaque(true);
		RHSensorPanel.add(RSensor17);

		RSensor18.setSize(20, 20);
		RSensor18.setLocation(60, 212);
		RSensor18.setBackground(Color.RED);
		RSensor18.setOpaque(true);
		RHSensorPanel.add(RSensor18);

		RSensor19.setSize(20, 20);
		RSensor19.setLocation(125, 200);
		RSensor19.setBackground(Color.RED);
		RSensor19.setOpaque(true);
		RHSensorPanel.add(RSensor19);

		Dimension RPSize = new Dimension(300, 300);
		RHLayerPane.setOpaque(true);
		RHLayerPane.setPreferredSize(RPSize);
		RHLayerPane.add(RHImage, JLayeredPane.DEFAULT_LAYER);
		RHLayerPane.add(RHSensorPanel, JLayeredPane.PALETTE_LAYER);

		GroupLayout RHPPanelLayout = new GroupLayout(RHPPanel);
		RHPPanel.setLayout(RHPPanelLayout);
		RHPPanelLayout.setHorizontalGroup(RHPPanelLayout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						GroupLayout.Alignment.TRAILING,
						RHPPanelLayout
								.createSequentialGroup()
								.addContainerGap(28, Short.MAX_VALUE)
								.addComponent(RHLayerPane,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										22, Short.MAX_VALUE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(RightHandPressureTablePane,
										GroupLayout.PREFERRED_SIZE, 271,
										GroupLayout.PREFERRED_SIZE)));
		RHPPanelLayout.setVerticalGroup(RHPPanelLayout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						RHPPanelLayout
								.createSequentialGroup()
								.addContainerGap(10, 10)
								.addComponent(RHLayerPane,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGroup(
						RHPPanelLayout
								.createSequentialGroup()
								.addComponent(RightHandPressureTablePane,
										GroupLayout.PREFERRED_SIZE, 333,
										GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)));

		RHAPanel.setBackground(new Color(204, 204, 204));

		RHAccelLabel.setFont(new Font("Tahoma", 0, 18)); 
		RHAccelLabel.setText("Acceleration (m/s^2)");

		RHVelLabel.setFont(new Font("Tahoma", 0, 18)); 
		RHVelLabel.setText("Jerk (m/s^3)");

		RHAngLabel.setFont(new Font("Tahoma", 0, 18)); 
		RHAngLabel.setText("Angle (deg)");

		tfRightAccel.setFont(new Font("Tahoma", 0, 18)); 
		tfRightAccel.setText(Double.toString(RightAccel));
		tfRightAccel.setEditable(false);

		tfRightJerk.setFont(new Font("Tahoma", 0, 18)); 
		tfRightJerk.setText(Double.toString(RightJerk));
		tfRightJerk.setEditable(false);

		tfRightAngle.setFont(new Font("Tahoma", 0, 18)); 
		tfRightAngle.setText(Double.toString(RightAngle));
		tfRightAngle.setEditable(false);

		ImageIcon WesternImage = new ImageIcon(
				"C:\\Users\\Tim\\workspace\\Glove\\src\\Images\\Western_Logo.jpg");
		Western_Label.setIcon(WesternImage);

		GroupLayout RHAPanelLayout = new GroupLayout(RHAPanel);
		RHAPanel.setLayout(RHAPanelLayout);
		RHAPanelLayout
				.setHorizontalGroup(RHAPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								RHAPanelLayout
										.createSequentialGroup()
										.addGap(35, 35, 35)
										.addComponent(Western_Label)
										.addGap(38, 38, 38)
										.addGroup(
												RHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																RHVelLabel,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																RHAngLabel,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																RHAccelLabel,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												RHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																tfRightAccel)
														.addComponent(
																tfRightAngle,
																GroupLayout.DEFAULT_SIZE,
																97,
																Short.MAX_VALUE)
														.addComponent(
																tfRightJerk))
										.addContainerGap()));
		RHAPanelLayout
				.setVerticalGroup(RHAPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								RHAPanelLayout
										.createSequentialGroup()
										.addGroup(
												RHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																RHAPanelLayout
																		.createSequentialGroup()
																		.addGap(30,
																				30,
																				30)
																		.addGroup(
																				RHAPanelLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								RHAccelLabel,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								tfRightAccel,
																								GroupLayout.DEFAULT_SIZE,
																								42,
																								Short.MAX_VALUE))
																		.addPreferredGap(
																				LayoutStyle.ComponentPlacement.UNRELATED)
																		.addGroup(
																				RHAPanelLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								RHVelLabel,
																								GroupLayout.PREFERRED_SIZE,
																								47,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								tfRightJerk,
																								GroupLayout.PREFERRED_SIZE,
																								47,
																								GroupLayout.PREFERRED_SIZE))
																		.addGap(18,
																				18,
																				18)
																		.addGroup(
																				RHAPanelLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								RHAngLabel,
																								GroupLayout.PREFERRED_SIZE,
																								47,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								tfRightAngle,
																								GroupLayout.PREFERRED_SIZE,
																								47,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																RHAPanelLayout
																		.createSequentialGroup()
																		.addGap(35,
																				35,
																				35)
																		.addComponent(
																				Western_Label)))
										.addContainerGap(49, Short.MAX_VALUE)));

		LHAPanel.setBackground(new Color(204, 204, 204));

		lblLeftAccel.setFont(new Font("Tahoma", 0, 18)); 
		lblLeftAccel.setText("Acceleration (m/s^2)");

		tfLeftAccel.setFont(new Font("Tahoma", 0, 18)); 
		tfLeftAccel.setText(Double.toString(LeftAccel));
		tfLeftAccel.setEditable(false);

		lblLeftJerk.setFont(new Font("Tahoma", 0, 18)); 
		lblLeftJerk.setText("Jerk (m/s^3)");

		tfLeftJerk.setFont(new Font("Tahoma", 0, 18)); 
		tfLeftJerk.setText(Double.toString(LeftJerk));
		tfLeftJerk.setEditable(false);

		tfLeftAngle.setFont(new Font("Tahoma", 0, 18));
		tfLeftAngle.setText(Double.toString(LeftAngle));
		tfLeftAngle.setEditable(false);

		lblLeftAngle.setFont(new Font("Tahoma", 0, 18)); 
		lblLeftAngle.setText("Angle (deg)");

		ImageIcon WENGImage = new ImageIcon(
				"C:\\Users\\Tim\\workspace\\Glove\\src\\Images\\W_E_Logo.jpg");
		W_E_Label.setIcon(WENGImage); 

		GroupLayout LHAPanelLayout = new GroupLayout(LHAPanel);
		LHAPanel.setLayout(LHAPanelLayout);
		LHAPanelLayout
				.setHorizontalGroup(LHAPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								LHAPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(W_E_Label,
												GroupLayout.PREFERRED_SIZE,
												259, GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												LHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																lblLeftJerk,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblLeftAngle,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lblLeftAccel,
																GroupLayout.Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												LHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																tfLeftAngle)
														.addComponent(
																tfLeftAccel,
																GroupLayout.DEFAULT_SIZE,
																97,
																Short.MAX_VALUE)
														.addComponent(
																tfLeftJerk))
										.addContainerGap()));
		LHAPanelLayout
				.setVerticalGroup(LHAPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								LHAPanelLayout
										.createSequentialGroup()
										.addGap(31, 31, 31)
										.addGroup(
												LHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																tfLeftAccel,
																GroupLayout.PREFERRED_SIZE,
																47,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblLeftAccel))
										.addGap(18, 18, 18)
										.addGroup(
												LHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																tfLeftJerk,
																GroupLayout.PREFERRED_SIZE,
																47,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																lblLeftJerk,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(11, 11, 11)
										.addGroup(
												LHAPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																lblLeftAngle,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																tfLeftAngle,
																GroupLayout.DEFAULT_SIZE,
																47,
																Short.MAX_VALUE))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
						.addGroup(
								LHAPanelLayout
										.createSequentialGroup()
										.addGap(59, 59, 59)
										.addComponent(W_E_Label)
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		TabbedTitle.setFont(new Font("Tahoma", 1, 48)); 
		TabbedTitle.setForeground(new Color(204, 204, 204));
		TabbedTitle.setText("Training Glove");

		LeftHandTitle.setFont(new Font("Tahoma", 1, 36)); 
		LeftHandTitle.setForeground(new Color(204, 204, 204));
		LeftHandTitle.setText("Left Hand");

		RightHandTitle.setFont(new Font("Tahoma", 1, 36)); 
		RightHandTitle.setForeground(new Color(204, 204, 204));
		RightHandTitle.setText("Right Hand");

		StartButton.setFont(new Font("Tahoma", 0, 24)); 
		StartButton.setText("Start Recording");
		StartButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StartButtonActionPerformed(evt);
			}
		});

		StopButton.setFont(new Font("Tahoma", 0, 24)); 
		StopButton.setText("Stop Recording");
		StopButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				StopButtonActionPerformed(evt);
			}
		});

		GroupLayout LiveDataPanelLayout = new GroupLayout(LiveDataPanel);
		LiveDataPanel.setLayout(LiveDataPanelLayout);
		LiveDataPanelLayout
				.setHorizontalGroup(LiveDataPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								LiveDataPanelLayout
										.createSequentialGroup()
										.addGap(337, 337, 337)
										.addComponent(LeftHandTitle)
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED,
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(RightHandTitle)
										.addGap(339, 339, 339))
						.addGroup(
								LiveDataPanelLayout
										.createSequentialGroup()
										.addGroup(
												LiveDataPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addGroup(
																LiveDataPanelLayout
																		.createSequentialGroup()
																		.addGap(146,
																				146,
																				146)
																		.addGroup(
																				LiveDataPanelLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.TRAILING,
																								false)
																						.addComponent(
																								LeftHandPressurePanel,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								StartButton,
																								GroupLayout.PREFERRED_SIZE,
																								252,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								LHAPanel,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))
																		.addGap(145,
																				145,
																				145)
																		.addGroup(
																				LiveDataPanelLayout
																						.createParallelGroup(
																								GroupLayout.Alignment.LEADING,
																								false)
																						.addComponent(
																								StopButton,
																								GroupLayout.PREFERRED_SIZE,
																								252,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								RHAPanel,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)
																						.addComponent(
																								RHPPanel,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE)))
														.addGroup(
																LiveDataPanelLayout
																		.createSequentialGroup()
																		.addGap(635,
																				635,
																				635)
																		.addComponent(
																				TabbedTitle)))
										.addContainerGap(201, Short.MAX_VALUE)));
		LiveDataPanelLayout
				.setVerticalGroup(LiveDataPanelLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								LiveDataPanelLayout
										.createSequentialGroup()
										.addGap(36, 36, 36)
										.addComponent(TabbedTitle,
												GroupLayout.PREFERRED_SIZE, 58,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addGroup(
												LiveDataPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.BASELINE)
														.addComponent(
																LeftHandTitle)
														.addComponent(
																RightHandTitle))
										.addGap(18, 18, 18)
										.addGroup(
												LiveDataPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																LeftHandPressurePanel,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																RHPPanel,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												LiveDataPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																RHAPanel,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																LHAPanel,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addPreferredGap(
												LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												LiveDataPanelLayout
														.createParallelGroup(
																GroupLayout.Alignment.LEADING)
														.addComponent(
																StopButton,
																GroupLayout.PREFERRED_SIZE,
																95,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																StartButton,
																GroupLayout.PREFERRED_SIZE,
																95,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(20, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Live Data", LiveDataPanel);
		jTabbedPane1.addTab("Saved Data", SavedDataPanel);
		FileMenu.setText("File");

		FileMenuSave.setText("Save Session");
		FileMenuSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FileMenuSaveActionPerformed(evt);
			}

		});
		FileMenu.add(FileMenuSave);

		FileMenuLoad.setText("Load Session");
		FileMenuLoad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					FileMenuLoadActionPerformed(evt);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}

		});
		// FileMenu.add(FileMenuLoad);

		FileMenuClose.setText("Close");
		FileMenu.add(FileMenuClose);

		jMenuBar1.add(FileMenu);

		/*
		 * EditMenu.setText("Edit"); jMenuBar1.add(EditMenu);
		 * 
		 * HelpMenu.setText("Help");
		 * 
		 * HelpMenuHelp.setText("Help"); HelpMenu.add(HelpMenuHelp);
		 * 
		 * HelpMenuAbout.setText("About"); HelpMenuAbout.addActionListener(new
		 * java.awt.event.ActionListener() { public void
		 * actionPerformed(java.awt.event.ActionEvent evt) {
		 * HelpMenuAboutActionPerformed(evt); } }); HelpMenu.add(HelpMenuAbout);
		 * 
		 * jMenuBar1.add(HelpMenu);
		 */
		setJMenuBar(jMenuBar1);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup()
						.addGap(0, 0, Short.MAX_VALUE)
						.addComponent(jTabbedPane1, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addComponent(jTabbedPane1,
				GroupLayout.PREFERRED_SIZE, 980, GroupLayout.PREFERRED_SIZE));

		pack();
	}


	private void FileMenuSaveActionPerformed(ActionEvent evt) {

		final InOut Saver = new InOut();

		Saver.setData(RightHandData, LeftHandData);
		Saver.saveFile();
	}

	private void FileMenuLoadActionPerformed(ActionEvent evt)
			throws IOException { // Attempted at a Loader

		final InOut Loader = new InOut();
		Loader.ReadFile("2015-03-18-00-23-10.csv");
		RightHandData = Loader.getRightHand();
		setCards();

	}

	private void GraphAccelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		LeftCardLayout.show(LeftGraphCards, "Accel");
		RightCardLayout.show(RightGraphCards, "Accel");

	}

	private void GraphJerkButtonActionPerformed(java.awt.event.ActionEvent evt) {
	
		LeftCardLayout.show(LeftGraphCards, "Jerk");
		RightCardLayout.show(RightGraphCards, "Jerk");

	}

	private void GraphAngleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		LeftCardLayout.show(LeftGraphCards, "Angle");
		RightCardLayout.show(RightGraphCards, "Angle");
	}

	private void StopButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		Writable = false;

		LeftHandPressures.add(LSensor1Array);
		LeftHandPressures.add(LSensor2Array);
		LeftHandPressures.add(LSensor3Array);
		LeftHandPressures.add(LSensor4Array);
		LeftHandPressures.add(LSensor5Array);
		LeftHandPressures.add(LSensor6Array);
		LeftHandPressures.add(LSensor7Array);
		LeftHandPressures.add(LSensor8Array);
		LeftHandPressures.add(LSensor9Array);
		LeftHandPressures.add(LSensor10Array);
		LeftHandPressures.add(LSensor11Array);
		LeftHandPressures.add(LSensor12Array);
		LeftHandPressures.add(LSensor13Array);
		LeftHandPressures.add(LSensor14Array);
		LeftHandPressures.add(LSensor15Array);
		LeftHandPressures.add(LSensor16Array);
		LeftHandPressures.add(LSensor17Array);
		LeftHandPressures.add(LSensor18Array);
		LeftHandPressures.add(LSensor19Array);

		RightHandPressures.add(RSensor1Array);
		RightHandPressures.add(RSensor2Array);
		RightHandPressures.add(RSensor3Array);
		RightHandPressures.add(RSensor4Array);
		RightHandPressures.add(RSensor5Array);
		RightHandPressures.add(RSensor6Array);
		RightHandPressures.add(RSensor7Array);
		RightHandPressures.add(RSensor8Array);
		RightHandPressures.add(RSensor9Array);
		RightHandPressures.add(RSensor10Array);
		RightHandPressures.add(RSensor11Array);
		RightHandPressures.add(RSensor12Array);
		RightHandPressures.add(RSensor13Array);
		RightHandPressures.add(RSensor14Array);
		RightHandPressures.add(RSensor15Array);
		RightHandPressures.add(RSensor16Array);
		RightHandPressures.add(RSensor17Array);
		RightHandPressures.add(RSensor18Array);
		RightHandPressures.add(RSensor19Array);

		LeftHandData.setAccelerations(LAccelArray);
		LeftHandData.setJerks(LJerkArray);
		LeftHandData.setAngles(LAngleArray);
		LeftHandData.setPressures(LeftHandPressures);
		LeftHandData.setTimes(TimeArray);

		LeftGraph.setData(LeftHandData);
		RightHandData.setAccelerations(RAccelArray);

		RightHandData.setJerks(RJerkArray);
		RightHandData.setAngles(RAngleArray);
		RightHandData.setPressures(RightHandPressures);
		RightHandData.setTimes(TimeArray);
		RightGraph.setData(RightHandData);

		setCards();

	}

	private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {
		TimeArray.clear();
		RAccelArray.clear();
		RJerkArray.clear();
		RAngleArray.clear();
		RSensor1Array.clear();
		RSensor2Array.clear();
		RSensor3Array.clear();
		RSensor4Array.clear();
		RSensor5Array.clear();
		RSensor6Array.clear();
		RSensor7Array.clear();
		RSensor8Array.clear();
		RSensor9Array.clear();
		RSensor10Array.clear();
		RSensor11Array.clear();
		RSensor12Array.clear();
		RSensor13Array.clear();
		RSensor14Array.clear();
		RSensor15Array.clear();
		RSensor16Array.clear();
		RSensor17Array.clear();
		RSensor18Array.clear();
		RSensor19Array.clear();
		LAccelArray.clear();
		LJerkArray.clear();
		LAngleArray.clear();
		LSensor1Array.clear();
		LSensor2Array.clear();
		LSensor3Array.clear();
		LSensor4Array.clear();
		LSensor5Array.clear();
		LSensor6Array.clear();
		LSensor7Array.clear();
		LSensor8Array.clear();
		LSensor9Array.clear();
		LSensor10Array.clear();
		LSensor11Array.clear();
		LSensor12Array.clear();
		LSensor13Array.clear();
		LSensor14Array.clear();
		LSensor15Array.clear();
		LSensor16Array.clear();
		LSensor17Array.clear();
		LSensor18Array.clear();
		LSensor19Array.clear();

		Writable = true;
		setTime();
	}

	public void setRightAccel(Double CurrentAccel) {

		RightAccel = CurrentAccel;
	}

	public boolean getWriteable() {

		return Writable;
	}

	public void currentTime(Double Time) {
		if (Writable == true) {
			TimeArray.add(Time);

		}
	}

	public void currentRightAccel(Double Accel) {
		RightAccel = Accel;
		df.format(RightAccel);
		tfRightAccel.setText(Double.toString(RightAccel));

		if (Writable == true) {
			RAccelArray.add(RightAccel);

		}
	}

	public void currentRightAngle(Double Angle) {
		RightAngle = Angle;
		tfRightAngle.setText(Double.toString(RightAngle));
		if (Writable == true) {
			RAngleArray.add(RightAngle);
		}

	}

	public void currentRightJerk(Double Jerk) {
		RightJerk = Jerk;
		tfRightJerk.setText(Double.toString(RightJerk));
		if (Writable == true) {
			RJerkArray.add(RightJerk);
		}

	}

	public void currentRightSensor1(Double Pressure) {
		RightPressures[0] = Pressure;
		RHPTable.setValueAt(RightPressures[0], 0, 1);

		if (Writable == true) {
			RSensor1Array.add(Pressure);
		}

		if (Pressure > 0.001) {
			RSensor1.setBackground(Color.GREEN);
		} else {
			RSensor1.setBackground(Color.RED);
		}

	}

	public void currentRightSensor2(Double Pressure) {
		RightPressures[1] = Pressure;
		RHPTable.setValueAt(RightPressures[1], 1, 1);

		if (Writable == true) {
			RSensor2Array.add(RightPressures[1]);
		}

		if (Pressure > 0.001) {
			RSensor2.setBackground(Color.GREEN);
		} else {
			RSensor2.setBackground(Color.RED);
		}
	}

	public void currentRightSensor3(Double Pressure) {
		RightPressures[2] = Pressure;
		RHPTable.setValueAt(RightPressures[2], 2, 1);
		if (Writable == true) {
			RSensor3Array.add(RightPressures[2]);
		}

		if (Pressure > 0.001) {
			RSensor3.setBackground(Color.GREEN);
		} else {
			RSensor3.setBackground(Color.RED);
		}
	}

	public void currentRightSensor4(Double Pressure) {
		RightPressures[3] = Pressure;
		RHPTable.setValueAt(RightPressures[3], 3, 1);
		if (Writable == true) {
			RSensor4Array.add(RightPressures[3]);
		}

		if (Pressure > 0.001) {
			RSensor4.setBackground(Color.GREEN);
		} else {
			RSensor4.setBackground(Color.RED);
		}
	}

	public void currentRightSensor5(Double Pressure) {
		RightPressures[4] = Pressure;
		RHPTable.setValueAt(RightPressures[4], 4, 1);
		if (Writable == true) {
			RSensor5Array.add(RightPressures[4]);
		}

		if (Pressure > 0.001) {
			RSensor5.setBackground(Color.GREEN);
		} else {
			RSensor5.setBackground(Color.RED);
		}
	}

	public void currentRightSensor6(Double Pressure) {
		RightPressures[5] = Pressure;
		RHPTable.setValueAt(RightPressures[5], 5, 1);
		if (Writable == true) {
			RSensor6Array.add(RightPressures[5]);
		}

		if (Pressure > 0.001) {
			RSensor6.setBackground(Color.GREEN);
		} else {
			RSensor6.setBackground(Color.RED);
		}
	}

	public void currentRightSensor7(Double Pressure) {
		RightPressures[6] = Pressure;
		RHPTable.setValueAt(RightPressures[6], 6, 1);
		if (Writable == true) {
			RSensor7Array.add(RightPressures[6]);
		}

		if (Pressure > 0.001) {
			RSensor7.setBackground(Color.GREEN);
		} else {
			RSensor7.setBackground(Color.RED);
		}
	}

	public void currentRightSensor8(Double Pressure) {
		RightPressures[7] = Pressure;
		RHPTable.setValueAt(RightPressures[7], 7, 1);
		if (Writable == true) {
			RSensor8Array.add(RightPressures[7]);
		}

		if (Pressure > 0.001) {
			RSensor8.setBackground(Color.GREEN);
		} else {
			RSensor8.setBackground(Color.RED);
		}
	}

	public void currentRightSensor9(Double Pressure) {
		RightPressures[8] = Pressure;
		RHPTable.setValueAt(RightPressures[8], 8, 1);
		if (Writable == true) {
			RSensor9Array.add(RightPressures[8]);
		}

		if (Pressure > 0.001) {
			RSensor9.setBackground(Color.GREEN);
		} else {
			RSensor9.setBackground(Color.RED);
		}
	}

	public void currentRightSensor10(Double Pressure) {
		RightPressures[9] = Pressure;
		RHPTable.setValueAt(RightPressures[9], 9, 1);
		if (Writable == true) {
			RSensor10Array.add(RightPressures[9]);
		}

		if (Pressure > 0.001) {
			RSensor10.setBackground(Color.GREEN);
		} else {
			RSensor10.setBackground(Color.RED);
		}
	}

	public void currentRightSensor11(Double Pressure) {
		RightPressures[10] = Pressure;
		RHPTable.setValueAt(RightPressures[10], 10, 1);
		if (Writable == true) {
			RSensor11Array.add(RightPressures[10]);
		}

		if (Pressure > 0.001) {
			RSensor11.setBackground(Color.GREEN);
		} else {
			RSensor11.setBackground(Color.RED);
		}
	}

	public void currentRightSensor12(Double Pressure) {
		RightPressures[11] = Pressure;
		RHPTable.setValueAt(RightPressures[11], 11, 1);
		if (Writable == true) {
			RSensor12Array.add(RightPressures[11]);
		}

		if (Pressure > 0.001) {
			RSensor12.setBackground(Color.GREEN);
		} else {
			RSensor12.setBackground(Color.RED);
		}
	}

	public void currentRightSensor13(Double Pressure) {
		RightPressures[12] = Pressure;
		RHPTable.setValueAt(RightPressures[12], 12, 1);

		if (Writable == true) {
			RSensor13Array.add(RightPressures[12]);

		}

		if (Pressure > 0.001) {
			RSensor13.setBackground(Color.GREEN);
		} else {
			RSensor13.setBackground(Color.RED);
		}
	}

	public void currentRightSensor14(Double Pressure) {
		RightPressures[13] = Pressure;
		RHPTable.setValueAt(RightPressures[13], 13, 1);
		if (Writable == true) {
			RSensor14Array.add(RightPressures[13]);
		}

		if (Pressure > 0.001) {
			RSensor14.setBackground(Color.GREEN);
		} else {
			RSensor14.setBackground(Color.RED);
		}
	}

	public void currentRightSensor15(Double Pressure) {
		RightPressures[14] = Pressure;

		RHPTable.setValueAt(RightPressures[14], 14, 1);
		if (Writable == true) {
			RSensor15Array.add(RightPressures[14]);
		}

		if (Pressure > 0.001) {
			RSensor15.setBackground(Color.GREEN);
		} else {
			RSensor15.setBackground(Color.RED);
		}
	}

	public void currentRightSensor16(Double Pressure) {
		RightPressures[15] = Pressure;
		RHPTable.setValueAt(RightPressures[15], 15, 1);
		if (Writable == true) {
			RSensor16Array.add(RightPressures[15]);
		}

		if (Pressure > 0.001) {
			RSensor16.setBackground(Color.GREEN);
		} else {
			RSensor16.setBackground(Color.RED);
		}
	}

	public void currentRightSensor17(Double Pressure) {
		RightPressures[16] = Pressure;
		RHPTable.setValueAt(RightPressures[16], 16, 1);
		if (Writable == true) {
			RSensor17Array.add(RightPressures[16]);
		}

		if (Pressure > 0.001) {
			RSensor17.setBackground(Color.GREEN);
		} else {
			RSensor17.setBackground(Color.RED);
		}
	}

	public void currentRightSensor18(Double Pressure) {
		RightPressures[17] = Pressure;
		RHPTable.setValueAt(RightPressures[17], 17, 1);
		if (Writable == true) {
			RSensor18Array.add(RightPressures[17]);
		}

		if (Pressure > 0.001) {
			RSensor18.setBackground(Color.GREEN);
		} else {
			RSensor18.setBackground(Color.RED);
		}
	}

	public void currentRightSensor19(Double Pressure) {
		RightPressures[18] = Pressure;
		RHPTable.setValueAt(RightPressures[18], 18, 1);
		if (Writable == true) {
			RSensor19Array.add(RightPressures[18]);
		}

		if (Pressure > 0.001) {
			RSensor19.setBackground(Color.GREEN);
		} else {
			RSensor19.setBackground(Color.RED);
		}
	}

	public void currentLeftAccel(Double Accel) {
		LeftAccel = Accel;
		tfLeftAccel.setText(Double.toString(LeftAccel));

		if (Writable == true) {
			LAccelArray.add(LeftAccel);
		}
	}

	public void currentLeftAngle(Double Angle) {
		LeftAngle = Angle;
		tfLeftAngle.setText(Double.toString(LeftAngle));
		if (Writable == true) {
			LAngleArray.add(LeftAngle);
		}

	}

	public void currentLeftJerk(Double Jerk) {
		LeftJerk = Jerk;
		tfLeftJerk.setText(Double.toString(LeftJerk));
		if (Writable == true) {
			LJerkArray.add(LeftJerk);
		}

	}

	public void currentLeftSensor1(Double Pressure) {
		LeftPressures[0] = Pressure;
		LHPTable.setValueAt(LeftPressures[0], 0, 1);
		if (Writable == true) {
			LSensor1Array.add(LeftPressures[0]);
		}

		if (Pressure > 0.001) {
			LSensor1.setBackground(Color.GREEN);
		} else {
			LSensor1.setBackground(Color.RED);
		}

	}

	public void currentLeftSensor2(Double Pressure) {
		LeftPressures[1] = Pressure;
		LHPTable.setValueAt(LeftPressures[1], 1, 1);
		if (Writable == true) {
			LSensor2Array.add(LeftPressures[1]);
		}

		if (Pressure > 0.001) {
			LSensor2.setBackground(Color.GREEN);
		} else {
			LSensor2.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor3(Double Pressure) {
		LeftPressures[2] = Pressure;
		LHPTable.setValueAt(LeftPressures[2], 2, 1);
		if (Writable == true) {
			LSensor3Array.add(LeftPressures[2]);
		}

		if (Pressure > 0.001) {
			LSensor3.setBackground(Color.GREEN);
		} else {
			LSensor3.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor4(Double Pressure) {
		LeftPressures[3] = Pressure;
		LHPTable.setValueAt(LeftPressures[3], 3, 1);
		if (Writable == true) {
			LSensor4Array.add(LeftPressures[3]);
		}

		if (Pressure > 0.001) {
			LSensor4.setBackground(Color.GREEN);
		} else {
			LSensor4.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor5(Double Pressure) {
		LeftPressures[4] = Pressure;
		LHPTable.setValueAt(LeftPressures[4], 4, 1);
		if (Writable == true) {
			LSensor5Array.add(LeftPressures[4]);
		}

		if (Pressure > 0.001) {
			LSensor5.setBackground(Color.GREEN);
		} else {
			LSensor5.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor6(Double Pressure) {
		LeftPressures[5] = Pressure;
		LHPTable.setValueAt(LeftPressures[5], 5, 1);
		if (Writable == true) {
			LSensor6Array.add(LeftPressures[5]);
		}

		if (Pressure > 0.001) {
			LSensor6.setBackground(Color.GREEN);
		} else {
			LSensor6.setBackground(Color.RED);
		}

	}

	public void currentLeftSensor7(Double Pressure) {
		LeftPressures[6] = Pressure;
		LHPTable.setValueAt(LeftPressures[6], 6, 1);
		if (Writable == true) {
			LSensor7Array.add(LeftPressures[6]);
		}

		if (Pressure > 0.001) {
			LSensor7.setBackground(Color.GREEN);
		} else {
			LSensor7.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor8(Double Pressure) {
		LeftPressures[7] = Pressure;
		LHPTable.setValueAt(LeftPressures[7], 7, 1);
		if (Writable == true) {
			LSensor8Array.add(LeftPressures[7]);
		}

		if (Pressure > 0.001) {
			LSensor8.setBackground(Color.GREEN);
		} else {
			LSensor8.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor9(Double Pressure) {
		LeftPressures[8] = Pressure;
		LHPTable.setValueAt(LeftPressures[8], 8, 1);
		if (Writable == true) {
			LSensor9Array.add(LeftPressures[8]);
		}

		if (Pressure > 0.001) {
			LSensor9.setBackground(Color.GREEN);
		} else {
			LSensor9.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor10(Double Pressure) {
		LeftPressures[9] = Pressure;
		LHPTable.setValueAt(LeftPressures[9], 9, 1);
		if (Writable == true) {
			LSensor10Array.add(LeftPressures[9]);
		}

		if (Pressure > 0.001) {
			LSensor10.setBackground(Color.GREEN);
		} else {
			LSensor10.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor11(Double Pressure) {
		LeftPressures[10] = Pressure;
		LHPTable.setValueAt(LeftPressures[10], 10, 1);
		if (Writable == true) {
			LSensor11Array.add(LeftPressures[10]);
		}

		if (Pressure > 0.001) {
			LSensor11.setBackground(Color.GREEN);
		} else {
			LSensor11.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor12(Double Pressure) {
		LeftPressures[11] = Pressure;
		LHPTable.setValueAt(LeftPressures[11], 11, 1);
		if (Writable == true) {
			LSensor12Array.add(LeftPressures[11]);
		}

		if (Pressure > 0.001) {
			LSensor12.setBackground(Color.GREEN);
		} else {
			LSensor12.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor13(Double Pressure) {
		LeftPressures[12] = Pressure;
		LHPTable.setValueAt(LeftPressures[12], 12, 1);

		if (Writable == true) {
			LSensor13Array.add(LeftPressures[12]);
		}

		if (Pressure > 0.001) {
			LSensor13.setBackground(Color.GREEN);
		} else {
			LSensor13.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor14(Double Pressure) {
		LeftPressures[13] = Pressure;
		LHPTable.setValueAt(LeftPressures[13], 13, 1);

		if (Writable == true) {

			LSensor14Array.add(LeftPressures[13]);
		}

		if (Pressure > 0.001) {
			LSensor14.setBackground(Color.GREEN);
		} else {
			LSensor14.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor15(Double Pressure) {
		LeftPressures[14] = Pressure;
		LHPTable.setValueAt(LeftPressures[14], 14, 1);
		if (Writable == true) {
			LSensor15Array.add(LeftPressures[14]);
		}

		if (Pressure > 0.001) {
			LSensor15.setBackground(Color.GREEN);
		} else {
			LSensor15.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor16(Double Pressure) {
		LeftPressures[15] = Pressure;
		LHPTable.setValueAt(LeftPressures[15], 15, 1);
		if (Writable == true) {
			LSensor16Array.add(LeftPressures[15]);
		}

		if (Pressure > 0.001) {
			LSensor16.setBackground(Color.GREEN);
		} else {
			LSensor16.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor17(Double Pressure) {
		LeftPressures[16] = Pressure;
		LHPTable.setValueAt(LeftPressures[16], 16, 1);
		if (Writable == true) {
			LSensor17Array.add(LeftPressures[16]);
		}

		if (Pressure > 0.001) {
			LSensor17.setBackground(Color.GREEN);
		} else {
			LSensor17.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor18(Double Pressure) {
		LeftPressures[17] = Pressure;
		LHPTable.setValueAt(LeftPressures[17], 17, 1);
		if (Writable == true) {

			LSensor18Array.add(LeftPressures[17]);
		}

		if (Pressure > 0.001) {
			LSensor18.setBackground(Color.GREEN);
		} else {
			LSensor18.setBackground(Color.RED);
		}
	}

	public void currentLeftSensor19(Double Pressure) {
		LeftPressures[18] = Pressure;
		LHPTable.setValueAt(LeftPressures[18], 18, 1);
		if (Writable == true) {
			LSensor19Array.add(LeftPressures[18]);
		}

		if (Pressure > 0.001) {
			LSensor19.setBackground(Color.GREEN);
		} else {
			LSensor19.setBackground(Color.RED);
		}
	}

	public void setCards() {

		if (doneonce == true) {
			LeftGraphPanel1.removeAll();
			LeftGraphPanel2.removeAll();
			LeftGraphPanel3.removeAll();
			LeftGraphPanel4.removeAll();
			LeftGraphPanel5.removeAll();
			LeftGraphPanel6.removeAll();
			LeftGraphPanel7.removeAll();
			LeftGraphPanel8.removeAll();
			LeftGraphPanel9.removeAll();
			LeftGraphPanel10.removeAll();
			LeftGraphPanel11.removeAll();
			LeftGraphPanel12.removeAll();
			LeftGraphPanel13.removeAll();
			LeftGraphPanel14.removeAll();
			LeftGraphPanel15.removeAll();
			LeftGraphPanel16.removeAll();
			LeftGraphPanel17.removeAll();
			LeftGraphPanel18.removeAll();
			LeftGraphPanel19.removeAll();
			LeftGraphPanel20.removeAll();
			LeftGraphPanel21.removeAll();
			LeftGraphPanel22.removeAll();

			Lcard1.removeAll();
			Lcard2.removeAll();
			Lcard3.removeAll();
			Lcard4.removeAll();
			Lcard5.removeAll();
			Lcard6.removeAll();
			Lcard7.removeAll();
			Lcard8.removeAll();
			Lcard9.removeAll();
			Lcard10.removeAll();
			Lcard11.removeAll();
			Lcard12.removeAll();
			Lcard13.removeAll();
			Lcard14.removeAll();
			Lcard15.removeAll();
			Lcard16.removeAll();
			Lcard17.removeAll();
			Lcard18.removeAll();
			Lcard19.removeAll();
			Lcard20.removeAll();
			Lcard21.removeAll();
			Lcard22.removeAll();

			LeftGraphCards.removeAll();

			RightGraphPanel1.removeAll();
			RightGraphPanel2.removeAll();
			RightGraphPanel3.removeAll();
			RightGraphPanel4.removeAll();
			RightGraphPanel5.removeAll();
			RightGraphPanel6.removeAll();
			RightGraphPanel7.removeAll();
			RightGraphPanel8.removeAll();
			RightGraphPanel9.removeAll();
			RightGraphPanel10.removeAll();
			RightGraphPanel11.removeAll();
			RightGraphPanel12.removeAll();
			RightGraphPanel13.removeAll();
			RightGraphPanel14.removeAll();
			RightGraphPanel15.removeAll();
			RightGraphPanel16.removeAll();
			RightGraphPanel17.removeAll();
			RightGraphPanel18.removeAll();
			RightGraphPanel19.removeAll();
			RightGraphPanel20.removeAll();
			RightGraphPanel21.removeAll();
			RightGraphPanel22.removeAll();

			Rcard1.removeAll();
			Rcard2.removeAll();
			Rcard3.removeAll();
			Rcard4.removeAll();
			Rcard5.removeAll();
			Rcard6.removeAll();
			Rcard7.removeAll();
			Rcard8.removeAll();
			Rcard9.removeAll();
			Rcard10.removeAll();
			Rcard11.removeAll();
			Rcard12.removeAll();
			Rcard13.removeAll();
			Rcard14.removeAll();
			Rcard15.removeAll();
			Rcard16.removeAll();
			Rcard17.removeAll();
			Rcard18.removeAll();
			Rcard19.removeAll();
			Rcard20.removeAll();
			Rcard21.removeAll();
			Rcard22.removeAll();

			RightGraphCards.removeAll();

			LeftGraph.setData(LeftHandData);
			RightGraph.setData(RightHandData);
			LeftGraph.MakeGraphs();
			RightGraph.MakeGraphs();

		}

		GroupLayout RightGraphLayout1 = new GroupLayout(RightGraphPanel1);
		RightGraphPanel1.setLayout(RightGraphLayout1);
		RightGraphLayout1.setHorizontalGroup(RightGraphLayout1
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getAccelChartPanel())
				.addGap(0, 1144, Short.MAX_VALUE));
		RightGraphLayout1.setVerticalGroup(RightGraphLayout1
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getAccelChartPanel())
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout2 = new GroupLayout(RightGraphPanel2);
		RightGraphPanel2.setLayout(RightGraphLayout2);
		RightGraphLayout2.setHorizontalGroup(RightGraphLayout2
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getJerkChartPanel())
				.addGap(0, 1144, Short.MAX_VALUE));
		RightGraphLayout2.setVerticalGroup(RightGraphLayout2
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getJerkChartPanel())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout3 = new GroupLayout(RightGraphPanel3);
		RightGraphPanel3.setLayout(RightGraphLayout3);
		RightGraphLayout3.setHorizontalGroup(RightGraphLayout3
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getAngleChartPanel())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout3.setVerticalGroup(RightGraphLayout3
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getAngleChartPanel())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout4 = new GroupLayout(RightGraphPanel4);
		RightGraphPanel4.setLayout(RightGraphLayout4);
		RightGraphLayout4.setHorizontalGroup(RightGraphLayout4
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel1())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout4.setVerticalGroup(RightGraphLayout4
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel1())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout5 = new GroupLayout(RightGraphPanel5);
		RightGraphPanel5.setLayout(RightGraphLayout5);
		RightGraphLayout5.setHorizontalGroup(RightGraphLayout5
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel2())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout5.setVerticalGroup(RightGraphLayout5
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel2())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout6 = new GroupLayout(RightGraphPanel6);
		RightGraphPanel6.setLayout(RightGraphLayout6);
		RightGraphLayout6.setHorizontalGroup(RightGraphLayout6
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel3())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout6.setVerticalGroup(RightGraphLayout6
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel3())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout7 = new GroupLayout(RightGraphPanel7);
		RightGraphPanel7.setLayout(RightGraphLayout7);
		RightGraphLayout7.setHorizontalGroup(RightGraphLayout7
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel4())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout7.setVerticalGroup(RightGraphLayout7
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel4())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout8 = new GroupLayout(RightGraphPanel8);
		RightGraphPanel8.setLayout(RightGraphLayout8);
		RightGraphLayout8.setHorizontalGroup(RightGraphLayout8
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel5())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout8.setVerticalGroup(RightGraphLayout8
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel5())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout9 = new GroupLayout(RightGraphPanel9);
		RightGraphPanel9.setLayout(RightGraphLayout9);
		RightGraphLayout9.setHorizontalGroup(RightGraphLayout9
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel6())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout9.setVerticalGroup(RightGraphLayout9
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel6())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout10 = new GroupLayout(RightGraphPanel10);
		RightGraphPanel10.setLayout(RightGraphLayout10);
		RightGraphLayout10.setHorizontalGroup(RightGraphLayout10
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel7())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout10.setVerticalGroup(RightGraphLayout10
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel7())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout11 = new GroupLayout(RightGraphPanel11);
		RightGraphPanel11.setLayout(RightGraphLayout11);
		RightGraphLayout11.setHorizontalGroup(RightGraphLayout11
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel8())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout11.setVerticalGroup(RightGraphLayout11
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel8())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout12 = new GroupLayout(RightGraphPanel12);
		RightGraphPanel12.setLayout(RightGraphLayout12);
		RightGraphLayout12.setHorizontalGroup(RightGraphLayout12
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel9())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout12.setVerticalGroup(RightGraphLayout12
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel9())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout13 = new GroupLayout(RightGraphPanel13);
		RightGraphPanel13.setLayout(RightGraphLayout13);
		RightGraphLayout13.setHorizontalGroup(RightGraphLayout13
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel10())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout13.setVerticalGroup(RightGraphLayout13
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel10())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout14 = new GroupLayout(RightGraphPanel14);
		RightGraphPanel14.setLayout(RightGraphLayout14);
		RightGraphLayout14.setHorizontalGroup(RightGraphLayout14
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel11())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout14.setVerticalGroup(RightGraphLayout14
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel11())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout15 = new GroupLayout(RightGraphPanel15);
		RightGraphPanel15.setLayout(RightGraphLayout15);
		RightGraphLayout15.setHorizontalGroup(RightGraphLayout15
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel12())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout15.setVerticalGroup(RightGraphLayout15
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel12())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout16 = new GroupLayout(RightGraphPanel16);
		RightGraphPanel16.setLayout(RightGraphLayout16);
		RightGraphLayout16.setHorizontalGroup(RightGraphLayout16
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel13())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout16.setVerticalGroup(RightGraphLayout16
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel13())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout17 = new GroupLayout(RightGraphPanel17);
		RightGraphPanel17.setLayout(RightGraphLayout17);
		RightGraphLayout17.setHorizontalGroup(RightGraphLayout17
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel14())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout17.setVerticalGroup(RightGraphLayout17
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel14())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout18 = new GroupLayout(RightGraphPanel18);
		RightGraphPanel18.setLayout(RightGraphLayout18);
		RightGraphLayout18.setHorizontalGroup(RightGraphLayout18
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel15())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout18.setVerticalGroup(RightGraphLayout18
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel15())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout19 = new GroupLayout(RightGraphPanel19);
		RightGraphPanel19.setLayout(RightGraphLayout19);
		RightGraphLayout19.setHorizontalGroup(RightGraphLayout19
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel16())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout19.setVerticalGroup(RightGraphLayout19
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel16())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout20 = new GroupLayout(RightGraphPanel20);
		RightGraphPanel20.setLayout(RightGraphLayout20);
		RightGraphLayout20.setHorizontalGroup(RightGraphLayout20
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel17())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout20.setVerticalGroup(RightGraphLayout20
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel17())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout21 = new GroupLayout(RightGraphPanel21);
		RightGraphPanel21.setLayout(RightGraphLayout21);
		RightGraphLayout21.setHorizontalGroup(RightGraphLayout21
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel18())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout21.setVerticalGroup(RightGraphLayout21
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel18())

				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout RightGraphLayout22 = new GroupLayout(RightGraphPanel22);
		RightGraphPanel22.setLayout(RightGraphLayout22);
		RightGraphLayout22.setHorizontalGroup(RightGraphLayout22
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel19())

				.addGap(0, 1144, Short.MAX_VALUE));

		RightGraphLayout22.setVerticalGroup(RightGraphLayout22
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(RightGraph.getSensorChartPanel19())

				.addGap(0, 320, Short.MAX_VALUE));

		Rcard1.add(RightGraphPanel1);

		Rcard2.add(RightGraphPanel2);

		Rcard3.add(RightGraphPanel3);

		Rcard4.add(RightGraphPanel4);

		Rcard5.add(RightGraphPanel5);

		Rcard6.add(RightGraphPanel6);

		Rcard7.add(RightGraphPanel7);

		Rcard8.add(RightGraphPanel8);

		Rcard9.add(RightGraphPanel9);

		Rcard10.add(RightGraphPanel10);

		Rcard11.add(RightGraphPanel11);

		Rcard12.add(RightGraphPanel12);

		Rcard13.add(RightGraphPanel13);

		Rcard14.add(RightGraphPanel14);

		Rcard15.add(RightGraphPanel15);

		Rcard16.add(RightGraphPanel16);

		Rcard17.add(RightGraphPanel17);

		Rcard18.add(RightGraphPanel18);

		Rcard19.add(RightGraphPanel19);

		Rcard20.add(RightGraphPanel20);

		Rcard21.add(RightGraphPanel21);

		Rcard22.add(RightGraphPanel22);


		RightGraphCards.setLayout(RightCardLayout);
		RightGraphCards.add(Rcard1, "Accel");
		RightGraphCards.add(Rcard2, "Jerk");
		RightGraphCards.add(Rcard3, "Angle");
		RightGraphCards.add(Rcard4, "Sensor1");
		RightGraphCards.add(Rcard5, "Sensor2");
		RightGraphCards.add(Rcard6, "Sensor3");
		RightGraphCards.add(Rcard7, "Sensor4");
		RightGraphCards.add(Rcard8, "Sensor5");
		RightGraphCards.add(Rcard9, "Sensor6");
		RightGraphCards.add(Rcard10, "Sensor7");
		RightGraphCards.add(Rcard11, "Sensor8");
		RightGraphCards.add(Rcard12, "Sensor9");
		RightGraphCards.add(Rcard13, "Sensor10");
		RightGraphCards.add(Rcard14, "Sensor11");
		RightGraphCards.add(Rcard15, "Sensor12");
		RightGraphCards.add(Rcard16, "Sensor13");
		RightGraphCards.add(Rcard17, "Sensor14");
		RightGraphCards.add(Rcard18, "Sensor15");
		RightGraphCards.add(Rcard19, "Sensor16");
		RightGraphCards.add(Rcard20, "Sensor17");
		RightGraphCards.add(Rcard21, "Sensor18");
		RightGraphCards.add(Rcard22, "Sensor19");

		GraphLayout1 = new GroupLayout(LeftGraphPanel1);
		LeftGraphPanel1.setLayout(GraphLayout1);
		GraphLayout1.setHorizontalGroup(GraphLayout1
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getAccelChartPanel())
				
				.addGap(0, 1144, Short.MAX_VALUE));
		GraphLayout1.setVerticalGroup(GraphLayout1
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getAccelChartPanel())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout2 = new GroupLayout(LeftGraphPanel2);
		LeftGraphPanel2.setLayout(GraphLayout2);
		GraphLayout2.setHorizontalGroup(GraphLayout2
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getJerkChartPanel())
				
				.addGap(0, 1144, Short.MAX_VALUE));
		GraphLayout2.setVerticalGroup(GraphLayout2
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getJerkChartPanel())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout3 = new GroupLayout(LeftGraphPanel3);
		LeftGraphPanel3.setLayout(GraphLayout3);
		GraphLayout3.setHorizontalGroup(GraphLayout3
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getAngleChartPanel())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout3.setVerticalGroup(GraphLayout3
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getAngleChartPanel())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout4 = new GroupLayout(LeftGraphPanel4);
		LeftGraphPanel4.setLayout(GraphLayout4);
		GraphLayout4.setHorizontalGroup(GraphLayout4
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel1())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout4.setVerticalGroup(GraphLayout4
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel1())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout5 = new GroupLayout(LeftGraphPanel5);
		LeftGraphPanel5.setLayout(GraphLayout5);
		GraphLayout5.setHorizontalGroup(GraphLayout5
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel2())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout5.setVerticalGroup(GraphLayout5
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel2())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout6 = new GroupLayout(LeftGraphPanel6);
		LeftGraphPanel6.setLayout(GraphLayout6);
		GraphLayout6.setHorizontalGroup(GraphLayout6
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel3())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout6.setVerticalGroup(GraphLayout6
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel3())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout7 = new GroupLayout(LeftGraphPanel7);
		LeftGraphPanel7.setLayout(GraphLayout7);
		GraphLayout7.setHorizontalGroup(GraphLayout7
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel4())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout7.setVerticalGroup(GraphLayout7
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel4())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout8 = new GroupLayout(LeftGraphPanel8);
		LeftGraphPanel8.setLayout(GraphLayout8);
		GraphLayout8.setHorizontalGroup(GraphLayout8
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel5())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout8.setVerticalGroup(GraphLayout8
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel5())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout9 = new GroupLayout(LeftGraphPanel9);
		LeftGraphPanel9.setLayout(GraphLayout9);
		GraphLayout9.setHorizontalGroup(GraphLayout9
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel6())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout9.setVerticalGroup(GraphLayout9
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel6())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout10 = new GroupLayout(LeftGraphPanel10);
		LeftGraphPanel10.setLayout(GraphLayout10);
		GraphLayout10.setHorizontalGroup(GraphLayout10
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel7())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout10.setVerticalGroup(GraphLayout10
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel7())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout11 = new GroupLayout(LeftGraphPanel11);
		LeftGraphPanel11.setLayout(GraphLayout11);
		GraphLayout11.setHorizontalGroup(GraphLayout11
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel8())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout11.setVerticalGroup(GraphLayout11
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel8())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout12 = new GroupLayout(LeftGraphPanel12);
		LeftGraphPanel12.setLayout(GraphLayout12);
		GraphLayout12.setHorizontalGroup(GraphLayout12
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel9())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout12.setVerticalGroup(GraphLayout12
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel9())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout13 = new GroupLayout(LeftGraphPanel13);
		LeftGraphPanel13.setLayout(GraphLayout13);
		GraphLayout13.setHorizontalGroup(GraphLayout13
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel10())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout13.setVerticalGroup(GraphLayout13
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel10())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout14 = new GroupLayout(LeftGraphPanel14);
		LeftGraphPanel14.setLayout(GraphLayout14);
		GraphLayout14.setHorizontalGroup(GraphLayout14
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel11())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout14.setVerticalGroup(GraphLayout14
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel11())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout15 = new GroupLayout(LeftGraphPanel15);
		LeftGraphPanel15.setLayout(GraphLayout15);
		GraphLayout15.setHorizontalGroup(GraphLayout15
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel12())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout15.setVerticalGroup(GraphLayout15
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel12())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout16 = new GroupLayout(LeftGraphPanel16);
		LeftGraphPanel16.setLayout(GraphLayout16);
		GraphLayout16.setHorizontalGroup(GraphLayout16
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel13())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout16.setVerticalGroup(GraphLayout16
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel13())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout17 = new GroupLayout(LeftGraphPanel17);
		LeftGraphPanel17.setLayout(GraphLayout17);
		GraphLayout17.setHorizontalGroup(GraphLayout17
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel14())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout17.setVerticalGroup(GraphLayout17
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel14())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout18 = new GroupLayout(LeftGraphPanel18);
		LeftGraphPanel18.setLayout(GraphLayout18);
		GraphLayout18.setHorizontalGroup(GraphLayout18
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel15())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout18.setVerticalGroup(GraphLayout18
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel15())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout19 = new GroupLayout(LeftGraphPanel19);
		LeftGraphPanel19.setLayout(GraphLayout19);
		GraphLayout19.setHorizontalGroup(GraphLayout19
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel16())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout19.setVerticalGroup(GraphLayout19
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel16())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout20 = new GroupLayout(LeftGraphPanel20);
		LeftGraphPanel20.setLayout(GraphLayout20);
		GraphLayout20.setHorizontalGroup(GraphLayout20
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel17())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout20.setVerticalGroup(GraphLayout20
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel17())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout21 = new GroupLayout(LeftGraphPanel21);
		LeftGraphPanel21.setLayout(GraphLayout21);
		GraphLayout21.setHorizontalGroup(GraphLayout21
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel18())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout21.setVerticalGroup(GraphLayout21
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel18())
				
				.addGap(0, 320, Short.MAX_VALUE));

		GroupLayout GraphLayout22 = new GroupLayout(LeftGraphPanel22);
		LeftGraphPanel22.setLayout(GraphLayout22);
		GraphLayout22.setHorizontalGroup(GraphLayout22
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel19())
				
				.addGap(0, 1144, Short.MAX_VALUE));

		GraphLayout22.setVerticalGroup(GraphLayout22
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(LeftGraph.getSensorChartPanel19())
				
				.addGap(0, 320, Short.MAX_VALUE));

		Lcard1.add(LeftGraphPanel1);

		Lcard2.add(LeftGraphPanel2);

		Lcard3.add(LeftGraphPanel3);

		Lcard4.add(LeftGraphPanel4);

		Lcard5.add(LeftGraphPanel5);

		Lcard6.add(LeftGraphPanel6);

		Lcard7.add(LeftGraphPanel7);

		Lcard8.add(LeftGraphPanel8);

		Lcard9.add(LeftGraphPanel9);

		Lcard10.add(LeftGraphPanel10);

		Lcard11.add(LeftGraphPanel11);

		Lcard12.add(LeftGraphPanel12);

		Lcard13.add(LeftGraphPanel13);

		Lcard14.add(LeftGraphPanel14);

		Lcard15.add(LeftGraphPanel15);

		Lcard16.add(LeftGraphPanel16);

		Lcard17.add(LeftGraphPanel17);

		Lcard18.add(LeftGraphPanel18);

		Lcard19.add(LeftGraphPanel19);

		Lcard20.add(LeftGraphPanel20);

		Lcard21.add(LeftGraphPanel21);

		Lcard22.add(LeftGraphPanel22);

		LeftGraphCards.setLayout(LeftCardLayout);

		LeftGraphCards.add(Lcard1, "Accel");
		LeftGraphCards.add(Lcard2, "Jerk");
		LeftGraphCards.add(Lcard3, "Angle");
		LeftGraphCards.add(Lcard4, "Sensor1");
		LeftGraphCards.add(Lcard5, "Sensor2");
		LeftGraphCards.add(Lcard6, "Sensor3");
		LeftGraphCards.add(Lcard7, "Sensor4");
		LeftGraphCards.add(Lcard8, "Sensor5");
		LeftGraphCards.add(Lcard9, "Sensor6");
		LeftGraphCards.add(Lcard10, "Sensor7");
		LeftGraphCards.add(Lcard11, "Sensor8");
		LeftGraphCards.add(Lcard12, "Sensor9");
		LeftGraphCards.add(Lcard13, "Sensor10");
		LeftGraphCards.add(Lcard14, "Sensor11");
		LeftGraphCards.add(Lcard15, "Sensor12");
		LeftGraphCards.add(Lcard16, "Sensor13");
		LeftGraphCards.add(Lcard17, "Sensor14");
		LeftGraphCards.add(Lcard18, "Sensor15");
		LeftGraphCards.add(Lcard19, "Sensor16");
		LeftGraphCards.add(Lcard20, "Sensor17");
		LeftGraphCards.add(Lcard21, "Sensor18");
		LeftGraphCards.add(Lcard22, "Sensor19");

		LeftGraphPanel1.repaint();
		LeftGraphPanel2.repaint();
		LeftGraphPanel3.repaint();
		LeftGraphPanel4.repaint();
		LeftGraphPanel5.repaint();
		LeftGraphPanel6.repaint();
		LeftGraphPanel7.repaint();
		LeftGraphPanel8.repaint();
		LeftGraphPanel9.repaint();
		LeftGraphPanel10.repaint();
		LeftGraphPanel11.repaint();
		LeftGraphPanel12.repaint();
		LeftGraphPanel13.repaint();
		LeftGraphPanel14.repaint();
		LeftGraphPanel15.repaint();
		LeftGraphPanel16.repaint();
		LeftGraphPanel17.repaint();
		LeftGraphPanel18.repaint();
		LeftGraphPanel19.repaint();
		LeftGraphPanel20.repaint();
		LeftGraphPanel21.repaint();
		LeftGraphPanel22.repaint();

		Lcard1.repaint();
		Lcard2.repaint();
		Lcard3.repaint();
		Lcard4.repaint();
		Lcard5.repaint();
		Lcard6.repaint();
		Lcard7.repaint();
		Lcard8.repaint();
		Lcard9.repaint();
		Lcard10.repaint();
		Lcard11.repaint();
		Lcard12.repaint();
		Lcard13.repaint();
		Lcard14.repaint();
		Lcard15.repaint();
		Lcard16.repaint();
		Lcard17.repaint();
		Lcard18.repaint();
		Lcard19.repaint();
		Lcard20.repaint();
		Lcard21.repaint();
		Lcard22.repaint();

		LeftGraphCards.repaint();

		RightGraphPanel1.repaint();
		RightGraphPanel2.repaint();
		RightGraphPanel3.repaint();
		RightGraphPanel4.repaint();
		RightGraphPanel5.repaint();
		RightGraphPanel6.repaint();
		RightGraphPanel7.repaint();
		RightGraphPanel8.repaint();
		RightGraphPanel9.repaint();
		RightGraphPanel10.repaint();
		RightGraphPanel11.repaint();
		RightGraphPanel12.repaint();
		RightGraphPanel13.repaint();
		RightGraphPanel14.repaint();
		RightGraphPanel15.repaint();
		RightGraphPanel16.repaint();
		RightGraphPanel17.repaint();
		RightGraphPanel18.repaint();
		RightGraphPanel19.repaint();
		RightGraphPanel20.repaint();
		RightGraphPanel21.repaint();
		RightGraphPanel22.repaint();

		Rcard1.repaint();
		Rcard2.repaint();
		Rcard3.repaint();
		Rcard4.repaint();
		Rcard5.repaint();
		Rcard6.repaint();
		Rcard7.repaint();
		Rcard8.repaint();
		Rcard9.repaint();
		Rcard10.repaint();
		Rcard11.repaint();
		Rcard12.repaint();
		Rcard13.repaint();
		Rcard14.repaint();
		Rcard15.repaint();
		Rcard16.repaint();
		Rcard17.repaint();
		Rcard18.repaint();
		Rcard19.repaint();
		Rcard20.repaint();
		Rcard21.repaint();
		Rcard22.repaint();

		RightGraphCards.repaint();

		LeftGraphPanel1.validate();
		LeftGraphPanel2.validate();
		LeftGraphPanel3.validate();
		LeftGraphPanel4.validate();
		LeftGraphPanel5.validate();
		LeftGraphPanel6.validate();
		LeftGraphPanel7.validate();
		LeftGraphPanel8.validate();
		LeftGraphPanel9.validate();
		LeftGraphPanel10.validate();
		LeftGraphPanel11.validate();
		LeftGraphPanel12.validate();
		LeftGraphPanel13.validate();
		LeftGraphPanel14.validate();
		LeftGraphPanel15.validate();
		LeftGraphPanel16.validate();
		LeftGraphPanel17.validate();
		LeftGraphPanel18.validate();
		LeftGraphPanel19.validate();
		LeftGraphPanel20.validate();
		LeftGraphPanel21.validate();
		LeftGraphPanel22.validate();

		Lcard1.validate();
		Lcard2.validate();
		Lcard3.validate();
		Lcard4.validate();
		Lcard5.validate();
		Lcard6.validate();
		Lcard7.validate();
		Lcard8.validate();
		Lcard9.validate();
		Lcard10.validate();
		Lcard11.validate();
		Lcard12.validate();
		Lcard13.validate();
		Lcard14.validate();
		Lcard15.validate();
		Lcard16.validate();
		Lcard17.validate();
		Lcard18.validate();
		Lcard19.validate();
		Lcard20.validate();
		Lcard21.validate();
		Lcard22.validate();

		LeftGraphCards.validate();

		RightGraphPanel1.validate();
		RightGraphPanel2.validate();
		RightGraphPanel3.validate();
		RightGraphPanel4.validate();
		RightGraphPanel5.validate();
		RightGraphPanel6.validate();
		RightGraphPanel7.validate();
		RightGraphPanel8.validate();
		RightGraphPanel9.validate();
		RightGraphPanel10.validate();
		RightGraphPanel11.validate();
		RightGraphPanel12.validate();
		RightGraphPanel13.validate();
		RightGraphPanel14.validate();
		RightGraphPanel15.validate();
		RightGraphPanel16.validate();
		RightGraphPanel17.validate();
		RightGraphPanel18.validate();
		RightGraphPanel19.validate();
		RightGraphPanel20.validate();
		RightGraphPanel21.validate();
		RightGraphPanel22.validate();

		Rcard1.validate();
		Rcard2.validate();
		Rcard3.validate();
		Rcard4.validate();
		Rcard5.validate();
		Rcard6.validate();
		Rcard7.validate();
		Rcard8.validate();
		Rcard9.validate();
		Rcard10.validate();
		Rcard11.validate();
		Rcard12.validate();
		Rcard13.validate();
		Rcard14.validate();
		Rcard15.validate();
		Rcard16.validate();
		Rcard17.validate();
		Rcard18.validate();
		Rcard19.validate();
		Rcard20.validate();
		Rcard21.validate();
		Rcard22.validate();

		RightGraphCards.validate();

		doneonce = true;
	}

	private Double[] formatDouble(Double[] inArray, DecimalFormat df3) {
		for (int counter = 0; counter < inArray.length; counter++) {
			df3.format(inArray[counter]);
		}

		return inArray;
	}

	private void setTime() {

		Calendar cal = Calendar.getInstance();
		Time = (double) cal.getTimeInMillis();
	}

	public Double getTime() {

		return Time;
	}


	private JMenu EditMenu;
	private JMenu FileMenu;
	private JMenuItem FileMenuClose;
	private JMenuItem FileMenuLoad;
	private JMenuItem FileMenuSave;
	private JPanel LeftGraphPanel1;
	private JPanel LeftGraphPanel2;
	private JPanel LeftGraphPanel3;
	private JPanel LeftGraphPanel4;
	private JPanel LeftGraphPanel5;
	private JPanel LeftGraphPanel6;
	private JPanel LeftGraphPanel7;
	private JPanel LeftGraphPanel8;
	private JPanel LeftGraphPanel9;
	private JPanel LeftGraphPanel10;
	private JPanel LeftGraphPanel11;
	private JPanel LeftGraphPanel12;
	private JPanel LeftGraphPanel13;
	private JPanel LeftGraphPanel14;
	private JPanel LeftGraphPanel15;
	private JPanel LeftGraphPanel16;
	private JPanel LeftGraphPanel17;
	private JPanel LeftGraphPanel18;
	private JPanel LeftGraphPanel19;
	private JPanel LeftGraphPanel20;
	private JPanel LeftGraphPanel21;
	private JPanel LeftGraphPanel22;
	private JPanel RightGraphPanel1;
	private JPanel RightGraphPanel2;
	private JPanel RightGraphPanel3;
	private JPanel RightGraphPanel4;
	private JPanel RightGraphPanel5;
	private JPanel RightGraphPanel6;
	private JPanel RightGraphPanel7;
	private JPanel RightGraphPanel8;
	private JPanel RightGraphPanel9;
	private JPanel RightGraphPanel10;
	private JPanel RightGraphPanel11;
	private JPanel RightGraphPanel12;
	private JPanel RightGraphPanel13;
	private JPanel RightGraphPanel14;
	private JPanel RightGraphPanel15;
	private JPanel RightGraphPanel16;
	private JPanel RightGraphPanel17;
	private JPanel RightGraphPanel18;
	private JPanel RightGraphPanel19;
	private JPanel RightGraphPanel20;
	private JPanel RightGraphPanel21;
	private JPanel RightGraphPanel22;

	private JButton GraphAccelButton;
	private JLabel GraphAnalysisTitle;
	private JButton GraphAngleButton;
	private JButton GraphJerkButton;
	private JLabel lblGraphPressure;
	private JList GraphPressureList;
	private JLabel GraphTimeLabel;
	private JMenu HelpMenu;
	private JMenuItem HelpMenuAbout;
	private JMenuItem HelpMenuHelp;
	private JPanel LHAPanel;
	private JLabel LHImage;
	private JLayeredPane LHLayerPane;
	private JTable LHPTable;
	private JPanel LeftHandPressurePanel;
	private JScrollPane LeftHandPressureTablePane;
	private JLabel LeftHandTitle;
	private JPanel LiveDataPanel;
	private JPanel RHAPanel;
	private JLabel RHAccelLabel;
	private JLabel RHAngLabel;
	private JPanel RHPPanel;
	private JTable RHPTable;
	private JLabel RHVelLabel;
	private JScrollPane RightHandPressureTablePane;
	private JLabel RightHandTitle;
	private JPanel SavedDataPanel;
	private JButton StartButton;
	private JButton StopButton;
	private JLabel TabbedTitle;
	private JLabel lblLeftAccel;
	private JLabel lblLeftJerk;
	private JLabel lblLeftAngle;
	private JLabel RHImage;
	private JLabel W_E_Label;
	private JLabel Western_Label;
	private JLayeredPane RHLayerPane;
	private JMenuBar jMenuBar1;
	private JScrollPane jScrollPane1;
	private JTabbedPane jTabbedPane1;
	private JPanel RightGraphCards;
	private JPanel LeftGraphCards;
	private JTextField tfLeftAccel;
	private JTextField tfLeftJerk;
	private JTextField tfLeftAngle;
	private JTextField tfRightAccel;
	private JTextField tfRightJerk;
	private JTextField tfRightAngle;
	private JPanel LHSensorPanel;
	private JLabel LSensor1;
	private JLabel LSensor2;
	private JLabel LSensor3;
	private JLabel LSensor4;
	private JLabel LSensor5;
	private JLabel LSensor6;
	private JLabel LSensor7;
	private JLabel LSensor8;
	private JLabel LSensor9;
	private JLabel LSensor10;
	private JLabel LSensor11;
	private JLabel LSensor12;
	private JLabel LSensor13;
	private JLabel LSensor14;
	private JLabel LSensor15;
	private JLabel LSensor16;
	private JLabel LSensor17;
	private JLabel LSensor18;
	private JLabel LSensor19;
	private JPanel RHSensorPanel;
	private JLabel RSensor1;
	private JLabel RSensor2;
	private JLabel RSensor3;
	private JLabel RSensor4;
	private JLabel RSensor5;
	private JLabel RSensor6;
	private JLabel RSensor7;
	private JLabel RSensor8;
	private JLabel RSensor9;
	private JLabel RSensor10;
	private JLabel RSensor11;
	private JLabel RSensor12;
	private JLabel RSensor13;
	private JLabel RSensor14;
	private JLabel RSensor15;
	private JLabel RSensor16;
	private JLabel RSensor17;
	private JLabel RSensor18;
	private JLabel RSensor19;
	private CardLayout LeftCardLayout;
	private CardLayout RightCardLayout;
	private JPanel Rcard1;
	private JPanel Rcard2;
	private JPanel Rcard3;
	private JPanel Rcard4;
	private JPanel Rcard5;
	private JPanel Rcard6;
	private JPanel Rcard7;
	private JPanel Rcard8;
	private JPanel Rcard9;
	private JPanel Rcard10;
	private JPanel Rcard11;
	private JPanel Rcard12;
	private JPanel Rcard13;
	private JPanel Rcard14;
	private JPanel Rcard15;
	private JPanel Rcard16;
	private JPanel Rcard17;
	private JPanel Rcard18;
	private JPanel Rcard19;
	private JPanel Rcard20;
	private JPanel Rcard21;
	private JPanel Rcard22;

	private JPanel Lcard1;
	private JPanel Lcard2;
	private JPanel Lcard3;
	private JPanel Lcard4;
	private JPanel Lcard5;
	private JPanel Lcard6;
	private JPanel Lcard7;
	private JPanel Lcard8;
	private JPanel Lcard9;
	private JPanel Lcard10;
	private JPanel Lcard11;
	private JPanel Lcard12;
	private JPanel Lcard13;
	private JPanel Lcard14;
	private JPanel Lcard15;
	private JPanel Lcard16;
	private JPanel Lcard17;
	private JPanel Lcard18;
	private JPanel Lcard19;
	private JPanel Lcard20;
	private JPanel Lcard21;
	private JPanel Lcard22;

	private GroupLayout GraphLayout1;
	private GloveGraph LeftGraph;
	private GloveGraph RightGraph;
	private GroupLayout SavedDataPanelLayout;
	private GroupLayout LeftGraphPanelLayout1;
	private static final long serialVersionUID1 = 1L;
	private DataGroup RightHandData;
	private DataGroup LeftHandData;

	private Double Time;
	private Double LeftAccel;
	private Double LeftJerk;
	private Double LeftAngle;
	private Double[] LeftPressures;
	private Double RightAccel;
	private Double RightJerk;
	private Double RightAngle;
	private Double[] RightPressures;
	private boolean Writable;
	private ArrayList<Double> TimeArray;
	private ArrayList<Double> RAccelArray;
	private ArrayList<Double> RJerkArray;
	private ArrayList<Double> RAngleArray;
	private ArrayList<Double> RSensor1Array;
	private ArrayList<Double> RSensor2Array;
	private ArrayList<Double> RSensor3Array;
	private ArrayList<Double> RSensor4Array;
	private ArrayList<Double> RSensor5Array;
	private ArrayList<Double> RSensor6Array;
	private ArrayList<Double> RSensor7Array;
	private ArrayList<Double> RSensor8Array;
	private ArrayList<Double> RSensor9Array;
	private ArrayList<Double> RSensor10Array;
	private ArrayList<Double> RSensor11Array;
	private ArrayList<Double> RSensor12Array;
	private ArrayList<Double> RSensor13Array;
	private ArrayList<Double> RSensor14Array;
	private ArrayList<Double> RSensor15Array;
	private ArrayList<Double> RSensor16Array;
	private ArrayList<Double> RSensor17Array;
	private ArrayList<Double> RSensor18Array;
	private ArrayList<Double> RSensor19Array;
	private ArrayList<Double> LAccelArray;
	private ArrayList<Double> LJerkArray;
	private ArrayList<Double> LAngleArray;
	private ArrayList<Double> LSensor1Array;
	private ArrayList<Double> LSensor2Array;
	private ArrayList<Double> LSensor3Array;
	private ArrayList<Double> LSensor4Array;
	private ArrayList<Double> LSensor5Array;
	private ArrayList<Double> LSensor6Array;
	private ArrayList<Double> LSensor7Array;
	private ArrayList<Double> LSensor8Array;
	private ArrayList<Double> LSensor9Array;
	private ArrayList<Double> LSensor10Array;
	private ArrayList<Double> LSensor11Array;
	private ArrayList<Double> LSensor12Array;
	private ArrayList<Double> LSensor13Array;
	private ArrayList<Double> LSensor14Array;
	private ArrayList<Double> LSensor15Array;
	private ArrayList<Double> LSensor16Array;
	private ArrayList<Double> LSensor17Array;
	private ArrayList<Double> LSensor18Array;
	private ArrayList<Double> LSensor19Array;
	private ArrayList<ArrayList<Double>> LeftHandPressures;
	private ArrayList<ArrayList<Double>> RightHandPressures;
	private DecimalFormat df = new DecimalFormat("##.#####");
	private DecimalFormat df2 = new DecimalFormat("####.##");

	private boolean doneonce;

}
