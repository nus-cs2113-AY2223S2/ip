package ChatTPG;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {

    public static String processDateTime(String date) throws InvalidDateFormat{
        verifyDateTime(date);
        LocalDate local_date = LocalDate.parse(date);
        String parsed_date = local_date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return parsed_date;
    }

    public static void verifyDateTime(String date) throws InvalidDateFormat {
        if (!date.matches("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
            throw new InvalidDateFormat();
        }
    }

    public static void verifyDateInterval(String begin, String end) throws InvalidStartEnd{
        LocalDate local_begin = LocalDate.parse(begin);
        LocalDate local_end = LocalDate.parse(end);
        if (local_begin.isAfter(local_end)) {
            throw new InvalidStartEnd();
        }
    }
}
