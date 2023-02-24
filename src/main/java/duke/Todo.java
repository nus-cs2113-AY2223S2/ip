package duke;

import java.io.IOException;

public class Todo extends Task {

    /**
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
    @Override
    public String toString() {
        return "[T]"+ super.toString();
    }
}

