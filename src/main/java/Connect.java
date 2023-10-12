import java.sql.*;

public class Connect{

    public static void main(String[] args){

    	Connection con = null;
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

}
