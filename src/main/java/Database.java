import java.sql.*;

public class Database {
	
	static Connection con = null;
	
	public static void Connect() {
		
		
	    //mdp : Ohv3uz6x  
	    //Nom de la base de données : projet_gei_31
	    //Le login d'accès à la base de donnée : projet_gei_31
	    String url = "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_031";
	    String user = "projet_gei_031";
	    String password = "Ohv3uz6x";
	
       try {
            con = DriverManager.getConnection(url,user,password);
            System.out.println("Connected successfully\n");
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed " + e.getMessage() + "\n");
            e.printStackTrace();
        }
	}

	
	
	public static void createUserTable() {
		try(Statement stmt = con.createStatement();)
		{
			String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
					+ "		id integer PRIMARY KEY, \n"
					+ "		name text NOT NULL, \n"
					+ "		surname text NOT NULL, \n"
					+ "     email text NOT NULL, \n"
					+ "     password text NOT NULL, \n"
					+ "     type text NOT NULL\n"
					+ ");";
			stmt.executeUpdate(sql);
			System.out.println("User table created successfully\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void createMissionTable() {
		try(Statement stmt = con.createStatement();)
		{
			String sql = "CREATE TABLE IF NOT EXISTS Users (\n"
					+ "		id integer PRIMARY KEY, \n"
					+ "		objective text NOT NULL, \n"
					+ "		location text NOT NULL, \n"
					+ "     creationDate text NOT NULL, \n"
					+ "     missionDate text NOT NULL, \n"
					+ ");";
			stmt.executeUpdate(sql);
			System.out.println("Mission table created successfully\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connect();
		createUserTable();
		createMissionTable();
	}

}
