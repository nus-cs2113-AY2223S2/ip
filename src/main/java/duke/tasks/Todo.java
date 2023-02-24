package duke.tasks;

/**
 * Todo class that keeps track of the task object and its description.
 */
public class Todo extends Task{
    /**
     * Constructs a todo task with the given description.
     *
     * @param description String description of the todo task.
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Converts the todo task into its string representation which contains
     * information such as the task type, completion status and description.
     *
     * @return String representation of the todo task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Converts the todo task into a format to be saved in the database.
     *
     * @return String representation of the todo task meant for saving into the database.
     */
    @Override
    public String taskInformation() {
        return String.format("%s , %s", "todo", super.taskInformation());
    }
}