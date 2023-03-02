package tasks;

import utility.Ui;

/**
 * Represents the class Deadline, which inherits from the parent class Task.
 * When created, it is stored in the arraylist in DukeSession.
 * It is a task, which contains a description and 1 date field, /by.
 * E.g. deadline Submit this Assignment /by Mar 3rd 2359
 */
public class Deadline extends Task {
    private static final String DEFAULT_DEADLINE_SYMBOL = "[D]";
    private static final String DEFAULT_BY_FORMATTER = "(by: ";
    private static final String END_BRACKET = ")";
    private static final String DEFAULT_DEADLINE_SAVE_SYMBOL = "d/";

    private String by;

    /**
     * Initialises an object of the Class Deadline.
     *
     * @param description Contains the description of the deadline that the user wants to do.
     * @param by Contains the time or date of the due date of the deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string that contains the information of the description of the deadline task.
     * It also contains information about whether it is marked, and the due date.
     * It is properly formatted so that the user will be able to understand it.
     *
     * @return Returns a formatted string of the deadline task that you wish to display to the user.
     */
    @Override
    public String toString() {
        return DEFAULT_DEADLINE_SYMBOL + super.toString() + DEFAULT_BY_FORMATTER + by + END_BRACKET;
    }

    /**
     * Returns a string that is formatted specifically to save it into a local text file.
     *
     * @return Returns a formatted string of the deadline task that you wish to save.
     */
    public String getSaveCommand() {
        return DEFAULT_DEADLINE_SAVE_SYMBOL + this.getStatusIcon()
                + Ui.DEFAULT_FLAG_SEPARATOR + this.getDescription()
                + Ui.DEFAULT_FLAG_SEPARATOR + this.by;
    }
}