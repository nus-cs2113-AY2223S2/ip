package duke.tasks;

/**
 * Represents tasks that are classified as a <code>Todo</code>
 * These are tasks that does not have a start or end date
 */
public class Todo extends Task {
    protected String type = "todo";

    /**
     * Creates an object of type <code>Todo</code>
     *
     * @param description name of task
     */
    public Todo (String description) {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.println("[T][" + getStatusIcon() + "] " + description);
    }
}
