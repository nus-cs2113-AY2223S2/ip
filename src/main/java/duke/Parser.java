package duke;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {
    static ArrayList<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();

    public static void setKnownPatterns() {
        knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd"));
        knownPatterns.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ"));
        knownPatterns.add(new SimpleDateFormat("MMM d yyyy"));
    }

    public String[] parseCommand(String userInputCommand) {
        final String[] split = userInputCommand.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[]{split[0], ""};
        return commandTypeAndParams;
    }
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
}
