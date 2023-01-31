package Entities;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Event extends Task {
    // protected LocalDateTime startDate;
    // protected LocalDateTime endDate;
    // private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
    protected String startDate;
    protected String endDate;

    public Event(String taskDescription, boolean isDone, String startDate, String endDate) {
        super(taskDescription, isDone);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
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
