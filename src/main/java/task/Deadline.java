package task;

public class Deadline extends Task {
    private String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String formatDeadline() {
        String[] dates = deadline.split("by", 2);
        return "(by: " + this.deadline + ")";
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + formatDeadline();
    }
}
