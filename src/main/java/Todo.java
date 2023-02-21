/**
 * Class for a To-do Task.
 */
public class Todo extends Task{

    /**
     * Constructor for to-do Task.
     *
     * @param description name of Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the string to the format of a to-do task
     *
     * @return a string representing the details of the task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}