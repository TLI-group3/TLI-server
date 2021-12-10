package initialize_database_test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
    @Test
    public void testConnection() throws SQLException {
        String url = "jdbc:mysql://" + System.getenv("AVANTAGE_SQLDB_URL");
        String user = System.getenv("AVANTAGE_SQLDB_USER");
        String password = System.getenv("AVANTAGE_SQLDB_PWD");
        Connection connection = DriverManager.getConnection(url, user, password);
        assertEquals(2, connection.getHoldability());
    }
}
