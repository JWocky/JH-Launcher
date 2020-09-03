import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class DefaultMPSelect extends JPanel implements ActionListener, ListSelectionListener {
	private Configuration config=null;

	private LinkedList<ServerEntry> servers=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	private JLabel lblGoMP=new JLabel("Activate multiplayer functions");
	private JCheckBox chkGoMP=new JCheckBox();
	
	DefaultListModel<String> dlmServers=new DefaultListModel<String>();
	private JList<String> lstServers=new JList<String>(dlmServers);
	private JScrollPane scrServers=null;


	private JLabel lblName=new JLabel("Name");
	private JTextField txtName=new JTextField();
		
	private JLabel lblHostname=new JLabel("Hostname");
	private JTextField txtHostname=new JTextField();
		
	private JLabel lblLocation=new JLabel("Location");
	private JTextField txtLocation=new JTextField();
		
	private JLabel lblInPort=new JLabel("inport");
	private JTextField txtInPort=new JTextField();
		
	private JLabel lblOutPort=new JLabel("outport");
	private JTextField txtOutPort=new JTextField();
		
	private JLabel lblInHz=new JLabel("in Hz");
	private JTextField txtInHz=new JTextField();
		
	private JLabel lblOutHz=new JLabel("out Hz");
	private JTextField txtOutHz=new JTextField();
	
	private JButton btnSave=new JButton("Save");
	private JButton btnRemove=new JButton("Remove");
	private JButton btnNew=new JButton("New");
	
	private JButton btnServer1=new JButton("Primary Server");
	private JTextField txtServer1=new JTextField();
	private JButton btnServer2=new JButton("Secondary Server");
	private JTextField txtServer2=new JTextField();
	private JButton btnServer3=new JButton("Tertiary Server");
	private JTextField txtServer3=new JTextField();
		


	public DefaultMPSelect(Configuration c) {
		config=c;
		servers=config.getServers();
		
		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		lblGoMP.setFont(fntPanel);
		lblGoMP.setBackground(Color.YELLOW);
		lblGoMP.setOpaque(true);
		add(lblGoMP);

		lblGoMP.setFont(fntPanel);
		lblGoMP.setBackground(Color.YELLOW);
		lblGoMP.setOpaque(true);
		add(lblGoMP);

		chkGoMP.setFont(fntPanel);
		chkGoMP.setBackground(Color.YELLOW);
		chkGoMP.setOpaque(true);
		chkGoMP.addActionListener(this);
		add(chkGoMP);

		lstServers.setFont(fntPanel);
		lstServers.addListSelectionListener(this);
		scrServers=new JScrollPane(
			lstServers, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		add(scrServers);

		lblName.setFont(fntPanel);
		lblName.setBackground(Color.YELLOW);
		lblName.setOpaque(true);
		add(lblName);

		txtName.setFont(fntPanel);
		txtName.setBackground(Color.YELLOW);
		txtName.setOpaque(true);
		add(txtName);

		lblHostname.setFont(fntPanel);
		lblHostname.setBackground(Color.YELLOW);
		lblHostname.setOpaque(true);
		add(lblHostname);

		txtHostname.setFont(fntPanel);
		txtHostname.setBackground(Color.YELLOW);
		txtHostname.setOpaque(true);
		add(txtHostname);

		lblLocation.setFont(fntPanel);
		lblLocation.setBackground(Color.YELLOW);
		lblLocation.setOpaque(true);
		add(lblLocation);

		txtLocation.setFont(fntPanel);
		txtLocation.setBackground(Color.YELLOW);
		txtLocation.setOpaque(true);
		add(txtLocation);

		lblInPort.setFont(fntPanel);
		lblInPort.setBackground(Color.YELLOW);
		lblInPort.setOpaque(true);
		add(lblInPort);

		txtInPort.setFont(fntPanel);
		txtInPort.setBackground(Color.YELLOW);
		txtInPort.setOpaque(true);
		add(txtInPort);

		lblOutPort.setFont(fntPanel);
		lblOutPort.setBackground(Color.YELLOW);
		lblOutPort.setOpaque(true);
		add(lblOutPort);

		txtOutPort.setFont(fntPanel);
		txtOutPort.setBackground(Color.YELLOW);
		txtOutPort.setOpaque(true);
		add(txtOutPort);

		lblInHz.setFont(fntPanel);
		lblInHz.setBackground(Color.YELLOW);
		lblInHz.setOpaque(true);
		add(lblInHz);

		txtInHz.setFont(fntPanel);
		txtInHz.setBackground(Color.YELLOW);
		txtInHz.setOpaque(true);
		add(txtInHz);

		lblOutHz.setFont(fntPanel);
		lblOutHz.setBackground(Color.YELLOW);
		lblOutHz.setOpaque(true);
		add(lblOutHz);

		txtOutHz.setFont(fntPanel);
		txtOutHz.setBackground(Color.YELLOW);
		txtOutHz.setOpaque(true);
		add(txtOutHz);

		btnSave.setFont(fntPanel);
		btnSave.setBackground(Color.YELLOW);
		btnSave.setOpaque(true);
		btnSave.addActionListener(this);
		add(btnSave);

		btnRemove.setFont(fntPanel);
		btnRemove.setBackground(Color.YELLOW);
		btnRemove.setOpaque(true);
		btnRemove.addActionListener(this);
		add(btnRemove);

		btnNew.setFont(fntPanel);
		btnNew.setBackground(Color.YELLOW);
		btnNew.setOpaque(true);
		btnNew.addActionListener(this);
		add(btnNew);

		btnServer1.setFont(fntPanel);
		btnServer1.setBackground(Color.YELLOW);
		btnServer1.setOpaque(true);
		btnServer1.addActionListener(this);
		add(btnServer1);

		txtServer1.setFont(fntPanel);
		txtServer1.setBackground(Color.YELLOW);
		txtServer1.setOpaque(true);
		add(txtServer1);

		btnServer2.setFont(fntPanel);
		btnServer2.setBackground(Color.YELLOW);
		btnServer2.setOpaque(true);
		btnServer2.addActionListener(this);
		add(btnServer2);

		txtServer2.setFont(fntPanel);
		txtServer2.setBackground(Color.YELLOW);
		txtServer2.setOpaque(true);
		add(txtServer2);

		btnServer3.setFont(fntPanel);
		btnServer3.setBackground(Color.YELLOW);
		btnServer3.setOpaque(true);
		btnServer3.addActionListener(this);
		add(btnServer3);

		txtServer3.setFont(fntPanel);
		txtServer3.setBackground(Color.YELLOW);
		txtServer3.setOpaque(true);
		add(txtServer3);

		layout1.putConstraint(SpringLayout.NORTH, lblGoMP, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, lblGoMP, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblGoMP, 410, SpringLayout.WEST, this); 

		layout1.putConstraint(SpringLayout.NORTH, chkGoMP, 0, SpringLayout.NORTH, lblGoMP); 
		layout1.putConstraint(SpringLayout.WEST, chkGoMP, 10, SpringLayout.EAST, lblGoMP); 

		layout1.putConstraint(SpringLayout.NORTH, scrServers, 40, SpringLayout.SOUTH, lblGoMP); 
		layout1.putConstraint(SpringLayout.WEST, scrServers, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, scrServers, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, scrServers, 410, SpringLayout.WEST, this); 

		layout1.putConstraint(SpringLayout.NORTH, lblName, 0, SpringLayout.NORTH, scrServers); 
		layout1.putConstraint(SpringLayout.WEST, lblName, 10, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, lblName, 160, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, txtName, 0, SpringLayout.NORTH, lblName); 
		layout1.putConstraint(SpringLayout.WEST, txtName, 170, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, txtName, 650, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, lblHostname, 10, SpringLayout.SOUTH, lblName); 
		layout1.putConstraint(SpringLayout.WEST, lblHostname, 10, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, lblHostname, 160, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, txtHostname, 0, SpringLayout.NORTH, lblHostname); 
		layout1.putConstraint(SpringLayout.WEST, txtHostname, 170, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, txtHostname, 650, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, lblLocation, 10, SpringLayout.SOUTH, lblHostname); 
		layout1.putConstraint(SpringLayout.WEST, lblLocation, 10, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, lblLocation, 160, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, txtLocation, 0, SpringLayout.NORTH, lblLocation); 
		layout1.putConstraint(SpringLayout.WEST, txtLocation, 170, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, txtLocation, 650, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, lblInPort, 20, SpringLayout.SOUTH, lblLocation); 
		layout1.putConstraint(SpringLayout.WEST, lblInPort, 10, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, lblInPort, 160, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, txtInPort, 0, SpringLayout.NORTH, lblInPort); 
		layout1.putConstraint(SpringLayout.WEST, txtInPort, 170, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, txtInPort, 650, SpringLayout.EAST, scrServers); 
	
		layout1.putConstraint(SpringLayout.NORTH, lblOutPort, 10, SpringLayout.SOUTH, lblInPort); 
		layout1.putConstraint(SpringLayout.WEST, lblOutPort, 10, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, lblOutPort, 160, SpringLayout.EAST, scrServers); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtOutPort, 0, SpringLayout.NORTH, lblOutPort); 
		layout1.putConstraint(SpringLayout.WEST, txtOutPort, 170, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, txtOutPort, 650, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, lblInHz, 20, SpringLayout.SOUTH, lblOutPort); 
		layout1.putConstraint(SpringLayout.WEST, lblInHz, 10, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, lblInHz, 160, SpringLayout.EAST, scrServers); 

		layout1.putConstraint(SpringLayout.NORTH, txtInHz, 0, SpringLayout.NORTH, lblInHz); 
		layout1.putConstraint(SpringLayout.WEST, txtInHz, 170, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, txtInHz, 650, SpringLayout.EAST, scrServers); 
	
		layout1.putConstraint(SpringLayout.NORTH, lblOutHz, 10, SpringLayout.SOUTH, lblInHz); 
		layout1.putConstraint(SpringLayout.WEST, lblOutHz, 10, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, lblOutHz, 160, SpringLayout.EAST, scrServers); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtOutHz, 0, SpringLayout.NORTH, lblOutHz); 
		layout1.putConstraint(SpringLayout.WEST, txtOutHz, 170, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, txtOutHz, 650, SpringLayout.EAST, scrServers); 
	
		layout1.putConstraint(SpringLayout.NORTH, btnSave, 20, SpringLayout.SOUTH, lblOutHz); 
		layout1.putConstraint(SpringLayout.WEST, btnSave, 20, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, btnSave, 150, SpringLayout.WEST, btnSave); 
	
		layout1.putConstraint(SpringLayout.NORTH, btnRemove, 20, SpringLayout.SOUTH, lblOutHz); 
		layout1.putConstraint(SpringLayout.WEST, btnRemove, 20, SpringLayout.EAST, btnSave); 
		layout1.putConstraint(SpringLayout.EAST, btnRemove, 150, SpringLayout.WEST, btnRemove); 
	
		layout1.putConstraint(SpringLayout.NORTH, btnNew, 20, SpringLayout.SOUTH, lblOutHz); 
		layout1.putConstraint(SpringLayout.WEST, btnNew, 20, SpringLayout.EAST, btnRemove); 
		layout1.putConstraint(SpringLayout.EAST, btnNew, 150, SpringLayout.WEST, btnNew); 
	
		layout1.putConstraint(SpringLayout.NORTH, btnServer1, 40, SpringLayout.SOUTH, btnSave); 
		layout1.putConstraint(SpringLayout.WEST, btnServer1, 20, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, btnServer1, 300, SpringLayout.WEST, btnServer1); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtServer1, 0, SpringLayout.NORTH, btnServer1); 
		layout1.putConstraint(SpringLayout.WEST, txtServer1, 20, SpringLayout.EAST, btnServer1); 
		layout1.putConstraint(SpringLayout.EAST, txtServer1, 400, SpringLayout.WEST, txtServer1); 
	
		layout1.putConstraint(SpringLayout.NORTH, btnServer2, 10, SpringLayout.SOUTH, btnServer1); 
		layout1.putConstraint(SpringLayout.WEST, btnServer2, 20, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, btnServer2, 300, SpringLayout.WEST, btnServer2); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtServer2, 0, SpringLayout.NORTH, btnServer2); 
		layout1.putConstraint(SpringLayout.WEST, txtServer2, 20, SpringLayout.EAST, btnServer2); 
		layout1.putConstraint(SpringLayout.EAST, txtServer2, 400, SpringLayout.WEST, txtServer2); 
	
		layout1.putConstraint(SpringLayout.NORTH, btnServer3, 10, SpringLayout.SOUTH, btnServer2); 
		layout1.putConstraint(SpringLayout.WEST, btnServer3, 20, SpringLayout.EAST, scrServers); 
		layout1.putConstraint(SpringLayout.EAST, btnServer3, 300, SpringLayout.WEST, btnServer3); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtServer3, 0, SpringLayout.NORTH, btnServer3); 
		layout1.putConstraint(SpringLayout.WEST, txtServer3, 20, SpringLayout.EAST, btnServer3); 
		layout1.putConstraint(SpringLayout.EAST, txtServer3, 400, SpringLayout.WEST, txtServer3); 

		setData();
	}

	private void setData() {
		chkGoMP.setSelected(config.getGoMP());

		ServerEntry se=new ServerEntry();
		se.setName("mpserver01");
		se.setHostname("mpserver01.flightgear.org");
		se.setLocation("Frankfurt/Germany");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver03");
		se.setHostname("mpserver03.flightgear.org");
		se.setLocation("unknown/Germany");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver04");
		se.setHostname("mpserver04.flightgear.org");
		se.setLocation("unknown/United Kingdom");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver13");
		se.setHostname("mpserver13.fligthgear.org");
		se.setLocation("Grenoble/France");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver14");
		se.setHostname("mpserver14.flightgear.org");
		se.setLocation("Zurich/Switzerland");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver15");
		se.setHostname("mpserver15.flightgear.org");
		se.setLocation("Northpoint/Hong Kong");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver16");
		se.setHostname("mpserver16.flightgear.org");
		se.setLocation("Texas/USA");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver19");
		se.setHostname("mpserver19.flightgear.org");
		se.setLocation("London/United Kingdom");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver20");
		se.setHostname("mpserver20.flightgear.org");
		se.setLocation("South San Francisco/USA");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver21");
		se.setHostname("mpserver21.flightgear.org");
		se.setLocation("Singapore");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver22");
		se.setHostname("mpserver22.flightgear.org");
		se.setLocation("Munich/Germany");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver51");
		se.setHostname("mpserver51.flightgear.org");
		se.setLocation("Atlnt/USA");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("mpserver87");
		se.setHostname("mpserver87.flightgear.org");
		se.setLocation("Paris/France");
		se.setInPort("5000");
		se.setOutPort("5000");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		servers.add(se);

		se=new ServerEntry();
		se.setName("JAFVAmpserver");
		se.setHostname("172.93.103.204");
		se.setLocation("unknown/USA");
		se.setInPort("16605");
		se.setOutPort("16005");
		se.setInHz("10");
		se.setOutHz("20");
		se.setPinged(false);
		
		
		servers.add(se);
		dlmServers.removeAllElements();
		for (int i=0; i<servers.size(); i++){
			dlmServers.addElement(servers.get(i).getName());												
		}
	
wg
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==chkGoMP) {
			config.setJafva(chkGoMP.isSelected());
		}
	}
	
	pulic voidvalueChanged(SelectionEvent se){
		Sting selected=lstServers.getSelectedValue().toString();
		servers=config.getServers();
		for (int i=0;i <servers.Size(); i++){
			ServerEntry se=servers.get(i);
			if(se.getName().equals(selected))  {
				txtName.setText(se.getName());
				txtHostname.setText(se.getohstname());
				txtInPort.setText(se.getInPort());
				txtOutPort.setText(se.getOutPort());
				txtInHz.setText(se.getInHz());
				txtOutJz.setText(se.getOutHz());
			}
			
		}

	}
	

}
