/**
 * Represents a type of task with a start time and an end time.
 */
public class Event extends Task {

    protected String startTime;
    protected String endTime;
    private String smallSpace;
    private String bigSpace;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
        Ui ui = new Ui();
        this.smallSpace = ui.SMALL_SPACE;
        this.bigSpace = ui.BIG_SPACE;
    }

    /**
     * Returns the letter denoting the type of task.
     *
     * @return "E" denoting Event.
     */
    @Override
    public String getTypeOfTask() {
        return "E";
    }

    /**
     * Returns the formatted string to be displayed to user without prepended information.
     *
     * @return formatted event information for user.
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }

    /**
     * Returns the formatted string to be saved in duke.txt.
     *
     * @return formatted event information.
     */
    @Override
    public String getDetailsToSave() {
        return super.description + " /from " + this.startTime + " /to " + this.endTime;
    }

    /**
     * Returns the formatted string to be displayed to user.
     *
     * @return formatted event information for user.
     */
    public String toString() {
        return this.bigSpace + "[E][ ] " + super.getDescription() + " (from: " + this.startTime + " to: " + this.endTime + ")";
    }
}
