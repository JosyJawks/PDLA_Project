package Model;


public class Client extends User{
	public Client(){
		setType("Client");
	}
	private Mission request;

	//Each mission is associated with a Client
	public Mission getRequest() {
		return request;
	}

	public void setRequest(Mission task) {
		this.request = task;
	}


}
