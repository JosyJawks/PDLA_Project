package Model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class User {
    
    protected String name;
    protected String surname;
    protected String email;

    public User(String name, String surname, String email) {
    	this.name = name;
    	this.surname = surname;
    	this.email = email;
    }
    
    public static void SignUp() throws IOException{
    	
    	try {
	    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    	
	    	// Ask user for name, surname, email, password and type
	    	System.out.println("Name : ");
	    	String name = reader.readLine();
	    	
	    	System.out.println("Surname : ");
	    	String surname = reader.readLine();
	    	
	    	System.out.println("E-mail adress : ");
	    	String email = reader.readLine();
	    	
	    	System.out.println("Are you looking for help (Client) or do you want to volunteer (Volunteer) ? : ");
	    	String type  = reader.readLine();
	    	while(!(type.equals("Client") || type.equals("Volunteer"))) {
	    		System.out.println("This does not correspond to a role (Client or Volunteer)\n");
	    		System.out.println("Are you looking for help (Client) or do you want to volunteer (Volunteer) ? : ");
	    		type = reader.readLine();
	    	}
	    	
	    	System.out.println("Password : ");
	    	String password = reader.readLine();
	    	
	    	String confirmPassword = "";
	    	System.out.println("Confirm Password : \n");
	    	confirmPassword = reader.readLine();
	    	while (!confirmPassword.equals(password)) {
	    		System.out.println("Confirmed password does not correspond to password \n");
	    		System.out.println("Confirm Password : \n");
	    		confirmPassword = reader.readLine();
		    	
	    	}
	    	
	    	System.out.println("Do you confirm these informations (y or n) : \n");
	    	System.out.println("Name : " + name + "\n");
	    	System.out.println("Surname : " + surname + "\n");
	    	System.out.println("E-mail : " + email + "\n");
	    	System.out.println("I want to be seen as a " + type + "\n");
	    	
	    	//If informations above are confirmed by User
	    	if(reader.readLine().equals("y")) {
	    		//Addition to database
	    		Connection con = null;
	    		
	    		String sql ="INSERT INTO Users (name, surname, email, password, type) VALUES (?,?,?,?,?);";
	    		
	    		con = Database.Connect();
	    		
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
	
	    	
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
   
    	
    }
    
    
    public void main(String[] args) throws IOException {
    	SignUp();
    }
    
}
