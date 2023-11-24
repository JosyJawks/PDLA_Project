package Controller;


import Model.Client;
import Model.Database;
import Model.Mission;
import Model.Volunteer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MissionControllerTest {

    private static Connection testConnection;

    private static Mission testMission;
    private static Client testClient;

    private static Volunteer testVol;

    @BeforeAll
    public static void setUpBeforeClass() {
        // Initialize the database connection for testing
        testConnection = Database.Connect();
        // Create test tables
        Database.createUserTable();
        Database.createMissionTable();
    }

    @AfterAll
    public static void tearDownAfterClass() {
        // Close the test database connection after all tests
        try (PreparedStatement pstmt = testConnection.prepareStatement("DELETE FROM Missions " +
                "WHERE client = 'John Doe' AND " +
                "objective = 'Test Objective' " +
                "AND location = 'Test Location' " +
                "AND missionDate = '2023-11-20' " +
                "AND creationDate = '2023-11-19';")) {
            pstmt.executeUpdate();
            testConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
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
    }

    @Test
    public void testChangeMissionStatus() {
        // Save a mission first to have a mission to change status
        MissionController.saveMission(testMission);

        // Change the status of the mission
        MissionController.changeMissionStatusConfirmed(testMission, testClient.getName() + " " + testClient.getSurname(), "Confirmed", testVol.getName() + " " + testVol.getSurname() );

        // Retrieve the mission to check if the status is changed
        try (PreparedStatement pstmt = testConnection.prepareStatement("SELECT * FROM Missions WHERE client = ? AND volunteer = ?")) {
            pstmt.setString(1, testClient.getName() + " " + testClient.getSurname());
            pstmt.setString(2, testVol.getName() + " " + testVol.getSurname());

            ResultSet resultSet = pstmt.executeQuery();
            assertTrue(resultSet.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
