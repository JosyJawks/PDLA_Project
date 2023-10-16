package Model;
import java.sql.*;

public class Database {
	
 
	
	public static Connection Connect() {
		Connection con = null;
		
	    //mdp : Ohv3uz6x  
	    //Nom de la base de données : projet_gei_31
	    //Le login d'accès à la base de donnée : projet_gei_31
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

	
	
	public static void createUserTable() {
		Connection con = Connect();
		try(Statement stmt = con.createStatement();)
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
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createMissionTable() {
		Connection con = Connect();
		try(Statement stmt = con.createStatement();)
		{
			String sql = "CREATE TABLE IF NOT EXISTS Missions (\n"
					+ "		id int AUTO_INCREMENT PRIMARY KEY, \n"
					+ "     client text NOT NULL, \n"
					+ "		objective text NOT NULL, \n"
					+ "		location text NOT NULL, \n"
					+ "     creationDate datetime default now(), \n"
					+ "     missionDate text NOT NULL\n"
					+ ");";
			stmt.executeUpdate(sql);
			System.out.println("Missions table created successfully\n");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		createUserTable();
		createMissionTable();
	}

}
