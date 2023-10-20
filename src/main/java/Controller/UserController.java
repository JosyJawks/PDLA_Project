package Controller;

import Model.Database;
import Model.User;
import View.SignUpApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserController {

	public static User createUser(String name, String surname, String email, String password, String type) {
		User U = new User();
		U.setName(name);
		U.setSurname(surname);
		U.setEmail(email);
		U.setPassword(password);
		U.setType(type);
		
		return U;
	}
	
	 public static void SignUp(){

		 	// Get Array of data from interface
            String[] UserData = SignUpApp.getFinal();
		 	//Create user with data
		    User U = createUser(UserData[0],UserData[1],UserData[2],UserData[4],UserData[3]);

			String name = U.getName();
			String surname = U.getSurname();
			String email = U.getEmail();
			String type = U.getType();
			String password = U.getPassword();


			//Addition to database
			String sql ="INSERT INTO Users (name, surname, email, password, type) VALUES (?,?,?,?,?);";
			
			Connection con = Database.Connect();
			
			try (PreparedStatement pstmt = con.prepareStatement(sql)) {
				pstmt.setString(1,name);
				pstmt.setString(2,surname);
				pstmt.setString(3,email);
				pstmt.setString(4,password);
				pstmt.setString(5, type);
				pstmt.executeUpdate();
				System.out.println("Account created successfully\n");
				
			} catch (SQLException e) {
	            System.out.println("Account creation failed " + e.getMessage() + "\n");
	            e.printStackTrace();
	        }
	 }

}