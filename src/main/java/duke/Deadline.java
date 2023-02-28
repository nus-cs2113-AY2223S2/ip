package duke;

/**
 *
 * Represents an extension of a task with a deadline.
 * It is a task including the deadline of the task.
 *
 */

public class Deadline extends Task {

    protected String by;
    protected int descExistence;
    protected int dateExistence;

    /**
     * Creates a deadline task.
     *
     * @param description is the task name
     * @param by is the date the task is due by
     * @param descExistence is the number of arguments for the task description
     * @param dateExistence is the number of arguments for the date of the event
     * @throws DukeException Its thrown when the user input is empty
     */

    public Deadline(String description, String by, int descExistence, int dateExistence)
            throws DukeException {

        super(description);
        this.by = by;
        this.descExistence=descExistence;
        this.dateExistence=dateExistence;

        if (descExistence<2 || dateExistence<2 ) {
            throw new DukeException();
        }
    }

    /**
     * To display the task in a specific way.
     *
     * @return the format of how the task is shown
     *
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

