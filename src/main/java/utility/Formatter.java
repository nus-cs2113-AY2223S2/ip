package utility;

import exception.GenesisException;

/**
 * CommandFormatter class contains utility methods for formatting user commands related to deadlines and events.
 */
public class Formatter {
    public static String[] deadlineFromatter(String content) throws GenesisException {
        String[] parts = content.split(" /by ", 2);
        if (parts.length < 2) {
            throw new GenesisException("Description for deadline is invalid");
        }

        return parts;
    }

    public static String[] eventFormatter(String content) throws GenesisException {
        String[] parts = content.split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new GenesisException("Description for event is invalid");
        }

        return parts;
    }
}