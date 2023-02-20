package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    public static DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("uuuu/MM/dd");
    public static DateTimeFormatter inputDateTimeFormat = DateTimeFormatter.ofPattern("uuuu/MM/dd HHmm");
    public static DateTimeFormatter outDateFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
    public static DateTimeFormatter outDateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM uuuu HHmm");

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

    public static LocalDateTime storeLocalDateTime(String date) {
        String[] arrayDateTime = date.split(" ");
        if (arrayDateTime.length == 2) {
            try {
                return LocalDateTime.parse(date.trim(), DateTime.inputDateTimeFormat);
            } catch (DateTimeParseException e) {
                return null;
            }
        }
        return null;
    }
}
