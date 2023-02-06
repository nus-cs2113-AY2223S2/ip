package duke.task;

public class Deadline extends Task {
    public static final String DELIMITER_BY = "/by";
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return " [D]" + super.toString() + " (by: " + by + ")";
    }
}
