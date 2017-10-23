package view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import component.PSButton;
import component.PSFileChooser;
import component.PSFrame;
import component.PSLabel;
import listener.OpenDatabaseButtonClickedListener;

public class SelectDatabaseViewController {

	private static final int WIDTH = 400;
	private static final int HEIGHT = 150;

	public SelectDatabaseViewController() {
		new PSFrame("Property Sales", WIDTH, HEIGHT, setupComponents());
	}
	
	private List<JComponent> setupComponents() {
		List<JComponent> components = new ArrayList<>();

		// setup file chooser
		JFileChooser fileChooser = new PSFileChooser();

		//setup label for an invalid database selection
		JLabel invalidLabel = new PSLabel(
				"The database you have selected is invalid. Please try again.", 12, 60);
		invalidLabel.setForeground(Color.RED);
		invalidLabel.setVisible(false);

		// setup Open Database button
		JButton buttonOpenDatabase = new PSButton("Open database file...",
				new Rectangle(112, 20, 175, 25));
		ActionListener openDatabaseListener = new OpenDatabaseButtonClickedListener(fileChooser,
				invalidLabel);
		buttonOpenDatabase.addActionListener(openDatabaseListener);

		components.add(invalidLabel);
		components.add(buttonOpenDatabase);
		return components;
	}
}
