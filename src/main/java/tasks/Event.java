package tasks;

import utility.Ui;

/**
 * Represents the class Event, which inherits from the parent class Task.
 * When created, it is stored in the arraylist in DukeSession.
 * It is a task, which contains a description and 2 date fields, /from and /to.
 * E.g. event attend CS2113T tutorial /from thursday 9am /to 10am.
 */
public class Event extends Task {
    private static final String DEFAULT_EVENT_SYMBOL = "[E]";
    private static final String DEFAULT_FROM_FORMATTER = "from: ";
    private static final String DEFAULT_TO_FORMATTER = "to: ";
    private static final String DEFAULT_EVENT_SAVE_SYMBOL = "e/";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";

    private String from;

    private String to;

    /**
     * Initialises an object of the Class Event.
     *
     * @param description Contains the description of the event that the user wants to do.
     * @param from Contains the starting date or time of the event.
     * @param to Contains the ending date or time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string that contains the information of the description of the event
     * It also contains information about whether it is marked and the start and end of the event.
     * It is properly formatted so that the user will be able to understand it.
     *
     * @return Returns a formatted string of the event that you wish to display to the user.
     */
    @Override
    public String toString() {
        return DEFAULT_EVENT_SYMBOL + super.toString() + OPEN_BRACKET + DEFAULT_FROM_FORMATTER + from
                + DEFAULT_TO_FORMATTER + to + CLOSE_BRACKET;
    }

    /**
     * Returns a string that is formatted specifically to save the event into a local text file.
     *
     * @return Returns a formatted string of the event that you wish to save.
     */
    public String getSaveCommand() {
        return DEFAULT_EVENT_SAVE_SYMBOL + this.getStatusIcon() + Ui.DEFAULT_FLAG_SEPARATOR + this.getDescription()
                + Ui.DEFAULT_FLAG_SEPARATOR + this.from
                + Ui.DEFAULT_FLAG_SEPARATOR + this.to;
    }
}
