import java.util.ArrayList;

/**
 * Represents Task that has all data
 * including description, and if it
 * has been completed or not
 */
public class Task {
    static ArrayList<Task> tasks = new ArrayList<>();
    protected String description;
    protected boolean isDone = false;
    static int taskNum = 0;

    /**
     * Creates a Task object along with
     * descriptions of a task
     * @param description The description of task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Prints task in a readable format
     * along with description of it
     * @return String that represents
     * the Task object
     */
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Obtains status icon of Task by
     * checking the Boolean value of
     * isDone field.
     * @return Marks the task with X
     * if it has been specified by user.
     * Else, add whitespace instead
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Assigns Boolean value of isDone
     * to true if the task has been
     * completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Assigns Boolean value of isDone
     * to false if the task has not been
     * completed
     */
    public void markNotDone() {
        this.isDone = false;
    }
}