package listener;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import component.PSAddressInformation;
import component.PSTableModel;
import model.PropertySale;

public class TableRowSelectedListener implements ListSelectionListener {

	private JTable table;
	private PSAddressInformation addressInformation;
	
	public TableRowSelectedListener(JTable table, PSAddressInformation addressInformation) {
		this.table = table;
		this.addressInformation = addressInformation;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			addressInformation.setNoPropertySelectedLabelVisibility(false);
			PSTableModel model = (PSTableModel) table.getModel();
			int selectedRowIndex = table.convertRowIndexToModel(selectedRow);
			PropertySale propertySale = model.getModelFromRow(selectedRowIndex);
			addressInformation.setAddressInformation(propertySale);
		} else {
			addressInformation.setNoPropertySelectedLabelVisibility(true);
		}
	}

}