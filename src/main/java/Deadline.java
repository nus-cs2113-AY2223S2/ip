/**
 * Deadline is a subclass of Task that represents a task with a specific deadline.
 */
public class Deadline extends Task {
    /**
     * The deadline of the task.
     */
    private String by;

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    Deadline(String description, String by) {
        super(description);
        setBy(by);
        System.out.println("Added!");
        System.out.println(String.format(" [%s] [%s] %s (%s)", 'D', " ", description, by));
    }

    /**
     * Sets the deadline of the task.
     *
     * @param by The deadline of the task.
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns the type icon of the task.
     *
     * @return The type icon of the task ('D' for Deadline).
     */
    @Override
    public char getTypeIcon() {
        return 'D';
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
