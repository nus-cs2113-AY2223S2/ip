package duke.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Printing the Task that includes whether it is done
     *
     * @return Returns a string that describes the object
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

    @Override
    public String saveToFile() {
        return "D | " + this.getStatus() + " | " + description + " | " + by + "\n";
    }
}
