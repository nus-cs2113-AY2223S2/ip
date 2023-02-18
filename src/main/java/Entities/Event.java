package Entities;

import java.time.LocalDateTime;
import EntityUtils.DateParser;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // protected LocalDateTime startDate;
    // protected LocalDateTime endDate;
    // private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    public Event(String taskDescription, boolean isDone, LocalDateTime startDate, LocalDateTime endDate) {
        super(taskDescription, isDone);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public String getStartDate() {
        return DateParser.dateToString(this.startDate);
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return DateParser.dateToString(this.endDate);
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    // @Override
    // public String toString() {
    //     return "[E]" + super.toString() + " (from: " + getStartDate().format(dateFormatter) + "to: "
    //             + getEndDate().format(dateFormatter) + ")";
    // }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getStartDate() + " to: " + getEndDate() + ")";
    }

}
