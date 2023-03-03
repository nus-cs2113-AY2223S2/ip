/**
 * Represents a task which has a due
 * date that is represented by
 * Deadline
 */
public class Deadline extends Task {

    String due;

    /**
     * Creates a Deadline object which is inherited by
     * Task object, which then assigns the deadline
     * and description of the task
     * @param description The description of Deadline task
     * @param due The deadline of task
     */
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }

    /**
     * Forms a string based on the data input
     * by user
     * @return Returns a string that indicates
     * the task and the deadline
     */
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + due + ")");
    }
}