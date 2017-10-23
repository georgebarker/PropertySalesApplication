package component;

import java.awt.Rectangle;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PSScrollPane extends JScrollPane {

	public PSScrollPane(JTable table, Rectangle rectangle) {
		super(table);
		setBounds(rectangle);
	}
}
