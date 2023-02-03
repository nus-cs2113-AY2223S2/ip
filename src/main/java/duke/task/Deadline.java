package duke.task;
public class Deadline extends Task {
    protected String byDate;

    public Deadline(String task, int number, boolean isDone, String byDate) {
        super(task, number, isDone);
        this.type = "D";
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + byDate + ")";
    }
}
