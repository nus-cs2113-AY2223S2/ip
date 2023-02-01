public class Deadline extends Task {
    private String by;

    public Deadline(String description, char type, String by) {
        super(description, type);
        this.by = by;
    }

    @Override
    public String getListDescription() {
        return "[" + super.getType() + "]" + "[" + getStatusIcon() + "] " + super.getDescription()
                + " (by: " + by + ")";
    }
}
