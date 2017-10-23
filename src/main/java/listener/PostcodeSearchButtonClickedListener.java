package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;

import component.PSTableModel;
import model.PropertySale;
import service.PropertySalesService;

public class PostcodeSearchButtonClickedListener implements ActionListener {
	JTextField textField;
	PSTableModel model;
	PropertySalesService service;
	
	public PostcodeSearchButtonClickedListener(JTextField textField, PSTableModel model, PropertySalesService service) {
		this.textField = textField;
		this.model = model;
		this.service = service;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<PropertySale> propertySales = service.getPropertySalesByPostcode(textField.getText());
		model.updateModel(propertySales);
		model.fireTableDataChanged();
	}

}
