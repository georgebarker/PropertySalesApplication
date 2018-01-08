package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import component.PSAddressInformation;
import component.PSChart;
import component.PSTableModel;
import dao.PropertySalesDao;
import model.PropertySale;

public class PostcodeSearchButtonClickedListener implements ActionListener, KeyListener {
	JTextField textField;
	PSTableModel model;
	PropertySalesDao dao;
	PSAddressInformation addressInformation;
	PSChart chart;
	JCheckBox checkBox;

	public PostcodeSearchButtonClickedListener(JTextField textField, PSTableModel model,
			PropertySalesDao dao, PSChart chart, PSAddressInformation addressInformation,
			JCheckBox checkBox) {
		this.textField = textField;
		this.model = model;
		this.dao = dao;
		this.chart = chart;
		this.addressInformation = addressInformation;
		this.checkBox = checkBox;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonClicked();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			buttonClicked();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Intentionally left blank
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Intentionally left blank

	}

	private void buttonClicked() {
		String text = textField.getText();
		boolean filterOutNewBuild = checkBox.isSelected();
		if (!text.isEmpty()) {
			addressInformation.resetAddressInformation();
			List<PropertySale> propertySales = dao.getPropertySalesByPostcode(text, filterOutNewBuild);
			model.updateModel(propertySales);
			model.fireTableDataChanged();
			chart.updateDataset(propertySales);
		}
	}
}
