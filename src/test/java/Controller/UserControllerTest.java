package Controller;

import static org.junit.Assert.*;

import Model.Client;
import Model.Database;
import Model.User;
import Model.Volunteer;
import View.SignInApp;
import View.SignUpApp;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    private static Connection testConnection;
    private static Client testClient;
    private static Volunteer testVolunteer;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Initialize the database connection for testing
        testConnection = Database.Connect();
        // Create test tables
        Database.createUserTable();
        Database.createMissionTable();
    }

    @After
    public void tearDown() {
        // Clear test data after each test
        clearUserData();
    }

    private void clearUserData() {
        try (PreparedStatement pstmt = testConnection.prepareStatement("DELETE FROM Users")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Before
    public void setUp() {
        // Initialize test data
        testClient = new Client();
        testClient.setName("John");
        testClient.setSurname("Doe");
        testClient.setEmail("john.doe@example.com");
        testClient.setPassword("password");
        testClient.setType("Client");

        testVolunteer = new Volunteer();
        testVolunteer.setName("Jane");
        testVolunteer.setSurname("Doe");
        testVolunteer.setEmail("jane.doe@example.com");
        testVolunteer.setPassword("password");
        testVolunteer.setType("Volunteer");
    }

    @Test
    public void testCreateUser() {
        User user = UserController.createUser("John", "Doe", "john.doe@example.com", "password", "Client");

        assertNotNull(user);
        assertEquals("John", user.getName());
        assertEquals("Doe", user.getSurname());
        assertEquals("john.doe@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals("Client", user.getType());
    }

    @Test
    public void testCreateClient() {
        Client client = UserController.createClient("John", "Doe", "john.doe@example.com", "password", "Client");

        assertNotNull(client);
        assertEquals("John", client.getName());
        assertEquals("Doe", client.getSurname());
        assertEquals("john.doe@example.com", client.getEmail());
        assertEquals("password", client.getPassword());
        assertEquals("Client", client.getType());
    }

    @Test
    public void testCreateVolunteer() {
        Volunteer volunteer = UserController.createVolunteer("Jane", "Doe", "jane.doe@example.com", "password", "Volunteer");

        assertNotNull(volunteer);
        assertEquals("Jane", volunteer.getName());
        assertEquals("Doe", volunteer.getSurname());
        assertEquals("jane.doe@example.com", volunteer.getEmail());
        assertEquals("password", volunteer.getPassword());
        assertEquals("Volunteer", volunteer.getType());
    }

    @Test
    public void testSignUp() {
        // Set up the SignUpApp interface with test data
        SignUpApp.setFinal(new String[]{"John", "Doe", "john.doe@example.com", "Client", "password"});

        UserController.SignUp();

        // Check if the client is saved in the database
        // Adjust the SQL query based on your database schema
        try (PreparedStatement pstmt = testConnection.prepareStatement("SELECT * FROM Users WHERE email = ?")) {
            pstmt.setString(1, "john.doe@example.com");
            ResultSet resultSet = pstmt.executeQuery();
            assertTrue(resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSignIn() {
        // Set up the SignUpApp interface with test data
        SignUpApp.setFinal(new String[]{"John", "Doe", "john.doe@example.com", "Client", "password"});

        // Sign up
        UserController.SignUp();

        // Check if the client is saved in the database
        // Adjust the SQL query based on your database schema
        try (PreparedStatement pstmt = testConnection.prepareStatement("SELECT * FROM Users WHERE email = ?")) {
            pstmt.setString(1, "john.doe@example.com");
            ResultSet resultSet = pstmt.executeQuery();
            assertTrue(resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
            fail("Error checking if the client is saved in the database");
        }

        // Set up the SignInApp interface with test data
        SignInApp.setFinal(new String[]{"john.doe@example.com", "password"});

        // Try signing in with the test credentials
        UserController.SignIn();

        // Check if the connectedClient is not null
        assertNotNull(UserController.getConnectedClient());
    }


}
