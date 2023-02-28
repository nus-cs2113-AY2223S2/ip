package ChatTPG;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses and verifies the dates in the user commands passed in.
 */
public class DateTimeParser {

    /**
     * Processes and verifies the date if it has not been processed before.
     *
     * @param date Date to be processed.
     * @param processed True if the date passed in has already been processed, and false otherwise.
     * @return the processed date.
     * @throws InvalidDateFormat if the date to be processed is not in YYYY-MM-DD format.
     */
    public static String processDateTime(String date, boolean processed) throws InvalidDateFormat{
        if (processed) {
            return date;
        } else {
            verifyDateTime(date);
            LocalDate localDate = LocalDate.parse(date);
            String parsedDate = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return parsedDate;
        }
    }

    /**
     * Verifies the format of the date passed in.
     *
     * @param date Date to be verified.
     * @throws InvalidDateFormat if the date to be processed is not in YYYY-MM-DD format.
     */
    public static void verifyDateTime(String date) throws InvalidDateFormat {
        if (!date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            throw new InvalidDateFormat();
        }
    }

    /**
     * Verifies the duration of the event is legitimate.
     *
     * @param start Start date passed in.
     * @param end End date passed in.
     * @throws InvalidStartEnd if start date occurs after the end date.
     */
    public static void verifyStartEnd(String start, String end) throws InvalidStartEnd {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        if (startDate.isAfter(endDate)) {
            throw new InvalidStartEnd();
        }
    }
}
