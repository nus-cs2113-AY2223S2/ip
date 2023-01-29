/**
 * Represents tasks without any date/time attached to it.
 * e.g., visit new theme park.
 */
public class Todo extends Task {
    /**
     * Constructor initializing the content of the task.
     * The task is unmarked by default.
     * @param content content of the task.
     */
    public Todo(String content) {
        super(content);
    }

    /**
     * Converts the class to a string with label and marked status.
     * @return a string containing the task's label and marked status.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
