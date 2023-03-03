package siri.task;

/**
 * Represents a deadline task in the task list.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Create a Deadline task with its task description and deadline.
     *
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileString(){
        return "D | " + super.toFileString() + " /by: " + by ;
    }
}