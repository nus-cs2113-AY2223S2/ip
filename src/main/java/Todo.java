/**
 * Represents tasks that are labelled as <code>Todo</code>.
 */
public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    /**
     * Add label "[T]" to a task.
     * @return labelled task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}
