
public class Client extends User{
	
	private String request; //To be changed with type Mission
	
	public Client (String name, String surname, String email, String request) {
		super(name,surname,email);
		this.request = request;
	}
	

}
