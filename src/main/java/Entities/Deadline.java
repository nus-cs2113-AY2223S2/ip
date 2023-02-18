package Entities;

import java.time.LocalDateTime;
import EntityUtils.DateParser;

public class Deadline extends Task {
    protected LocalDateTime endDate;

    public Deadline(String taskDescription, boolean isDone, LocalDateTime endDate) {
        super(taskDescription, isDone);
        setEndDate(endDate);
    }

    public String getEndDate() {
        return DateParser.dateToString(endDate);
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getEndDate() + ")";
    }

}
