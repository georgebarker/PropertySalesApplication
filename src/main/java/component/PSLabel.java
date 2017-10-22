package component;

import javax.swing.JLabel;

public class PSLabel extends JLabel {

	public PSLabel(String text, int x, int y) {
		setText(text);
		setLocation(x, y);
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		setSize(getPreferredSize());
	}
}
