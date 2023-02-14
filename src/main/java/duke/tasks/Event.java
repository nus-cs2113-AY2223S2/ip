package duke.tasks;

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
        return taskTypeBoxFormat() + markedBoxFormat() + " " + getTaskName() +
                " (from: " + startTime + " to: " + endTime + ')';
    }
    @Override
    public String taskTypeBoxFormat() {
        return "[E]";
    }

}
