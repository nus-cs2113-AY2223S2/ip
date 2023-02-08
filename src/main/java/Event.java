public class Event extends Task {
    // tasks that start at a specific date/time and ends at specific date/time
    protected String startDateTime;
    protected String endDateTime;

    public Event(String taskName, String startDateTime, String endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[E][" + getStatusIcon() + "] " + taskName + " (from: " + startDateTime + " to: "
                + endDateTime + ")";
        return taskDetail;
    }
}
