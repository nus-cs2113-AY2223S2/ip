public class Deadline extends Task {

    protected String by;

    /**
     * constructs a Deadline item
     *
     * @param description String containing description of the deadline added
     * @param by          String containing due date and/or time of deadline added
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * formats a String to contains the task code, status, description and due date and/or time
     *
     * @return String containing task code, status, description and due date and/or time
     */
    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + by + ")");
    }

    /**
     * formats a String to be saved to the file
     *
     * @return String containing task code, status, description and due date and/or time
     */
    @Override
    public String toSerialisedString() {
        String isDoneStr = isDone ? "1" : "0";
        return ("D|" + isDoneStr + "|" + description + "|" + by);
    }
}
