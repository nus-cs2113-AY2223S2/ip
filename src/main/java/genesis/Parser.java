package genesis;

import exception.InvalidArgumentException;

/**
 * The Parser class provides methods to parse user input and extract relevant information from it.
 */
public class Parser {

    /**
     * Formats the deadline task input and splits it into description and deadline.
     *
     * @param content The input string for deadline task
     * @return An array of two strings containing the description and deadline of the deadline task
     * @throws InvalidArgumentException If the input string is invalid
     */
    public static String[] formatDeadline(String content) throws InvalidArgumentException {
        String[] parts = content.split(" /by ", 2);
        if (parts.length < 2) {
            throw new InvalidArgumentException("Description for deadline is invalid");
        }

        return parts;
    }

    /**
     * Formats the event task input and splits it into description, start time and end time.
     *
     * @param content The input string for event task
     * @return An array of three strings containing the description, start time and end time of the event task
     * @throws InvalidArgumentException If the input string is invalid
     */
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