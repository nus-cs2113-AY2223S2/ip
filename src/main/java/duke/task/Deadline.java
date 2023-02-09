package duke.task;
public class Deadline extends Task {
    protected String byDate;

    public Deadline(String task, boolean isDone, String byDate) {
        super(task, isDone);
        this.type = "D";
        this.byDate = byDate;
    }

    @Override
    public String getTask() {
        return super.getTask() + " | " + byDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + byDate + ")";
    }
}
