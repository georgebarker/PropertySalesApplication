package dao;

import database.Database;
import model.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertySalesDao {
    private static final String ID_COLUMN = "id";
    private static final String PRICE_COLUMN = "price";
    private static final String SALE_DATE_COLUMN = "sale_date";
    private static final String POSTCODE_COLUMN = "postcode";
    private static final String PROPERTY_TYPE_COLUMN = "property_type";
    private static final String NEW_BUILD_COLUMN = "new_build";
    private static final String PROPERTY_LEASE_TYPE_COLUMN = "lease_type";
    private static final String PAON_COLUMN = "paon";
    private static final String SAON_COLUMN = "saon";
    private static final String STREET_COLUMN = "street";
    private static final String LOCALITY_COLUMN = "locality";
    private static final String TOWN_COLUMN = "town";
    private static final String DISTRICT_COLUMN = "district";
    private static final String COUNTY_COLUMN = "county";
    private static final String CATEGORY_COLUMN = "category";
    private static final String STATUS_COLUMN = "status";
    private static final String FIND_PROPERTY_SALES_BY_POSTCODE_QUERY = "SELECT * FROM sales WHERE postcode LIKE '%%%s%%';";
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    private Database database;

    public PropertySalesDao(Database database) {
        this.database = database;
    }

    public List<PropertySale> findPropertySalesByPostcode(String postcode) {
        try {
            String formatted = String.format(FIND_PROPERTY_SALES_BY_POSTCODE_QUERY, postcode);
            ResultSet resultSet = database.query(formatted);
            return mapResultSetToPropertySales(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<PropertySale> mapResultSetToPropertySales(ResultSet resultSet) throws SQLException {
        List<PropertySale> propertySales = new ArrayList<>();
        while (resultSet.next()) {
            PropertySale propertySale = new PropertySale();
            propertySale.setId(retrieveResultAndStripQuotes(resultSet, ID_COLUMN));
            propertySale.setPrice(stringToMoney(retrieveResultAndStripQuotes(resultSet, PRICE_COLUMN)));
            propertySale.setSaleDate(DateTime.parse(retrieveResultAndStripQuotes(resultSet, SALE_DATE_COLUMN), FORMATTER));
            propertySale.setPostcode(retrieveResultAndStripQuotes(resultSet, POSTCODE_COLUMN));
            propertySale.setPropertyType(PropertyType.getTypeByCode(retrieveResultAndStripQuotes(resultSet, PROPERTY_TYPE_COLUMN)));
            propertySale.setNewBuild(evaluateBoolean(retrieveResultAndStripQuotes(resultSet, NEW_BUILD_COLUMN)));
            propertySale.setPropertyLeaseType(PropertyLeaseType.getTypeByCode(retrieveResultAndStripQuotes(resultSet, PROPERTY_LEASE_TYPE_COLUMN)));
            propertySale.setPrimaryAddressableObjectName(retrieveResultAndStripQuotes(resultSet, PAON_COLUMN));
            propertySale.setSecondaryAddressableObjectName(retrieveResultAndStripQuotes(resultSet, SAON_COLUMN));
            propertySale.setStreet(retrieveResultAndStripQuotes(resultSet, STREET_COLUMN));
            propertySale.setLocality(retrieveResultAndStripQuotes(resultSet, LOCALITY_COLUMN));
            propertySale.setTown(retrieveResultAndStripQuotes(resultSet, TOWN_COLUMN));
            propertySale.setDistrict(retrieveResultAndStripQuotes(resultSet, DISTRICT_COLUMN));
            propertySale.setCounty(retrieveResultAndStripQuotes(resultSet, COUNTY_COLUMN));
            propertySale.setCategory(PPDCategoryType.getTypeByCode((retrieveResultAndStripQuotes(resultSet, CATEGORY_COLUMN))));
            propertySale.setStatus(RecordStatus.getTypeByCode((retrieveResultAndStripQuotes(resultSet, STATUS_COLUMN))));
            propertySales.add(propertySale);
        }
        return propertySales;
    }

    private String stripQuotes(String text) {
        if (text != null) {
            String quote = "\"";
            if (text.startsWith(quote) && text.endsWith(quote))
                text = text.substring(1, text.length() - 1);
        }

        return text;
    }

    private String retrieveResultAndStripQuotes(ResultSet resultSet, String column) {
        try {
            return stripQuotes(resultSet.getString(column));
        } catch (SQLException e) {
            System.out.println(String.format("Issue retrieving result from ResultSet for column: %s", column));
            e.printStackTrace();
            return null;
        }
    }


    private Money stringToMoney(String moneyString) {
        return Money.of(CurrencyUnit.GBP, Double.valueOf(moneyString));
    }

    private boolean evaluateBoolean(String bool) {
            return bool.equals("Y");
    }
}
