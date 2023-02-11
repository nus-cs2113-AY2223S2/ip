package Support;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Time {
    private LocalDate date;
    private LocalDateTime datetime;

    /*
    Only accept two form of time (with hours/minute or without)
    */
    //Time
    public Time(String year, String month, String day) {
        String stringDate = year + "-" + month + "-" + day;
        this.date = LocalDate.parse(stringDate);
    }

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
