package component;

import java.awt.Rectangle;

import javax.swing.JButton;

public class PSButton extends JButton {

	public PSButton(String text, Rectangle bounds) {
		setText(text);
		setBounds(bounds);
	}
}
