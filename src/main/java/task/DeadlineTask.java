package task;

import exceptions.InvalidSyntaxException;

public class DeadlineTask extends Task {

    private static final String SYNTAX = "deadline <description> /by <deadline>";

    protected String deadline;

    public DeadlineTask(String description, String deadline) {
        super(description);

        this.deadline = deadline;
    }

    public static DeadlineTask createFromInput(String[] splitInput) throws InvalidSyntaxException {
        try {
            String[] params = splitInput[1].split("/by");
            return new DeadlineTask(params[0].trim(), params[1].trim());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidSyntaxException(SYNTAX);
        }
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "x" : " ") + "] " + description + " (by: " + deadline + ")";
    }
}
