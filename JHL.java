import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JHL extends JFrame implements WindowListener, ActionListener {

	private Configuration config=new Configuration(this);
	private Setup setup=new Setup(config);
	private SurfaceLauncher surfaceLauncher=new SurfaceLauncher(config, setup);
	private SurfaceSettings surfaceSettings=new SurfaceSettings(config, setup);

	private Font fntMenu=new Font("Courier", Font.BOLD,20);

	private JMenu mnuLauncher=new JMenu("Launcher");
	private JMenuItem itmLauncher=new JMenuItem("Launcher");
	private JMenuItem itmExit=new JMenuItem("Exit");

	private JMenu mnuSettings=new JMenu("Settings");
	private JMenuItem itmLauncherSettings=new JMenuItem("Launcher Settings");

	private int activeSurface=1;
	private SpringLayout layout=null;

	public static void main(String[] args) {
		JHL launcher=new JHL();
	}
	
	public JHL() {
		super("JWocky's Toybox");
		setSize(600, 400);
		setLocation(50, 50);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		addWindowListener(this);
		
		mnuLauncher.setFont(fntMenu);
		
		itmLauncher.setFont(fntMenu);
		itmLauncher.addActionListener(this);

		itmExit.setFont(fntMenu);
		itmExit.addActionListener(this);

		mnuLauncher.add(itmLauncher);
		mnuLauncher.add(new JSeparator());
		mnuLauncher.add(itmExit);

		mnuSettings.setFont(fntMenu);

		itmLauncherSettings.setFont(fntMenu);
		itmLauncherSettings.addActionListener(this);

		mnuSettings.add(itmLauncherSettings);

		JMenuBar bar=new JMenuBar();
		bar.add(mnuLauncher);
		bar.add(mnuSettings);

		setJMenuBar(bar);

		layout=new SpringLayout();
		setLayout(layout);

		getContentPane().add(surfaceLauncher);

		layout.putConstraint(SpringLayout.NORTH, surfaceLauncher, 1, SpringLayout.NORTH, getContentPane()); 
		layout.putConstraint(SpringLayout.WEST, surfaceLauncher, 1, SpringLayout.WEST, getContentPane()); 
		layout.putConstraint(SpringLayout.SOUTH, surfaceLauncher, -1, SpringLayout.SOUTH, getContentPane()); 
		layout.putConstraint(SpringLayout.EAST, surfaceLauncher, -1, SpringLayout.EAST, getContentPane()); 

		activeSurface=1;		

		setVisible(true);
	}

	public void stopProgram() {
		config.writeConfig();
		setVisible(false);
		dispose();
		System.exit(0);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==itmLauncher) {
			if (activeSurface!=1) {
				getContentPane().removeAll();
				getContentPane().add(surfaceLauncher);
				layout.putConstraint(SpringLayout.NORTH, surfaceLauncher, 1, SpringLayout.NORTH, getContentPane()); 
				layout.putConstraint(SpringLayout.WEST, surfaceLauncher, 1, SpringLayout.WEST, getContentPane()); 
				layout.putConstraint(SpringLayout.SOUTH, surfaceLauncher, -1, SpringLayout.SOUTH, getContentPane()); 
				layout.putConstraint(SpringLayout.EAST, surfaceLauncher, -1, SpringLayout.EAST, getContentPane()); 
				activeSurface=1;
				pack();
				repaint();
			}
		}
		if (ae.getSource()==itmExit) {
			stopProgram();
		}
		if (ae.getSource()==itmLauncherSettings) {
			if (activeSurface!=2) {
				getContentPane().removeAll();
				add(surfaceSettings);
				layout.putConstraint(SpringLayout.NORTH, surfaceSettings, 1, SpringLayout.NORTH, getContentPane()); 
				layout.putConstraint(SpringLayout.WEST, surfaceSettings, 1, SpringLayout.WEST, getContentPane()); 
				layout.putConstraint(SpringLayout.SOUTH, surfaceSettings, -1, SpringLayout.SOUTH, getContentPane()); 
				layout.putConstraint(SpringLayout.EAST, surfaceSettings, -1, SpringLayout.EAST, getContentPane()); 
				activeSurface=2;
				pack();
				repaint();
			}
		}
	}

	public void windowActivated(WindowEvent we) {
	}

	public void windowClosed(WindowEvent we) {
	}

	public void windowClosing(WindowEvent we) {
		stopProgram();
	}

	public void windowDeactivated(WindowEvent we) {
	}

	public void windowDeiconified(WindowEvent we) {
	}

	public void windowIconified(WindowEvent we) {
	}

	public void windowOpened(WindowEvent we) {
	}

}
