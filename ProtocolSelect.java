import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class ProtocolSelect extends JPanel implements TableModelListener {
	private Configuration config=null;

	private Font fntPanel=new Font("Courier", Font.PLAIN,30);
	private Font fntPanelBig=new Font("Courier", Font.BOLD,40);

	JTable tblProtocols=null;
	JScrollPane scrProtocols=null;

	public ProtocolSelect(Configuration c) {
		config=c;
		config.setProtocolSelect(this);

		SpringLayout layout1=new SpringLayout();
		setLayout(layout1);
		setBackground(Color.DARK_GRAY);

		tblProtocols=buildTable();
		scrProtocols=new JScrollPane(
			tblProtocols, 
			ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, 
			ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
		);
		add(scrProtocols);

		layout1.putConstraint(SpringLayout.NORTH, scrProtocols, 10, SpringLayout.NORTH, this); 
		layout1.putConstraint(SpringLayout.WEST, scrProtocols, 10, SpringLayout.WEST, this); 
		layout1.putConstraint(SpringLayout.SOUTH, scrProtocols, -10, SpringLayout.SOUTH, this); 
		layout1.putConstraint(SpringLayout.EAST, scrProtocols, -10, SpringLayout.EAST, this); 

	}

	private JTable buildTable() {
		LinkedList<ProtocolEntry> buffer=config.getProtocols();
		Object[] columnNames = {"Select", "Name", "Definition"};
		Object[] columnData=buffer.toArray();
		DefaultTableModel dtmResult=new DefaultTableModel(){
			public boolean isCellEditable(int row, int column) {
				boolean result=false;
				if (column==0) {
					result=true;
				}
				return(result);
			}
		};
		dtmResult.addColumn("Select");
		dtmResult.addColumn("Name");
		dtmResult.addColumn("Definition");
		for (int i=0; i<buffer.size(); i++) {
			Object[] row={Boolean.valueOf(buffer.get(i).getSelected()), "", buffer.get(i).toString()};
			dtmResult.addRow(row);
		}

		JTable result=new JTable(dtmResult){
			public Class getColumnClass(int column) {
				switch (column) {
					case 0:
			                        return Boolean.class;
					case 1:
						return String.class;
					case 2:
						return Integer.class;
					default:
						return String.class;
		                }
			};
		};
		JTableHeader header = result.getTableHeader();
		header.setFont(fntPanel);
		result.setRowHeight(40);
		TableColumnModel columnModel = result.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(100);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(1000);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		result.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);

		result.setFont(fntPanel);
		dtmResult.addTableModelListener(this);
		config.setProtocolsClean();
		return(result);
	}

	public void reloadList() {
		DefaultTableModel dtm=(DefaultTableModel)tblProtocols.getModel();
		dtm.getDataVector().removeAllElements();
		LinkedList<ProtocolEntry> buffer=config.getProtocols();
		for (int i=0; i<buffer.size(); i++) {
			Object[] row={Boolean.valueOf(buffer.get(i).getSelected()), "", buffer.get(i).toString()};
			dtm.addRow(row);
		}
		config.setProtocolsClean();
		scrProtocols.validate();
	}

	public void tableChanged(TableModelEvent tme) {
		if (tme.getType()==TableModelEvent.UPDATE) {
			int row=tme.getFirstRow();
			int col=tme.getColumn();
			if (col==0) {
				DefaultTableModel dtm=(DefaultTableModel)tme.getSource();
				Boolean val=(Boolean)dtm.getValueAt(row, 0);
				config.getProtocols().get(row).setSelected(val.booleanValue());
			}
		}
	}

}
