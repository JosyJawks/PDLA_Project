package Model;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class DatabaseTest {

    @Test
    public void testDatabaseConnection() {
        try {
            // Attempt to connect to the database
            Connection connection = Database.Connect();

            // Check if the connection is not null
            assertNotNull(connection, "Database connection should not be null");

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception thrown while testing database connection");
        }
    }

    // Add additional test methods for other functionalities of the Database class
}
