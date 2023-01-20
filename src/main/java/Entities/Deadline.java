package Entities;

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // protected LocalDateTime endDate;
    // private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
    protected String endDate;

    public Deadline(String taskName, String endDate) {
        super(taskName);
        setEndDate(endDate);
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getEndDate() + ")";
    }

}
