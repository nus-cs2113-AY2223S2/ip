package duke.model;

import duke.exception.InvalidCommandException;

public class ToDo extends Task {
    public ToDo(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 1) {
            throw new InvalidCommandException("Incomplete description of ToDo!");
        }
        this.taskName = descriptionArray[0];
        this.isDone = false;
    }

    @Override
    public String toString() {
        String todoPrefix = "[T]";
        String taskString = super.toString();
        return todoPrefix + taskString;
    }
}
