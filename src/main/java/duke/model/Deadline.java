package duke.model;

import duke.exception.InvalidCommandException;

public class Deadline extends Task {
    protected String deadlineBy;

    public Deadline(String[] descriptionArray) throws InvalidCommandException {
        if (descriptionArray.length < 2) {
            throw new InvalidCommandException("Incomplete deadline description!");
        }
        this.taskName = descriptionArray[0];
        this.deadlineBy = descriptionArray[1];
    }

    @Override
    public String toString() {
        String deadlinePrefix = "[D]";
        String taskString = super.toString();
        String deadlinePostfix = " (" + "by: " + this.deadlineBy + ")";
        return deadlinePrefix + taskString + deadlinePostfix;
    }
}
