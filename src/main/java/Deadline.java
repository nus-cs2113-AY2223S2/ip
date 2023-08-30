/**
 * Represents a task that the user has inputted. A <code>Deadline</code> object contains a
 * basic <code>description</code> and <code>isDone</code> to indicate completion.
 * It also contains a <code>by</code> variable to store the due date of its given task.
 */
public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        initialize();
    }
    /**
     * Returns the contents of task in an appropriate string format for printing.
     * @return Formatted string output.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Handles prior stored data in a given text file upon initialization of Duke.
     */
    public void initialize() {
        TaskArray.count++;
        System.out.println(UI.LINE +
                "     Got it. I've added this task:\n" + this +
                "\n     Now you have " + TaskArray.count + " tasks in the list.\n" +
                UI.LINE);
    }
    /**
     * Returns the contents of task in an appropriate string format for adding to text file.
     * @return Formatted string output.
     */
    public String toFile() {
        return this.isDone + " | " +"d"+ this.description + " by " + this.by + "\n";
    }
}
