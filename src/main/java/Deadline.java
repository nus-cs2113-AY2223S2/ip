public class Deadline extends Task {
    protected String by;

    public Deadline(String description, int taskID, String by) {
        super(description, taskID);
        this.by = by;
        Task.taskCount += 1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}