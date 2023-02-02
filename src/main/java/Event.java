public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String TaskName, String startTime, String endTime ){
        super(TaskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String toString(){
        return "[E]" + super.toString()+ " (from: " + startTime + " to: " + endTime + ")";
    }

} // Event class ends here