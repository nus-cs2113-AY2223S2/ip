package task;

import error.DukeIllegalSyntaxException;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    // Returns a String array containing {taskName, deadline}
    public static String[] handler(String userInput) throws DukeIllegalSyntaxException {

        // Format of userInput: <command> <taskName> /by <deadline>
        int numberOfWords = userInput.split(" ").length - 1;
        int endOfTaskNameIndex = 0;
        String[] userInputArray = new String[numberOfWords];
        String[] outputArray = {"", ""};

        userInput = userInput.replaceFirst("deadline ", "");

        // Checks if userInput contains "\by"
        if (!userInput.contains("/by")) {
            throw new DukeIllegalSyntaxException();
        }

        // Get index of "/by"
        userInputArray = userInput.split(" ");
        for (int i = 0; i < numberOfWords; i++) {
            if (userInputArray[i].equals("/by")) {
                endOfTaskNameIndex = i;
                break;
            }
        }

        // Add the taskName into index 0 of outputArray
        for (int i = 0; i < endOfTaskNameIndex; i++) {
            outputArray[0] += userInputArray[i] + " ";
        }
        outputArray[0] = outputArray[0].trim();

        // Add the deadline into index 1 of outputArray
        for (int i = endOfTaskNameIndex + 1; i < numberOfWords; i++) {
            outputArray[1] += userInputArray[i] + " ";
        }
        outputArray[1] = outputArray[1].trim();

        // Return {taskName, deadline}
        return outputArray;

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}
