public class Deadline extends Task{
    protected String dueDate;

    public Deadline(String description, String dueBy) {
        super(description);
        this.dueDate = dueBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (due by: " + dueDate + ")";
    }
}
