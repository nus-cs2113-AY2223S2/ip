package Tasks;
public class Deadline extends Task{
    protected String by;

    /**
     * Constructor for deadline
     *
     * @param description string of description of deadline
     * @param by string of the due date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Getter for deadline due date
     *
     * @return string of due date of deadline
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Getter for deadline type
     *
     * @return string of deadline
     */
    @Override
    public String getType() {
        return "deadline";
    }

    /**
     * Getter for full description of a deadline
     *
     * @return string with deadline status, description and due date
     */
    @Override
    public String fullDescription() {
        String fullSentence = (isDone ? "[D][X] " : "[D][ ] ") + this.description +
                " (by: " + this.by + ")";
        return fullSentence;
    }
}
