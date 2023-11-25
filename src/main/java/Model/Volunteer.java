package Model;



public class Volunteer extends User {

   private Mission acceptedTask;

   public Volunteer(){
       setType("Volunteer");
   }

    //Each mission is associated with a Volunteer

   public Mission getAcceptedTask() {
       return acceptedTask;
   }

   public void setAcceptedTask(Mission task) {
       this.acceptedTask = task;
   }

}
