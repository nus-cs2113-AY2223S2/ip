package duke;

/**
 * Represents an extension of a task with an event.
 * It is a task including the beginning and end timeline of the task.
 */

public class Event extends Task {

    protected String from;
    protected String to;
    protected int descExistence;
    protected int fromDateExistence;
    protected int toDateExistence;

    /**
     * Creates an Event task.
     *
     * @param description is the task name
     * @param from is the beginning of the event task
     * @param to is the end of the event task
     * @param descExistence is the number of arguments for the task description
     * @param fromDateExistence is the number of arguments for the beginning date of the event
     * @param toDateExistence is the number of arguments for the end date of the event
     * @throws DukeException Its thrown when the user input is empty
     *
     */

    public Event(String description, String from, String to,int descExistence,
                 int fromDateExistence, int toDateExistence) throws DukeException {

        super(description);
        this.from = from;
        this.to=to;
        this.descExistence=descExistence;
        this.fromDateExistence=fromDateExistence;
        this.toDateExistence=toDateExistence;

        if (descExistence<2 || fromDateExistence<2 || toDateExistence<2  ) {
            throw new DukeException();
        }
    }

    /**
     * To display the task in a specific way.
     *
     * @return the format of how the task is shown
     */
    @Override
    public String toString() {
        return "[E]"+ super.toString()+ " (from: " + from +" to: " + to + ")";
    }
}

