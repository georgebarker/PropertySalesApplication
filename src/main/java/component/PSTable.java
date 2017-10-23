package component;

import javax.swing.JTable;

import org.joda.time.DateTime;

import renderer.DateTimeTableCellRenderer;

public class PSTable extends JTable {

	PSTableModel model;
	
	public PSTable(PSTableModel model) {
		super(model);
		setAutoCreateRowSorter(true);
		setDefaultRenderer(DateTime.class, new DateTimeTableCellRenderer());
	}
}
