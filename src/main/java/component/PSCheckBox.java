package component;

import java.awt.Rectangle;

import javax.swing.JCheckBox;

public class PSCheckBox extends JCheckBox {
	
	public PSCheckBox(String text, Rectangle bounds) {
		setText(text);
		setBounds(bounds);
	}

}
