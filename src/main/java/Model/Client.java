package Model;
/*import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;*/

public class Client extends User{
	
	private Mission request;

	public Mission getRequest() {
		return request;
	}

	public void setRequest(Mission task) {
		this.request = task;
	}


}
