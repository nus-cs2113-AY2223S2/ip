package duke.model;

import duke.exception.InvalidCommandException;

public class Deadline extends Task {
    protected String deadlineBy;

    public static String[] parseCommand (String command) throws InvalidCommandException {
        String[] descriptionArray = new String[2];
        String[] commandDescriptionArray = command.split("deadline");
        if (commandDescriptionArray.length < 2 || commandDescriptionArray[1].split("/").length < 2){
            throw new InvalidCommandException("Incomplete deadline description");
        }

        for (int i = 0; i < 2; i++) {
            descriptionArray[i] = commandDescriptionArray[1].split("/")[i].trim();
        }
        return descriptionArray;
    }
    public Deadline(String[] descriptionArray) {
        super(descriptionArray);
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
