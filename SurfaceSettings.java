import java.awt.*;
import javax.swing.*;

public class SurfaceSettings extends JPanel{
	private Configuration config=null;
	private Setup setup=null;
	private JFrame win=null;

	private Font fntTabbed=new Font("Courier", Font.PLAIN,20);

	private RootDirectorySelect rootDirectorySelect=null;
	private AircraftDirectorySelect aircraftDirectorySelect=null;
	private TerrainDirectorySelect terrainDirectorySelect=null;
	private DefaultProtocolSelect defaultProtocolSelect=null;
	private DefaultJAFVASelect defaultJAFVASelect=null;

	private JTabbedPane tabbed=new JTabbedPane(JTabbedPane.LEFT);

	public SurfaceSettings(Configuration c, Setup s) {
		config=c;
		setup=s;
		win=config.getMainWindow();

		rootDirectorySelect=new RootDirectorySelect(config);
		aircraftDirectorySelect=new AircraftDirectorySelect(config);
		terrainDirectorySelect=new TerrainDirectorySelect(config);
		defaultProtocolSelect=new DefaultProtocolSelect(config);
		defaultJAFVASelect=new DefaultJAFVASelect(config);

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		tabbed.setFont(fntTabbed);
		add(tabbed);

		layout1.putConstraint(SpringLayout.NORTH, tabbed, 1, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, tabbed, 1, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, tabbed, -1, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, tabbed, -1, SpringLayout.EAST, this); 

		tabbed.addTab("Root Directory", rootDirectorySelect);
		tabbed.addTab("Aircraft Directories", aircraftDirectorySelect);
		tabbed.addTab("Terrain Directories", terrainDirectorySelect);
		tabbed.addTab("Default Protocols", defaultProtocolSelect);
		tabbed.addTab("JAFVA", defaultJAFVASelect);
	}

	public void activate() {
	}

}
