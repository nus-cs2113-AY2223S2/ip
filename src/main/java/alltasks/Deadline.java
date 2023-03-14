package alltasks;

/**
 * This Deadline class represents tasks with a specific deadline.
 * Coffee Bot keeps track of the deadline tasks.
 */
public class Deadline extends Task {
    private String byDeadline;

    /**
     * Creates a Deadline instance from the input command.
     *
     * @param descriptor description of input command.
     * @param byDeadline deadline indicated in the input command.
     */
    public Deadline(String descriptor, String byDeadline) {
        super(descriptor);
        this.byDeadline = byDeadline;
    }

    //Solution below adapted from Student Oh Yi Xiu Wilson
    @Override
    public String getInfo() {
        return String.format("%s|%s|%s|%s", "D", this.isDone ? 1 : 0, this.description, this.byDeadline);
    }
    //End of adapted solution from Student Oh Yi Xiu Wilson

    /**
     * Returns the status of completion of the task item,
     * the description of the input command, and the deadline of the task item.
     *
     * @return getStatusIcon() status of completion of task item.
     * @return description description of the input command.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + "(by:" + this.byDeadline + ")";
    }
}
