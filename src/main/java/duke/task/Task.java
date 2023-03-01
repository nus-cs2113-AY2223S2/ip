package duke.task;

import duke.main.Storage;

import java.util.StringJoiner;

/**
 * Parent class for Task objects.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone = false;

    /**
     * Constructs a task with the given description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Sets the task's completion status.
     *
     * @param isDone Whether the task should be marked as completed.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Checks if the task description contains the specified string.
     *
     * @param filter The string that the task description will be searched for.
     * @return True if the task description contains the search string, false otherwise.
     */
    public boolean containsFilter(String filter) {
        return description.contains(filter);
    }

    /**
     * Converts the task into its string representation,
     * which contains its completion status, description, and possibly other parameters.
     *
     * @return String representation of the task.
     */
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }

    /**
     * Converts the task into its string representation for saving purposes,
     * which contains delimiter characters for easier parsing when loading.
     *
     * @return String representation of the task meant for saving purposes only.
     */
    public abstract String toSaveString();

    /**
     * Converts the given task parameters into a string representation for saving purposes.
     *
     * @param taskParameters The parameters of the task, such as its type and description.
     * @return String representation of the task meant for saving purposes only.
     */
    public String toSaveString(String... taskParameters) {
        StringJoiner saveString = new StringJoiner(Storage.DELIMITER);
        for (String string : taskParameters) {
            saveString.add(string);
        }
        return saveString.toString();
    }
}
