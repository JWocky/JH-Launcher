import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class AirportSelect extends JPanel implements TreeSelectionListener {
	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);
	private Font fntPanelBig=new Font("Courier", Font.BOLD,40);

	JTree treAirports=null;
	JScrollPane scrAirports=null;
	JLabel lblCurrSelected=new JLabel("Currently Selected");
	JTextField txtCurrSelected=new JTextField("");

	public AirportSelect(Configuration c) {
		config=c;
		config.setAirportSelect(this);

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		treAirports=buildTree();
		treAirports.setFont(fntPanel);
		scrAirports=new JScrollPane(
			treAirports, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		add(scrAirports);

		lblCurrSelected.setFont(fntPanel);
		lblCurrSelected.setBackground(Color.YELLOW);
		lblCurrSelected.setOpaque(true);
		add(lblCurrSelected);

		txtCurrSelected.setFont(fntPanelBig);
		txtCurrSelected.setBackground(Color.YELLOW);
		txtCurrSelected.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrSelected.setEditable(false);
		add(txtCurrSelected);

		layout1.putConstraint(SpringLayout.NORTH, scrAirports, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, scrAirports, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, scrAirports, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, scrAirports, 610, SpringLayout.WEST, scrAirports); 

		layout1.putConstraint(SpringLayout.NORTH, lblCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, lblCurrSelected, 10, SpringLayout.EAST, scrAirports); 
		layout1.putConstraint(SpringLayout.EAST, lblCurrSelected, 250, SpringLayout.WEST, lblCurrSelected); 

		layout1.putConstraint(SpringLayout.NORTH, txtCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, txtCurrSelected, 10, SpringLayout.EAST, lblCurrSelected); 
		layout1.putConstraint(SpringLayout.EAST, txtCurrSelected, -10, SpringLayout.EAST, this); 

	}

	private File[] listFilesSorted(File f) {
		File[] files=f.listFiles();
		Arrays.sort(files, (f1, f2) -> {
			if (f1.isDirectory() && !f2.isDirectory()) {
				return(-1);
			} else if (!f1.isDirectory() && f2.isDirectory()) {
				return(1);
			} else {
				return(f1.compareTo(f2));
			}
		});
		return(files);
	}

	private void readPartTree(DefaultMutableTreeNode node, String dir) {
		File search=new File(dir);
		if (search.exists()) {
			if (search.isDirectory()) {
				File[] folder=listFilesSorted(new File(dir));
				for (final File files : folder) {
				        if (files.isDirectory()) {
						DefaultMutableTreeNode node2=new DefaultMutableTreeNode(files.getName());
						node.add(node2);
						readPartTree(node2, files.getPath());					
					} else {
						// this is no deeper subdirectory, just a file
						String name=files.getName();
						int posi=name.indexOf(".threshold.");
						if (posi>-1) {
							// add only threshold files because they are the absolute minimum for an airport
							String code=name.substring(0, posi);
							DefaultMutableTreeNode node2=new DefaultMutableTreeNode(code);
							node.add(node2);
						}
					}
				}
			} else {
				// this is no deeper subdirectory, just a file
				node.add(new DefaultMutableTreeNode(search.getName()));
			}
		}
	}

	private JTree buildTree() {
		LinkedList<DirectoryEntry> buffer=config.getTerrainDirectories();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Airports");
		for (int i=0; i<buffer.size(); i++) {
			String airportsDir=buffer.get(i).getPath()+File.separator+"Airports";
			DefaultMutableTreeNode direc=new DefaultMutableTreeNode(buffer.get(i).getPath());
			File folder=new File(airportsDir);
			if (folder.exists()) {
				readPartTree(root, airportsDir);
			}
		}
		JTree result=new JTree(root);
		result.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		result.addTreeSelectionListener(this);
		config.setAirportClean();
		return(result);
	}

	public void reloadList() {
		treAirports.removeAll();
		LinkedList<DirectoryEntry> buffer=config.getTerrainDirectories();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Airports");
		for (int i=0; i<buffer.size(); i++) {
			String airportsDir=buffer.get(i).getPath()+File.separator+"Airports";
			DefaultMutableTreeNode direc=new DefaultMutableTreeNode(buffer.get(i).getPath());
			File folder=new File(airportsDir);
			if (folder.exists()) {
				readPartTree(root, airportsDir);
			}
		}
		DefaultTreeModel treemod=(DefaultTreeModel)treAirports.getModel();
		treemod.setRoot(root);
		config.setAirportClean();
		scrAirports.validate();
	}

	public void valueChanged(TreeSelectionEvent tse) {
		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)treAirports.getLastSelectedPathComponent();
		String helpstr=(String)selectedNode.getUserObject();
//		int posi=helpstr.indexOf(".threshold.");
//		helpstr=helpstr.substring(0, posi);
		txtCurrSelected.setText(helpstr);
		config.setSelectedAirport(helpstr);
	}

}
