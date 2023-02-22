package duke;

import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;

public class Parser {

    public static String[] command(String userCommand) {
        final String[] split = userCommand.trim().split("\\s+", 2);
        return (split.length == 2) ? split : new String[]{split[0], ""};
    }

    public static String[] deadline(String param) throws InvalidDeadline {
        String[] split = param.trim().split("\\s/by\\s", 2);
        if (split.length != 2) {
            throw new InvalidDeadline();
        }
        return split;
    }

    public static String[] event(String param) throws InvalidEvent {
        String[] split = param.trim().split("\\s/from\\s|\\s/to\\s", 3);
        if (split.length != 3) {
            throw new InvalidEvent();
        }
        return split;
    }
}
