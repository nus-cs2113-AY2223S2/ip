package duke.parser;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeParser {
    private final String[] dateFormats = {
            "dd-MM-yyyy",
            "yyyy-MM-dd",
            "d MMM yyyy",
            "MMM d yyyy",
    };
    private final String[] dateTimeFormats = {
            "dd-MM-yyyy HH:mm",
            "yyyy-MM-dd HH:mm",
            "d MMM yyyy HH:mm",
            "MMM d yyyy HH:mm",
    };
    //Convert parsed date format -> output format
    private final DateTimeFormatter dateFormatter;
    //Convert parsed datetime format -> output format
    private final DateTimeFormatter dateTimeFormatter;

    public DateTimeParser() {
        DateTimeFormatterBuilder dateFormatterBuilder = new DateTimeFormatterBuilder().parseCaseInsensitive();
        for (String dateFormat : dateFormats) {
            dateFormatterBuilder.appendPattern("[" + dateFormat + "]");
        }

        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder().parseCaseInsensitive();
        for (String dateTimeFormat : dateTimeFormats) {
            dateTimeFormatterBuilder.appendPattern("[" + dateTimeFormat + "]");
        }
        this.dateFormatter = dateFormatterBuilder.toFormatter(Locale.ENGLISH);
        this.dateTimeFormatter = dateTimeFormatterBuilder.toFormatter(Locale.ENGLISH);
    }
    public String parse(String inputDateTime) throws DateTimeParseException {
        try {
            LocalDate parsedDate = LocalDate.parse(inputDateTime, dateFormatter);
            return parsedDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeParseException exception) {
            // Try to parse as datetime instead
            LocalDateTime parsedDateTime = LocalDateTime.parse(inputDateTime, this.dateTimeFormatter);
            return parsedDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
        }
    }
}
