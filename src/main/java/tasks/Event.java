package tasks;

/**
 * The Event class that represents the event task in the tasklist. Has the from and to arguments
 * to simulates the duration of the event.
 */

public class Event extends Task {
    private String fromWhen;
    private String toWhen;

    /**
     * Creates an event task.
     * 
     * @param name the name of the event
     * 
     * @param from the start time of the event as a string
     * 
     * @param to the end time of the event as a string
     */

    public Event(String name, String from, String to) {
        super(name, "E");
        this.fromWhen = from;
        this.toWhen = to;
    }

    public String getFromWhen() {
        return this.fromWhen;
    }

    public String getToWhen() {
        return this.toWhen;
    }

    public String toString() {
        return super.toString() + " (from: " + fromWhen + ")" + " (to: " + toWhen + ')';
    }

    public String toSaveString() {
        return super.toSaveString() + '|' + fromWhen + '|' + toWhen;
    }
}
