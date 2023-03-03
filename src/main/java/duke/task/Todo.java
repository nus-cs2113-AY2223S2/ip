package duke.task;

/**
 * A class representing a Todo task.
 */
public class Todo extends Task {
    public Todo(String desciption) {
        super(desciption);
    }

    /**
     * Returns a string representation of the todo task.
     *
     * @return a string representation of the todo task
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }

    /**
     * Returns the task type, "D" for deadline.
     *
     * @return the task type
     */
    @Override
    public String getTaskType() {
        return "T";
    }
}
