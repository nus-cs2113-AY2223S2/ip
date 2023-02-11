public class Deadline extends Task {

    protected String by;
    public Deadline(String description, String by) {
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

    @Override

    public String getDetailsToSave() {
        return super.description + " /by " + this.by;
    }

    public String toString() {
        return "       [D][ ] " + super.description + " (by: " + this.by + ")";
    }
}
