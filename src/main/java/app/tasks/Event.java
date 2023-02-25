package app.tasks;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String taskDescription, boolean isDone, String startTime, String endTime) {
        super(taskDescription, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return " [E][" + getStatusIcon() + "] " + taskDescription + " (from: " + startTime + " to: " + endTime + ")";
    }
}
