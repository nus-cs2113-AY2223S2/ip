package duke.task;

public class Event extends Task {
    public String startTime;
    public String endTime;

    /**
     * Initial constructor for event task. Status is set to false (undone) by default.
     *
     * @param description description of the event task content
     * @param startTime starting date/time of the event
     * @param endTime ending data/time of the event
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Constructor for event task when task status is given.
     *
     * @param description event task description
     * @param isDone status of the event task. True if done and false if not done.
     * @param startTime starting date/time of the event
     * @param endTime ending date/time of the event
     */
    public Event(String description, boolean isDone, String startTime, String endTime) {
        super(description, isDone);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    /**
     * Returns event task with all the details.
     *
     * @return Full event task in the format of "[E][status symbol] Task description (from: starting time to: ending time)
     */
    @Override
    public String printTask() {
        return "[E]" + super.printTask() +
                "(from: " + getStartTime() + " to: " + getEndTime() + ")";
    }
}
