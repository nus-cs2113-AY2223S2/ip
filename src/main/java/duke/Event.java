package duke;

/**
 * Represents a <code>Event</code> task that can be added to the list. An <code>Event</code> object stores a
 * description of the task and its duration.
 */

public class Event extends Todo {
    protected String by;
    protected String end;
    public Event(String description, String by, String end) {
        super(description);
        this.by = by;
        this.end = end;
    }
    @Override
    public String getBy() {
        return by;
    }
    @Override
    public String getEnd() {
        return end;
    }

    public void setBy(String time) {
        by = time;
    }

    public void setEnd(String time) {
        end = time;
    }

    public void print() {
        System.out.println("    _________________________________________");
        System.out.println("    " + "added: " + description + " (from: " + by + ", to: " + end + ")");
        System.out.println("    _________________________________________");
        System.out.println("    ");
    }

    public void printInList() {
        if (isDone) {
            System.out.println(" " + "[E][X] " + description + " (from: " + by + ", to: " + end + ")");
        } else {
            System.out.println(" " + "[E][ ] " + description + " (from: " + by + ", to: " + end + ")");
        }
    }
}
