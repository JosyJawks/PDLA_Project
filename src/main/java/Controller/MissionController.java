package Controller;

import Model.Database;
import Model.Mission;
import Model.Client;
import View.MissionApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MissionController {

    public static Mission createMission(Client client, String location, String dateMission, String objective, String dateCreation) {
        Mission M = new Mission();
        M.setClient(client);
        M.setLocation(location);
        M.setDateMission(dateMission);
        M.setObjective(objective);
        M.setDateCreation(dateCreation);
        M.setStatus("Pending");
        return M;
    }

    public static void Mission() {

        // Get Array of data from interface
        String[] MissionData = MissionApp.getFinal();
        // Get connected client
        Client client = UserController.getConnectedClient();
        //Create user with data
        Mission M = createMission(client, MissionData[0], MissionData[1], MissionData[2], MissionData[3]);

        String clientFullName = M.getClient().getName() + " " + M.getClient().getSurname();
        String location = M.getLocation();
        String dateMission = M.getDateMission();
        String objective = M.getObjective();
        String dateCreation = M.getDateCreation();
        String status = M.getStatus();


        //Addition to database
        String sql = "INSERT INTO Missions (client, objective, location, missionDate, creationDate, status) VALUES (?,?,?,?,?,?);";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, clientFullName);
            pstmt.setString(2, objective);
            pstmt.setString(3, location);
            pstmt.setString(4, dateMission);
            pstmt.setString(5, dateCreation);
            pstmt.setString(6, status);
            pstmt.executeUpdate();
            System.out.println("Mission created successfully\n");

        } catch (SQLException e) {
            System.out.println("Mission creation failed " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
}
