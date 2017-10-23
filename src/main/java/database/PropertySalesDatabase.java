package database;

import java.io.File;
import java.sql.*;

public class PropertySalesDatabase implements Database {
	private Statement statement = null;
	private static Database database;
	private static final String DATABASE_PREFIX = "jdbc:sqlite:%s";

	private PropertySalesDatabase(String databaseName) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager
					.getConnection(String.format(DATABASE_PREFIX, databaseName));
			statement = connection.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public synchronized static Database getInstance(String databaseName) {
		if (database == null || !(new File(databaseName).exists())) {
			database = new PropertySalesDatabase(databaseName);
		}
		return database;
	}

	@Override
	public ResultSet query(String query) {
		try {
			return statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Issue with query.");
			return null;
		} catch (NullPointerException e) {
			System.out.println("Statement is null, database hasn't been instantiated.");
			return null;
		}
	}

	@Override
	public int update(String query) {
		try {
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Issue with query.");
			return 0;
		} catch (NullPointerException e) {
			System.out.println("Statement is null, database hasn't been instantiated.");
			return 0;
		}
	}

	@Override
	public boolean isValid() {
		return query("SELECT 1 FROM sales") != null;
	}
}
