public class Event extends Task{
    protected String startTime;
    protected String endTime;

    public Event(String taskDescription, String startTime, String endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString(){
        return "[E][" + getStatusIcon() + "] " + taskDescription + " (from: " + startTime  + " to: " + endTime + ")" ;
    }
}
