package view;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import component.PSFrame;
import component.PSLabel;
import model.PropertySale;

public class MapImageViewController {
	private static final int WIDTH = 1024;
	private static final int HEIGHT = 600;
	private static final String STREET_VIEW_URL = "https://maps.googleapis.com/maps/api/streetview?size=450x450&location=";
	private static final String STREET_VIEW_KEY = "&key=AIzaSyDrIbSmI7DMYQNKryM810SvSa249a8GRsk";
	private static final String MAP_VIEW_URL = "https://maps.googleapis.com/maps/api/staticmap?center=";
	private static final String MAP_VIEW_PARAMETERS = "&zoom=13&size=450x450&maptype=roadmap&markers=color:red%7C";
	private static final String MAP_VIEW_KEY = "&key=AIzaSyCEtamYKMx1Wu--6lAgJ21QOx-Q6wscSFY";
	PropertySale propertySale;
	JLabel streetViewLabel;
	JLabel mapLabel;
	JLabel errorLabel;
	JLabel noteLabel;

	public MapImageViewController(PropertySale propertySale) {
		this.propertySale = propertySale;
		JFrame frame = new PSFrame("Property Sales", WIDTH, HEIGHT, setupComponents());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private List<JComponent> setupComponents() {
		List<JComponent> components = new ArrayList<>();
		errorLabel = getErrorLabel();
		errorLabel.setVisible(false);
		errorLabel.setForeground(Color.RED);
		Image streetViewImage = getImage(getFormedStreetViewUrl());
		Image mapImage = getImage(getFormedMapsUrl());
		components.add(errorLabel);
		if (streetViewImage != null & mapImage != null) {
			streetViewLabel = new PSLabel(streetViewImage, 15, 30);
			mapLabel = new PSLabel(mapImage, 515, 30);
			components.add(mapLabel);
			components.add(streetViewLabel);

			noteLabel = new PSLabel(
					"NOTE: This feature is experimental, as it is dependent on Google Maps and does not always produce the correct images.",
					125, 525);
			noteLabel.setForeground(Color.RED);
			components.add(noteLabel);
		}
		return components;
	}

	private Image getImage(String formedUrl) {
		Image image = null;
		try {
			URL url = new URL(formedUrl);
			image = ImageIO.read(url.openStream());
			return image;
		} catch (IOException e) {
			errorLabel.setVisible(true);
			noteLabel.setVisible(false);
			return null;
		}
	}

	private String getFormedStreetViewUrl() {
		return STREET_VIEW_URL + getEncodedCriteria() + STREET_VIEW_KEY;
	}

	private String getFormedMapsUrl() {
		String encodedCriteria = getEncodedCriteria();
		return MAP_VIEW_URL + encodedCriteria + MAP_VIEW_PARAMETERS + encodedCriteria
				+ MAP_VIEW_KEY;
	}

	private String getEncodedCriteria() {
		String criteria = propertySale.getPrimaryAddressableObjectName() + " "
				+ propertySale.getSecondaryAddressableObjectName() + " " + propertySale.getStreet()
				+ " " + propertySale.getTown() + propertySale.getPostcode() + " United Kingdom";
		try {
			return URLEncoder.encode(criteria, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	private JLabel getErrorLabel() {
		return new PSLabel(
				"There was an issue retreiving the data for this property. Please check your internet connection, or try a different property.",
				110, 50);
	}

}
