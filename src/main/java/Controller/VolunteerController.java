package Controller;

import Model.Database;
import Model.Volunteer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VolunteerController {

    // Get a volunteer's id from Users table
    public static int getVolunteerID(Volunteer vol) {
        int id = -1;

        // Define the SQL query to select the id of a specific user
        String sql = "SELECT id FROM Users WHERE name = ? AND surname = ? AND email = ? AND password = ? AND type = 'volunteer';";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set the volunteer's information as parameters in the SQL query
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


    // Get volunteer's information from Users table from its ID
    public static Volunteer getVolunteerInfo(int id) {
        Volunteer vol = new Volunteer();

        // Define the SQL query to select all information related to the volunteer's ID
        String sql = "SELECT * FROM Users WHERE id = ?;";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set the volunteer's id as a parameter in the SQL query
            pstmt.setInt(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                // Create a volunteer with new information
                vol.setName(resultSet.getString("name"));
                vol.setSurname(resultSet.getString("surname"));
                vol.setEmail(resultSet.getString("email"));
                vol.setPassword(resultSet.getString("password"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return volunteer's information
        return vol;
    }
}
