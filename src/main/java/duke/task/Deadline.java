package duke.task;

public class Deadline extends Task {

    protected final String taskName;
    protected String dueBy;

    public Deadline(String description, String dueBy) {
        super(description);
        this.taskName = description;
        this.dueBy = dueBy;
    }

    /**
     * returns the task stored as a string format to be
     * saved in the save file
     *
     * @return returns the task in the format of user command
     */
    public String returnCommand() {
        return super.completed() + "deadline " + taskName + " /by " + dueBy;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueBy + ")";
    }
}
