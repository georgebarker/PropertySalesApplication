package dao;

import database.Database;
import database.PropertySalesDatabase;
import model.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertySaleDao {
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
    private static final String DATABASE_NAME = "property-sales.db";
    private static final String FIND_PROPERTY_SALES_BY_POSTCODE_QUERY = "SELECT * FROM sales WHERE postcode LIKE '%%%s%%';";
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    private Database database;

    public PropertySaleDao() {
        database = PropertySalesDatabase.getInstance(DATABASE_NAME);
    }

    List<PropertySale> findPropertySalesByPostcode(String postcode) {
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
            propertySale.setId(stripQuotes(resultSet.getString(ID_COLUMN)));
            propertySale.setPrice(new BigDecimal(stripQuotes(resultSet.getString(PRICE_COLUMN))));
            propertySale.setSaleDate(DateTime.parse(stripQuotes(resultSet.getString(SALE_DATE_COLUMN)), FORMATTER));
            propertySale.setPostcode(stripQuotes(resultSet.getString(POSTCODE_COLUMN)));
            propertySale.setPropertyType(PropertyType.getTypeByCode(stripQuotes(resultSet.getString(PROPERTY_TYPE_COLUMN))));
            propertySale.setNewBuild(evaluateBoolean(stripQuotes(resultSet.getString(NEW_BUILD_COLUMN))));
            propertySale.setPropertyLeaseType(PropertyLeaseType.getTypeByCode(stripQuotes(resultSet.getString(PROPERTY_LEASE_TYPE_COLUMN))));
            propertySale.setPrimaryAddressableObjectName(stripQuotes(resultSet.getString(PAON_COLUMN)));
            propertySale.setSecondaryAddressableObjectName(stripQuotes(resultSet.getString(SAON_COLUMN)));
            propertySale.setStreet(stripQuotes(resultSet.getString(STREET_COLUMN)));
            propertySale.setLocality(stripQuotes(resultSet.getString(LOCALITY_COLUMN)));
            propertySale.setTown(stripQuotes(resultSet.getString(TOWN_COLUMN)));
            propertySale.setDistrict(stripQuotes(resultSet.getString(DISTRICT_COLUMN)));
            propertySale.setCounty(stripQuotes(resultSet.getString(COUNTY_COLUMN)));
            propertySale.setCategory(PPDCategoryType.getTypeByCode((stripQuotes(resultSet.getString(CATEGORY_COLUMN)))));
            propertySale.setStatus(RecordStatus.getTypeByCode((stripQuotes(resultSet.getString(STATUS_COLUMN)))));
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

    private Boolean evaluateBoolean(String bool) {
        if (bool != null)
            return bool.equals("Y");
        return null;
    }
}
