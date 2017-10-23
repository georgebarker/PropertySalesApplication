package database;

import java.sql.*;

public interface Database {
    ResultSet query(String query);

    int update(String query);
    
    boolean isValid();

}
