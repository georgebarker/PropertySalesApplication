package component;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PSFileChooser extends JFileChooser {
	public PSFileChooser() {
		super();
		setAcceptAllFileFilterUsed(false);
		setFileFilter(new FileNameExtensionFilter("SQLite Databases", "db", "sqlite", "sqlite3", "db3"));
	}
}
