package Controller;

import Model.Database;
import Model.Mission;
import Model.Client;
import Model.Volunteer;

import static Controller.VolunteerController.*;
import static Controller.ClientController.*;

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
            String sql = "INSERT IGNORE INTO Missions (clientID, objective, location, missionDate, creationDate, status) VALUES (?,?,?,?,?,?);";

            Connection con = Database.Connect();

            try (PreparedStatement newmission = con.prepareStatement(sql)) {
                // Set parameters in the prepared statement with mission details
                newmission.setInt(1, getClientID(mission.getClient()));
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
        // Return a boolean to continue (or not) action performed from Create Mission button
        return validMission;
    }

    public static List<Mission> getMissionsForClient(Client client) {
        // Create a list to store Mission objects retrieved from the database
        List<Mission> missionList = new ArrayList<>();

        Connection con = Database.Connect();

        // Define the SQL query to select all Missions related to the client's ID
        String sql1 = "SELECT * FROM Missions WHERE clientID = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql1)) {
            //Get clientID from table "Users"
            pstmt.setInt(1, getClientID(client));
            ResultSet resultSet = pstmt.executeQuery();
            // Iterate through the ResultSet to retrieve mission details
            while (resultSet.next()) {
                Volunteer vol;
                int volID;
                // If there is already a volunteer on the mission
                try {
                    volID = resultSet.getInt("volunteerID");
                    vol = getVolunteerInfo(volID);
                } catch (NullPointerException e){
                    vol = new Volunteer();
                }
                // Create a new Mission object to store the retrieved details
                Mission mission = new Mission();
                mission.setClient(client);
                mission.setVolunteer(vol);
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


    public static List<Mission> getMissionsForVolunteer(Volunteer volunteer) {
        // Create a list to store Mission objects retrieved from the database
        List<Mission> missionList = new ArrayList<>();

        // Define the SQL query to select pending missions or missions confirmed by the connected volunteer
        String sql = "SELECT * FROM Missions WHERE status = 'Pending' OR (volunteerID = ? AND status = 'Confirmed');";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Get volunteerID from table "Users"
            pstmt.setInt(1,getVolunteerID(volunteer));
            // Execute the SQL query and obtain a ResultSet
            ResultSet resultSet = pstmt.executeQuery();

            // Iterate through the ResultSet to retrieve mission details
            while (resultSet.next()) {
                Volunteer vol;
               if(resultSet.getString("status").equals("Confirmed")) {
                   vol = volunteer;
               } else {
                   vol = new Volunteer();
               }
                // Create a new Mission object to store the retrieved details
                Mission mission = new Mission();
                mission.setClient(getClientInfo(resultSet.getInt("clientID")));
                mission.setVolunteer(vol);
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


    //Update missions status in the database
    public static void changeMissionStatusPending(Mission mission) {
        //Get the mission's data
        Client cli = mission.getClient();
        Volunteer vol = mission.getVolunteer();
        String location = mission.getLocation();
        String dateMission = mission.getDateMission();
        String objective = mission.getObjective();
        String dateCreation = mission.getDateCreation();

        // Define SQL query to update the selected mission's status to Pending and volunteerID to NULL in table "Missions"
        String sql = "UPDATE Missions " +
                "SET status = 'Pending', volunteerID = NULL " +
                "WHERE clientID = ? AND volunteerID = ? AND objective = ? AND location = ? AND missionDate = ? AND creationDate = ?;";

        Connection con = Database.Connect();
        // Connection to the database and update with new data
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1,getClientID(cli));
            pstmt.setInt(2, getVolunteerID(vol));
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


    public static void changeMissionStatusConfirmed(Mission mission, Volunteer vol) {
        //Get the mission data
        Client cli = mission.getClient();
        String location = mission.getLocation();
        String dateMission = mission.getDateMission();
        String objective = mission.getObjective();
        String dateCreation = mission.getDateCreation();

        // Define SQL query to update the selected mission's status to Confirmed and associate volunteerID to the connected volunteer's ID in table "Missions"
        String sql = "UPDATE Missions " +
                "SET status = 'Confirmed', volunteerID = ? " +
                "WHERE clientID = ? AND objective = ? AND location = ? AND missionDate = ? AND creationDate = ?;";

        Connection con = Database.Connect();
        // Connection to the database and update with new data
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, getVolunteerID(vol));
            pstmt.setInt(2, getClientID(cli));
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
