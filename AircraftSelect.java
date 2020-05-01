import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.tree.*;

public class AircraftSelect extends JPanel{
	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	JTree treAircraft=null;

	public AircraftSelect(Configuration c) {
		config=c;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		treAircraft=buildTree();
		treAircraft.setFont(fntPanel);
		JScrollPane scrAircraft=new JScrollPane(
			treAircraft, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		add(scrAircraft);

		layout1.putConstraint(SpringLayout.NORTH, scrAircraft, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, scrAircraft, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, scrAircraft, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, scrAircraft, 610, SpringLayout.WEST, scrAircraft); 
	}

	private JTree buildTree() {
		LinkedList<DirectoryEntry> buffer=config.getAircraftDirectories();
		DefaultMutableTreeNode root=new DefaultMutableTreeNode("Aircraft");
		for (int i=0; i<buffer.size(); i++) {
			System.out.println("DIR "+buffer.get(i).getPath());
			DefaultMutableTreeNode direc=new DefaultMutableTreeNode(buffer.get(i).getPath());
			root.add(direc);
			File folder=new File(buffer.get(i).getPath());
			for (final File fileEntry : folder.listFiles()) {
			        if (fileEntry.isDirectory()) {
					System.out.println("  SUBDIR :"+fileEntry.getName());
					DefaultMutableTreeNode project=new DefaultMutableTreeNode(fileEntry.getName());
					for (final File fileEntry2 : fileEntry.listFiles()) {
						boolean found=false;
						String teststr="-SET.XML";
						if (fileEntry2.getName().length()>8) {
							String filestr=fileEntry2.getName().substring(fileEntry2.getName().length()-8);
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
								System.out.println("    File: "+fileEntry2.getName());
							}
						}
					}
			        }
			}
		}
		JTree result=new JTree(root);
		return(result);
	}

}
