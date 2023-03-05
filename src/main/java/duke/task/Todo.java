package duke.task;

/**
 * Represents a todo task with a description and isDone
 */
public class Todo extends Task {

    /**
     * Instantiates string description and defaults isDone as false
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Instantiates string description and isDone
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Overrides toString method to return output as shown in list of tasks
     *
     * @return string of todo in listed format
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
