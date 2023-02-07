package task;

import exceptions.InvalidSyntaxException;
import ui.Command.Syntax;

public class EventTask extends Task {


    protected String start;
    protected String end;

    public EventTask(String description, String start, String end) {
        super(description);

        this.start = start;
        this.end = end;
    }

    public static EventTask createFromInput(String[] splitInput) throws InvalidSyntaxException {
        try {
            String[] params = splitInput[1].split("(/from|/to)");
            return new EventTask(params[0].trim(), params[1].trim(), params[2].trim());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidSyntaxException(Syntax.EVENT);
        }
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "x" : " ") + "] " + description + " (from: " + start + " to: " + end + ")";
    }
}
