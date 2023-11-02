package Controller;
import Model.Client;
import Model.Mission;
import Model.Volunteer;


public class ClientController {


    private void endMission(){
        // /!\ Need to get information about the Client
        Client C = new Client();

        // /!\ Need to get information about the Mission from interface ClientApp
        Mission M = new Mission();

        //Set mission status from "Confirmed" to "Realized"
        M.setStatus("Realized");

        C.setRequest(null);

        //Update Missions table with new status
    }


    private void cancelMission() {
        // /!\ Need to get information about the Client
        Client C = new Client();

        // /!\ Need to get information about the Mission from interface ClientApp
        Mission M = new Mission();


        C.setRequest(null);

        //Delete mission from table Missions

        //Informs Volunteer (if mission already accepted) that task was cancelled
    }



}
