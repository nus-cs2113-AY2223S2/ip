package Tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    @Override
    public String getDescription() {
        return "[D]" + getStatusIcon() + super.getDescription() + " (by: " + by + ")";
    }

    @Override
    public String formatTask() {
        String saveString = "D" + "|" + super.formatTask() + "|" + by;
        return saveString;
    }
}
