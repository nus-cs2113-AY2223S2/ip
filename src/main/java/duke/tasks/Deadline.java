package duke.tasks;

public class Deadline extends Task {
    public static final String MARKER = "D";
    private final String by;

    public Deadline(String description, String by) {
        super(description, TaskEnum.DEADLINE);
        this.by = by;
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER)
                + super.describe()
                + " (by: " + by + ")";
    }
}
