package tasks;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    public Event(String taskName, String startTime, String endTime){
        super(taskName, "[E]");
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString(){
        return taskSign + super.toString()+ " (from: " + startTime + " to: " + endTime + ")";
    }

} // tasks.Event class ends here