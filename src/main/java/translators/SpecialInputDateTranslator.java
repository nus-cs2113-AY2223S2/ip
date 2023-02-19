package translators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a translator that converts a string of date in the form <code>yyyy-MM-dd</code>
 * to the form of <code>MMM dd yyyy</code>, e.g., translate <code>2023-03-03</code> to
 * <code>Mar 03 2023</code>.
 */
public class SpecialInputDateTranslator {
    /**
     * Checks if the String of date is in the specific format for the translation to be possible.
     *
     * @param date A string representing a date.
     * @return True if the string is in the format of <code>yyyy-MM-dd</code>, else return false.
     */
    public static boolean isInSpecialFormat(String date) {
        return date.matches("([0-9]{4}-[0-9]{2}-[0-9]{2})");
    }

    /**
     * Converts the date from String format to a LocalDate Object.
     *
     * @param date A String containing information of a date in the format of <code>yyyy-MM-dd</code>.
     * @return A LocalDate Object translated from the String <code>date</code>.
     */
    public static LocalDate convertToDateObject(String date) {
            DateTimeFormatter dateOnlyInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(date, dateOnlyInputFormatter);

    }

    /**
     * Convert a DateTime Object to a string of format <code>MMM dd yyyy</code>.
     *
     * @param date A DateTime Object that needs to be converted.
     * @return A String representing date in the format of <code>MMM dd yyyy</code>.
     */
    public static String formatDate (LocalDate date) {
        DateTimeFormatter dateOnlyOutputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(dateOnlyOutputFormatter);
    }
}
