package task;

import error.DukeIllegalSyntaxException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description, 'D');
        this.by = by;

        replaceDate(by);
    }

    public String getBy() {
        return by;
    }


    // Replaces dates of `yyyy-mm-dd` formats into `MMM dd yyyy` formats
    private void replaceDate(String by) {

        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        Pattern dateRegex = Pattern.compile(datePattern);
        Matcher dateMatcher = dateRegex.matcher(by);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String oldDateString;
        String newDateString;

        if (dateMatcher.find()) {
            oldDateString = dateMatcher.group();
            newDateString = getReplacementDateString(LocalDate.parse(dateMatcher.group(), formatter));
            this.by = by.replace(oldDateString, newDateString);
        }

    }

    // Returns the new date format from the input
    private String getReplacementDateString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(formatter);
    }

    /**
     * Method extracts the relevant information (description and by fields) from the userInput.
     * It returns a String array containing {description, deadline}.
     *
     * @param userInput The input entered by the user.
     * @return An array of Strings containing each word of the input in each of the indexes.
     * @throws DukeIllegalSyntaxException If the syntax entered by the user is invalid.
     */
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
