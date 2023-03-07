package Arsdorint.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    public static final String TYPE_EVENT = "E";
    public String dateString;
    public LocalDate date;
    public Event(String description, String date) {
        super(description);
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException err) {
            this.date = null;
            this.dateString = date;
        }
        this.taskType = "[E]";
        this.taskName = TYPE_EVENT;
    }

    public Event(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }

    @Override
    public String toSave() {
        return (this.taskName + VERTICAL_BAR + binaryRes() + VERTICAL_BAR
                + this.description + VERTICAL_BAR + printDate("yyyy-MM-dd") + "\n");
    }

    @Override
    public String toString() {
        return (this.taskType + this.getStatus() + " " + this.description + "\t(" + printDate("d MMM yyy") + ")");
    }

    @Override
    public boolean isDateNull() {
        return (this.date == null ? true : false);
    }

    public String printDate(String date) {
        return (this.date == null) ? this.dateString : this.date.format(DateTimeFormatter.ofPattern(date));
    }
    public LocalDate getDate() {
        return this.date;
    }
}
