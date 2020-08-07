import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class SurfaceHangar extends JPanel {
	private Configuration config=null;
	private Setup setup=null;
	private JFrame win=null;
	
	private AircraftBuffer buffer=new AircraftBuffer();

	private Font fntTabbed=new Font("Courier", Font.PLAIN,20);

	private HangarOverview hangarOverview=null;
//	private AirportSelect airportSelect=null;
//	private ProtocolSelect protocolSelect=null;
//	private JAFVASelect jafvaSelect=null;
//	private StartFlightgear startFlightgear=null;

	private JTabbedPane tabbed=new JTabbedPane(JTabbedPane.LEFT);

	public SurfaceHangar(Configuration c, Setup s) {
		config=c;
		config.setSurfaceHangar(this);
		setup=s;
		win=config.getMainWindow();

		hangarOverview=new HangarOverview(config, buffer);
//		airportSelect=new AirportSelect(config);
//		protocolSelect=new ProtocolSelect(config);
//		jafvaSelect=new JAFVASelect(config);
//		startFlightgear=new StartFlightgear(config, setup);

		SpringLayout layout=new SpringLayout();
		win.setLayout(layout);

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		tabbed.setFont(fntTabbed);
		add(tabbed);

		layout1.putConstraint(SpringLayout.NORTH, tabbed, 1, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, tabbed, 1, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, tabbed, -1, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, tabbed, -1, SpringLayout.EAST, this); 

		tabbed.addTab("Overview", hangarOverview);
//		tabbed.addTab("Select Airport", airportSelect);
//		tabbed.addTab("Protocols", protocolSelect);
//		tabbed.addTab("JAFVA", jafvaSelect);
//		tabbed.addTab("Start FlightGear", startFlightgear);
	}

	public void activate() {
	}
	
	public void reload_aircraft(String name) {
		buffer.clear();
		
		// 1st step find first occurance of this plane in aircraft-directory-list
		LinkedList<DirectoryEntry> directories=config.getAircraftDirectories();
		for (int i=0; i<directories.size(); i++) {
			DirectoryEntry de=directories.get(i);
			
			File folder=new File(de.getPath());
			if (folder.exists()) {
				File[] f=folder.listFiles();
				Arrays.sort(f);
				for (final File fileEntry : f) {
				        if (fileEntry.isDirectory()) {
						File[] g=fileEntry.listFiles();
						Arrays.sort(g);
						for (final File fileEntry2 : g) {
//							System.out.println(" TESTING "+fileEntry2.getName());
							if (fileEntry2.getName().startsWith(name) && fileEntry2.getName().endsWith("-set.xml")) {
								if (buffer.isClear()) {
									buffer.setName(name);
									buffer.setPath(fileEntry.getAbsolutePath());
									buffer.setRootfile(fileEntry2.getName());
									buffer.load();
									if (hangarOverview!=null) {
										hangarOverview.updateBuffer();
									}
								}
							}
						}
					}
			        }
		        }
		}
	}

}
