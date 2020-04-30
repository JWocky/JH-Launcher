import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class DefaultProtocolSelect extends JPanel implements ActionListener, ListSelectionListener {

	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	private LinkedList<ProtocolEntry> buffer=null;
	private JList<ProtocolEntry> protocols=new JList<ProtocolEntry>(new DefaultListModel());
	private JButton btnAdd=new JButton("Add Protocol");
	private JButton btnRemove=new JButton("Remove Protocol");
	private JTextField txtProtocol=new JTextField("");
	private JButton btnSave=new JButton("Save Protocol");

	public DefaultProtocolSelect(Configuration c) {
		config=c;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		protocols.setFont(fntPanel);
		protocols.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		protocols.addListSelectionListener(this);
		loadProtocols();
		add(protocols);

		btnAdd.setFont(fntPanel);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnRemove.setFont(fntPanel);
		btnRemove.addActionListener(this);
		add(btnRemove);

		btnSave.setFont(fntPanel);
		btnSave.addActionListener(this);
		add(btnSave);

		txtProtocol.setFont(fntPanel);
		add(txtProtocol);

		layout1.putConstraint(SpringLayout.NORTH, protocols, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, protocols, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, protocols, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, protocols, 610, SpringLayout.WEST, this); 

		layout1.putConstraint(SpringLayout.NORTH, btnAdd, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, btnAdd, 10, SpringLayout.EAST, protocols); 
		layout1.putConstraint(SpringLayout.EAST, btnAdd, 250, SpringLayout.WEST, btnAdd); 

		layout1.putConstraint(SpringLayout.NORTH, btnRemove, 10, SpringLayout.SOUTH, btnAdd); 
		layout1.putConstraint(SpringLayout.WEST, btnRemove, 10, SpringLayout.EAST, protocols); 
		layout1.putConstraint(SpringLayout.EAST, btnRemove, 250, SpringLayout.WEST, btnRemove); 

		layout1.putConstraint(SpringLayout.NORTH, txtProtocol, 40, SpringLayout.SOUTH, btnRemove); 
		layout1.putConstraint(SpringLayout.WEST, txtProtocol, 10, SpringLayout.EAST, protocols); 
		layout1.putConstraint(SpringLayout.EAST, txtProtocol, -10, SpringLayout.EAST, this); 

		layout1.putConstraint(SpringLayout.NORTH, btnSave, 10, SpringLayout.SOUTH, txtProtocol); 
		layout1.putConstraint(SpringLayout.WEST, btnSave, 10, SpringLayout.EAST, protocols); 
		layout1.putConstraint(SpringLayout.EAST, btnSave, 250, SpringLayout.WEST, btnSave); 

		setButtons();
	}

	private void loadProtocols() {
		DefaultListModel dlmProtocols=(DefaultListModel)protocols.getModel();
		dlmProtocols.removeAllElements();
		buffer=config.getProtocols();
		for (int i=0; i<buffer.size(); i++) {
			dlmProtocols.addElement(buffer.get(i));
		}		
	}

	private void setButtons() {
		if (protocols.getSelectedIndex()==-1) {
			btnRemove.setEnabled(false);
			txtProtocol.setText("");
		} else {
			btnRemove.setEnabled(true);
			txtProtocol.setText(protocols.getSelectedValue().toString());
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnAdd) {
			protocols.setSelectedIndex(-1);
			txtProtocol.setText("");
		}
		if (ae.getSource()==btnRemove) {
			if (protocols.getSelectedIndex()>=0) {
				ProtocolEntry proto=protocols.getSelectedValue();
				int found=buffer.indexOf(proto);
				buffer.remove(found);
				loadProtocols();
			}
		}
		if (ae.getSource()==btnSave) {
			if (protocols.getSelectedIndex()>=0) {
				ProtocolEntry proto=protocols.getSelectedValue();
				int found=buffer.indexOf(proto);
				ProtocolEntry proto2=buffer.get(found);
				proto2.setParameters(txtProtocol.getText());
				loadProtocols();
			} else {
				ProtocolEntry proto=new ProtocolEntry();
				proto.setParameters(txtProtocol.getText());
				buffer.add(proto);
				loadProtocols();
			}
		}
	}

	public void valueChanged(ListSelectionEvent lse) {
		setButtons();
	}

}
