package duke.tasks;

/**
 * This class inherits from Task class.
 * Deadlines contain a description as well as a due date.
 */
public class Deadline extends Task {

    private String dueInfo;

    /**
     * Creates a deadline object.
     * @param description
     * @param dueInfo
     */
    public Deadline(String description, String dueInfo) {
        super(description, "D");
        setDueInfo(dueInfo);
    }

    public String getDueInfo() {
        return dueInfo;
    }

    public void setDueInfo(String dueInfo) {
        this.dueInfo = dueInfo;
    }

}
