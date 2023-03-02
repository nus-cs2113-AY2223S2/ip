package duke.tasks;

public class Event extends Task implements java.io.Serializable {
    private String startTime;
    private String endTime;

    public Event(String taskName, String startTime, String endTime) {
        super(taskName); // invoke superclass constructor
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return taskTypeBoxFormat() + markedBoxFormat() + " " + getTaskName() +
                " (from: " + startTime + " to: " + endTime + ')';
    }

    @Override
    public String taskTypeBoxFormat() {
        return "[E]";
    }

}
