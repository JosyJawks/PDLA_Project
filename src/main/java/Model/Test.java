package Model;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Client c1 = new Client("Josua","Ramouche","ramouche@insa-toulouse.fr");
		//c1.addMission();
		Database.Connect();
	}

}
