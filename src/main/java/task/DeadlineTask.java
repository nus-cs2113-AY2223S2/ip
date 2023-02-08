package task;

import exceptions.InvalidSyntaxException;
import ui.Command;

public class DeadlineTask extends Task {

    // For serialization
    private static final long serialVersionUID = (1 << 5) + 1;

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
            throw new InvalidSyntaxException(Command.DEADLINE.expectedSyntax);
        }
    }

    @Override
    public String toString() {
        return "[D][" + (isDone ? "x" : " ") + "] " + description + " (by: " + deadline + ")";
    }
}
