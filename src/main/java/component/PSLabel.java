package component;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.apache.commons.text.WordUtils;

public class PSLabel extends JLabel {

	public PSLabel(String text, int x, int y) {
		setText(text);
		setLocation(x, y);
	}
	
	public PSLabel(Image image, int x, int y) {
		super(new ImageIcon(image));
		setLocation(x, y);
		setSize(new Dimension(500, 500));
		setVisible(true);
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
