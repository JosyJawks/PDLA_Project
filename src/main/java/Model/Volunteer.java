package Model;

import java.util.List;

public class Volunteer extends User {

   private Mission acceptedTask;

   public Mission getAcceptedTask() {
       return acceptedTask;
   }

   public void setAcceptedTask(Mission task) {
       this.acceptedTask = task;
   }

}
