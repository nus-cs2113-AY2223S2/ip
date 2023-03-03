package tasks;

import dukeException.DukeException;

public class Todo extends Task {
    public Todo(String description, boolean isMark)  throws DukeException {
        super(description, isMark);
        
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}