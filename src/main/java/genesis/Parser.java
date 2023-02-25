package genesis;

import exception.InvalidArgumentException;

/**
 * CommandFormatter class contains utility methods for formatting user commands related to deadlines and events.
 */
public class Parser {
    public static String[] formatDeadline(String content) throws InvalidArgumentException {
        String[] parts = content.split(" /by ", 2);
        if (parts.length < 2) {
            throw new InvalidArgumentException("Description for deadline is invalid");
        }

        return parts;
    }

    public static String[] formatEvent(String content) throws InvalidArgumentException {
        String[] parts = content.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new InvalidArgumentException("Description for event is invalid");
        }

        return parts;
    }

    public static int extractIndex(String content) {
        return Integer.parseInt(content) - 1;
    }
}