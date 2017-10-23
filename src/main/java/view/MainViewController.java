package view;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import component.PSButton;
import component.PSFrame;
import component.PSScrollPane;
import component.PSTable;
import component.PSTableModel;
import listener.PostcodeSearchButtonClickedListener;
import service.PropertySalesService;

public class MainViewController {
	
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 768;

	private PropertySalesService service;

	public MainViewController(PropertySalesService service) {
		this.service = service;
		new PSFrame("Property Sales", WIDTH, HEIGHT, setupComponents());
	}

	private List<JComponent> setupComponents() {
		List<JComponent> components = new ArrayList<>();

		//setup table
		PSTableModel model = new PSTableModel();
		JTable table = new PSTable(model);		
		JScrollPane scrollPane = new PSScrollPane(table, new Rectangle(500, 22, 500, 700));
		
		//setup search field & button
		JTextField textField = new JTextField();
		textField.setBounds(new Rectangle(180, 22, 150, 20));
		JButton button = new PSButton("Search", new Rectangle(340, 22, 100, 20));
		button.addActionListener(new PostcodeSearchButtonClickedListener(textField, model, service));
		
	
		components.add(textField);
		components.add(button);
		components.add(scrollPane);		
		return components;
	}
}
