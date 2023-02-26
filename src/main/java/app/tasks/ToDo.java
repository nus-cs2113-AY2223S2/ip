package app.tasks;

/**
 * Class used to represent a ToDo.
 */
public class ToDo extends Task {

    /**
     * Constructor to create a new ToDo.
     * @param taskDescription Description of the ToDo inherited from Task.
     * @param isDone Boolean value indicating if the ToDo is done or not.
     */
    public ToDo(String taskDescription, boolean isDone) {
        super(taskDescription, isDone);
    }

    /**
     * Method to print a ToDo in specified format with its given attributes.
     * @return A string representation of a ToDo.
     */
    @Override
    public String toString() {
        return " [T][" + getStatusIcon() + "] " + taskDescription;
    }

}
