public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String taskName, String startTime, String endTime ){
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString(){
        return "[E]" + super.toString()+ " (from: " + startTime + " to: " + endTime + ")";
    }

} // Event class ends here