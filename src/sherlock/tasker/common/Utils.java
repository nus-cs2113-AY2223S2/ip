package common;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Utils {
    /**
     * Parses DateTimeFormatter for parsing/formatting string
     * in the custom format: "dd-MM-yyyy(required) HH:mm(optional)"
     */
    public static DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
            .appendPattern("dd-MM-yyyy")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .toFormatter();
}
