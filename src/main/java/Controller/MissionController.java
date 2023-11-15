package Controller;

import Model.Database;
import Model.Mission;
import Model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MissionController {

    public static void saveMission(Mission mission) {
        // SQL query to insert mission details into the "Missions" table
        String sql = "INSERT INTO Missions (client, objective, location, missionDate, creationDate, status) VALUES (?,?,?,?,?,?);";

        Connection con = Database.Connect();

        try (PreparedStatement newmission = con.prepareStatement(sql)) {
            // Set parameters in the prepared statement with mission details
            newmission.setString(1, mission.getClient().getName() + " " + mission.getClient().getSurname());
            newmission.setString(2, mission.getObjective());
            newmission.setString(3, mission.getLocation());
            newmission.setString(4, mission.getDateMission());
            newmission.setString(5, mission.getDateCreation());
            newmission.setString(6, mission.getStatus());
            newmission.executeUpdate();
            System.out.println("Mission created successfully\n");

        } catch (SQLException e) {
            System.out.println("Mission creation failed " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
    public static List<Mission> getMissionsForClient(Client client) {
        // Create a list to store Mission objects retrieved from the database
        List<Mission> missionList = new ArrayList<>();

        // Define the SQL query to select missions for a specific client
        String sql = "SELECT * FROM Missions WHERE client = ?;";

        Connection con = Database.Connect();

        try (PreparedStatement fullname = con.prepareStatement(sql)) {
            // Set the client's name and surname as a parameter in the SQL query
            fullname.setString(1, client.getName() + " " + client.getSurname());

            // Execute the SQL query and obtain a ResultSet
            ResultSet resultSet = fullname.executeQuery();

            // Iterate through the ResultSet to retrieve mission details
            while (resultSet.next()) {
                // Create a new Mission object to store the retrieved details
                Mission mission = new Mission();
                mission.setObjective(resultSet.getString("objective"));
                mission.setLocation(resultSet.getString("location"));
                mission.setDateMission(resultSet.getString("missionDate"));
                mission.setDateCreation(resultSet.getString("creationDate"));
                mission.setStatus(resultSet.getString("status"));

                // Add the Mission object to the list
                missionList.add(mission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of Mission objects retrieved from the database
        return missionList;
    }
}
