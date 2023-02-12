package chronos.tasktype;
//simple inheritance, that's all
public class Deadline extends Task {
    private String due;

    public Deadline(String description, String due) {
        super(description);
        if (due == null) {
            throw new IllegalArgumentException("Please provide a due date.");
        }
        this.due = due;
    }
    public Deadline(String description, String due, boolean isDone) {
        super(isDone, description);
        if (due == null) {
            throw new IllegalArgumentException("Please provide a due date.");
        }
        this.due = due;
    }

    //second constructor

    public String getDue() {
        return due;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (DUE: %s)", super.toString(), getDue());
    }
}
