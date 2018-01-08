package listener;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import dao.PropertySalesDao;
import database.PropertySalesDatabase;
import view.MainViewController;

public class OpenDatabaseButtonClickedListener implements ActionListener, KeyListener {
	JFileChooser fileChooser;
	JLabel invalidLabel;

	public OpenDatabaseButtonClickedListener(JFileChooser fileChooser, JLabel invalidLabel) {
		this.invalidLabel = invalidLabel;
		this.fileChooser = fileChooser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonClicked(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			buttonClicked(e);
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

	private void buttonClicked(AWTEvent e) {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			String databasePath = fileChooser.getSelectedFile().getAbsolutePath();
			PropertySalesDatabase database = PropertySalesDatabase.getInstance(databasePath);
			if (database.isValid()) {
				frame.dispose();
				new MainViewController(new PropertySalesDao(database));
			} else {
				invalidLabel.setVisible(true);
			}
		}
	}
}
