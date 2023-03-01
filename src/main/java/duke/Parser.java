package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Parser class helps to extract necessary
 * information from the user command for later use.
 */
public class Parser {

    /**
     * This method is used to get the index in the user command
     * string for various scenarios
     *
     * @param userCommand User's command
     * @param type Type of index to be extracted
     * @return int
     */
    public static int getIndex(String userCommand, String type) {
        switch (type) {
        case "task":
            return Integer.parseInt(userCommand.split(" ")[1])-1;
        case "by":
            return userCommand.indexOf("/by");
        case "from":
            return userCommand.indexOf("/from");
        case "to":
            return userCommand.indexOf("/to");
        }
        return 0;
    }

    /**
     * This method is used to get a string in the user command
     * string for various scenarios
     *
     * @param userCommand User's command
     * @param type Type of String to be extracted
     * @param task Type of Task String is being extracted from
     * @return String
     */
    public static String extractInfo(String userCommand, String type, String task) throws ParseException {
        int indexOfBy, indexOfFrom, indexOfTo;
        String dateInString, strDate;
        switch (type) {
        case "desc":
            switch (task) {
            case "todo":
                return userCommand.substring(5);
            case "deadline":
                indexOfBy = Parser.getIndex(userCommand, "by");
                return userCommand.substring(9, indexOfBy - 1);
            case "event":
                indexOfFrom = Parser.getIndex(userCommand, "from");
                return userCommand.substring(6,indexOfFrom-1);
            }
        case "by":
            indexOfBy = Parser.getIndex(userCommand, "by");
            dateInString = userCommand.substring(indexOfBy + 4);
            strDate = extractDateAndTime(dateInString);
            return strDate;
        case "from":
            indexOfFrom = Parser.getIndex(userCommand, "from");
            indexOfTo = Parser.getIndex(userCommand, "to");
            dateInString = userCommand.substring(indexOfFrom+6,indexOfTo-1);
            strDate = extractDateAndTime(dateInString);
            return strDate;
        case "to":
            indexOfTo = Parser.getIndex(userCommand, "to");
            dateInString = userCommand.substring(indexOfTo+4);
            strDate = extractDateAndTime(dateInString);
            return strDate;
        }

        return "";
    }

    public static String extractDateAndTime(String dateAndTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm a");
        Date date = formatter.parse(dateAndTime);
        formatter = new SimpleDateFormat("dd MMMM yyyy hh:mm a");
        return formatter.format(date);
    }

}
