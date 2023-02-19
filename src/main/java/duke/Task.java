package duke;
import java.util.ArrayList;

/**
 * Represents a Task that contains attributes like the task description and
 * an indication of whether the task has been completed.
 */
public class Task {
    protected String description;
    protected boolean isDone = false;
    public static ArrayList<Task> tasks = new ArrayList<>(); //keep a collection of tasks
    public static int taskCount = 0; //keep track of number of tasks

    /**
     * Constructs a Task object that contains the descriptions or details of a task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Prints Task object in a specific format.
     *
     * @return A string representing the Task object.
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }

    /**
     * Gets the Status Icon of the task object by checking the isDone field.
     * @return Marks the Task with an "X" if it has been completed
     * and with a whitespace character " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Sets the isDone attribute of a task to true to indicate that the task
     * has been completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone attribute of a task to false to indicate that the task
     * has not been completed.
     */
    public void markNotDone() {
        this.isDone = false;
    }
}

