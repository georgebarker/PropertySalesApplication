package application;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.SelectDatabaseViewController;

public class Application {
	public static void main(String... args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new SelectDatabaseViewController();
	}
}