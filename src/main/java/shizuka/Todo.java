package shizuka;

public class Todo {
    public static final String ITEM_SEPARATOR = " / ";
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "T";
    }

    public Todo(String[] args) {
        this.description = args[2];
        this.isDone = args[1].equals("1");
        this.taskType = "T";
    }

    public String getStatusIcon() {
        return '[' + (isDone ? "X" : " ") + ']';
    }

    public String getTaskIcon() {
        return '[' + taskType + ']';
    }

    /**
     * Reformats the task to a string to be printed.
     *
     * @return The string to be printed.
     */
    public String printTask() {
        return this.getTaskIcon() + getStatusIcon() + ' ' + this.description;
    }

    /**
     * Reformats the task to a string to be saved in a file.
     *
     * @return The string to be saved in a file.
     */
    public String saveTask() {
        return this.taskType + ITEM_SEPARATOR + (this.isDone ? "1" : "0") + ITEM_SEPARATOR
                + this.description + System.lineSeparator();
    }
}
