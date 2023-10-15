import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Client extends User{
	
	private String request; //To be changed with type Mission
	
	public Client (String name, String surname, String email) {
		super(name,surname,email);
	}
	
	
	public static void addMission() throws IOException{
    	
    	try {
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    	
	    	// Ask client for mission's objective, location and date
	    	System.out.println("Objective : ");
	    	String objective = reader.readLine();
	    	
	    	System.out.println("Location : ");
	    	String location = reader.readLine();
	    	
	    	System.out.println("Date : ");
	    	String missionDate = reader.readLine();
	    	
	    	
	    	System.out.println("Do you confirm these informations (y or n) : \n");
	    	System.out.println("Objective : " + objective + "\n");
	    	System.out.println("Location : " + location + "\n");
	    	System.out.println("Date : " + missionDate + "\n");
	    	
	    	//If informations above are confirmed by client
	    	if(reader.readLine().equals("y")) {
	    		//Addition to database
	    		Connection con = null;
	    		
	    		//TO DO : Find how to insert current date in creationDate row
	    		String sql ="INSERT INTO Missions (objective, location, missionDate) VALUES (?,?,?);";
	    		
	    		con = Database.Connect();
	    		
	    		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	    			pstmt.setString(1,objective);
	    			pstmt.setString(2,location);
	    			pstmt.setString(3,missionDate);

	    			pstmt.executeUpdate();
	    			System.out.println("Mission added successfully\n");
	    			
	    		} catch (SQLException e) {
	                System.out.println("Mission addition failed " + e.getMessage() + "\n");
	                e.printStackTrace();
	            }
	    		
	    	}
	
	    	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
   
    	
    }
	
	public static void main(String[] args) throws IOException{
		addMission();
	}

}
