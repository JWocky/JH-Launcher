import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class HangarOverview extends JPanel implements TreeSelectionListener {
	private Configuration config=null;
	private AircraftBuffer buffer=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);
	private Font fntPanelBig=new Font("Courier", Font.BOLD,40);

	private JLabel lblCurrSelected=new JLabel("Currently loaded");
	private JTextField txtCurrSelected=new JTextField("");

	public HangarOverview(Configuration c, AircraftBuffer a) {
		config=c;
		buffer=a;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		lblCurrSelected.setFont(fntPanel);
		lblCurrSelected.setBackground(Color.YELLOW);
		lblCurrSelected.setOpaque(true);
		add(lblCurrSelected);

		txtCurrSelected.setText(buffer.getName());
		txtCurrSelected.setFont(fntPanelBig);
		txtCurrSelected.setBackground(Color.YELLOW);
		txtCurrSelected.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrSelected.setEditable(false);
		add(txtCurrSelected);

		layout1.putConstraint(SpringLayout.NORTH, lblCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, lblCurrSelected, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblCurrSelected, 250, SpringLayout.WEST, lblCurrSelected); 

		layout1.putConstraint(SpringLayout.NORTH, txtCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, txtCurrSelected, 10, SpringLayout.EAST, lblCurrSelected); 
		layout1.putConstraint(SpringLayout.EAST, txtCurrSelected, -10, SpringLayout.EAST, this); 

	}

	public void updateBuffer() {
		txtCurrSelected.setText(buffer.getName());
	}


	public void valueChanged(TreeSelectionEvent tse) {
//		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)treAirports.getLastSelectedPathComponent();
//		String helpstr=(String)selectedNode.getUserObject();
//		int posi=helpstr.indexOf(".threshold.");
//		helpstr=helpstr.substring(0, posi);
//		txtCurrSelected.setText(helpstr);
//		config.setSelectedAirport(helpstr);
	}

}
