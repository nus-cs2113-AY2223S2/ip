package duke.task;

public class Event extends Task {
    protected String startTime;
    protected String endTime;

    public Event(String task, String startTime, String endTime) {
        super(task);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * gets the duration of the event
     *
     * @return the formatted string of the event duration
     */
    public String getDuration() {
        return "(from:" + startTime + "to:" + endTime + ")";
    }

    /**
     * gets the type of task (event type)
     *
     * @return letter representing type of task
     */
    @Override
    public String getTaskType() {
        return "[E]";
    }

    /**
     * Shows the full event status and description
     *
     * @return event status and description
     */
    @Override
    public String getTaskStatus() {
        return "[E]" + "[" + getStatusIcon() + "] " + task + getDuration();
    }
}
