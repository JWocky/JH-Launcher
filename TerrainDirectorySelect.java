import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class TerrainDirectorySelect extends JPanel implements ActionListener, ListSelectionListener {

	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	private LinkedList<DirectoryEntry> buffer=null;
	private JList<DirectoryEntry> directories=new JList<DirectoryEntry>(new DefaultListModel());
	private JButton btnAdd=new JButton("Add Directory");
	private JButton btnRemove=new JButton("Remove Directory");
	private JButton btnUp=new JButton("Directory Up");
	private JButton btnDown=new JButton("Directory Down");
	private JFileChooser chooser=null;

	public TerrainDirectorySelect(Configuration c) {
		config=c;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		directories.setFont(fntPanel);
		directories.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		directories.addListSelectionListener(this);
		loadTerrainDirectories();
		add(directories);

		btnAdd.setFont(fntPanel);
		btnAdd.addActionListener(this);
		add(btnAdd);

		btnRemove.setFont(fntPanel);
		btnRemove.addActionListener(this);
		add(btnRemove);

		btnUp.setFont(fntPanel);
		btnUp.addActionListener(this);
		add(btnUp);

		btnDown.setFont(fntPanel);
		btnDown.addActionListener(this);
		add(btnDown);

		layout1.putConstraint(SpringLayout.NORTH, directories, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, directories, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, directories, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, directories, 610, SpringLayout.WEST, this); 

		layout1.putConstraint(SpringLayout.NORTH, btnAdd, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, btnAdd, 10, SpringLayout.EAST, directories); 
		layout1.putConstraint(SpringLayout.EAST, btnAdd, 250, SpringLayout.WEST, btnAdd); 

		layout1.putConstraint(SpringLayout.NORTH, btnRemove, 10, SpringLayout.SOUTH, btnAdd); 
		layout1.putConstraint(SpringLayout.WEST, btnRemove, 10, SpringLayout.EAST, directories); 
		layout1.putConstraint(SpringLayout.EAST, btnRemove, 250, SpringLayout.WEST, btnRemove); 

		layout1.putConstraint(SpringLayout.NORTH, btnUp, 40, SpringLayout.SOUTH, btnRemove); 
		layout1.putConstraint(SpringLayout.WEST, btnUp, 10, SpringLayout.EAST, directories); 
		layout1.putConstraint(SpringLayout.EAST, btnUp, 250, SpringLayout.WEST, btnUp); 

		layout1.putConstraint(SpringLayout.NORTH, btnDown, 10, SpringLayout.SOUTH, btnUp); 
		layout1.putConstraint(SpringLayout.WEST, btnDown, 10, SpringLayout.EAST, directories); 
		layout1.putConstraint(SpringLayout.EAST, btnDown, 250, SpringLayout.WEST, btnDown); 

		setButtons();
	}

	private void loadTerrainDirectories() {
		DefaultListModel dlmDirectories=(DefaultListModel)directories.getModel();
		dlmDirectories.removeAllElements();
		buffer=config.getTerrainDirectories();
		for (int i=0; i<buffer.size(); i++) {
			dlmDirectories.addElement(buffer.get(i));
		}		
	}

	private void setButtons() {
		if (directories.getSelectedIndex()==-1) {
			btnRemove.setEnabled(false);
			btnUp.setEnabled(false);
			btnDown.setEnabled(false);
		} else {
			btnRemove.setEnabled(true);
			btnUp.setEnabled(true);
			btnDown.setEnabled(true);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnAdd) {
			chooser=new JFileChooser();
			chooser.setFont(fntPanel);
			setFileChooserFont(chooser.getComponents());
			chooser.setDialogTitle("Select a Direcotry");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
				DirectoryEntry dir=new DirectoryEntry();
				dir.setPath(chooser.getCurrentDirectory().getAbsolutePath());
				buffer.add(dir);
				loadTerrainDirectories();
			}
		}
		if (ae.getSource()==btnRemove) {
			if (directories.getSelectedIndex()>=0) {
				DirectoryEntry dir=directories.getSelectedValue();
				int found=buffer.indexOf(dir);
				buffer.remove(found);
				loadTerrainDirectories();
			}
		}
		if (ae.getSource()==btnUp) {
			if (directories.getSelectedIndex()>=0) {
				DirectoryEntry dir=directories.getSelectedValue();
				int found1=buffer.indexOf(dir);
				int found2=found1-1;
				if (found2>=0) {
					DirectoryEntry dir1=buffer.get(found1);
					DirectoryEntry dir2=buffer.get(found2);
					buffer.set(found1, dir2);
					buffer.set(found2, dir1);
				}					
				loadTerrainDirectories();
			}
		}
		if (ae.getSource()==btnDown) {
			if (directories.getSelectedIndex()>=0) {
				DirectoryEntry dir=directories.getSelectedValue();
				int found1=buffer.indexOf(dir);
				int found2=found1+1;
				if (found2<buffer.size()) {
					DirectoryEntry dir1=buffer.get(found1);
					DirectoryEntry dir2=buffer.get(found2);
					buffer.set(found1, dir2);
					buffer.set(found2, dir1);
				}					
				loadTerrainDirectories();
			}
		}
	}

	public void setFileChooserFont(Component[] comp) {
	    for(int i = 0; i < comp.length; i++) {
		if(comp[i] instanceof Container) setFileChooserFont(((Container)comp[i]).getComponents());
		try{comp[i].setFont(fntPanel);}
		catch(Exception e){}//do nothing
	    }
	}

	public void valueChanged(ListSelectionEvent lse) {
		setButtons();
	}

}
