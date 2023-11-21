package Model;
import java.sql.*;

public class Database {

	//Connection to the database
	public static Connection Connect() {
		//Login and password for the database connection
		Connection con = null;
	    String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_031";
	    String user = "projet_gei_031";
	    String password = "Ohv3uz6x";
	
       try {
            con = DriverManager.getConnection(url,user,password);
            System.out.println("Connected successfully to database\n");
            
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed " + e.getMessage() + "\n");
            e.printStackTrace();
        }
       
       return con;
	}

	
	//Create a User Table in the database
	public static void createUserTable() {
		Connection con = Connect();
		//Create User table after connection to the database
		try(Statement stmt = con.createStatement())
		{
			String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
					+ "		id int AUTO_INCREMENT PRIMARY KEY, \n"
					+ "		name text NOT NULL, \n"
					+ "		surname text NOT NULL, \n"
					+ "     email text NOT NULL, \n"
					+ "     password text NOT NULL, \n"
					+ "     type text NOT NULL\n"
					+ ");";
			stmt.executeUpdate(sql);
			System.out.println("Users table created successfully\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//end the connection after the creation of the table
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Create a Mission Table in the database
	public static void createMissionTable() {
		Connection con = Connect();
		//Create Mission table after connection to the database
		try(Statement stmt = con.createStatement())
		{
			String sql = "CREATE TABLE IF NOT EXISTS Missions (\n"
					+ "		id int AUTO_INCREMENT PRIMARY KEY, \n"
					+ "     client text NOT NULL, \n"
					+ "		objective text NOT NULL, \n"
					+ "		location text NOT NULL, \n"
					+ "     missionDate text NOT NULL, \n"
					+ "     creationDate text NOT NULL, \n"
					+ "		status text NOT NULL\n"
					+ ");";
			stmt.executeUpdate(sql);
			System.out.println("Missions table created successfully\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//end the connection after the creation of the table
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//main for the creation of the database (executed 1 time at the beginning of the project)
	/*
	public static void main(String[] args) {
		createUserTable();
		createMissionTable();
	}
	*/

}
