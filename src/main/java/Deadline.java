/**
 * A Deadline object is a Task object that has a due date.
 */
public class Deadline extends Task{
    private String by;

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
        setDescription(getDescription() + '(' + by + ')');
    }

    /** Upon creating the Deadline, the by date will be concatenated into the displayed description.
     * @param description The description of this task.
     * @param by The date this task has to be done.
     */
    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }
}
