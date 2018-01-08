package database;

import java.io.File;
import java.sql.*;

public class PropertySalesDatabase {
	Connection connection = null;
	private static PropertySalesDatabase database;
	private static final String DATABASE_PREFIX = "jdbc:sqlite:%s";

	private PropertySalesDatabase(String databaseName) {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(String.format(DATABASE_PREFIX, databaseName));
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public synchronized static PropertySalesDatabase getInstance(String databaseName) {
		if (database == null || !(new File(databaseName).exists())) {
			database = new PropertySalesDatabase(databaseName);
		}
		return database;
	}

	public ResultSet query(String query, String postcode) {
		if (connection != null) {
			try {
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, "%" + postcode + "%");
				return statement.executeQuery();
			} catch (SQLException e) {
				System.err.println("Issue with query.");
				return null;
			}
		} else {
			System.err.println("Connection is null, database has not been instantiated.");
			return null;
		}
	}

	public boolean isValid() {
		try {
			return connection.createStatement().executeQuery("SELECT 1 FROM sales") != null;
		} catch (SQLException e) {
			System.err.println("Database is not valid.");
			database = null;
			return false;
		}
	}
}
