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
	private StartFlightgear startFlightgear=null;

	private JTabbedPane tabbed=new JTabbedPane(JTabbedPane.LEFT);

	public SurfaceLauncher(Configuration c, Setup s) {
		config=c;
		setup=s;
		win=config.getMainWindow();

		aircraftSelect=new AircraftSelect(config);
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

JPanel test2=new JPanel();
test2.setBackground(Color.BLUE);
JPanel test3=new JPanel();
test3.setBackground(Color.GREEN);
JPanel test4=new JPanel();
test4.setBackground(Color.CYAN);

		tabbed.addTab("Select Aircraft", aircraftSelect);
		tabbed.addTab("Select Airport", test2);
		tabbed.addTab("Protocols", test3);
		tabbed.addTab("JAFVA", test4);
		tabbed.addTab("Start FlightGear", startFlightgear);
	}

	public void activate() {
	}

}
