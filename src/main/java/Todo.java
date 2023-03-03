/**
 * Represents a task that does not
 * have any attributes assigned to
 * it (such as date/time)
 */
public class Todo extends Task {

    /**
     * Creates an ToDo object which is inherited by
     * Task object
     * @return Description of task
     */
    public String toString() {
        return ("[T]" + super.toString());
    }

    /**
     * Forms a string based on the data input
     * by user
     * @return Returns a string that indicates
     * the ToDo task
     */
    public Todo(String description) {
        super(description);
    }

}