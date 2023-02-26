package app.tasks;

/**
 * Class used to represent a Deadline.
 */
public class Deadline extends Task{
    protected String deadline;

    /**
     * Constructor to create a new Deadline.
     * @param taskDescription Description of the Deadline inherited from Task.
     * @param isDone Boolean value indicating if the Deadline is done or not.
     * @param deadline The actual deadline (due date / expiry / end date etc.).
     */
    public Deadline(String taskDescription, boolean isDone, String deadline) {
        super(taskDescription, isDone);
        this.deadline = deadline;
    }

    /**
     * Getter to obtain the actual deadline of a given Deadline Task.
     * @return The deadline of a particular Deadline.
     */
    public String getDeadline() {
        return deadline;
    }

    /**
     * Method to print a Deadline in specified format with its given attributes.
     * @return A string representation of a Deadline.
     */
    @Override
    public String toString(){
        return " [D][" + getStatusIcon() + "] " + taskDescription + " (by: " + deadline + ")";
    }
}
