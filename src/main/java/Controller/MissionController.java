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

    public static boolean saveMission(Mission mission) {
        boolean validMission = false;
        if (!mission.getDateMission().equals("") && !mission.getLocation().equals("") && !mission.getObjective().equals("") ) {
            // SQL query to insert mission details into the "Missions" table
            String sql = "INSERT IGNORE INTO Missions (client, objective, location, missionDate, creationDate, status) VALUES (?,?,?,?,?,?);";

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
            validMission = true;
        } else {
            System.out.println("Mission's information invalid\n");
        }
        return validMission;
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

    public static List<Mission> getMissionsForVolunteer() {
        // Create a list to store Mission objects retrieved from the database
        List<Mission> missionList = new ArrayList<>();

        // Define the SQL query to select missions for a specific client
        String sql = "SELECT * FROM Missions WHERE status = ? OR status = ?";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1,"Pending");
            pstmt.setString(2,"Confirmed");
            // Execute the SQL query and obtain a ResultSet
            ResultSet resultSet = pstmt.executeQuery();

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

    public static List<String> getNamesForVolunteer() {
        // Create a list to store Mnames retrieved from the database
        List<String> nameList = new ArrayList<>();

        // Define the SQL query to select missions for a specific client
        String sql = "SELECT client FROM Missions WHERE status = ? OR status = ?";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1,"Pending");
            pstmt.setString(2,"Confirmed");
            // Execute the SQL query and obtain a ResultSet
            ResultSet resultSet = pstmt.executeQuery();

            // Iterate through the ResultSet to retrieve mission details
            while (resultSet.next()) {
                // Add the names to the list
                nameList.add(resultSet.getString("client"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return the list of Mission objects retrieved from the database
        return nameList;
    }

    //Update missions status in the database
    public static void changeMissionStatus(Mission mission, String name, String status) {
        //Get the mission data
        String location = mission.getLocation();
        String dateMission = mission.getDateMission();
        String objective = mission.getObjective();
        String dateCreation = mission.getDateCreation();

        String sql = "UPDATE Missions " +
                "SET Status = ? " +
                "WHERE client = ? AND objective = ? AND location = ? AND missionDate = ? AND creationDate = ?;";

        Connection con = Database.Connect();
        //connection to the database and update with new data
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1,status);
            pstmt.setString(2, name);
            pstmt.setString(3, objective);
            pstmt.setString(4, location);
            pstmt.setString(5, dateMission);
            pstmt.setString(6, dateCreation);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Mission status update failed\n");
            e.printStackTrace();
        }
    }

}
