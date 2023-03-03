package tasks;

import utility.Ui;

/**
 * Represents the class ToDo, which inherits from the parent class Task.
 * When created, it is stored in the arraylist in DukeSession.
 * It is a task, which only contains a description.
 * E.g. todo Say Goodnight To Duke
 */
public class Todo extends Task {
    private static final String DEFAULT_TODO_SYMBOL = "[T]";
    private static final String DEFAULT_TODO_SAVE_SYMBOL = "t/";

    /**
     * Initialises an object of the Class Todo.
     *
     * @param description Contains the description of the task that the user wants to do.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the formatted string that you wish to display to the user.
     *
     * @return Formatted string of a Todo task.
     */
    @Override
    public String toString() {
        return DEFAULT_TODO_SYMBOL + super.toString();
    }

    /**
     * Returns the formatted string that you wish to save to the local text file.
     *
     * @return The formatted string of a Todo task that you wish to save.
     */
    public String getSaveCommand() {
        return DEFAULT_TODO_SAVE_SYMBOL + this.getStatusIcon() + Ui.DEFAULT_FLAG_SEPARATOR + this.getDescription();
    }
}


