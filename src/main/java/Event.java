public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName); // invoke superclass constructor
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String listTask() {
        if (getIsComplete()) {
            return "[E][X] " + getTaskName() + " (from: " + startTime + " to: " + endTime + ')';
        } else {
            return "[E][] " + getTaskName() + " (from: " + startTime + " to: " + endTime + ')';
        }
    }

}
