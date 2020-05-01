import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class DefaultJAFVASelect extends JPanel implements ActionListener {

	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	JLabel lblIsJafva=new JLabel("Activate JAFVA functions");
	JCheckBox chkIsJafva=new JCheckBox();
	JLabel lblJafvaName=new JLabel("Your JAFVA name (Pilot Nickname)");
	JTextField txtJafvaName=new JTextField("");
	JButton btnSave=new JButton("Save");

	public DefaultJAFVASelect(Configuration c) {
		config=c;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		lblIsJafva.setFont(fntPanel);
		lblIsJafva.setBackground(Color.YELLOW);
		lblIsJafva.setOpaque(true);
		add(lblIsJafva);

		chkIsJafva.setFont(fntPanel);
		chkIsJafva.setBackground(Color.YELLOW);
		chkIsJafva.setOpaque(true);
		add(chkIsJafva);

		lblJafvaName.setFont(fntPanel);
		lblJafvaName.setBackground(Color.YELLOW);
		lblJafvaName.setOpaque(true);
		add(lblJafvaName);

		txtJafvaName.setFont(fntPanel);
		txtJafvaName.setBackground(Color.YELLOW);
		txtJafvaName.setOpaque(true);
		add(txtJafvaName);

		btnSave.setFont(fntPanel);
		btnSave.addActionListener(this);
		add(btnSave);

		layout1.putConstraint(SpringLayout.NORTH, lblIsJafva, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, lblIsJafva, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblIsJafva, 410, SpringLayout.WEST, this); 

		layout1.putConstraint(SpringLayout.NORTH, chkIsJafva, 0, SpringLayout.NORTH, lblIsJafva); 
		layout1.putConstraint(SpringLayout.WEST, chkIsJafva, 10, SpringLayout.EAST, lblIsJafva); 

		layout1.putConstraint(SpringLayout.NORTH, lblJafvaName, 10, SpringLayout.SOUTH, lblIsJafva); 
		layout1.putConstraint(SpringLayout.WEST, lblJafvaName, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblJafvaName, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtJafvaName, 0, SpringLayout.NORTH, lblJafvaName); 
		layout1.putConstraint(SpringLayout.WEST, txtJafvaName, 10, SpringLayout.EAST, lblJafvaName); 
		layout1.putConstraint(SpringLayout.EAST, txtJafvaName, 250, SpringLayout.WEST, txtJafvaName); 

		layout1.putConstraint(SpringLayout.NORTH, btnSave, 40, SpringLayout.SOUTH, txtJafvaName); 
		layout1.putConstraint(SpringLayout.WEST, btnSave, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, btnSave, 410, SpringLayout.WEST, this); 
		setData();
	}

	private void setData() {
		chkIsJafva.setSelected(config.getJafva());
		txtJafvaName.setText(config.getJafvaName());
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnSave) {
			config.setJafva(chkIsJafva.isSelected());
			config.setJafvaName(txtJafvaName.getText());
		}
	}

}
