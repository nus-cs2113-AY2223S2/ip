package duke;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String storeString() {
        return super.storeString() + "|D|" + description + "|" + by + "\n";
    }

    @Override
    /**
     * Overrides string representation for deadlines, with a [D] to
     * indicate deadline class and including by field.
     *
     * @return String Representation.
     */
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
