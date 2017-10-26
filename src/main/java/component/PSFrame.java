package component;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class PSFrame extends JFrame {

	public PSFrame(String title, int width, int height, List<JComponent> components) {
		setLayout(null);
		setTitle(title);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (JComponent component : components) {
			add(component);
		}
		setResizable(false);
		setLocationRelativeTo(null); //centralises frame on screen
		setVisible(true);
		
	}

}
