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
	private JTree treProperties=null;
	private JScrollPane scrProperties=null;
	private JTextField txtValue=new JTextField("");
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

		treProperties=buildTree();
		treProperties.setFont(fntPanel);
		scrProperties=new JScrollPane(
			treProperties, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		add(scrProperties);

		txtValue.setText("");
		txtValue.setFont(fntPanelBig);
		txtValue.setBackground(Color.WHITE);
		txtValue.setHorizontalAlignment(SwingConstants.CENTER);
		txtValue.setEditable(false);
		add(txtValue);

		layout1.putConstraint(SpringLayout.NORTH, lblCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, lblCurrSelected, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblCurrSelected, 250, SpringLayout.WEST, lblCurrSelected); 

		layout1.putConstraint(SpringLayout.NORTH, txtCurrSelected, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, txtCurrSelected, 10, SpringLayout.EAST, lblCurrSelected); 
		layout1.putConstraint(SpringLayout.EAST, txtCurrSelected, -10, SpringLayout.EAST, this); 

		layout1.putConstraint(SpringLayout.NORTH, scrProperties, 20, SpringLayout.SOUTH, txtCurrSelected); 
		layout1.putConstraint(SpringLayout.WEST, scrProperties, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, scrProperties, 500, SpringLayout.WEST, scrProperties); 

		layout1.putConstraint(SpringLayout.NORTH, txtValue, 0, SpringLayout.NORTH, scrProperties); 
		layout1.putConstraint(SpringLayout.WEST, txtValue, 20, SpringLayout.EAST, scrProperties); 
		layout1.putConstraint(SpringLayout.EAST, txtValue, -20, SpringLayout.EAST, this); 

	}

	public void updateBuffer() {
		txtCurrSelected.setText(buffer.getName());
	}

	private void addChildren(DefaultMutableTreeNode dmtn, LinkedList<PropertyEntry> c) {
		for (int i=0; i<c.size(); i++) {
			PropertyEntry pe=c.get(i);
			LinkedList<PropertyEntry> children=pe.getChildren();
			DefaultMutableTreeNode node=new DefaultMutableTreeNode(pe);
			dmtn.add(node);
			addChildren(node, children);
		}
	}

	private JTree buildTree() {
		
		PropertyEntry pe=buffer.getRootNode();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode(pe);
		LinkedList<PropertyEntry> children=pe.getChildren();
		
		System.out.println(pe.getName()+" with "+children.size()+" children");
		addChildren(root, children);
		JTree result=new JTree(root);
		result.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		result.addTreeSelectionListener(this);
		return(result);
	}

	public void reloadTree() {
		treProperties.removeAll();

		PropertyEntry pe=buffer.getRootNode();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode(pe);
		LinkedList<PropertyEntry> children=pe.getChildren();
		
		System.out.println(pe.getName()+" with "+children.size()+" children");
		addChildren(root, children);

		DefaultTreeModel treemod=(DefaultTreeModel)treProperties.getModel();
		treemod.setRoot(root);
		scrProperties.validate();
	}

	public void valueChanged(TreeSelectionEvent tse) {
		DefaultMutableTreeNode selectedNode=(DefaultMutableTreeNode)treProperties.getLastSelectedPathComponent();
		if (selectedNode!=null) {
			PropertyEntry help=(PropertyEntry)selectedNode.getUserObject();
			txtValue.setText(help.getValue());
System.out.println("Selected "+help.getName()+" = "+help.getValue());
		}
	}

}