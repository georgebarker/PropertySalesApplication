package database;

import java.sql.*;

public interface Database {
    ResultSet query(String query) throws SQLException;

    int update(String query) throws SQLException;

}
