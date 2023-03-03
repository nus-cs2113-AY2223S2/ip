package duke;

/**
 * A subclass Deadline of Task
 * Represents a Deadline added by the user and contains the deadline info "by"
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Constructor to construct a Deadline
     * @param description the details of the task
     * @param by the time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * gets the type of the task which is deadline
     * @return a string representing the type ([D])
     */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /**
     * Returns all the information about the task
     *
     * @return a String with all the information about the task
     */
    @Override
    public String toString(){
        return getTypeIcon() + super.getStatusIcon() +super.description + " (by: " + this.by + ")";
    }
}
