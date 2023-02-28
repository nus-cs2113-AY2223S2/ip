package duke.task;

/**
 * Represents a todo Task
 */
public class Todo extends Task {

    /**
     * Constructor for a todo task
     *
     * @param description the description of the todo task
     * @param taskType the type of task
     */
    public Todo(String description, String taskType) {
        super(description, taskType);
    }


    /**
     * Output the todo task and its details
     *
     * @return the type, status, description of the todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }


    /**
     * Represents the details of the todo task to be saved on the text file
     *
     * @return the type, status, description of the todo task
     */
    @Override
    public String textToSave() {
        return "T | " + (super.isDone ? 1 : 0) + " | " + super.description;
    }
}