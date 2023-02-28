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

    /**
     * Reformats the task to a string to be printed.
     *
     * @return The string to be printed.
     */
    public String printTask() {
        return this.getTaskIcon() + this.getStatusIcon() + ' ' + this.description + " (by: " + deadline + ")";
    }

    /**
     * Reformats the task to a string to be saved in a file.
     *
     * @return The string to be saved in a file.
     */
    public String saveTask() {
        return this.taskType + ITEM_SEPARATOR + (this.isDone ? "1" : "0") + ITEM_SEPARATOR + this.description
                + ITEM_SEPARATOR + this.deadline + System.lineSeparator();
    }
}
