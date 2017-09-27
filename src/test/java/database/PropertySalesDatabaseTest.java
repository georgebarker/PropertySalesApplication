package database;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PropertySalesDatabaseTest {
    private static final String DATABASE_NAME = "property-sales.db";
    private static final String TEST_DATABASE_LOCATION = "src/test/resources/" + DATABASE_NAME;
    private static final String TEST_TABLE_EXISTS_QUERY = "select name from sqlite_master where type='table';";
    private static final String TEST_INDEX_EXISTS_QUERY = "select name from sqlite_master where type='index';";
    private static final String INSERT_SALE_QUERY = "insert into sales (postcode) values ('\"M33 3UR\"');";
    private static final String SELECT_SALE_QUERY = "select * from sales;";
    private static final String TABLE_NAME_LABEL = "name";
    private static final String POSTCODE_LABEL = "postcode";
    private static final String EXPECTED_TABLE_NAME = "sales";
    private static final String EXPECTED_INDEX_NAME = "i_postcode";
    private static final String EXPECTED_POSTCODE = "\"M33 3UR\"";
    private Database database;
    private long expectedLastModified;
    private File file;
    private ResultSet resultSet;

    @Before
    public void setup() {
        file = new File(TEST_DATABASE_LOCATION);
        if (file.exists()) {
            assertTrue(file.delete());
        }
        file = null;
    }

    @After
    public void cleanup() {
        assertTrue(file.delete());
        database = null;
        resultSet = null;
    }

    @Test
    public void testICreateADatabaseWhenOneDoesntAlreadyExist() {
        givenADatabaseDoesNotAlreadyExist();
        whenICreateADatabaseObject();
        thenADatabaseFileIsCreated();
    }

    @Test
    public void testIDoNotCreateANewDatabaseFileWhenOneAlreadyExists() throws IOException {
        givenADatabaseFileAlreadyExists();
        whenICreateADatabaseObject();
        thenANewDatabaseFileIsNotCreated();
    }

    @Test
    public void testDatabaseSchemaIsCreated() throws SQLException {
        givenADatabaseDoesNotAlreadyExist();
        whenICreateADatabaseObject();
        whenIQueryForTables();
        thenTheSalesTableExists();
        whenIQueryForIndices();
        thenThePostcodeIndexExists();
    }

    @Test
    public void testIReturnAValidResultWhenQueried() throws SQLException {
        givenADatabaseDoesNotAlreadyExist();
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

    private void thenThePostcodeIndexExists() throws SQLException {
            String actualIndexName = resultSet.getString(TABLE_NAME_LABEL);
            assertEquals(EXPECTED_INDEX_NAME, actualIndexName);
    }

    private void whenIQueryForIndices() throws SQLException {
        resultSet = database.query(TEST_INDEX_EXISTS_QUERY);
    }

    private void thenTheSalesTableExists() throws SQLException {
            String actualTableName = resultSet.getString(TABLE_NAME_LABEL);
            assertEquals(EXPECTED_TABLE_NAME, actualTableName);
    }

    private void whenIQueryForTables() throws SQLException {
        resultSet = database.query(TEST_TABLE_EXISTS_QUERY);
    }

    private void givenADatabaseDoesNotAlreadyExist() {
        //delete a file if there is one?
        file = new File(TEST_DATABASE_LOCATION);
        assertFalse(file.exists());
    }

    private void givenADatabaseFileAlreadyExists() throws IOException {
        file = new File(TEST_DATABASE_LOCATION);
        assertTrue(file.createNewFile());
        expectedLastModified = file.lastModified();
    }

    private void whenICreateADatabaseObject() {
        database = PropertySalesDatabase.getInstance(TEST_DATABASE_LOCATION);
    }

    private void thenADatabaseFileIsCreated() {
        file = new File(TEST_DATABASE_LOCATION);
        boolean exists = file.exists();
        assertTrue(exists);
        assertNotNull(database);
    }

    private void thenANewDatabaseFileIsNotCreated() {
        File file = new File(TEST_DATABASE_LOCATION);
        assertEquals(expectedLastModified, file.lastModified());
    }
}