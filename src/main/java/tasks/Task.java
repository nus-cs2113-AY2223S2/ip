package tasks;

import utility.Ui;

/**
 * Represents the parent class Task, which is a parent to Todo, Event, Deadline.
 * It contains a description, and another isDone variable that indicates whether the task is done (or marked).
 * There are also methods to produce a string that is properly formatted to the user or to save in storage.
 */
public class Task {
    private static final String DEFAULT_MARKED_TASK_SYMBOL = "X";
    private static final String START_BRACKET = "[";
    private static final String END_BRACKET = "] ";

    private String description;

    private boolean isMarked;

    private String saveCommand;

    /**
     * Initialises an object of the class Task.
     *
     * @param description Contains the details of the description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    /**
     * Returns a string that shows if the task is marked by the user.
     *
     * @return The string that shows if it is marked or not.
     */
    public String getStatusIcon() {
        return (isMarked ? DEFAULT_MARKED_TASK_SYMBOL : Ui.DEFAULT_LINE_SEPARATOR);
    }

    /**
     * Allows the user to mark the tasks.
     * The mark and unmark method is used specifically so that users can mark or unmark tasks more simply.
     * By using setMark and getMark, it will allow the user to unmark a marked task accidentally.
     * <p>
     * Can be performed even if the task is already marked.
     */
    public void mark() {
        this.isMarked = true;
    }

    /**
     * Allows the user to unmark the tasks.
     * Can be performed even if the task is already unmarked.
     */
    public void unmark() {
        this.isMarked = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Returns the string that contains the description.
     */
    public String getDescription() {
        return (this.description);
    }

    /**
     * Returns a string that contains the information of the description of the task and also whether it is marked.
     * It is properly formatted so that the user will be able to understand it.
     *
     * @return Returns a formatted string of the task that you wish to display to the user.
     */
    public String toString() {
        return (START_BRACKET + this.getStatusIcon() + END_BRACKET + this.getDescription());
    }

    /**
     * Returns a string that is formatted specifically to save it into a local text file.
     *
     * @return Returns a formatted string of the task that you wish to save.
     */
    public String getSaveCommand() {
        return this.saveCommand;
    }
}