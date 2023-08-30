/**
 * Represents a task that the user has inputted. A <code>Event</code> object contains a
 * basic <code>description</code> and <code>isDone</code> to indicate completion.
 * It also contains a <code>from</code> variable to store the start date of its given task,
 * as well as a <code>to</code> variable to store the end date of its given task.
 */
public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        initialize();
    }
    /**
     * Returns the contents of task in an appropriate string format for printing.
     * @return Formatted string output.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    /**
     * Handles prior stored data in a given text file upon initialization of Duke.
     */
    public void initialize() {
        TaskArray.count++;
        System.out.println(UI.LINE +
                "     Got it. I've added this task:\n" +
                "        " + this +
                "\n     Now you have " + TaskArray.count + " tasks in the list.\n" +
                UI.LINE);
    }
    /**
     * Returns the contents of task in an appropriate string format for adding to text file.
     * @return Formatted string output.
     */
    public String toFile() {
        return this.isDone + " | " +"e"+ this.description + " from " + this.from + " to " + this.to + "\n";
    }
}
