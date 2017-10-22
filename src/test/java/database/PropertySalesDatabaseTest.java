package database;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PropertySalesDatabaseTest {
    private static final String TEST_DATABASE_LOCATION = "src/test/resources/test.db";
    private static final String INSERT_SALE_QUERY = "insert into sales (postcode) values ('\"M33 3UR\"');";
    private static final String SELECT_SALE_QUERY = "select * from sales;";
    private static final String POSTCODE_LABEL = "postcode";
    private static final String EXPECTED_POSTCODE = "\"M33 3UR\"";
    private Database database;
    private File file;
    private ResultSet resultSet;

    @Test
    public void testIReturnAValidResultWhenQueried() throws SQLException {
    		givenADatabaseAlreadyExists();
        whenICreateADatabaseObject();
        whenIInsertAPropertySale();
        whenIQueryForAPropertySale();
        thenIAmReturnedTheSaleAsAValidResultSet();
    }

    private void thenIAmReturnedTheSaleAsAValidResultSet() throws SQLException {
        assertEquals(EXPECTED_POSTCODE, resultSet.getString(POSTCODE_LABEL));
    }

    private void whenIQueryForAPropertySale() throws SQLException {
        resultSet = database.query(SELECT_SALE_QUERY);
    }

    private void whenIInsertAPropertySale() throws SQLException {
        assertEquals(1, database.update(INSERT_SALE_QUERY));
    }

    private void givenADatabaseAlreadyExists() {
        file = new File(TEST_DATABASE_LOCATION);
        assertTrue(file.exists());
    }

    private void whenICreateADatabaseObject() {
        database = PropertySalesDatabase.getInstance(TEST_DATABASE_LOCATION);
    }
}