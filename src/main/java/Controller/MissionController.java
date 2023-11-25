package Controller;

import Model.Database;
import Model.Mission;
import Model.Client;
import Model.Volunteer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MissionController {


    public static int getClientID(Client cli) {
        int id = -1;

        // Define the SQL query to select missions for a specific client
        String sql = "SELECT id FROM Users WHERE name = ? AND surname = ? AND email = ? AND password = ? AND type = 'client';";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set the client's info as parameters in the SQL query
            pstmt.setString(1, cli.getName());
            pstmt.setString(2, cli.getSurname());
            pstmt.setString(3, cli.getEmail());
            pstmt.setString(4, cli.getPassword());

            // Execute the SQL query and obtain a ResultSet
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static Client getClientInfo(int id) {
        Client cli = new Client();

        String sql = "SELECT * FROM Users WHERE id = ?;";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            //Get volunteer info from table Users
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                cli.setName(resultSet.getString("name"));
                cli.setSurname(resultSet.getString("surname"));
                cli.setEmail(resultSet.getString("email"));
                cli.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cli;
    }

    public static int getVolunteerID(Volunteer vol) {
        int id = -1;

        // Define the SQL query to select missions for a specific client
        String sql = "SELECT id FROM Users WHERE name = ? AND surname = ? AND email = ? AND password = ? AND type = 'volunteer';";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set the client's info as parameters in the SQL query
            pstmt.setString(1, vol.getName());
            pstmt.setString(2, vol.getSurname());
            pstmt.setString(3, vol.getEmail());
            pstmt.setString(4, vol.getPassword());

            // Execute the SQL query and obtain a ResultSet
            ResultSet resultSet = pstmt.executeQuery();

            while(resultSet.next()) {
                id = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static Volunteer getVolunteerInfo(int id) {
        Volunteer vol = new Volunteer();

        String sql = "SELECT * FROM Users WHERE id = ?;";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            //Get volunteer info from table Users
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                vol.setName(resultSet.getString("name"));
                vol.setSurname(resultSet.getString("surname"));
                vol.setEmail(resultSet.getString("email"));
                vol.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vol;
    }

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
        return validMission;
    }

    public static List<Mission> getMissionsForClient(Client client) {
        // Create a list to store Mission objects retrieved from the database
        List<Mission> missionList = new ArrayList<>();

        Connection con = Database.Connect();

        String sql1 = "SELECT * FROM Missions WHERE clientID = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql1)) {
            //Get clientID from table Users
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

        // Define the SQL query to select missions for a specific client
        String sql = "SELECT * FROM Missions WHERE status = 'Pending' OR (volunteerID = ? AND status = 'Confirmed');";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
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

    public static List<String> getNamesForVolunteer() {
        // Create a list to store names retrieved from the database
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
    public static void changeMissionStatusPending(Mission mission) {
        //Get the mission data
        Client cli = mission.getClient();
        Volunteer vol = mission.getVolunteer();
        String location = mission.getLocation();
        String dateMission = mission.getDateMission();
        String objective = mission.getObjective();
        String dateCreation = mission.getDateCreation();

        String sql = "UPDATE Missions " +
                "SET status = 'Pending', volunteerID = NULL " +
                "WHERE clientID = ? AND volunteerID = ? AND objective = ? AND location = ? AND missionDate = ? AND creationDate = ?;";

        Connection con = Database.Connect();
        //connection to the database and update with new data
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

        String sql = "UPDATE Missions " +
                "SET status = 'Confirmed', volunteerID = ? " +
                "WHERE clientID = ? AND objective = ? AND location = ? AND missionDate = ? AND creationDate = ?;";

        Connection con = Database.Connect();
        //connection to the database and update with new data

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
