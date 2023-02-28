package duke;


import duke.exceptions.FormatException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
    static ArrayList<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();

    /**
     * Add date format to the knowPatterns list.
     * This will allow duke to accept more different format of input date
     */
    public static void setKnownPatterns() {
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ"));
        knownPatterns.add(new SimpleDateFormat("MMM d yyyy"));
    }

    /**
     * Parse the user input into command and description
     *
     * @param userInputCommand The input entered by the user
     * @return A string array consist of two elements, the first element is the command,
     * and the second one is the description after the command
     */
    public String[] parseCommand(String userInputCommand) {
        final String[] split = userInputCommand.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};
        return commandTypeAndParams;
    }

    /**
     * @param userInputDate The string input date entered by the user
     * @return return data type Date date transformed from the string input date
     * @throws ParseException If the user input couldn't be parse into a Date type, the exception will be thrown.
     */
    public static Date parseDate(String userInputDate) throws ParseException {
        for (SimpleDateFormat pattern : knownPatterns) {
            try {
                return new Date(pattern.parse(userInputDate).getTime());

            } catch (ParseException pe) {
                // only show message when every format doesn't fit
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date time = dateFormat.parse(userInputDate);
        return time;
    }

    /**
     * Parse user input index for delete, mark, or unmark into integer.
     *
     * @param userInputIndex The string index entered by the user
     * @return return date type Integer index transformed from the user input string
     * @throws FormatException If the user input couldn't be formatted into an Integer, the exception will be thrown.
     */
    public static Integer parseIndex(String userInputIndex) throws FormatException{
        try {
            return Integer.parseInt(userInputIndex);
        } catch (NumberFormatException e) {
            System.out.println("OOPS, the index must be an number!");
        }
        return -1;
    }
}
