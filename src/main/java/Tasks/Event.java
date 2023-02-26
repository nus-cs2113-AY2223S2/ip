package Tasks;

public class Event extends Task {

    protected String startTime;
    
    protected String endTime;

    public Event(String taskName, String startTime, String endTime){
        super(taskName+ " (from: " + startTime + " to: " + endTime + ")", false, "[E]");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString(){
        return super.toString();
    }

}