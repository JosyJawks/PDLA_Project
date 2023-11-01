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
        //String dateCreation = M.getDateCreation(); --> Not necessary as the table is defined to add autonomously a date in this field



        //Addition to database
        String sql = "INSERT INTO Missions (client, objective, location, missionDate) VALUES (?,?,?,?);";
        //AJOUTER LE USER QUI CREE LA MISSION!!!!!!!!!
        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1,clientFullName);
            pstmt.setString(2, objective);
            pstmt.setString(3, location);
            pstmt.setString(4, dateMission);
            pstmt.executeUpdate();
            System.out.println("Mission created successfully\n");

        } catch (SQLException e) {
            System.out.println("Mission creation failed " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
}
