public class Deadlines extends Task {

    protected String by;
    public Deadlines (String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeOfTask() {
        return "D";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + this.by + ")";
    }
}
