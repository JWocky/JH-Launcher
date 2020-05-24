import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class RootDirectorySelect extends JPanel implements ActionListener {

	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	private JTextField txtRootDirectory=new JTextField("");
	private JButton btnBrowse=new JButton("Browse");
	private JFileChooser chooser=null;

	public RootDirectorySelect(Configuration c) {
		config=c;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		txtRootDirectory.setFont(fntPanel);
		txtRootDirectory.setBackground(Color.YELLOW);
		txtRootDirectory.setText(config.getRoot().getPath());
		add(txtRootDirectory);

		btnBrowse.setFont(fntPanel);
		btnBrowse.addActionListener(this);
		add(btnBrowse);

		layout1.putConstraint(SpringLayout.NORTH, txtRootDirectory, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, txtRootDirectory, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, txtRootDirectory, 610, SpringLayout.WEST, this); 

		layout1.putConstraint(SpringLayout.NORTH, btnBrowse, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, btnBrowse, 10, SpringLayout.EAST, txtRootDirectory); 
		layout1.putConstraint(SpringLayout.EAST, btnBrowse, 250, SpringLayout.WEST, btnBrowse); 

	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnBrowse) {
			chooser=new JFileChooser();
			chooser.setFont(fntPanel);
			setFileChooserFont(chooser.getComponents());
			chooser.setDialogTitle("Select a Direcotry");
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			chooser.setCurrentDirectory(new File(config.getRoot().getPath()));
			chooser.setAcceptAllFileFilterUsed(false);
			if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
				DirectoryEntry dir=new DirectoryEntry();
				dir.setPath(chooser.getSelectedFile().getAbsolutePath());
				config.setRoot(dir);
				txtRootDirectory.setText(dir.getPath());
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

}
