import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SurfaceLauncher extends JPanel {
	private Configuration config=null;
	private Setup setup=null;
	private JFrame win=null;

	private Font fntTabbed=new Font("Courier", Font.PLAIN,20);

	private AircraftSelect aircraftSelect=null;
	private AirportSelect airportSelect=null;
	private ProtocolSelect protocolSelect=null;
	private JAFVASelect jafvaSelect=null;
	private StartFlightgear startFlightgear=null;

	private JTabbedPane tabbed=new JTabbedPane(JTabbedPane.LEFT);

	public SurfaceLauncher(Configuration c, Setup s) {
		config=c;
		setup=s;
		win=config.getMainWindow();

		aircraftSelect=new AircraftSelect(config);
		airportSelect=new AirportSelect(config);
		protocolSelect=new ProtocolSelect(config);
		jafvaSelect=new JAFVASelect(config);
		startFlightgear=new StartFlightgear(config, setup);

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

		tabbed.addTab("Select Aircraft", aircraftSelect);
		tabbed.addTab("Select Airport", airportSelect);
		tabbed.addTab("Protocols", protocolSelect);
		tabbed.addTab("JAFVA", jafvaSelect);
		tabbed.addTab("Start FlightGear", startFlightgear);
	}

	public void activate() {
	}

}
