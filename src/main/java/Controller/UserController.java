package Controller;

import Model.Database;
import Model.User;
import Model.Client;
import Model.Volunteer;
import View.SignUpApp;
import View.SignInApp;
import View.ClientApp;
import View.VolunteerApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserController {

	private static Client connectedClient;
	private static Volunteer connectedVolunteer;

	public static User createUser(String name, String surname, String email, String password, String type) {
		User U = new User();
		U.setName(name);
		U.setSurname(surname);
		U.setEmail(email);
		U.setPassword(password);
		U.setType(type);

		return U;
	}

	public static Client createClient(String name, String surname, String email, String password, String type) {
		Client C = new Client();
		C.setName(name);
		C.setSurname(surname);
		C.setEmail(email);
		C.setPassword(password);
		C.setType(type);

		return C;
	}

	public static Volunteer createVolunteer(String name, String surname, String email, String password, String type) {
		Volunteer V = new Volunteer();
		V.setName(name);
		V.setSurname(surname);
		V.setEmail(email);
		V.setPassword(password);
		V.setType(type);

		return V;
	}

	public static void SignUp() {
		// Get Array of data from interface
		String[] UserData = SignUpApp.getFinal();
		System.out.println(Arrays.toString(UserData));
		// Check if UserData is not null and has the expected length
		if (UserData != null && UserData.length == 6) {
			// Create user with data
			User U = createUser(UserData[0], UserData[1], UserData[2], UserData[4], UserData[3]);

			String name = U.getName();
			String surname = U.getSurname();
			String email = U.getEmail();
			String type = U.getType();
			String password = U.getPassword();

			// Addition to database
			String sql = "INSERT INTO Users (name, surname, email, password, type) VALUES (?,?,?,?,?);";

			Connection con = Database.Connect();

			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1, name);
				pstmt.setString(2, surname);
				pstmt.setString(3, email);
				pstmt.setString(4, password);
				pstmt.setString(5, type);
				pstmt.executeUpdate();
				System.out.println("Account created successfully\n");

			} catch (SQLException e) {
				System.out.println("Account creation failed " + e.getMessage() + "\n");
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid user data. Sign up failed.\n");
		}
	}


	public static void SignIn() {

		// Get Array of data from interface
		String[] UserData = SignInApp.getFinal();

		String email = UserData[0];
		String password = UserData[1];


		// SQL query to check if the email and password match a user in the database
		String selectSql = "SELECT * FROM Users WHERE email = ? AND password = ?";

		Connection con = Database.Connect();

		try (PreparedStatement selectStmt = con.prepareStatement(selectSql)) {
			selectStmt.setString(1, email);
			selectStmt.setString(2, password);

			ResultSet resultSet = selectStmt.executeQuery();

			if (resultSet.next()) {
				// User with matching email and password exists in the database
				System.out.println("Sign in successful\n");
				// Get User type, return Client or Volunteer
				if ("Client".equalsIgnoreCase(resultSet.getString("type"))) {
					String nameCli = resultSet.getString("name");
					String surnameCli = resultSet.getString("surname");
					String emailCli = resultSet.getString("email");
					String pwdCli = resultSet.getString("password");
					String typeCli = "Client";
					connectedClient = createClient(nameCli,surnameCli,emailCli,pwdCli,typeCli);
					System.out.println("Connected as " + connectedClient.getName() + " " + connectedClient.getSurname() + " - " + connectedClient.getType() + "\n");
					ClientApp cliApp = new ClientApp(connectedClient);
					cliApp.setVisible(true);
				} else if ("Volunteer".equalsIgnoreCase(resultSet.getString("type"))) {
					String nameVolunt = resultSet.getString("name");
					String surnameVolunt = resultSet.getString("surname");
					String emailVolunt = resultSet.getString("email");
					String pwdVolunt = resultSet.getString("password");
					String typeVolunt = "Volunteer";
					connectedVolunteer = createVolunteer(nameVolunt,surnameVolunt,emailVolunt,pwdVolunt,typeVolunt);
					System.out.println("Connected as " + connectedVolunteer.getName() + " " + connectedVolunteer.getSurname() + " - " + connectedVolunteer.getType() + "\n");
					VolunteerApp volApp = new VolunteerApp(connectedVolunteer);
					volApp.setVisible(true);
				}
			} else {
				// User does not exist or the credentials are incorrect
				System.out.println("Sign in failed. Email or password is incorrect.\n");
			}
		} catch (SQLException e) {
			System.out.println("Sign in failed " + e.getMessage() + "\n");
			e.printStackTrace();
		}
	}

	public static Client getConnectedClient() {
		return connectedClient;
	}

	public static Volunteer getConnectedVolunteer() {
		return connectedVolunteer;
	}
}