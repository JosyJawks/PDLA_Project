package Controller;

import static org.junit.Assert.*;

import Model.Client;
import Model.Database;
import Model.Mission;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MissionControllerTest {

    private static Connection testConnection;

    private Mission testMission;
    private Client testClient;

    @BeforeClass
    public static void setUpBeforeClass() {
        // Initialize the database connection for testing
        testConnection = Database.Connect();
        // Create test tables
        Database.createUserTable();
        Database.createMissionTable();
    }

    @AfterClass
    public static void tearDownAfterClass() {
        // Close the test database connection after all tests
        try {
            testConnection.close();
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

        testMission = new Mission();
        testMission.setClient(testClient);
        testMission.setObjective("Test Objective");
        testMission.setLocation("Test Location");
        testMission.setDateMission("2023-11-20");
        testMission.setDateCreation("2023-11-19");
        testMission.setStatus("Pending");
    }

    @Test
    public void testSaveMission() {
        MissionController.saveMission(testMission);

        // Check if the mission is saved in the database
        // Adjust the SQL query based on your database schema
        try (PreparedStatement pstmt = testConnection.prepareStatement("SELECT * FROM Missions WHERE client = ?")) {
            pstmt.setString(1, testClient.getName() + " " + testClient.getSurname());
            ResultSet resultSet = pstmt.executeQuery();
            assertTrue(resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetMissionsForClient() {
        // Add a mission for the test client
        MissionController.saveMission(testMission);

        // Retrieve missions for the test client
        List<Mission> missions = MissionController.getMissionsForClient(testClient);

        // Assert that the list is not empty
        assertFalse(missions.isEmpty());
        // Add more assertions based on your expected data
    }

    @Test
    public void testGetMissionsForVolunteer() {
        // Add missions with different statuses
        MissionController.saveMission(testMission);
        testMission.setStatus("Confirmed");
        MissionController.saveMission(testMission);

        // Retrieve missions for volunteers
        List<Mission> missions = MissionController.getMissionsForVolunteer();

        // Assert that the list is not empty
        assertFalse(missions.isEmpty());
        // Add more assertions based on your expected data
    }

    @Test
    public void testGetNamesForVolunteer() {
        // Add missions with different statuses
        MissionController.saveMission(testMission);
        testMission.setStatus("Confirmed");
        MissionController.saveMission(testMission);

        // Retrieve names for volunteers
        List<String> names = MissionController.getNamesForVolunteer();

        // Assert that the list is not empty
        assertFalse(names.isEmpty());
        // Add more assertions based on your expected data
    }

    @Test
    public void testChangeMissionStatus() {
        // Save a mission first to have a mission to change status
        MissionController.saveMission(testMission);

        // Change the status of the mission
        MissionController.changeMissionStatus(testMission, testClient.getName() + " " + testClient.getSurname(), "Confirmed");

        // Retrieve the mission to check if the status is changed
        try (PreparedStatement pstmt = testConnection.prepareStatement("SELECT * FROM Missions WHERE client = ?")) {
            pstmt.setString(1, testClient.getName() + " " + testClient.getSurname());
            ResultSet resultSet = pstmt.executeQuery();
            assertTrue(resultSet.next());
            // Add assertions based on your expected data and the changed status
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
