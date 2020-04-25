import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartFlightgear extends JPanel implements ActionListener {
	private Configuration config=null;
	private Setup setup=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	private JButton btnStart=new JButton("Start Flightgear");
	private JTextField txtMessages=new JTextField("");

	private String buffer="";

	public StartFlightgear(Configuration c, Setup s) {
		config=c;
		setup=s;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		btnStart.setFont(fntPanel);
		btnStart.addActionListener(this);
		add(btnStart);

		txtMessages.setFont(fntPanel);
		add(txtMessages);

		layout1.putConstraint(SpringLayout.NORTH, btnStart, 1, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, btnStart, 1, SpringLayout.WEST, this); 
//		layout1.putConstraint(SpringLayout.SOUTH, btnStart, -1, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, btnStart, -1, SpringLayout.EAST, this); 

		layout1.putConstraint(SpringLayout.NORTH, txtMessages, 10, SpringLayout.SOUTH, btnStart); 
		layout1.putConstraint(SpringLayout.WEST, txtMessages, 5, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, txtMessages, -5, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, txtMessages, -5, SpringLayout.EAST, this); 
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnStart) {
			if (setup.checkSetup()) {
				buffer="";
				txtMessages.setText(buffer);
				setup.startFG(this);
			}
		}
	}

	public void addLine(String l) {
		buffer=buffer+l;
		txtMessages.setText(buffer);
	}

}
