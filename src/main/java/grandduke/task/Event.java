package grandduke.task;

public class Event extends Task {
    private String eventFrom;
    private String eventTo;

    public Event(String taskDesc, String eventFrom, String eventTo) {
        super(taskDesc);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    @Override
    public String getTaskPrint() {
        return "[E]" + super.getTaskPrint() + " (from: " + this.eventFrom + " to: " + this.eventTo + ")";
    }
}
