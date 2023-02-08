public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws ArgumentBlankException {
        super(description);
        if (by.isBlank()) {
            throw new ArgumentBlankException("by");
        }
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}