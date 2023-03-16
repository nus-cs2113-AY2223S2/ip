package utility;

public class Parser {
    /**
     * Removes the command word for creating tasks, ie. to do, deadline or event
     */
    public static String removeCommandWord(String userInput) {
        return userInput.split(" ", 2)[1];
    }

    /**
     * Extracts the description given by the user for deadline tasks
     */
    public static String getDescriptionForDeadline(String input) {
        return input.split(" /by ", 2)[0];
    }

    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0].toLowerCase();
    }

    /**
     * Extracts the deadline for deadline tasks
     */
    public static String getDeadline(String input) {
        return input.split(" /by ", 2)[1];
    }

    /**
     * Extracts the description given by the user for event tasks
     */
    public static String getDescriptionForEvent(String input) {
        return input.split(" /from ", 2)[0];
    }

    /**
     * Extracts the start and end timing for event tasks
     */
    public static String[] getTimings(String input) {

        String[] timings = new String[2];
        String timing = input.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];

        return timings;
    }
}
