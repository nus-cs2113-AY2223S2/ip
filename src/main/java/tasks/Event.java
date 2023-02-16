package tasks;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    @Override
    public String printTask(){
        return "[E] " + super.printTask() + " (from: " + startTime + " to: " + endTime + ")";
   }

   @Override
    public String encode(){
        String encodedString = "event" + "/" + super.encode() + "/" + startTime + "/" + endTime;  
        return encodedString;
   }
}
