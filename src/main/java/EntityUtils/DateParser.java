package EntityUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateParser {
    public static LocalDateTime stringToDate(String d) throws DateTimeParseException {
        DateTimeFormatter formatter;
        switch (d.length()) 
        {
        case 8:
        case 9:
        case 10:
            formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            return LocalDate.parse(d, formatter).atTime(23, 59);
        case 14:
        case 15:
        case 16:
            formatter = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm");
            break;
        default:
            formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        }
        return LocalDateTime.parse(d, formatter);
    }

    public static String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public static String dateToString(LocalDateTime date, String format) throws IllegalArgumentException {
        return date.format(DateTimeFormatter.ofPattern(format));
    }
}
