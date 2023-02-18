package translators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SpecialInputTimeTranslator {

    public static boolean isInSpecialFormat(String date) {
        return date.matches("([0-9]{4}-[0-9]{2}-[0-9]{2})");
    }
    public static LocalDate convertToDateObject(String date) {
            DateTimeFormatter dateOnlyInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, dateOnlyInputFormatter);

    }

    public static String formatDate (LocalDate date) {
        DateTimeFormatter dateOnlyOutputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateOnlyOutputFormatter);
    }
}
