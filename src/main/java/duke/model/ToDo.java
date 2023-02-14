package duke.model;

import duke.exception.InvalidCommandException;

import java.util.Arrays;

public class ToDo extends Task {
    public ToDo(String[] descriptionArray) throws InvalidCommandException {
        super(descriptionArray);
    }

    @Override
    public String toString() {
        String todoPrefix = "[T]";
        String taskString = super.toString();
        return todoPrefix + taskString;
    }
}
