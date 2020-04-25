import java.awt.*;
import javax.swing.*;

public class AircraftSelect extends JPanel{
	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,20);

	public AircraftSelect(Configuration c) {
		config=c;

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

//		layout1.putConstraint(SpringLayout.NORTH, tabbed, 1, SpringLayout.NORTH, this); 
//		layout1.putConstraint(SpringLayout.WEST, tabbed, 1, SpringLayout.WEST, this); 
//		layout1.putConstraint(SpringLayout.SOUTH, tabbed, -1, SpringLayout.SOUTH, this); 
//		layout1.putConstraint(SpringLayout.EAST, tabbed, -1, SpringLayout.EAST, this); 
	}

}
