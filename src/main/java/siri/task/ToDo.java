package siri.task;

/**
 * Represents a Todo task in the task list.
 */
public class ToDo extends Task {

    /**
     * Create an Todo task with its task description.
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString(){
        return "T | " + super.toFileString();
    }
}