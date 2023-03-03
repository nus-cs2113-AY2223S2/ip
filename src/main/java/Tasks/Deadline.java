package Tasks;

import Tasks.Task;

public class Deadline extends Task {

    protected String by;
    protected String symbol = "[D]";

    public String getDescription() {
        return super.getDescription();
    }

    public String getSymbol() {
        return symbol;
    }

    /**
     * Constructs a deadline-type task
     * @param description the activity that needs be done by the user
     * @param by the deadline by which the user needs to finish said activity
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.description = description + "(By:" + by + ")";
    }

    /**
     * Returns a string representation of the deadline-type task to be printed for the user
     * @return the formatted string output
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() ;
    }

    /**
     * Returns a string representation of the deadline-type task to be stored in the text file
     * @return the formatted string output
     */
    @Override
    public String toFile() {
        return this.getStatusIcon() + " : " +"D"+ " : " + this.description;
    }
}
