package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Contains methods and variables used for the date and time
 */
public class DateTime {

    public static DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    public static DateTimeFormatter inputDateTimeFormat = DateTimeFormatter.ofPattern("uuuu/MM/dd HHmm");
    public static DateTimeFormatter outDateFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
    public static DateTimeFormatter outDateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HHmm");

    /**
     * Generates a LocalDate variable given a date in string if possible
     *
     * @param date string date to be converted into a LocalDate variable
     * @return LocalDate variable if date is in the correct format, else returns null
     */
    public static LocalDate storeLocalDate(String date) {
        String[] arrayDateTime = date.split(" ");
        if (arrayDateTime.length == 1) {
            try {
                return LocalDate.parse(date.trim(), DateTime.inputDateFormat);
            } catch (DateTimeParseException e) {
                return null;
            }
        } else if (arrayDateTime.length == 2) {
            try {
                return LocalDate.parse(arrayDateTime[0].trim(), DateTime.inputDateFormat);
            } catch (DateTimeParseException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Generates a LocalDateTime variable given a date in string if possible
     *
     * @param dateTime string date to be converted into a LocalDate variable
     * @return LocalDateTime variable if date is in the correct format, else returns null
     */
    public static LocalDateTime storeLocalDateTime(String dateTime) {
        String[] arrayDateTime = dateTime.split(" ");
        if (arrayDateTime.length == 2) {
            try {
                return LocalDateTime.parse(dateTime.trim(), DateTime.inputDateTimeFormat);
            } catch (DateTimeParseException e) {
                return null;
            }
        }
        return null;
    }
}
