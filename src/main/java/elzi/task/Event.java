package elzi.task;

import elzi.ElziException;

/**
 * @author : Steven A. O. Waskito
 * Class Event that inherits Task and has attributes
 * Description, isDone, From, To
 **/
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for event object with attribute: from, to
     * @param description event description
     * @param from event from attribute
     * @param to event to attribute
     * @throws ElziException if from or to is blank
     */
    public Event(String description, String from, String to) throws ElziException {
        super(description);
        if (from.isBlank() || to.isBlank()) {
            throw new ElziException("Event needs to have both /from and /to flags");
        }
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        return ("[E][" + (isDone ? "X" : " ") + "]");
    }
    public String getFrom() {
        return ("from: " + from);
    }
    public String getTo() {
        return ("to: " + to);
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description + " (" + getFrom() + " " + getTo() + ")";
    }
    /**
     * Method encoding the given task to save
     */
    @Override
    public String encode() {
        return ("E_" + (isDone ? "1_" : "0_")
                + description + "_"
                + from + "_"
                + to);
    }
    @Override
    public String getType() {
        return "E";
    }
}
