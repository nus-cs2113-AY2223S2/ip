import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * DateTime class for dealing with date and time conversions
 */
public class DateTime {

    /**
     * Alters date from whatever input format to YYYY-MM-DD so it can be stored as LocalDate/LocalDateTime
     * @param datestr user input date string in any format
     * @return datestr2 after conversion to YYYY-MM-DD
     */
    public static String Date(String datestr) {
        String datestr2 = datestr;
        if (datestr.indexOf('-') != 4) {
            int month = Integer.parseInt(datestr.substring(3, 5));
            if (month > 12) {
                datestr2 = datestr.substring(6) + "-" + datestr.substring(0, 3) + datestr.substring(3, 5);
            } else {
                datestr2 = datestr.substring(6) + "-" + datestr.substring(3, 6) + datestr.substring(0, 2);
            }
        }
        return datestr2;
    }

    /**
     * Alters time from whatever input format to 24 hour format so it cna be stored as LocalDate/LocalDateTime
     * @param timestr user input time string in any format
     * @return timestr2 after conversion to 24 hour format
     */
    public static String Time(String timestr) {
        String timestr2 = timestr;
        if (timestr.endsWith("PM") && timestr.charAt(0) == '0') {
            int hour = Character.getNumericValue(timestr.charAt(1));
            hour = hour + 12;
            timestr2 = hour + timestr.substring(2, 5);
        } else if (timestr.endsWith("AM") && timestr.startsWith("12")) {
            timestr2 = "00:00";
        } else {
            timestr2 = timestr.substring(0, 5);
        }
        return timestr2;
    }

    /**
     * Converts user input date and time string format to YYYY-MM-DDTHH:mm
     * @param str user input date and/or time as string
     * @return datetimestr in the format YYYY-MM-DDTHH:mm
     */
    public static String DT(String str) {
        String datestr;
        String timestr;
        String datetimestr;
        if (str.length() == 10) {
            datestr = Date(str);
            timestr = "00:00";
            datetimestr = datestr + "T" + timestr;
        } else if (str.length() <= 8) {
            datestr = (LocalDate.now()).toString();
            timestr = Time(str);
            datetimestr = datestr + "T" + timestr;
        } else {
            datestr = str.substring(0, 10);
            datestr = Date(datestr);
            timestr = str.substring(11);
            timestr = Time(timestr);
            datetimestr = datestr + "T" + timestr;
        }
        return datetimestr;
    }

    /**
     * Parses user input date and/or time string in any format to LocalDateTime
     * @param str user input date and/or time as string
     * @param formatter for formatting LocalDateTime values to "yyyy-MM-dd'T'HH:mm"
     * @return user inputted string as LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String str, DateTimeFormatter formatter) {
        String datetimestr = DT(str);
        return LocalDateTime.parse(datetimestr, formatter);
    }

}
