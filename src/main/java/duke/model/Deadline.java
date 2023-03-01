package duke.model;

import duke.exception.InvalidCommandException;

/**
 * A class to store the Deadline entity
 */
public class Deadline extends Task {
    /**
     * Represents the time when the deadline is due
     */
    protected String deadlineBy;

    /**
     * Constructor that initializes the deadline's deadlineBy and the deadline name
     *
     * @param descriptionArray
     * @throws InvalidCommandException
     */
    public Deadline(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 2) {
            throw new InvalidCommandException("Incomplete deadline description!");
        }
        this.taskName = descriptionArray[0];
        this.deadlineBy = descriptionArray[1];
    }

    /**
     * Method to represent Deadline as a string
     *
     * @return String representation of Deadline
     */
    @Override
    public String toString() {
        String deadlinePrefix = "[D]";
        String taskString = super.toString();
        String deadlinePostfix = " (" + "by: " + this.deadlineBy + ")";
        return deadlinePrefix + taskString + deadlinePostfix;
    }
}
