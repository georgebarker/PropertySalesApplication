package listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import dao.PropertySalesDao;
import database.Database;
import database.PropertySalesDatabase;
import service.PropertySalesService;
import view.MainViewController;

public class OpenDatabaseButtonClickedListener implements ActionListener {
	JFileChooser fileChooser;
	JLabel invalidLabel;

	public OpenDatabaseButtonClickedListener(JFileChooser fileChooser, JLabel invalidLabel) {
		this.invalidLabel = invalidLabel;
		this.fileChooser = fileChooser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame frame = (JFrame) SwingUtilities.windowForComponent((Component) e.getSource());
		if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
			String databasePath = fileChooser.getSelectedFile().getAbsolutePath();
			Database database = PropertySalesDatabase.getInstance(databasePath);
			if (database.isValid()) {
				frame.dispose();
				new MainViewController(new PropertySalesService(new PropertySalesDao(database)));
			} else {
				invalidLabel.setVisible(true);
			}

		}
	}
}
