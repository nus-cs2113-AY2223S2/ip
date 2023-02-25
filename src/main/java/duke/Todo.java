package duke;

/**
 * Represents an extension of a task to do.
 */

public class Todo extends Task {

    /**
     * Creates a task to do.
     * @param description is the task name
     * @param length is the number of arguments in user's input
     * @throws DukeException Its thrown when the user input is empty
     */

    protected int length;

    public Todo(String description, int length) throws DukeException {

        super(description);
        this.length=length;

        if(length<2) {
            throw new DukeException();
        }
    }

    /**
     * To display the task in a specific way
     * @return the format of how the task is shown
     */

    @Override
    public String toString() {
        return "[T]"+ super.toString();
    }
}

