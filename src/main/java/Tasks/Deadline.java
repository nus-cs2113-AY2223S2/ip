package Tasks;

public class Deadline extends Task {
    
    protected String dueDate;

    public Deadline(String description, String by) {
        super(description);
        this.dueDate = by;
    }

    @Override
    public String describeTask() {
        return "[D]" + super.describeTask() + " (by: " + dueDate + ")";
    }

    @Override
    public String describeTaskForFile() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + dueDate;
    }
}
