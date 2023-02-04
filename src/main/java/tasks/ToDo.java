package tasks;

import exceptions.ArchdukeException;

public class ToDo extends Task {
    public ToDo(String description) throws ArchdukeException {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("T %s", super.toString());
    }
}
