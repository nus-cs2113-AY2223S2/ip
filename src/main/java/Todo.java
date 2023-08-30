/**
 * Represents a todo that the user has inputted. A <code>Todo</code> object contains a
 * basic <code>description</code> and <code>isDone</code> to indicate completion.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
        initialize();
    }
    /**
     * Returns the contents of task in an appropriate string format for printing.
     * @return Formatted string output.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Handles prior stored data in a given text file upon initialization of Duke.
     */
    public void initialize() {
        TaskArray.count++;
        System.out.println(UI.LINE +
                "     Got it. I've added this task:\n" + toString() +
                "\n     Now you have " + TaskArray.count + " tasks in the list.\n" +
                UI.LINE);
    }

    /**
     * Returns the contents of task in an appropriate string format for adding to textfile.
     * @return Formatted string output.
     */
    public String toFile() {
        return this.isDone + " | " +"t"+ this.description + "\n";
    }
}