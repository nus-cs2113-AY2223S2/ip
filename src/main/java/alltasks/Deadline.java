package alltasks;

/**
 * This Deadline class represents tasks with a specific deadline.
 * Coffee Bot keeps track of the deadline tasks.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Creates a Deadline class from the input command.
     *
     * @param descriptor description of input command.
     * @param by         deadline indicated in the input command.
     */
    public Deadline(String descriptor, String by) {
        super(descriptor);
        this.by = by;
    }

    @Override
    public String getInfo() {
        return String.format("%s|%s|%s|%s", "Deadline", this.isDone ? 1 : 0, this.description, this.by);
    }

    /**
     * Returns the status of completion of the task item,
     * the description of the input command, and the deadline of the task item.
     *
     * @return getStatusIcon() status of completion of task item.
     * @return description description of the input command.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "]" + " " + description + "(by:" + this.by + ")";
    }
}
