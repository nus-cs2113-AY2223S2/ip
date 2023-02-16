package task;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String getDeadline() {
        String[] dates = deadline.split("\\s+", 2);
        return "(" + dates[0] + ":" + dates[1] + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + this.getDeadline();
    }
}
