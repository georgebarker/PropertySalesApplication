package component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

import model.PropertySale;

public class PSAddressInformation {
	private JLabel firstLineAddressLabel;
	private JLabel townLabel;
	private JLabel districtLabel;
	private JLabel postcodeLabel;
	private JLabel countyLabel;
	private List<JComponent> components;

	private static final String ADDRESS_LINE_FORMAT = "%s %s %s";

	public PSAddressInformation(int x, int y) {
		firstLineAddressLabel = new PSLabel(20, 50);
		townLabel = new PSLabel(20, 65);
		districtLabel = new PSLabel(20, 80);
		countyLabel = new PSLabel(20, 95);
		postcodeLabel = new PSLabel(20, 110);

		components = new ArrayList<>();
		components.add(firstLineAddressLabel);
		components.add(townLabel);
		components.add(districtLabel);
		components.add(postcodeLabel);
		components.add(countyLabel);
	}

	public void setAddressInformation(PropertySale propertySale) {
		firstLineAddressLabel.setText(String.format(ADDRESS_LINE_FORMAT, propertySale.getPrimaryAddressableObjectName(),
				propertySale.getSecondaryAddressableObjectName(), propertySale.getStreet()));
		townLabel.setText(propertySale.getTown());
		districtLabel.setText(propertySale.getDistrict());
		postcodeLabel.setText(propertySale.getPostcode());
		countyLabel.setText(propertySale.getCounty());
	}

	public List<JComponent> getComponents() {
		return components;
	}
}
