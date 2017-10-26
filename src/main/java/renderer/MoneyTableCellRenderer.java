package renderer;

import javax.swing.table.DefaultTableCellRenderer;

import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;

public class MoneyTableCellRenderer extends DefaultTableCellRenderer {
	@Override
	protected void setValue(Object value) {
		Money money = (Money) value;
		MoneyFormatter formatter = new MoneyFormatterBuilder().appendCurrencySymbolLocalized().appendAmount().toFormatter();
		String m = formatter.print(money);
		super.setValue(m);
	}
}
