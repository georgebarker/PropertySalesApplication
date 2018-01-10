package component;

import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

import org.joda.money.Money;
import org.joda.time.DateTime;

import renderer.DateTimeTableCellRenderer;
import renderer.MoneyTableCellRenderer;

public class PSTable extends JTable {

	PSTableModel model;
	
	public PSTable(PSTableModel model) {
		super(model);
		setAutoCreateRowSorter(true);
		setDefaultRenderer(DateTime.class, new DateTimeTableCellRenderer());
		setDefaultRenderer(Money.class, new MoneyTableCellRenderer());
		TableColumnModel columnModel = getColumnModel();
		
			columnModel.getColumn(columnModel.getColumnIndex("Address")).setMaxWidth(165);
			columnModel.getColumn(columnModel.getColumnIndex("Price")).setMaxWidth(115);
			columnModel.getColumn(columnModel.getColumnIndex("Date")).setMaxWidth(135);
			columnModel.getColumn(columnModel.getColumnIndex("Postcode")).setMaxWidth(85);
	}
}
