package Model;

import java.util.Date;

public class Mission {
    private String location;
    private Date dateMission;
    private String objective;
    private Date dateCreation;

    // Constructor
    public Mission(String location, Date dateMission, String objective, Date dateCreation) {
        this.location = location;
        this.dateMission = dateMission;
        this.objective = objective;
        this.dateCreation = dateCreation;
    }

    // Getters and setters
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDateMission() {
        return dateMission;
    }

    public void setDateMission(Date date) {
        this.dateMission = date;
    }
    
    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date date) {
        this.dateCreation = date;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    @Override
    public String toString() {
        return "Mission{" +
                "location='" + location + '\'' +
                ", date of the mission=" + dateMission +
                ", objective='" + objective + '\'' +
                ", date of creation of the mission='" + dateCreation + '\'' +
                '}';
    }

}

