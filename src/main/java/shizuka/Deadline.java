package shizuka;

public class Deadline extends Todo {
    public static final String ITEM_SEPARATOR = " | ";
    private final String deadline;

    public Deadline(String[] args) {
        super(args[0]);
        this.taskType = "D";
        this.deadline = args[1];
    }

    public String printTask() {
        return this.getTaskIcon() + this.getStatusIcon() + ' ' + this.description + " (by: " + deadline + ")";
    }

    public String saveTask() {
        return this.taskType + ITEM_SEPARATOR + (this.isDone ? "1" : "0") + ITEM_SEPARATOR + this.description
                + ITEM_SEPARATOR + this.deadline;
    }
}
