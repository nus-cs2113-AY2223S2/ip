public class Event extends Task {

    String startTime;
    String endTime;

    /**
     * Constructor for Event task
     * @param taskName name of Event task
     * @param startTime start time of event task
     * @param endTime end time of event task
     */
    public Event(String taskName, String startTime, String endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Overrides toString() of Object class.
     * @return String indication of task type + whether task is done + task name.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
