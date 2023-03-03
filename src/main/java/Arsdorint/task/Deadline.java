package Arsdorint.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public static final String TYPE_DEADLINE = "D";
    public String dateString;
    public LocalDate date;
    public Deadline(String description, String date) {
        super(description);
        this.taskType = "[D]";
        this.taskName = TYPE_DEADLINE;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException err) {
            this.date = null;
            this.dateString = date;
        }
    }

    public Deadline(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
    }
    public void printTask() {
        System.out.println(this.taskType + this.getStatus()
        + " " + this.description + "(" + this.date + ")");
    }
    @Override
    public String toSave() {
        return (this.taskName + VERTICAL_BAR + binaryRes() + VERTICAL_BAR +
                this.description + VERTICAL_BAR + printDate("yyyy-MM-dd") + "\n");
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
