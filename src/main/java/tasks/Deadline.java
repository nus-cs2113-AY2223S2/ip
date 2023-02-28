package tasks;

/**
 * The Deadline class that represent a deadline task in the tasklist. Has the bywhen argument
 * to simulate an actual deadline.
 */

public class Deadline extends Task {
    private String byWhen;

    /**
     * Creates a deadline task.
     * 
     * @param name the name of the deadline
     * 
     * @param by the deadline of the deadline task
     */

    public Deadline(String name, String by) {
        super(name, "D");
        this.byWhen = by;
    }

    //Erm I get a feeling that getters and setters are trivial, so yea, no Javadocs

    public String getByWhen() {
        return byWhen;
    }

    public String toString() {
        return super.toString() + " (by: " + byWhen + ')';
    }

    public String toSaveString() {
        return super.toSaveString() + '|' + byWhen;
    }
}
