package view;

import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import component.PSAddressInformation;
import component.PSButton;
import component.PSChart;
import component.PSCheckBox;
import component.PSFrame;
import component.PSScrollPane;
import component.PSTable;
import component.PSTableModel;
import dao.PropertySalesDao;
import listener.PostcodeSearchButtonClickedListener;
import listener.TableRowSelectedListener;
import listener.ViewMapImageButtonClickedListener;

public class MainViewController {

	private static final int WIDTH = 1024;
	private static final int HEIGHT = 600;

	private PropertySalesDao dao;

	public MainViewController(PropertySalesDao dao) {
		this.dao = dao;
		new PSFrame("Property Sales", WIDTH, HEIGHT, setupComponents());
	}

	private List<JComponent> setupComponents() {
		List<JComponent> components = new ArrayList<>();

		// setup table
		PSTableModel model = new PSTableModel();
		JTable table = new PSTable(model);
		JScrollPane scrollPane = new PSScrollPane(table, new Rectangle(500, 22, 500, 500));

		PSAddressInformation addressInformation = new PSAddressInformation(20, 60);

		PSChart chart = new PSChart(new Rectangle(10, 250, 475, 270));
		JCheckBox checkBox = new PSCheckBox("Filter out new build properties?", new Rectangle(180, 48, 250, 20));
		
		JButton viewMapImageButton = new PSButton("View street view & map", new Rectangle(250, 215, 170, 20));
		viewMapImageButton.addActionListener(new ViewMapImageButtonClickedListener(table));
		viewMapImageButton.setVisible(false);

		table.getSelectionModel()
				.addListSelectionListener(new TableRowSelectedListener(table, addressInformation, viewMapImageButton));

		// setup search field & buttons
		JTextField textField = new JTextField();
		EventListener listener = new PostcodeSearchButtonClickedListener(textField, model, dao,
				chart, addressInformation, checkBox);
		textField.addKeyListener((KeyListener) listener);
		textField.setBounds(new Rectangle(180, 22, 150, 20));
		
		JButton searchButton = new PSButton("Search", new Rectangle(340, 22, 100, 20));
		searchButton.addActionListener((ActionListener) listener);
		
		components.addAll(addressInformation.getComponents());
		components.add(checkBox);
		components.add(chart.getChartPanel());
		components.add(textField);
		components.add(searchButton);
		components.add(viewMapImageButton);
		components.add(scrollPane);
		return components;
	}
}
