public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * constructs an Event item
     *
     * @param description String containing description of event added
     * @param start       String containing start date and/or time of event added
     * @param end         String containing end date and/or time of event added
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * formats a String to contains the task code, status, description, start and end date and/or time
     *
     * @return String containing task code, status, description, start and end date and/or time
     */
    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (from: " + start + " to: " + end + ")");
    }

    /**
     * formats a String to be saved to the file
     *
     * @return String containing task code, status, description, start and end date and/or time
     */
    @Override
    public String toSerialisedString() {
        String isDoneStr = isDone ? "1" : "0";
        return ("E|" + isDoneStr + "|" + description + "|" + start + "|" + end);
    }
}
