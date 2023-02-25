package luke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A <code>Event</code> object represents a task that has a start and end.
 */
public class Event extends Task {
    protected String startDate;
    protected String  endDate;
    public Event(String name, int ID, String  startDate, String  endDate) {
        super(name, ID);
        this.label.setLabel("E");
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /** Prints out the label, checkBox, name followed by the deadline */
    @Override
    public void printTaskName() {
        LocalDateTime start = LocalDateTime.parse(this.startDate);
        LocalDateTime end = LocalDateTime.parse(this.endDate);

        System.out.print(this.serialNumber + ". ");
        this.label.printLabel();
        this.checkBox.printCheckBox();
        System.out.print(this.getTaskName());
        System.out.println(" (From: "
                + start.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh mm a"))
                + " to: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh mm a")) + ")");
    }
}
