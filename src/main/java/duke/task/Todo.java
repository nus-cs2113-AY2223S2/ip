package duke.task;

import duke.DukeException;

public class Todo extends Task{
    public Todo(String description) throws DukeException {
        super(description);
    }
    public String getStatus() {
        return ("T [" + (isDone? "u\2718" : " ") + "]");
    }
    @Override
    public String toString() {
        return this.getStatus() + " " + description;
    }
}
