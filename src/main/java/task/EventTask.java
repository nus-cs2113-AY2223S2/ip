package task;

import exceptions.InvalidSyntaxException;
import ui.Syntax;

/**
 * Represents an event
 */
public class EventTask extends Task {

    // For serialization
    private static final long serialVersionUID = (1 << 5) + 2;

    public static final String START_DELIMITER = "/from";
    public static final String END_DELIMITER = "/to";
    private static final String EVENT_REGEX = "(" + START_DELIMITER + "|" + END_DELIMITER + ")";

    protected String start;
    protected String end;

    public EventTask(String description, String start, String end) {
        super(description);

        this.start = start;
        this.end = end;
    }

    /**
     * Creates a {@link EventTask} from user input
     *
     * @param splitInput Tokenized user input
     * @return Parsed EventTask
     * @throws InvalidSyntaxException If user input does not match expected syntax
     */
    public static EventTask createFromInput(String[] splitInput) throws InvalidSyntaxException {
        try {
            String[] params = splitInput[1].split(EVENT_REGEX);
            return new EventTask(params[0].trim(), params[1].trim(), params[2].trim());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidSyntaxException(Syntax.EVENT.expectedSyntax);
        }
    }

    @Override
    public String toString() {
        return "[E][" + (isDone ? "x" : " ") + "] " + description + " (from: " + start + " to: " + end + ")";
    }
}
