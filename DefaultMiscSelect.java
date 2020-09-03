import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class JAFVASelect extends JPanel implements ActionListener, DocumentListener {
	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	private JLabel lblIsJafva=new JLabel("Activate JAFVA functions");
	private JCheckBox chkIsJafva=new JCheckBox();
	private JLabel lblJafvaName=new JLabel("Your JAFVA name (Pilot Nickname)");
	private JTextField txtJafvaName=new JTextField("");
	private JButton btnReload=new JButton("Reload");
	private JLabel lblCode=new JLabel("Code");
	private JTextField txtCode=new JTextField("");
	private JLabel lblCountry=new JLabel("Country");
	private JTextField txtCountry=new JTextField("");
	private JLabel lblRank=new JLabel("Rank");
	private JTextField txtRank=new JTextField("");
	private JLabel lblSuffix=new JLabel("Suffix");
	private JTextField txtSuffix=new JTextField("");
	private JLabel lblTailsign=new JLabel("Tailsign");
	private JTextField txtTailsign=new JTextField("");
	private JLabel lblTimeIR=new JLabel("Time in rank");
	private JTextField txtTimeIR=new JTextField("");
	private JLabel lblTimeTP=new JLabel("Time to promotion");
	private JTextField txtTimeTP=new JTextField("");
	private JLabel lblTimeTotal=new JLabel("Time total");
	private JTextField txtTimeTotal=new JTextField("");

	private JLabel lblCallsign=new JLabel("Callsign (overwrite start)");
	private JCheckBox chkCallsign=new JCheckBox();
	private JLabel lblAirline=new JLabel("Airline");
	private JTextField txtAirline=new JTextField("");
	private JLabel lblSuffix2=new JLabel("Suffix");
	private JTextField txtSuffix2=new JTextField("");

	private JLabel lblPosition=new JLabel("Position (overwrite start)");
	private JCheckBox chkPosition=new JCheckBox();
	private JLabel lblAirport=new JLabel("Airport");
	private JTextField txtAirport=new JTextField("");
	private JLabel lblLatitude=new JLabel("Latitude");
	private JTextField txtLatitude=new JTextField("");
	private JLabel lblLongitude=new JLabel("Longitude");
	private JTextField txtLongitude=new JTextField("");
	private JLabel lblHeading=new JLabel("Heading");
	private JTextField txtHeading=new JTextField("");

	private boolean exists=false;
	private String code="";
	private String country="";
	private String rank="";
	private String suffix="";
	private String tailsign="";
	private String timeIR="";
	private String timeTP="";
	private String timeTotal="";
	private String airport="";
	private String lat="";
	private String lon="";
	private String heading="";

	public JAFVASelect(Configuration c) {
		config=c;
		config.setJAFVASelect(this);

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		lblIsJafva.setFont(fntPanel);
		lblIsJafva.setBackground(Color.YELLOW);
		lblIsJafva.setOpaque(true);
		add(lblIsJafva);

		chkIsJafva.setFont(fntPanel);
		chkIsJafva.setBackground(Color.YELLOW);
		chkIsJafva.setOpaque(true);
		chkIsJafva.addActionListener(this);
		add(chkIsJafva);

		lblJafvaName.setFont(fntPanel);
		lblJafvaName.setBackground(Color.YELLOW);
		lblJafvaName.setOpaque(true);
		add(lblJafvaName);

		txtJafvaName.setFont(fntPanel);
		txtJafvaName.setBackground(Color.YELLOW);
		txtJafvaName.setOpaque(true);
		add(txtJafvaName);

		btnReload.setFont(fntPanel);
		btnReload.addActionListener(this);
		add(btnReload);

		lblCode.setFont(fntPanel);
		lblCode.setBackground(Color.YELLOW);
		lblCode.setOpaque(true);
		add(lblCode);

		txtCode.setFont(fntPanel);
		txtCode.setBackground(Color.YELLOW);
		txtCode.setOpaque(true);
		add(txtCode);

		lblCountry.setFont(fntPanel);
		lblCountry.setBackground(Color.YELLOW);
		lblCountry.setOpaque(true);
		add(lblCountry);

		txtCountry.setFont(fntPanel);
		txtCountry.setBackground(Color.YELLOW);
		txtCountry.setOpaque(true);
		add(txtCountry);

		lblRank.setFont(fntPanel);
		lblRank.setBackground(Color.YELLOW);
		lblRank.setOpaque(true);
		add(lblRank);

		txtRank.setFont(fntPanel);
		txtRank.setBackground(Color.YELLOW);
		txtRank.setOpaque(true);
		add(txtRank);

		lblSuffix.setFont(fntPanel);
		lblSuffix.setBackground(Color.YELLOW);
		lblSuffix.setOpaque(true);
		add(lblSuffix);

		txtSuffix.setFont(fntPanel);
		txtSuffix.setBackground(Color.YELLOW);
		txtSuffix.setOpaque(true);
		add(txtSuffix);

		lblTailsign.setFont(fntPanel);
		lblTailsign.setBackground(Color.YELLOW);
		lblTailsign.setOpaque(true);
		add(lblTailsign);

		txtTailsign.setFont(fntPanel);
		txtTailsign.setBackground(Color.YELLOW);
		txtTailsign.setOpaque(true);
		add(txtTailsign);

		lblTimeIR.setFont(fntPanel);
		lblTimeIR.setBackground(Color.YELLOW);
		lblTimeIR.setOpaque(true);
		add(lblTimeIR);

		txtTimeIR.setFont(fntPanel);
		txtTimeIR.setBackground(Color.YELLOW);
		txtTimeIR.setOpaque(true);
		add(txtTimeIR);

		lblTimeTP.setFont(fntPanel);
		lblTimeTP.setBackground(Color.YELLOW);
		lblTimeTP.setOpaque(true);
		add(lblTimeTP);

		txtTimeTP.setFont(fntPanel);
		txtTimeTP.setBackground(Color.YELLOW);
		txtTimeTP.setOpaque(true);
		add(txtTimeTP);

		lblTimeTotal.setFont(fntPanel);
		lblTimeTotal.setBackground(Color.YELLOW);
		lblTimeTotal.setOpaque(true);
		add(lblTimeTotal);

		txtTimeTotal.setFont(fntPanel);
		txtTimeTotal.setBackground(Color.YELLOW);
		txtTimeTotal.setOpaque(true);
		add(txtTimeTotal);

		lblCallsign.setFont(fntPanel);
		lblCallsign.setBackground(Color.YELLOW);
		lblCallsign.setOpaque(true);
		add(lblCallsign);

		chkCallsign.setFont(fntPanel);
		chkCallsign.setBackground(Color.YELLOW);
		chkCallsign.setOpaque(true);
		chkCallsign.addActionListener(this);
		add(chkCallsign);

		lblAirline.setFont(fntPanel);
		lblAirline.setBackground(Color.YELLOW);
		lblAirline.setOpaque(true);
		add(lblAirline);

		txtAirline.setFont(fntPanel);
		txtAirline.setBackground(Color.YELLOW);
		txtAirline.setOpaque(true);
		txtAirline.getDocument().addDocumentListener(this);
		add(txtAirline);

		lblSuffix2.setFont(fntPanel);
		lblSuffix2.setBackground(Color.YELLOW);
		lblSuffix2.setOpaque(true);
		add(lblSuffix2);

		txtSuffix2.setFont(fntPanel);
		txtSuffix2.setBackground(Color.YELLOW);
		txtSuffix2.setOpaque(true);
		add(txtSuffix2);

		lblPosition.setFont(fntPanel);
		lblPosition.setBackground(Color.YELLOW);
		lblPosition.setOpaque(true);
		add(lblPosition);

		chkPosition.setFont(fntPanel);
		chkPosition.setBackground(Color.YELLOW);
		chkPosition.setOpaque(true);
		chkPosition.addActionListener(this);
		add(chkPosition);

		lblAirport.setFont(fntPanel);
		lblAirport.setBackground(Color.YELLOW);
		lblAirport.setOpaque(true);
		add(lblAirport);

		txtAirport.setFont(fntPanel);
		txtAirport.setBackground(Color.YELLOW);
		txtAirport.setOpaque(true);
		txtAirport.getDocument().addDocumentListener(this);
		add(txtAirport);

		lblLatitude.setFont(fntPanel);
		lblLatitude.setBackground(Color.YELLOW);
		lblLatitude.setOpaque(true);
		add(lblLatitude);

		txtLatitude.setFont(fntPanel);
		txtLatitude.setBackground(Color.YELLOW);
		txtLatitude.setOpaque(true);
		txtLatitude.getDocument().addDocumentListener(this);
		add(txtLatitude);

		lblLongitude.setFont(fntPanel);
		lblLongitude.setBackground(Color.YELLOW);
		lblLongitude.setOpaque(true);
		add(lblLongitude);

		txtLongitude.setFont(fntPanel);
		txtLongitude.setBackground(Color.YELLOW);
		txtLongitude.setOpaque(true);
		txtLongitude.getDocument().addDocumentListener(this);
		add(txtLongitude);

		lblHeading.setFont(fntPanel);
		lblHeading.setBackground(Color.YELLOW);
		lblHeading.setOpaque(true);
		add(lblHeading);

		txtHeading.setFont(fntPanel);
		txtHeading.setBackground(Color.YELLOW);
		txtHeading.setOpaque(true);
		txtHeading.getDocument().addDocumentListener(this);
		add(txtHeading);

		layout1.putConstraint(SpringLayout.NORTH, lblIsJafva, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, lblIsJafva, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblIsJafva, 410, SpringLayout.WEST, this); 

		layout1.putConstraint(SpringLayout.NORTH, chkIsJafva, 0, SpringLayout.NORTH, lblIsJafva); 
		layout1.putConstraint(SpringLayout.WEST, chkIsJafva, 10, SpringLayout.EAST, lblIsJafva); 

		layout1.putConstraint(SpringLayout.NORTH, lblJafvaName, 10, SpringLayout.SOUTH, lblIsJafva); 
		layout1.putConstraint(SpringLayout.WEST, lblJafvaName, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblJafvaName, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtJafvaName, 0, SpringLayout.NORTH, lblJafvaName); 
		layout1.putConstraint(SpringLayout.WEST, txtJafvaName, 10, SpringLayout.EAST, lblJafvaName); 
		layout1.putConstraint(SpringLayout.EAST, txtJafvaName, 250, SpringLayout.WEST, txtJafvaName); 
	
		layout1.putConstraint(SpringLayout.NORTH, btnReload, 0, SpringLayout.NORTH, txtJafvaName); 
		layout1.putConstraint(SpringLayout.WEST, btnReload, 10, SpringLayout.EAST, txtJafvaName); 
		layout1.putConstraint(SpringLayout.SOUTH, btnReload, 0, SpringLayout.SOUTH, txtJafvaName); 

		layout1.putConstraint(SpringLayout.NORTH, lblCode, 60, SpringLayout.SOUTH, lblJafvaName); 
		layout1.putConstraint(SpringLayout.WEST, lblCode, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblCode, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtCode, 0, SpringLayout.NORTH, lblCode); 
		layout1.putConstraint(SpringLayout.WEST, txtCode, 10, SpringLayout.EAST, lblCode); 
		layout1.putConstraint(SpringLayout.EAST, txtCode, 250, SpringLayout.WEST, txtCode); 

		layout1.putConstraint(SpringLayout.NORTH, lblCountry, 10, SpringLayout.SOUTH, lblCode); 
		layout1.putConstraint(SpringLayout.WEST, lblCountry, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblCountry, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtCountry, 0, SpringLayout.NORTH, lblCountry); 
		layout1.putConstraint(SpringLayout.WEST, txtCountry, 10, SpringLayout.EAST, lblCountry); 
		layout1.putConstraint(SpringLayout.EAST, txtCountry, 450, SpringLayout.WEST, txtCountry); 

		layout1.putConstraint(SpringLayout.NORTH, lblRank, 10, SpringLayout.SOUTH, lblCountry); 
		layout1.putConstraint(SpringLayout.WEST, lblRank, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblRank, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtRank, 0, SpringLayout.NORTH, lblRank); 
		layout1.putConstraint(SpringLayout.WEST, txtRank, 10, SpringLayout.EAST, lblRank); 
		layout1.putConstraint(SpringLayout.EAST, txtRank, 450, SpringLayout.WEST, txtRank); 

		layout1.putConstraint(SpringLayout.NORTH, lblSuffix, 10, SpringLayout.SOUTH, lblRank); 
		layout1.putConstraint(SpringLayout.WEST, lblSuffix, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblSuffix, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtSuffix, 0, SpringLayout.NORTH, lblSuffix); 
		layout1.putConstraint(SpringLayout.WEST, txtSuffix, 10, SpringLayout.EAST, lblSuffix); 
		layout1.putConstraint(SpringLayout.EAST, txtSuffix, 250, SpringLayout.WEST, txtSuffix); 

		layout1.putConstraint(SpringLayout.NORTH, lblTailsign, 10, SpringLayout.SOUTH, lblSuffix); 
		layout1.putConstraint(SpringLayout.WEST, lblTailsign, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblTailsign, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtTailsign, 0, SpringLayout.NORTH, lblTailsign); 
		layout1.putConstraint(SpringLayout.WEST, txtTailsign, 10, SpringLayout.EAST, lblTailsign); 
		layout1.putConstraint(SpringLayout.EAST, txtTailsign, 250, SpringLayout.WEST, txtTailsign); 

		layout1.putConstraint(SpringLayout.NORTH, lblTimeIR, 10, SpringLayout.SOUTH, lblTailsign); 
		layout1.putConstraint(SpringLayout.WEST, lblTimeIR, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblTimeIR, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtTimeIR, 0, SpringLayout.NORTH, lblTimeIR); 
		layout1.putConstraint(SpringLayout.WEST, txtTimeIR, 10, SpringLayout.EAST, lblTimeIR); 
		layout1.putConstraint(SpringLayout.EAST, txtTimeIR, 450, SpringLayout.WEST, txtTimeIR); 

		layout1.putConstraint(SpringLayout.NORTH, lblTimeTP, 10, SpringLayout.SOUTH, lblTimeIR); 
		layout1.putConstraint(SpringLayout.WEST, lblTimeTP, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblTimeTP, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtTimeTP, 0, SpringLayout.NORTH, lblTimeTP); 
		layout1.putConstraint(SpringLayout.WEST, txtTimeTP, 10, SpringLayout.EAST, lblTimeTP); 
		layout1.putConstraint(SpringLayout.EAST, txtTimeTP, 450, SpringLayout.WEST, txtTimeTP); 

		layout1.putConstraint(SpringLayout.NORTH, lblTimeTotal, 10, SpringLayout.SOUTH, lblTimeTP); 
		layout1.putConstraint(SpringLayout.WEST, lblTimeTotal, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblTimeTotal, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtTimeTotal, 0, SpringLayout.NORTH, lblTimeTotal); 
		layout1.putConstraint(SpringLayout.WEST, txtTimeTotal, 10, SpringLayout.EAST, lblTimeTotal); 
		layout1.putConstraint(SpringLayout.EAST, txtTimeTotal, 450, SpringLayout.WEST, txtTimeTotal); 

		layout1.putConstraint(SpringLayout.NORTH, lblCallsign, 60, SpringLayout.SOUTH, lblTimeTotal); 
		layout1.putConstraint(SpringLayout.WEST, lblCallsign, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblCallsign, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, chkCallsign, 0, SpringLayout.NORTH, lblCallsign); 
		layout1.putConstraint(SpringLayout.WEST, chkCallsign, 10, SpringLayout.EAST, lblCallsign); 

		layout1.putConstraint(SpringLayout.NORTH, lblAirline, 10, SpringLayout.SOUTH, lblCallsign); 
		layout1.putConstraint(SpringLayout.WEST, lblAirline, 25, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblAirline, 425, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtAirline, 0, SpringLayout.NORTH, lblAirline); 
		layout1.putConstraint(SpringLayout.WEST, txtAirline, 10, SpringLayout.EAST, lblAirline); 
		layout1.putConstraint(SpringLayout.EAST, txtAirline, 350, SpringLayout.WEST, txtAirline); 

		layout1.putConstraint(SpringLayout.NORTH, lblSuffix2, 10, SpringLayout.SOUTH, lblAirline); 
		layout1.putConstraint(SpringLayout.WEST, lblSuffix2, 25, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblSuffix2, 425, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtSuffix2, 0, SpringLayout.NORTH, lblSuffix2); 
		layout1.putConstraint(SpringLayout.WEST, txtSuffix2, 10, SpringLayout.EAST, lblSuffix2); 
		layout1.putConstraint(SpringLayout.EAST, txtSuffix2, 350, SpringLayout.WEST, txtSuffix2); 

		layout1.putConstraint(SpringLayout.NORTH, lblPosition, 60, SpringLayout.SOUTH, lblSuffix2); 
		layout1.putConstraint(SpringLayout.WEST, lblPosition, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblPosition, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, chkPosition, 0, SpringLayout.NORTH, lblPosition); 
		layout1.putConstraint(SpringLayout.WEST, chkPosition, 10, SpringLayout.EAST, lblPosition); 

		layout1.putConstraint(SpringLayout.NORTH, lblAirport, 10, SpringLayout.SOUTH, lblPosition); 
		layout1.putConstraint(SpringLayout.WEST, lblAirport, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblAirport, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtAirport, 0, SpringLayout.NORTH, lblAirport); 
		layout1.putConstraint(SpringLayout.WEST, txtAirport, 10, SpringLayout.EAST, lblAirport); 
		layout1.putConstraint(SpringLayout.EAST, txtAirport, 350, SpringLayout.WEST, txtAirport); 

		layout1.putConstraint(SpringLayout.NORTH, lblLatitude, 10, SpringLayout.SOUTH, lblAirport); 
		layout1.putConstraint(SpringLayout.WEST, lblLatitude, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblLatitude, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtLatitude, 0, SpringLayout.NORTH, lblLatitude); 
		layout1.putConstraint(SpringLayout.WEST, txtLatitude, 10, SpringLayout.EAST, lblLatitude); 
		layout1.putConstraint(SpringLayout.EAST, txtLatitude, 350, SpringLayout.WEST, txtLatitude); 

		layout1.putConstraint(SpringLayout.NORTH, lblLongitude, 10, SpringLayout.SOUTH, lblLatitude); 
		layout1.putConstraint(SpringLayout.WEST, lblLongitude, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblLongitude, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtLongitude, 0, SpringLayout.NORTH, lblLongitude); 
		layout1.putConstraint(SpringLayout.WEST, txtLongitude, 10, SpringLayout.EAST, lblLongitude); 
		layout1.putConstraint(SpringLayout.EAST, txtLongitude, 350, SpringLayout.WEST, txtLongitude); 

		layout1.putConstraint(SpringLayout.NORTH, lblHeading, 10, SpringLayout.SOUTH, lblLongitude); 
		layout1.putConstraint(SpringLayout.WEST, lblHeading, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.EAST, lblHeading, 410, SpringLayout.WEST, this); 
	
		layout1.putConstraint(SpringLayout.NORTH, txtHeading, 0, SpringLayout.NORTH, lblHeading); 
		layout1.putConstraint(SpringLayout.WEST, txtHeading, 10, SpringLayout.EAST, lblHeading); 
		layout1.putConstraint(SpringLayout.EAST, txtHeading, 350, SpringLayout.WEST, txtHeading); 

		setData();
	}

	private void setData() {
		chkIsJafva.setSelected(config.getJafva());
		txtJafvaName.setText(config.getJafvaName());
		String res=executePost("http://www.jafva.com/JHL/", "nickname="+config.getJafvaName()+"&command=datapack");
	}

	private void setData2() {
		if (exists) {
			txtCode.setEnabled(true);
			txtCode.setText(code);
			txtCountry.setEnabled(true);
			txtCountry.setText(country);
			txtRank.setEnabled(true);
			txtRank.setText(rank);
			txtSuffix.setEnabled(true);
			txtSuffix.setText(suffix);
			txtTailsign.setEnabled(true);
			txtTailsign.setText(tailsign);
			txtTimeIR.setEnabled(true);
			txtTimeIR.setText(timeIR);
			txtTimeTP.setEnabled(true);
			txtTimeTP.setText(timeTP);
			txtTimeTotal.setEnabled(true);
			txtTimeTotal.setText(timeTotal);

			chkCallsign.setEnabled(true);
			config.setJafvaCallsign(chkCallsign.isSelected());
			txtAirline.setEnabled(true);
			txtAirline.setText("???");
			config.setAirlineJafva(txtAirline.getText());
			txtSuffix2.setEnabled(true);
			txtSuffix2.setText(suffix);
			config.setSuffixJafva(suffix);

			chkPosition.setEnabled(true);
			config.setJafvaOverwrite(chkPosition.isSelected());
			txtAirport.setEnabled(true);
			txtAirport.setText(airport);
			config.setAirportJafva(airport);
			txtLatitude.setEnabled(true);
			txtLatitude.setText(lat);
			config.setLatitudeJafva(lat);
			txtLongitude.setEnabled(true);
			txtLongitude.setText(lon);
			config.setLongitudeJafva(lon);
			txtHeading.setEnabled(true);
			txtHeading.setText(heading);
			config.setHeadingJafva(heading);
		} else {
			txtCode.setEnabled(false);			
			txtCode.setText("not found");			
			txtCountry.setEnabled(false);
			txtCountry.setText("not found");
			txtRank.setEnabled(false);
			txtRank.setText("not found");
			txtSuffix.setEnabled(false);
			txtSuffix.setText("not found");
			txtTailsign.setEnabled(false);
			txtTailsign.setText("not found");
			txtTimeIR.setEnabled(false);
			txtTimeIR.setText("not found");
			txtTimeTP.setEnabled(false);
			txtTimeTP.setText("not found");
			txtTimeTotal.setEnabled(false);
			txtTimeTotal.setText("not found");

			chkCallsign.setEnabled(false);
			txtAirline.setEnabled(false);
			txtAirline.setText("not found");
			txtSuffix2.setEnabled(false);
			txtSuffix2.setText("not found");

			chkPosition.setEnabled(false);
			txtAirport.setEnabled(false);
			txtAirport.setText("not found");
			txtLatitude.setEnabled(false);
			txtLatitude.setText("not found");
			txtLongitude.setEnabled(false);
			txtLongitude.setText("not found");
			txtHeading.setEnabled(false);
			txtHeading.setText("not found");
		}
	}

	private void setVariable(String t, String v) {
		String tag="";
		String value="";
		try {
			byte[] buffer=t.getBytes();
			tag=new String(buffer, "UTF8");
		
			buffer=v.getBytes();
			value=new String(buffer, "UTF8");
		} catch(UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}

		tag=tag.trim();
		tag=tag.substring(1, tag.length()-1);
		value=value.trim();

		if (value.substring(0,1).equals("\"")) {
			value=value.substring(1, value.length()-1);
		}
		if (tag.equals("exist")) {
			exists=value.equalsIgnoreCase("true");
		}
		if (tag.equals("code")) {
			code=value;
		}
		if (tag.equals("country")) {
			country=value;
		}
		if (tag.equals("rank")) {
			rank=value;
		}
		if (tag.equals("prefix")) {
			suffix=value;
		}
		if (tag.equals("tailsign")) {
			tailsign=value;
		}
		if (tag.equals("timeinRank")) {
			timeIR=value;
		}
		if (tag.equals("timetoPromotion")) {
			timeTP=value;
		}
		if (tag.equals("timeTotal")) {
			timeTotal=value;
		}
		if (tag.equals("airport")) {
			airport=value;
		}
		if (tag.equals("lat")) {
			lat=value;
		}
		if (tag.equals("lon")) {
			lon=value;
		}
		if (tag.equals("heading")) {
			heading=value;
		}
	}

	private void parseData(String res) {
		int i=0;
		boolean inData=false;
		boolean inTag=false;
		boolean inQuot=false;
		String tag="";
		String value="";
		for (i=0; i<res.length(); i++) {
			String c=res.substring(i, i+1);
			if (c.equals("{")) {
				if (!inData && !inQuot) {
					inData=true;
					inTag=true;
				} else {
					if (inTag) {
						tag=tag+c;
					} else {
						value=value+c;
					}
				}
			} else if (c.equals("}")) {
				if (inData && !inQuot) {
					inData=false;
					setVariable(tag, value);
					tag="";
					value="";
				} else {
					if (inTag) {
						tag=tag+c;
					} else {
						value=value+c;
					}
				}
			} else if (c.equals("\"")) {
				inQuot=!inQuot;
				if (inTag) {
					tag=tag+c;
				} else {
					value=value+c;
				}
			} else if (c.equals(":")) {
				if (!inQuot) {
					inTag=false;
				} else {
					if (inTag) {
						tag=tag+c;
					} else {
						value=value+c;
					}
				}
			} else if (c.equals(",")) {
				if (!inQuot) {
					inTag=true;
					setVariable(tag, value);
					tag="";
					value="";
				} else {
					if (inTag) {
						tag=tag+c;
					} else {
						value=value+c;
					}
				}
			} else {
				if (inTag) {
					tag=tag+c;
				} else {
					value=value+c;
				}
			}
		}
		setData2();
	}

	public String executePost(String targetURL, String urlParameters) {
		HttpURLConnection connection = null;
		try {
			//Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Content-Length", 
			Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");  
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			byte[] postData=urlParameters.getBytes("UTF-8");
			//Send request
			DataOutputStream wr = new DataOutputStream (
			connection.getOutputStream());
			wr.write(postData);
			wr.close();

			//Get Response  
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			String buffer="";
			while ( (line=rd.readLine()) != null) {
				if (buffer.equals("")) {
					buffer=buffer+"\n";
				}
				buffer=buffer+line;
			}
			rd.close();
			parseData(buffer);
			return(buffer);
		} catch (Exception e) {
			e.printStackTrace();
			return(null);
		} finally {
			if (connection!=null) {
				connection.disconnect();
    			}
		}
	}

	public void reloadData() {
		String res=executePost("http://www.jafva.com/JHL/", "nickname="+config.getJafvaName()+"&command=datapack");
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==btnReload) {
			config.setJafvaName(txtJafvaName.getText());
			reloadData();
		}
		if (ae.getSource()==chkIsJafva) {
			config.setJafva(chkIsJafva.isSelected());
		}
		if (ae.getSource()==chkPosition) {
			config.setJafvaOverwrite(chkPosition.isSelected());
		}
		if (ae.getSource()==chkCallsign) {
			config.setJafvaCallsign(chkCallsign.isSelected());
		}
	}

	public void changedUpdate​(DocumentEvent de) {
		if (de.getDocument()==txtAirport.getDocument()) {
			config.setAirportJafva(txtAirport.getText());
		}
		if (de.getDocument()==txtAirline.getDocument()) {
			config.setAirlineJafva(txtAirline.getText());
		}
		if (de.getDocument()==txtSuffix2.getDocument()) {
			config.setSuffixJafva(txtSuffix2.getText());
		}
		if (de.getDocument()==txtLatitude.getDocument()) {
			config.setLatitudeJafva(txtLatitude.getText());
		}
		if (de.getDocument()==txtLongitude.getDocument()) {
			config.setLongitudeJafva(txtLongitude.getText());
		}
		if (de.getDocument()==txtHeading.getDocument()) {
			config.setHeadingJafva(txtHeading.getText());
		}
	}

	public void insertUpdate​(DocumentEvent de) {
		if (de.getDocument()==txtAirport.getDocument()) {
			config.setAirportJafva(txtAirport.getText());
		}
		if (de.getDocument()==txtAirline.getDocument()) {
			config.setAirlineJafva(txtAirline.getText());
		}
		if (de.getDocument()==txtSuffix2.getDocument()) {
			config.setSuffixJafva(txtSuffix2.getText());
		}
		if (de.getDocument()==txtLatitude.getDocument()) {
			config.setLatitudeJafva(txtLatitude.getText());
		}
		if (de.getDocument()==txtLongitude.getDocument()) {
			config.setLongitudeJafva(txtLongitude.getText());
		}
		if (de.getDocument()==txtHeading.getDocument()) {
			config.setHeadingJafva(txtHeading.getText());
		}
	}

	public void removeUpdate​(DocumentEvent de) {
		if (de.getDocument()==txtAirport.getDocument()) {
			config.setAirportJafva(txtAirport.getText());
		}
		if (de.getDocument()==txtAirline.getDocument()) {
			config.setAirlineJafva(txtAirline.getText());
		}
		if (de.getDocument()==txtSuffix2.getDocument()) {
			config.setSuffixJafva(txtSuffix2.getText());
		}
		if (de.getDocument()==txtLatitude.getDocument()) {
			config.setLatitudeJafva(txtLatitude.getText());
		}
		if (de.getDocument()==txtLongitude.getDocument()) {
			config.setLongitudeJafva(txtLongitude.getText());
		}
		if (de.getDocument()==txtHeading.getDocument()) {
			config.setHeadingJafva(txtHeading.getText());
		}
	}

}
