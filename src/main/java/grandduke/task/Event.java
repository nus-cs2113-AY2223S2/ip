package grandduke.task;

public class Event extends Task {
    private String eventFrom;
    private String eventTo;

    /**
     * Creates a new Event object
     * @param taskDesc the description of the task
     * @param eventFrom the start time of the event
     * @param eventTo the end time of the event
     */
    public Event(String taskDesc, String eventFrom, String eventTo) {
        super(taskDesc);
        this.eventFrom = eventFrom;
        this.eventTo = eventTo;
    }

    /**
     * Returns the string representation of the Event object
     */
    @Override
    public String getTaskPrint() {
        return "[E]" + super.getTaskPrint() + " (from: " + this.eventFrom + " to: " + this.eventTo + ")";
    }

    /**
     * Returns the string representation of the Event object to be saved in the data
     */
    @Override
    public String getTaskSaveString() {
        return "event | " + (super.getIsDone() ? "1" : "0") + " | " + super.getTaskDesc() + " /from " + this.eventFrom
                + " /to "
                + this.eventTo;
    }
}
