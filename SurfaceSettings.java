import java.awt.*;
import javax.swing.*;

public class SurfaceSettings extends JPanel{
	private Configuration config=null;
	private Setup setup=null;
	private JFrame win=null;

	private Font fntTabbed=new Font("Courier", Font.PLAIN,20);

	private AircraftDirectorySelect aircraftDirectorySelect=null;
	private TerrainDirectorySelect terrainDirectorySelect=null;
	private DefaultProtocolSelect defaultProtocolSelect=null;
	private StartFlightgear startFlightgear=null;

	private JTabbedPane tabbed=new JTabbedPane(JTabbedPane.LEFT);

	public SurfaceSettings(Configuration c, Setup s) {
		config=c;
		setup=s;
		win=config.getMainWindow();

		aircraftDirectorySelect=new AircraftDirectorySelect(config);
		terrainDirectorySelect=new TerrainDirectorySelect(config);
		defaultProtocolSelect=new DefaultProtocolSelect(config);

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		tabbed.setFont(fntTabbed);
		add(tabbed);

		layout1.putConstraint(SpringLayout.NORTH, tabbed, 1, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, tabbed, 1, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, tabbed, -1, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, tabbed, -1, SpringLayout.EAST, this); 

JPanel test4=new JPanel();
test4.setBackground(Color.CYAN);

		tabbed.addTab("Aircraft Directories", aircraftDirectorySelect);
		tabbed.addTab("Terrain Directories", terrainDirectorySelect);
		tabbed.addTab("Default Protocols", defaultProtocolSelect);
		tabbed.addTab("JAFVA Position", test4);
	}

	public void activate() {
	}

}
