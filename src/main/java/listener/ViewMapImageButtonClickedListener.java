package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import component.PSTableModel;
import model.PropertySale;
import view.MapImageViewController;

public class ViewMapImageButtonClickedListener implements ActionListener {

	JTable table;

	public ViewMapImageButtonClickedListener(JTable table) {
		this.table = table;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PropertySale propertySale = getSelectedPropertySale();
		new MapImageViewController(propertySale);
	}

	public PropertySale getSelectedPropertySale() {
		int selectedRow = table.getSelectedRow();
		PSTableModel model = (PSTableModel) table.getModel();
		int selectedRowIndex = table.convertRowIndexToModel(selectedRow);
		return model.getModelFromRow(selectedRowIndex);
	}

}
