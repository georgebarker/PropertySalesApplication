package component;

import javax.swing.JLabel;

import org.apache.commons.text.WordUtils;

public class PSLabel extends JLabel {

	public PSLabel(String text, int x, int y) {
		setText(text);
		setLocation(x, y);
	}
	
	public PSLabel(int x, int y) {
		setLocation(x, y);
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		setSize(getPreferredSize());
	}
	
	public void setTextCapitalise(String text) {
		super.setText(WordUtils.capitalize(text.toLowerCase()));
		setSize(getPreferredSize());
	}
}
