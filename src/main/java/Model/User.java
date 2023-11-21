package Model;

public class User {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String type;

    // Getters and setters for the user class : the name, surname, password, type (volunteer or client)
    public String getName() {
    	return this.name;
    }
    
    public String getSurname() {
    	return this.surname;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setSurname(String surname) {
    	this.surname = surname;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public void setPassword(String pwd) {
    	this.password = pwd;
    }
    
    public void setType(String type) {
    	this.type = type;
    }
   
}
