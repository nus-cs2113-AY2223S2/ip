public class Event extends Task {
    private String eventFrom;
    private String eventTo;

    public Event(String taskName, String eventFrom, String eventTo) {
        super(taskName);
        super.taskType = TaskType.EVENT;
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    public String getTaskStatus() {
        return super.getTaskStatus() + "(From: " + eventFrom + "to: " + eventTo + ")";
    }
}
