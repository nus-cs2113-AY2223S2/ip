public class Event extends Task {
    private final String eventFrom;
    private final String eventTo;

    public Event(String taskName, String eventFrom, String eventTo) {
        super(taskName);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    public String getTaskStatus() {
        return "[E]" +super.getTaskStatus()
                + "(From: " + eventFrom
                + "to: " + eventTo + ")";
    }
}
