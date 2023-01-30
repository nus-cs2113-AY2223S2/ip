public class Deadline extends Task {
    public static final String MARKER = "D";
    private final String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String describe() {
        return getCheckbox(true, MARKER) + super.describe()
                + " (by: " + by + ")";
    }
}
