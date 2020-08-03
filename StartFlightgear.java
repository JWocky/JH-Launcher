import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class StartFlightgear extends JPanel implements ActionListener {
	private Configuration config=null;
	private Setup setup=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);
	private Font fntPanel2=new Font("Courier", Font.PLAIN,12);

	private JButton btnStart=new JButton("<html>To start Flightgear<br>PRESS THIS GIANT YELLOW BUTTON!</html>");
	private JTextArea txtMessages=new JTextArea("");
	private JScrollPane scrMessages=new JScrollPane(txtMessages, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	private JButton btnFile=new JButton("<html>Print output to file</html>");

	private String buffer="";

	public StartFlightgear(Configuration c, Setup s) {
		config=c;
		setup=s;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		btnStart.setFont(fntPanel);
		btnStart.setBackground(Color.YELLOW);
		btnStart.addActionListener(this);
		add(btnStart);

		txtMessages.setFont(fntPanel);
		add(scrMessages);

		btnFile.setFont(fntPanel2);
		btnFile.setBackground(Color.YELLOW);
		btnFile.addActionListener(this);
		add(btnFile);

		layout1.putConstraint(SpringLayout.NORTH, btnStart, 20, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, btnStart, 20, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, btnStart, 60, SpringLayout.NORTH, btnStart); 
		layout1.putConstraint(SpringLayout.EAST, btnStart, -20, SpringLayout.EAST, this); 

		layout1.putConstraint(SpringLayout.NORTH, scrMessages, 10, SpringLayout.SOUTH, btnStart); 
		layout1.putConstraint(SpringLayout.WEST, scrMessages, 20, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, scrMessages, -30, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, scrMessages, -20, SpringLayout.EAST, this); 

		layout1.putConstraint(SpringLayout.NORTH, btnFile, 5, SpringLayout.SOUTH, scrMessages); 
		layout1.putConstraint(SpringLayout.WEST, btnFile, 20, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, btnFile, -5, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, btnFile, -20, SpringLayout.EAST, this); 
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnStart) {
			if (config.getInstancesRunning()<1) {
				if (setup.checkSetup()) {
					buffer="";
					txtMessages.setText(buffer);
					setup.startFG(this);
				}
			}
		}
		if (ae.getSource()==btnFile) {
			JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

			int returnValue = jfc.showSaveDialog(null);

			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				System.out.println(selectedFile.getAbsolutePath());
				try {
					BufferedWriter out=new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath()));
					out.write(txtMessages.getText()); out.newLine();
					out.close();
				} catch (IOException ioe) {
					System.out.println("Error: "+ioe.getMessage());
				}
			}
		}
	}


	public void updateStartButton() {
		if (config.getInstancesRunning()<1) {
			btnStart.setFont(fntPanel);
			btnStart.setBackground(Color.YELLOW);
			btnStart.setText("<html>To start Flightgear<br>PRESS THIS GIANT YELLOW BUTTON!</html>");
		} else {
			btnStart.setFont(fntPanel);
			btnStart.setBackground(Color.RED);
			btnStart.setText("<html>Flightgar is already running!</html>");
		}
	}

	public void addLine(String l) {
		if (!buffer.equals("")) {
			buffer=buffer+"\n";
		}
		buffer=buffer+l;
		txtMessages.setText(buffer);
	}

}
