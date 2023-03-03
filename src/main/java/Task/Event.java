package Task;

public class Event extends Task {
    // Attributes of Event instances
    protected String start;
    protected String end;

    /**
     * Constructor
     *
     * @param description event description
     * @param start       start of event
     * @param end         end of event
     * 
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Constructor
     *
     * @param description event description
     * @param start       start of event
     * @param end         end of event
     * @param isDone      whether event is done or not
     * 
     */
    public Event(String description, String start, String end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * toString method
     *
     * @return string description
     * 
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + start + " " + end + ")";
    }

    /**
     * toString method when saving in file
     *
     * @return string description to be saved in file
     * 
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? 1 : 0) + " | " + description + " | "
                + start + " | " + end;
    }

    /**
     * toString method
     *
     * @param line input string from file
     * @return deadline item
     * 
     */
    public static Event fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];
        String start = parts[3];
        String end = parts[4];
        return new Event(description, start, end, isDone);
    }
}
