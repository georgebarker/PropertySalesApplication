package renderer;

import javax.swing.table.DefaultTableCellRenderer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class DateTimeTableCellRenderer extends DefaultTableCellRenderer {

	@Override
	protected void setValue(Object value) {
		DateTime dateTime = (DateTime) value;
		super.setValue(dateTime.toString(DateTimeFormat.mediumDate()));
	}
}