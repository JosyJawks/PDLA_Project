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

import static org.junit.jupiter.api.Assertions.*;

public class MissionControllerTest {

    private static Connection testConnection;
    private static Client testClient;
    private static Volunteer testVolunteer;

    @BeforeAll
    public static void setUpBeforeClass() {
        // Initialize the database connection for testing
        testConnection = Database.Connect();
    }

    @AfterAll
    public static void tearDownAfterClass() {
        // Close the test database connection after all tests
        try (PreparedStatement pstmt = testConnection.prepareStatement("DELETE FROM Missions " +
                "WHERE clientID = 57 " +
                "AND objective = 'Test Objective' " +
                "AND location = 'Test Location' " +
                "AND missionDate = '20-11-2023' " +
                "AND creationDate = '19-11-2023';")) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
        // Set up a test client with known data
        testClient = new Client();
        testClient.setName("Josua");
        testClient.setSurname("Ramouche");
        testClient.setEmail("ramouche@insa-toulouse.fr");
        testClient.setPassword("1234");

        // Set up a test volunteer with known data
        testVolunteer = new Volunteer();
        testVolunteer.setName("Lucile");
        testVolunteer.setSurname("Cossoul");
        testVolunteer.setEmail("cossoul@insa-toulouse.fr");
        testVolunteer.setPassword("2341");

    }

    @Test
    public void testSaveMission() {
        Mission testMission = new Mission();
        testMission.setClient(testClient);
        testMission.setObjective("Test objective");
        testMission.setLocation("Test location");
        testMission.setDateMission("20-11-2023");
        testMission.setDateCreation("19-11-2023");
        testMission.setStatus("Pending");

        assertTrue(MissionController.saveMission(testMission), "Mission should be saved in table Missions");

        String sql = "SELECT * FROM Missions WHERE " +
                "clientID = 57 " +
                "AND volunteerID = NULL " +
                "AND objective = 'Test objective' " +
                "AND location = 'Test location' " +
                "AND missionDate = '20-11-2023' " +
                "AND creationDate = '19-11-2023' " +
                "AND status = 'Pending';";

        // Check if the mission is saved in the database
        try (PreparedStatement pstmt = testConnection.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while(resultSet.next()) {
                assertEquals(57, resultSet.getInt("clientID"));
                assertEquals("Test objective", resultSet.getString("objective"));
                assertEquals("Test location", resultSet.getString("location"));
                assertEquals("20-11-2023", resultSet.getString("missionDate"));
                assertEquals("19-11-2023", resultSet.getString("creationDate"));
                assertEquals("Pending", resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetMissionsForClient()  {
        List<Mission> missionList = MissionController.getMissionsForClient(testClient);

        // Assert that the list is not null
        assertNotNull(missionList, "Mission list should not be null");

        // Assert that the list is not empty
        assertFalse(missionList.isEmpty(), "Mission list should not be empty");
    }

    @Test
    public void testGetMissionsForVolunteer() {
        // Retrieve missions for volunteer
        List<Mission> missionList = MissionController.getMissionsForVolunteer(testVolunteer);

        // Assert that the list is not null
        assertNotNull(missionList, "Mission list should not be null");
    }


    @Test
    public void testChangeMissionStatusConfirmed() {
        Mission testMission = new Mission();
        testMission.setClient(testClient);
        testMission.setObjective("Test objective");
        testMission.setLocation("Test location");
        testMission.setDateMission("20-11-2023");
        testMission.setDateCreation("19-11-2023");
        testMission.setStatus("Pending");

        // Change the status of the mission
        MissionController.changeMissionStatusConfirmed(testMission,testVolunteer);

        // Check if the mission status is updated to Confirmed and volunteerID is set
        List<Mission> missionList = MissionController.getMissionsForClient(testClient);
        assertTrue(missionList.stream().anyMatch(m ->
                m.getClient().equals(testClient) &&
                        m.getVolunteer() != null &&
                        m.getObjective().equals("Test objective") &&
                        m.getLocation().equals("Test location") &&
                        m.getDateMission().equals("20-11-2023") &&
                        m.getDateCreation().equals("19-11-2023") &&
                        m.getStatus().equals("Confirmed")
                ));
    }

    @Test
    public void testChangeMissionStatusPending() {
        Mission testMission = new Mission();
        testMission.setClient(testClient);
        testMission.setVolunteer(testVolunteer);
        testMission.setObjective("Test objective");
        testMission.setLocation("Test location");
        testMission.setDateMission("20-11-2023");
        testMission.setDateCreation("19-11-2023");
        testMission.setStatus("Confirmed");

        // Change the status of the mission
        MissionController.changeMissionStatusPending(testMission);

        // Check if the mission status is updated to Pending and volunteerID cleared
        List<Mission> missionList = MissionController.getMissionsForClient(testClient);
        assertTrue(missionList.stream().anyMatch(m ->
                m.getClient().equals(testClient) &&
                        m.getVolunteer().getName() == null &&
                        m.getVolunteer().getSurname() == null &&
                        m.getVolunteer().getEmail() == null &&
                        m.getVolunteer().getPassword() == null &&
                        m.getObjective().equals("Test objective") &&
                        m.getLocation().equals("Test location") &&
                        m.getDateMission().equals("20-11-2023") &&
                        m.getDateCreation().equals("19-11-2023") &&
                        m.getStatus().equals("Pending")
        ));
    }
}
