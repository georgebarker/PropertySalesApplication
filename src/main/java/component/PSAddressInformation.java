package component;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

import org.joda.money.Money;
import org.joda.money.format.MoneyFormatter;
import org.joda.money.format.MoneyFormatterBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import model.PropertySale;
import model.PropertyType;

public class PSAddressInformation {
	private PSLabel firstLineAddressLabel;
	private PSLabel townLabel;
	private PSLabel districtLabel;
	private PSLabel postcodeLabel;
	private PSLabel countyLabel;
	private PSLabel propertyTypeLabel;
	private PSLabel newBuildLabel;
	private PSLabel priceLabel;
	private PSLabel saleDateLabel;
	private PSLabel noPropertySelectedLabel;
	private List<JComponent> components;
	private int y;

	private static final String ADDRESS_LINE_FORMAT = "%s %s %s";

	private static final String PRICE_FORMAT = "Sold for: %s";
	private static final String SALE_DATE_FORMAT = "Sold on: %s";
	private static final String PROPERTY_TYPE_FORMAT = "Property type: %s";

	public PSAddressInformation(int x, int y) {
		this.y = y;
		firstLineAddressLabel = new PSLabel(x, getYValue(0));
		townLabel = new PSLabel(x, getYValue(0));
		districtLabel = new PSLabel(x, getYValue(0));
		countyLabel = new PSLabel(x, getYValue(0));
		postcodeLabel = new PSLabel(x, getYValue(0));
		propertyTypeLabel = new PSLabel(x, getYValue(15));
		newBuildLabel = new PSLabel(x, getYValue(0));
		priceLabel = new PSLabel(x, getYValue(15));
		saleDateLabel = new PSLabel(x, getYValue(0));
		
		noPropertySelectedLabel = new PSLabel("No property selected.", 180, 100);

		components = new ArrayList<>();
		components.add(firstLineAddressLabel);
		components.add(townLabel);
		components.add(districtLabel);
		components.add(postcodeLabel);
		components.add(countyLabel);
		components.add(propertyTypeLabel);
		components.add(newBuildLabel);
		components.add(priceLabel);
		components.add(saleDateLabel);
		components.add(noPropertySelectedLabel);
	}

	public void setAddressInformation(PropertySale propertySale) {
		firstLineAddressLabel.setTextCapitalise(String.format(ADDRESS_LINE_FORMAT,
				propertySale.getPrimaryAddressableObjectName(),
				propertySale.getSecondaryAddressableObjectName(), propertySale.getStreet()));
		townLabel.setTextCapitalise(propertySale.getTown());
		districtLabel.setTextCapitalise(propertySale.getDistrict());
		postcodeLabel.setText(propertySale.getPostcode());
		countyLabel.setTextCapitalise(propertySale.getCounty());
		propertyTypeLabel.setText(getFormattedPropertyType(propertySale.getPropertyType()));
		newBuildLabel.setText(getFormattedBuildType(propertySale.isNewBuild()));
		priceLabel.setText(getFormattedPrice(propertySale.getPrice()));
		saleDateLabel.setText(getFormattedDate(propertySale.getSaleDate()));
		
		noPropertySelectedLabel.setVisible(false);
	}

	public List<JComponent> getComponents() {
		return components;
	}
	
	public void setNoPropertySelectedLabelVisibility(boolean isVisible) {
		if (!isVisible) {
			for (JComponent component : components) {
				((JLabel) component).setText("");
			}
		}
		noPropertySelectedLabel.setVisible(isVisible);
	}

	private String getFormattedBuildType(boolean isNewBuild) {
		return isNewBuild ? "New build" : "Old build";
	}

	private String getFormattedPrice(Money money) {
		MoneyFormatter formatter = new MoneyFormatterBuilder().appendCurrencySymbolLocalized()
				.appendAmount().toFormatter();
		return String.format(PRICE_FORMAT, formatter.print(money));
	}

	private String getFormattedDate(DateTime dateTime) {
		return String.format(SALE_DATE_FORMAT, dateTime.toString(DateTimeFormat.forPattern("dd MMMM yyyy")));	
	}
	
	private String getFormattedPropertyType(PropertyType type) {
		return String.format(PROPERTY_TYPE_FORMAT, PropertyType.getTypeString(type));
	}
	
	private int getYValue(int extraOffset) {
		return y = y + 15 + extraOffset;
	}
}
