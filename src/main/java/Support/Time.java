package Support;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Time {
    // Since the input time can only take two formats, we use LocalDate and LocalDateTime to separate them.
    // See the details in class TransformingString
    private LocalDate date;
    private LocalDateTime datetime;

    /*
    Only accept two form of time (with hours/minute or without)
    */
    //Time
    /**
     * This class is to transform the input of time by users to a formal string format that fits with the requirement
     * of LocalDate.parse Function.
     *
     * @param year task year
     * @param month task month
     * @param day task day
     */
    public Time(String year, String month, String day) {
        String stringDate = year + "-" + month + "-" + day;
        this.date = LocalDate.parse(stringDate);
    }

    /**
     * This class is to transform the input of time by users to a formal string format that fits with the requirement
     * of LocalDateTime.parse Function.
     *
     * @param year task year
     * @param month task month
     * @param day task day
     * @param time task time, specific to hour and minute
     */
    public Time(String year, String month, String day, String time) {
        String stringDateTime = year + "-" + month + "-" + day + "T"
                + time.substring(0,2) + ":" + time.substring(2) + ":00";
        this.datetime = LocalDateTime.parse(stringDateTime);
        System.out.println(this.datetime);
    }

    public String transformLocalDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public String transformLocalDateTime() {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

}
