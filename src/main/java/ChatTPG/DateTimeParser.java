package ChatTPG;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

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

    public static void verifyDateTime(String date) throws InvalidDateFormat {
        if (!date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            throw new InvalidDateFormat();
        }
    }
}
