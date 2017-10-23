package component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.joda.money.Money;
import org.joda.time.DateTime;

import model.PropertySale;

public class PSTableModel extends AbstractTableModel {

	private List<PropertySale> propertySales;
	private String[] columnNames = new String[] { "Address", "Price", "Date", "Postcode" };
	
	public PSTableModel() {
		propertySales = new ArrayList<>();
	}

	public PSTableModel(List<PropertySale> propertySales) {
		this.propertySales = propertySales;
	}

	@Override
	public int getRowCount() {
		return propertySales.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return String.class;
		case 1:
			return Money.class;
		case 2:
			return DateTime.class;
		case 3:
			return String.class;
		}
		return Object.class;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		PropertySale propertySale = propertySales.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return propertySale.getPrimaryAddressableObjectName() + " "
					+ propertySale.getSecondaryAddressableObjectName();
		case 1:
			return propertySale.getPrice();
		case 2:
			return propertySale.getSaleDate();
		case 3:
			return propertySale.getPostcode();
		}
		return new String();
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	public void updateModel(List<PropertySale> propertySales) {
		this.propertySales = propertySales;
	}
}
