package EntityUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Helper Class that can convert between LocalDateTime and String
 */
public class DateParser {
    /**
     * Converts string into a datetime representation
     * @param d string to be parsed
     * @return LocalDateTime instance
     * @throws DateTimeParseException
     */
    public static LocalDateTime stringToDate(String d) throws DateTimeParseException {
        DateTimeFormatter formatter;
        switch (d.length()) {
        // Date does not have an associated time
        case 8:
        case 9:
        case 10:
            formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            return LocalDate.parse(d, formatter).atTime(23, 59);
        // Date has an associated time
        case 14:
        case 15:
        case 16:
            formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
            break;
        // Time Format for reading and writing to database
        default:
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        }
        return LocalDateTime.parse(d, formatter);
    }

    /**
     * Converts datetime representation to string
     * Format used is MMM dd yyyy HH:mm
     * @param date date to be parsed
     * @return
     */
    public static String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Converts date time represntation to string using custom formatter
     * @param date date to be parsed
     * @param format Format to parse the datetime represenation
     * @return String that corresponds the date instance provided
     * @throws IllegalArgumentException
     */
    public static String dateToString(LocalDateTime date, String format) throws IllegalArgumentException {
        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
