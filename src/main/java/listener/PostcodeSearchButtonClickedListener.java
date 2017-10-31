package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;

import component.PSAddressInformation;
import component.PSChart;
import component.PSTableModel;
import model.PropertySale;
import service.PropertySalesService;

public class PostcodeSearchButtonClickedListener implements ActionListener {
	JTextField textField;
	PSTableModel model;
	PropertySalesService service;
	PSAddressInformation addressInformation;
	PSChart chart;
	

	public PostcodeSearchButtonClickedListener(JTextField textField, PSTableModel model,
			PropertySalesService service, PSChart chart, PSAddressInformation addressInformation) {
		this.textField = textField;
		this.model = model;
		this.service = service;
		this.chart = chart;
		this.addressInformation = addressInformation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = textField.getText();
		if (!text.isEmpty()) {
			addressInformation.resetAddressInformation();
			List<PropertySale> propertySales = service.getPropertySalesByPostcode(text);
			model.updateModel(propertySales);
			model.fireTableDataChanged();

			chart.updateDataset(propertySales);
		}
	}

	
}
