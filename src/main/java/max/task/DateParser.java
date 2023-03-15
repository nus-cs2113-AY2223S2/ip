package max.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateParser helps with input-output validation for date strings
 * <p>
 * Input dates are checked and edited to follow this format: yyyy-MM-dd HHmm
 * Output dates are checked to edited to follow this format: MMM dd HHmm, yyyy
 * Transformation between the 2 formats are handled in this class
 */
public class DateParser {

    private static DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static DateTimeFormatter outputDateFormat = DateTimeFormatter.ofPattern("MMM dd HHmm, yyyy");
    private static String INPUT_FORMAT_HINT = "Max only accepts dates of format: yyyy-MM-dd HHmm";
    private static String INVALID_TO_FROM_MESSAGE = "Your --from date must be before the --to date!";

    /**
     * Creates a DateParser object that can validate date strings
     */
    public DateParser() {

    }

    /**
     * Converts a string representing an input date into a Java date format
     * <p>
     * This function will only accept input dates of format: "yyyy-MM-dd HHmm"
     *
     * @param dateString user input date
     * @return LocalDateTime representation of the user input date
     * @throws TaskException dateString does not conform to input format standards
     */
    public LocalDateTime parseDate(String dateString) throws TaskException {
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateString, inputDateFormat);
        } catch (DateTimeException exception) {
            throw new TaskException(INPUT_FORMAT_HINT);
        }
        return dateTime;
    }

    /**
     * Converts a LocalDateTime to the user-friendly output date format
     * <p>
     * Output date format is defined as "MMM dd HHmm, yyyy"
     *
     * @param dateTime dateTime to be converted to output format
     * @return String of date format "MMM dd HHmm, yyyy"
     */
    public String dateToString(LocalDateTime dateTime) {
        // The following should not throw an error
        return outputDateFormat.format(dateTime);
    }

    // Takes in a dateString to be formatted into the output date format
    public String formatInputString(String dateString) throws TaskException {
        LocalDateTime dateTime = parseDate(dateString);
        return dateToString(dateTime);
    }

    /**
     * Checks that user has input start and end dates that make sense
     *
     * @param toString   user input for ending date
     * @param fromString user input for starting date
     * @throws TaskException end date is before start date
     */
    public void validateToFromDates(String toString, String fromString) throws TaskException {
        LocalDateTime to = parseDate(toString);
        LocalDateTime from = parseDate(fromString);
        if (from.isAfter(to)) {
            throw new TaskException(INVALID_TO_FROM_MESSAGE);
        }
    }

}
