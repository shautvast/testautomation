package nl.sander.testautomation.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final Connection connection;

    public Database() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
    }

    public  Connection getConnection() throws SQLException {
        return connection;
    }
}
