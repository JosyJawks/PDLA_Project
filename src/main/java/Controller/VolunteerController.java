package Controller;
import Model.Volunteer;
import Model.Mission;
import View.VolunteerApp;

public class VolunteerController {

    //Sets accepted Mission state to "Confirmed", and informs Client
    private void acceptMission() {
        // /!\ Need to get information about the Volunteer
        Volunteer V = new Volunteer();

        // /!\ Need to get information about the Mission from interface
        Mission M = new Mission();

        V.setAcceptedTask(M);

        //Changes Mission status from Pending to Confirmed
        M.setStatus("Confirmed");

        //Change status in table Missions

        //Informs client that task was accepted

    }


    private void cancelMission() {
        // /!\ Need to get information about the Volunteer
        Volunteer V = new Volunteer();

        // /!\ Need to get information about the Mission from interface
        Mission M = new Mission();

        //Changes Mission status from Confirmed to Pending
        V.setAcceptedTask(null);

        M.setStatus("Pending");

        //Change status in table Missions

        //Informs client that task was cancelled
    }


}
