package duke.model;

import duke.exception.InvalidCommandException;

/**
 * A class to store the ToDo entity
 */
public class ToDo extends Task {
    /**
     * ToDo Constructor that takes an array of String to describe the entity
     *
     * @param descriptionArray The array of String of information
     * @throws InvalidCommandException When it is not given sufficient information
     */
    public ToDo(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 1) {
            throw new InvalidCommandException("Incomplete description of ToDo!");
        }
        this.taskName = descriptionArray[0];
        this.isDone = false;
    }

    /**
     * Method to represent ToDo as a string
     *
     * @return String representation of ToDo
     */
    @Override
    public String toString() {
        String todoPrefix = "[T]";
        String taskString = super.toString();
        return todoPrefix + taskString;
    }
}
