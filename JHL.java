import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JHL extends JFrame implements WindowListener {

	private Configuration config=new Configuration(this);
	private Setup setup=new Setup(config);
	private Surface surface=new Surface(config, setup);

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
		setVisible(true);
	}

	public void stopProgram() {
		setVisible(false);
		dispose();
		System.exit(0);
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
