import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class AircraftSelect extends JPanel implements TreeSelectionListener {
	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);
	private Font fntPanelBig=new Font("Courier", Font.BOLD,40);

	JTree treAircraft=null;
	JScrollPane scrAircraft=null;
	JLabel lblCurrSelected=new JLabel("Currently Selected");
	JTextField txtCurrSelected=new JTextField("");

	public AircraftSelect(Configuration c) {
		config=c;
		config.setAircraftSelect(this);

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		treAircraft=buildTree();
		treAircraft.setFont(fntPanel);
		scrAircraft=new JScrollPane(
			treAircraft, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		add(scrAircraft);

		lblCurrSelected.setFont(fntPanel);
		lblCurrSelected.setBackground(Color.YELLOW);
		lblCurrSelected.setOpaque(true);
		add(lblCurrSelected);

		txtCurrSelected.setFont(fntPanelBig);
		txtCurrSelected.setBackground(Color.YELLOW);
		txtCurrSelected.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrSelected.setEditable(false);
		add(txtCurrSelected);

		layout1.putConstraint(SpringLayout.NORTH, scrAircraft, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, scrAircraft, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, scrAircraft, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, scrAircraft, 610, SpringLayout.WEST, scrAircraft); 

		layout1.putConstraint(SpringLayout.NORTH, lblCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, lblCurrSelected, 10, SpringLayout.EAST, scrAircraft); 
		layout1.putConstraint(SpringLayout.EAST, lblCurrSelected, 250, SpringLayout.WEST, lblCurrSelected); 

		layout1.putConstraint(SpringLayout.NORTH, txtCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, txtCurrSelected, 10, SpringLayout.EAST, lblCurrSelected); 
		layout1.putConstraint(SpringLayout.EAST, txtCurrSelected, -10, SpringLayout.EAST, this); 

	}

	private JTree buildTree() {
		LinkedList<DirectoryEntry> buffer=config.getAircraftDirectories();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Aircraft");
		for (int i=0; i<buffer.size(); i++) {
			DefaultMutableTreeNode direc=new DefaultMutableTreeNode(buffer.get(i).getPath());
			File folder=new File(buffer.get(i).getPath());
			if (folder.exists()) {
				root.add(direc);
				for (final File fileEntry : folder.listFiles()) {
				        if (fileEntry.isDirectory()) {
						DefaultMutableTreeNode project=new DefaultMutableTreeNode(fileEntry.getName());
						if (fileEntry.exists()) {
							for (final File fileEntry2 : fileEntry.listFiles()) {
								boolean found=false;
								String teststr="-SET.XML";
								if (fileEntry2.getName().length()>8) {
									String helpstr=fileEntry2.getName();
									String 	filestr=helpstr.substring(helpstr.length()-8);
									filestr=filestr.toUpperCase();
									if (filestr.equals(teststr)) {
										if (!found) {
											found=true;
											direc.add(project);
										}					
										DefaultMutableTreeNode plane=new DefaultMutableTreeNode(
											fileEntry2.getName()
										);
										project.add(plane);
									}
								}
							}
						}
				        }
				}
			}
		}
		JTree result=new JTree(root);
		result.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		result.addTreeSelectionListener(this);
		config.setAircraftClean();
		return(result);
	}

	public void reloadTree() {
		treAircraft.removeAll();
		LinkedList<DirectoryEntry> buffer=config.getAircraftDirectories();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Aircraft");
		for (int i=0; i<buffer.size(); i++) {
			DefaultMutableTreeNode direc=new DefaultMutableTreeNode(buffer.get(i).getPath());
			File folder=new File(buffer.get(i).getPath());
			if (folder.exists()) {
				root.add(direc);
				for (final File fileEntry : folder.listFiles()) {
				        if (fileEntry.isDirectory()) {
						DefaultMutableTreeNode project=new DefaultMutableTreeNode(fileEntry.getName());
						if (fileEntry.exists()) {
							for (final File fileEntry2 : fileEntry.listFiles()) {
								boolean found=false;
								String teststr="-SET.XML";
								if (fileEntry2.getName().length()>8) {
									String helpstr=fileEntry2.getName();
									String 	filestr=helpstr.substring(helpstr.length()-8);
									filestr=filestr.toUpperCase();
									if (filestr.equals(teststr)) {
										if (!found) {
											found=true;
											direc.add(project);
										}					
										DefaultMutableTreeNode plane=new DefaultMutableTreeNode(
											fileEntry2.getName()
										);
										project.add(plane);
									}
								}
							}
						}
				        }
				}
			}
		}
		DefaultTreeModel treemod=(DefaultTreeModel)treAircraft.getModel();
		treemod.setRoot(root);
		config.setAircraftClean();
		scrAircraft.validate();
	}

	public void valueChanged(TreeSelectionEvent tse) {
		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)treAircraft.getLastSelectedPathComponent();
		String helpstr=(String)selectedNode.getUserObject();
		helpstr=helpstr.substring(0, helpstr.length()-8);
		txtCurrSelected.setText(helpstr);
	}

}
