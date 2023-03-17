package utility;

public class Parser {
    /**
     * Extracts the command word given by the user
     * @param userInput String given by user
     * @return Command word
     */
    public static String getCommand(String userInput) {
        return userInput.split(" ", 2)[0].toLowerCase();
    }

    /**
     * Removes the command word for further extraction of key words.
     * @param userInput String given by user
     * @return User's input without the command word
     */
    public static String removeCommandWord(String userInput) {
        return userInput.split(" ", 2)[1];
    }

    /**
     * Obtains description for deadline
     * @param input User's input without the command word
     * @return Description for deadline task
     */
    public static String getDescriptionForDeadline(String input) {
        return input.split(" /by ", 2)[0];
    }

    /**
     * Obtains the time for deadline
     * @param input Remaining input to be extracted
     * @return Time for deadline
     */
    public static String getDeadline(String input) {
        return input.split(" /by ", 2)[1];
    }

    /**
     * Obtains the description given by the user for event tasks
     * @param input User's input without the command word
     * @return Description for event
     */
    public static String getDescriptionForEvent(String input) {
        return input.split(" /from ", 2)[0];
    }

    /**
     * Obtains the start and end timing for event tasks
     * @param input Remaining input to be extracted
     * @return Array of time for event
     */
    public static String[] getTimings(String input) {

        String[] timings = new String[2];
        String timing = input.split(" /from ", 2)[1];

        timings[0] = timing.split(" /to ", 2)[0];
        timings[1] = timing.split(" /to ", 2)[1];

        return timings;
    }
}
