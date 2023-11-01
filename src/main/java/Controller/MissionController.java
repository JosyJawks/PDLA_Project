package Controller;

import Model.Database;
import Model.Mission;
import View.MissionApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MissionController {

    public static Mission createMission(String location, String dateMission, String objective, String dateCreation) {
        Mission M = new Mission();
        M.setLocation(location);
        M.setDateMission(dateMission);
        M.setObjective(objective);
        M.setDateCreation(dateCreation);
        return M;
    }

    public static void Mission() {

        // Get Array of data from interface
        String[] MissionData = MissionApp.getFinal();
        //Create user with data
        Mission M = createMission(MissionData[0], MissionData[1], MissionData[2], MissionData[3]);

        String location = M.getLocation();
        String dateMission = M.getDateMission();
        String objective = M.getObjective();
        String dateCreation = M.getDateCreation();



        //Addition to database
        String sql = "INSERT INTO Missions (objective, location, dateMission, dateCreation) VALUES (?,?,?,?);";
        //AJOUTER LE USER QUI CREE LA MISSION!!!!!!!!!
        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, objective);
            pstmt.setString(2, location);
            pstmt.setString(3, dateMission);
            pstmt.setString(4, dateCreation);
            pstmt.executeUpdate();
            System.out.println("Mission created successfully\n");

        } catch (SQLException e) {
            System.out.println("Mission creation failed " + e.getMessage() + "\n");
            e.printStackTrace();
        }
    }
}
