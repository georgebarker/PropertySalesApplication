package database;

import java.io.File;
import java.sql.*;

public class PropertySalesDatabase implements Database {
    private Statement statement = null;
    private static Database database;
    private static final String DATABASE_PREFIX = "jdbc:sqlite:";
    private static final String CREATE_TABLE_SALES = "CREATE TABLE IF NOT EXISTS sales(" +
            "id TEXT, price INTEGER, sale_date TEXT, postcode TEXT, " +
            "property_type CHAR, new_build CHAR, lease_type CHAR, paon TEXT, " +
            "saon TEXT, street TEXT, locality TEXT, town TEXT, " +
            "district TEXT, county TEXT, category CHAR, status CHAR);";
    private static final String CREATE_INDEX_POSTCODE = "CREATE INDEX IF NOT EXISTS i_postcode ON sales(postcode collate nocase);";

    private PropertySalesDatabase(String databaseName) {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(DATABASE_PREFIX + databaseName);
            statement = connection.createStatement();
            update(CREATE_TABLE_SALES);
            update(CREATE_INDEX_POSTCODE);
            //importData();?
        } catch (SQLException | ClassNotFoundException e) {
            //delete database?
            e.printStackTrace();
        }
    }

    //possibly not even necessary
    public static Database getInstance(String databaseName) {
        if (database == null || !(new File(databaseName).exists())) {
            database = new PropertySalesDatabase(databaseName);
        }
        return database;
    }

    @Override
    public ResultSet query(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    @Override
    public int update(String query) throws SQLException {
        return statement.executeUpdate(query);
    }
}
