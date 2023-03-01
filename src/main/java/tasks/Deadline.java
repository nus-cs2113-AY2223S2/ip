package tasks;

/**
* This is the Deadline Task Class, Stores a task with a description and deadline
* */
public class Deadline extends Task {
    private final String by;

    /**
     * Factory Function for Deadline
    * Stores and instantiates a Deadline Object
    * */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDeadline() {
        return by;
    }

    /*
    * Prints the description of the task when listed out
    * */
    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + super.getDescription() + "(by:" + by + ")";
    }
}
