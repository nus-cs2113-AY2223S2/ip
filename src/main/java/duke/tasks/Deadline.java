package duke.tasks;

/**
 * Represents tasks that are classified as a <code>Deadline</code>
 */
public class Deadline extends Task {
    protected String type = "deadline";
    protected String end;

    /**
     * Creates a new object of type <code>Deadline</code>
     *
     * @param description name of task
     * @param end the date at which task has to be completed by
     */
    public Deadline(String description, String end)
    {
        super(description);
        this.end = end;
    }

    @Override
    public void printTask() {
        System.out.println("[D][" + getStatusIcon() + "] " + description + "(by:" + end + ")");
    }
}
