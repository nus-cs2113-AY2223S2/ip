package shizuka;

public class Deadline extends Todo {
    public static final String ITEM_SEPARATOR = " / ";
    private final String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.taskType = "D";
        this.deadline = deadline;
    }

    public Deadline(String[] args) {
        super(args[2]);
        this.isDone = args[1].equals("1");
        this.taskType = "D";
        this.deadline = args[3];
    }

    public String printTask() {
        return this.getTaskIcon() + this.getStatusIcon() + ' ' + this.description + " (by: " + deadline + ")";
    }

    public String saveTask() {
        return this.taskType + ITEM_SEPARATOR + (this.isDone ? "1" : "0") + ITEM_SEPARATOR + this.description
                + ITEM_SEPARATOR + this.deadline + System.lineSeparator();
    }
}
