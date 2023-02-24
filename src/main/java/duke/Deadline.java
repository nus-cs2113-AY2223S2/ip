package duke;
/**
 * This object represents a task object of Deadline type.
 * Deadline type contains all the attributes and methods from template Task and also contains when this task should be completed by
 */
public class Deadline extends Task{
    protected String by;

    /**
     * Initialises a Deadline object that contains the description and when the task should be completed by
     * @param description The description of the task
     * @param by The date/time that this task should be completed by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overrides the template Task toString with an additional [D] string which symbolizes that it is a Deadline task
     * @return Returns the symbol [D], the status icon and the description and when to complete by
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Overrides the template Task getSymbol with "[D]" string which symbolizes that it is a Deadline task
     * @return Returns the character "D" symbolizes that it is a Deadline task
     */
    @Override
    public String getSymbol() {
        return "D";
    }

}
