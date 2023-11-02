package Model;

public class Mission {

    private Client client;
    private String location;
    private String dateMission;
    private String objective;
    private String dateCreation;

    private String status;


    // Getters and setters
    public Client getClient(){ return client; }

    public void setClient(Client cli) { this.client = cli; }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDateMission() {
        return dateMission;
    }

    public void setDateMission(String date) {
        this.dateMission = date;
    }
    
    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String date) {
        this.dateCreation = date;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }


}

