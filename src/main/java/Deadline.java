public class Deadline extends Task{
    protected String by;

    public Deadline(String name, boolean isCompleted, String by) {
        super(name, isCompleted);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
