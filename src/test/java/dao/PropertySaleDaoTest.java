package dao;

import database.Database;
import model.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PropertySaleDaoTest {

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

    private static final String TEST_ID = "{50F18103-0578-9FD5-E050-A8C063054923}";
    private static final String TEST_ID_WITH_QUOTES = "\"{50F18103-0578-9FD5-E050-A8C063054923}\"";
    private static final String TEST_PRICE_WITH_QUOTES = "\"225000\"";
    private static final String TEST_DATE_STRING_WITH_QUOTES = "\"2017-12-13 00:00\"";

    private static final String TEST_PRICE = "225000";
    private static final BigDecimal TEST_PRICE_BIG_DECIMAL = new BigDecimal(TEST_PRICE);
    private static final String TEST_DATE_STRING = "2017-12-13 00:00";
    private static final DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    private static final DateTime TEST_DATE_TIME = DateTime.parse(TEST_DATE_STRING, FORMATTER);
    private static final PropertyType TEST_PROPERTY_TYPE = PropertyType.TERRACED;
    private static final String TEST_PROPERTY_TYPE_CODE = "T";
    private static final String TEST_IS_NEW_BUILD = "Y";
    private static final boolean TEST_IS_NEW_BUILD_BOOL = true;
    private static final PropertyLeaseType TEST_PROPERTY_LEASE_TYPE = PropertyLeaseType.FREEHOLD;
    private static final String TEST_PROPERTY_LEASE_TYPE_CODE = "F";
    private static final String TEST_PAON = "92";
    private static final String TEST_SAON = "Apartment 14 Dane House";
    private static final String TEST_STREET = "Northenden Road";
    private static final String TEST_LOCALITY = "";
    private static final String TEST_TOWN = "Sale";
    private static final String TEST_DISTRICT = "Trafford";
    private static final String TEST_COUNTY = "Greater Manchester";
    private static final String TEST_PPD_CATEGORY_TYPE_CODE = "A";
    private static final PPDCategoryType TEST_PPD_CATEGORY_TYPE = PPDCategoryType.STANDARD_PRICE_PAID;
    private static final String TEST_RECORD_STATUS_CODE = "D";
    private static final RecordStatus TEST_RECORD_STATUS = RecordStatus.DELETE;

    private static final String FIND_PROPERTY_SALES_BY_POSTCODE_QUERY = "SELECT * FROM sales WHERE postcode LIKE '%M33%';";

    private static final String TEST_POSTCODE = "M33";

    @Mock
    private Database database;

    @Mock
    private ResultSet providedResultSet;

    @InjectMocks
    private PropertySaleDao propertySaleDao;

    private List<PropertySale> returnedPropertySales;

    public PropertySaleDaoTest() throws ParseException {
    }

    @Test
    public void testICanFindPropertySalesByPostcode() throws SQLException {
        givenIHaveADatabaseAndResultSet();
        whenIQueryTheDatabase();
        thenTheProvidedResultSetMatchesTheReturnedPropertySales();
    }

    @Test
    public void testQuotesAreStripped() throws SQLException {
        givenIHaveADatabaseAndResultSetThatHasPropertiesWithQuotes();
        whenIQueryTheDatabase();
        thenTheReturnedPropertySalesPropertiesDoNotContainQuotes();
    }

    private void givenIHaveADatabaseAndResultSetThatHasPropertiesWithQuotes() throws SQLException {
        when(database.query(FIND_PROPERTY_SALES_BY_POSTCODE_QUERY)).thenReturn(providedResultSet);
        when(providedResultSet.next()).thenReturn(true).thenReturn(false);
        when(providedResultSet.getString(ID_COLUMN)).thenReturn(TEST_ID_WITH_QUOTES);
        when(providedResultSet.getString(PRICE_COLUMN)).thenReturn(TEST_PRICE_WITH_QUOTES);
        when(providedResultSet.getString(SALE_DATE_COLUMN)).thenReturn(TEST_DATE_STRING_WITH_QUOTES);
    }

    private void givenIHaveADatabaseAndResultSet() throws SQLException {
        when(database.query(FIND_PROPERTY_SALES_BY_POSTCODE_QUERY)).thenReturn(providedResultSet);
        when(providedResultSet.next()).thenReturn(true).thenReturn(false);
        when(providedResultSet.getString(ID_COLUMN)).thenReturn(TEST_ID);
        when(providedResultSet.getString(PRICE_COLUMN)).thenReturn(TEST_PRICE);
        when(providedResultSet.getString(SALE_DATE_COLUMN)).thenReturn(TEST_DATE_STRING);
        when(providedResultSet.getString(POSTCODE_COLUMN)).thenReturn(TEST_POSTCODE);
        when(providedResultSet.getString(PROPERTY_TYPE_COLUMN)).thenReturn(TEST_PROPERTY_TYPE_CODE);
        when(providedResultSet.getString(NEW_BUILD_COLUMN)).thenReturn(TEST_IS_NEW_BUILD);
        when(providedResultSet.getString(PROPERTY_LEASE_TYPE_COLUMN)).thenReturn(TEST_PROPERTY_LEASE_TYPE_CODE);
        when(providedResultSet.getString(CATEGORY_COLUMN)).thenReturn(TEST_PPD_CATEGORY_TYPE_CODE);
        when(providedResultSet.getString(STATUS_COLUMN)).thenReturn(TEST_RECORD_STATUS_CODE);
        when(providedResultSet.getString(PAON_COLUMN)).thenReturn(TEST_PAON);
        when(providedResultSet.getString(SAON_COLUMN)).thenReturn(TEST_SAON);
        when(providedResultSet.getString(STREET_COLUMN)).thenReturn(TEST_STREET);
        when(providedResultSet.getString(LOCALITY_COLUMN)).thenReturn(TEST_LOCALITY);
        when(providedResultSet.getString(TOWN_COLUMN)).thenReturn(TEST_TOWN);
        when(providedResultSet.getString(DISTRICT_COLUMN)).thenReturn(TEST_DISTRICT);
        when(providedResultSet.getString(COUNTY_COLUMN)).thenReturn(TEST_COUNTY);

    }

    private void whenIQueryTheDatabase() throws SQLException {
        returnedPropertySales = propertySaleDao.findPropertySalesByPostcode(TEST_POSTCODE);
    }

    private void thenTheProvidedResultSetMatchesTheReturnedPropertySales() {
        PropertySale propertySale = returnedPropertySales.get(0);
        assertEquals(TEST_ID, propertySale.getId());
        assertEquals(TEST_PRICE_BIG_DECIMAL, propertySale.getPrice());
        assertEquals(TEST_DATE_TIME, propertySale.getSaleDate());
        assertEquals(TEST_POSTCODE, propertySale.getPostcode());
        assertEquals(TEST_PROPERTY_TYPE, propertySale.getPropertyType());
        assertEquals(TEST_IS_NEW_BUILD_BOOL, propertySale.isNewBuild());
        assertEquals(TEST_PROPERTY_LEASE_TYPE, propertySale.getPropertyLeaseType());
        assertEquals(TEST_PPD_CATEGORY_TYPE, propertySale.getCategory());
        assertEquals(TEST_RECORD_STATUS, propertySale.getStatus());
        assertEquals(TEST_PAON, propertySale.getPrimaryAddressableObjectName());
        assertEquals(TEST_SAON, propertySale.getSecondaryAddressableObjectName());
        assertEquals(TEST_STREET, propertySale.getStreet());
        assertEquals(TEST_LOCALITY, propertySale.getLocality());
        assertEquals(TEST_TOWN, propertySale.getTown());
        assertEquals(TEST_DISTRICT, propertySale.getDistrict());
        assertEquals(TEST_COUNTY, propertySale.getCounty());
    }

    private void thenTheReturnedPropertySalesPropertiesDoNotContainQuotes() {
        PropertySale propertySale = returnedPropertySales.get(0);
        assertEquals(TEST_ID, propertySale.getId());
        assertEquals(TEST_PRICE_BIG_DECIMAL, propertySale.getPrice());
        assertEquals(TEST_DATE_TIME, propertySale.getSaleDate());
    }
}