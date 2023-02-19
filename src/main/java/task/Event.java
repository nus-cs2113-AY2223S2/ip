package task;

import error.DukeIllegalSyntaxException;

public class Event extends Task {

    private String from;
    private String to;

    public Event(String description, String from, String to) {
        super(description, 'E');
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * Method extracts the relevant information (description, from, and to fields) from the userInput.
     * It returns a String array containing {description, from, to}.
     *
     * @param userInput The input entered by the user.
     * @return An array of Strings containing each word of the input in each of the indexes.
     * @throws DukeIllegalSyntaxException If the syntax entered by the user is invalid.
     */
    public static String[] handler(String userInput) throws DukeIllegalSyntaxException {

        // Format of userInput: <command> <taskName> /from <from> /to <to>
        int numberOfWords = userInput.split(" ").length - 1;
        int fromIndex = 0;
        int toIndex = 0;
        String[] userInputArray;
        String[] outputArray = {"", "", ""};
        boolean isFromPassed = false;
        boolean isToPassed = false;

        // Checks if userInput contains "\from" and "\to"
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new DukeIllegalSyntaxException();
        }

        // Remove `event` from
        userInput = userInput.replaceFirst("event ", "");
        userInputArray = userInput.split(" ");

        // Get indexes of `/from` and `/to` in userInput
        for (int i = 0; i < numberOfWords; i++) {
            if (userInputArray[i].equals("/from") && !isFromPassed) {
                fromIndex = i;
                isFromPassed = true;
            }
            if (userInputArray[i].equals("/to") && !isToPassed) {
                toIndex = i;
                isToPassed = true;
            }
        }

        // Add the taskName into index 0 of outputArray
        for (int i = 0; i < fromIndex; i++) {
            outputArray[0] += userInputArray[i] + " ";
        }
        outputArray[0] = outputArray[0].trim();

        // Add the `from` field into index 1 of outputArray
        for (int i = fromIndex + 1; i < toIndex; i++) {
            outputArray[1] += userInputArray[i] + " ";
        }
        outputArray[1] = outputArray[1].trim();

        // Add the `to` field into index 2 of outputArray
        for (int i = toIndex + 1; i < numberOfWords; i++) {
            outputArray[2] += userInputArray[i] + " ";
        }
        outputArray[2] = outputArray[2].trim();

        return outputArray;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
