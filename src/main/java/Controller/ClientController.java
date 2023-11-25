package Controller;

import Model.Client;
import Model.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientController {

    // Get a client's ID from Users table
    public static int getClientID(Client cli) {
        int id = -1;

        // Define the SQL query to select the id of a specific user
        String sql = "SELECT id FROM Users WHERE name = ? AND surname = ? AND email = ? AND password = ? AND type = 'client';";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set the client's information as parameters in the SQL query
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


    // Get a client's information from Users table from its ID
    public static Client getClientInfo(int id) {
        Client cli = new Client();

        // Define the SQL query to select all information related to the client's ID
        String sql = "SELECT * FROM Users WHERE id = ?;";

        Connection con = Database.Connect();

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Set the client's id as a parameter in the SQL query
            pstmt.setInt(1, id);

            // Execute the SQL query and obtain a ResultSet
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                // Create a client with new information
                cli.setName(resultSet.getString("name"));
                cli.setSurname(resultSet.getString("surname"));
                cli.setEmail(resultSet.getString("email"));
                cli.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Return client's information
        return cli;
    }
}
