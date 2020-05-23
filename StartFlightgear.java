import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StartFlightgear extends JPanel implements ActionListener {
	private Configuration config=null;
	private Setup setup=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	private JButton btnStart=new JButton("<html>To start Flightgear<br>PRESS THIS GIANT YELLOW BUTTON!</html>");
	private JTextArea txtMessages=new JTextArea("");

	private String buffer="";

	public StartFlightgear(Configuration c, Setup s) {
		config=c;
		setup=s;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		btnStart.setFont(fntPanel);
		btnStart.setBackground(Color.YELLOW);
		btnStart.addActionListener(this);
		add(btnStart);

		txtMessages.setFont(fntPanel);
		add(txtMessages);

		layout1.putConstraint(SpringLayout.NORTH, btnStart, 20, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, btnStart, 20, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, btnStart, 60, SpringLayout.NORTH, btnStart); 
		layout1.putConstraint(SpringLayout.EAST, btnStart, -20, SpringLayout.EAST, this); 

		layout1.putConstraint(SpringLayout.NORTH, txtMessages, 10, SpringLayout.SOUTH, btnStart); 
		layout1.putConstraint(SpringLayout.WEST, txtMessages, 20, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, txtMessages, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, txtMessages, -20, SpringLayout.EAST, this); 
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
		if (!buffer.equals("")) {
			buffer=buffer+"\n";
		}
		buffer=buffer+l;
		txtMessages.setText(buffer);
	}

}
