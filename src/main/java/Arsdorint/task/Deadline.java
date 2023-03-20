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

    /**
     * Initiate with status
     */
    public Deadline(boolean status, String description, String date) {
        this(description, date);
        this.isDone = status;
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

    /**
     * A boolean function to check if the date input is null or not
     *
     * @return true if the date is null
     */
    @Override
    public boolean isDateNull() {
        return (this.date == null ? true : false);
    }

    /**
     * Print the date in right format (DDDD-MM-YY)
     */
    public String printDate(String pattern) {
        return (this.date == null) ? this.dateString : this.date.format(DateTimeFormatter.ofPattern(pattern));
    }
    public LocalDate getDate() {
        return this.date;
    }

}
