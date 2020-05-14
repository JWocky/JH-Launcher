import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class JAFVASelect extends JPanel {
	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	JLabel lblIsJafva=new JLabel("Activate JAFVA functions");
	JCheckBox chkIsJafva=new JCheckBox();
	JLabel lblJafvaName=new JLabel("Your JAFVA name (Pilot Nickname)");
	JTextField txtJafvaName=new JTextField("");

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
		add(chkIsJafva);

		lblJafvaName.setFont(fntPanel);
		lblJafvaName.setBackground(Color.YELLOW);
		lblJafvaName.setOpaque(true);
		add(lblJafvaName);

		txtJafvaName.setFont(fntPanel);
		txtJafvaName.setBackground(Color.YELLOW);
		txtJafvaName.setOpaque(true);
		add(txtJafvaName);

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
		setData();
	}

	private void setData() {
		chkIsJafva.setSelected(config.getJafva());
		txtJafvaName.setText(config.getJafvaName());
//		String res=executePost("http://localhost/Test/index.php", "nickname=IH-COL&command=DATAPACK");
		String res=executePost("http://www.jafva.com/JHL/views.py", "nickname=IH-COL&command=DATAPACK");
// System.out.println(res);
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

			boolean redirect = false;
			// normally, 3xx is redirect
			int status = connection.getResponseCode();
System.out.println("status="+status);
String newUrl = connection.getHeaderField("Location");
System.out.println("newUrl="+newUrl);
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP
					|| status == HttpURLConnection.HTTP_MOVED_PERM
					|| status == HttpURLConnection.HTTP_SEE_OTHER
				) redirect = true;
			}

			//Get Response  
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder();
			String line;
			while ( (line=rd.readLine()) != null) {
System.out.println("Line="+line);
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return(response.toString());
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
	}

}
