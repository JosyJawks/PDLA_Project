package Model;

public class Mission {
    private String location;
    private String dateMission;
    private String objective;
    private String dateCreation;


    // Getters and setters
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


}

