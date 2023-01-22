package wilsonoh.sagyo.tasks;

public class DeadlineTask extends Task {

    private String by;

    public DeadlineTask(String taskName, String by) {
        super(taskName);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }
}
