package max.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateParser {

    private static DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM dd HHmm, yyyy");
    private static String INPUT_FORMAT_HINT = "Max only accepts dates of format: yyyy-MM-dd HHmm";
    private static String INVALID_TO_FROM_MESSAGE = "Your --from date must be before the --to date!";
    public DateParser() {

    }

    public LocalDateTime parseDate(String dateString) throws TaskException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateString, inputDateFormat);
        } catch (DateTimeException exception) {
            throw new TaskException(INPUT_FORMAT_HINT);
        }
        return dateTime;
    }

    public String dateToString(LocalDateTime dateTime) {
        // The following should not throw an error
        return outputDateFormat.format(dateTime);
    }

    // Takes in a dateString to be formatted into the output date format
    public String formatInputString(String dateString) throws TaskException {
        LocalDateTime dateTime = parseDate(dateString);
        return dateToString(dateTime);
    }

    public void validateToFromDates(String toString, String fromString) throws TaskException{
        LocalDateTime to = parseDate(toString);
        LocalDateTime from = parseDate(fromString);
        if(from.isAfter(to)){
            throw new TaskException(INVALID_TO_FROM_MESSAGE);
        }
    }

}
