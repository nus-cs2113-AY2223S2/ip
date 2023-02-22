package luke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A <code>Deadline</code> object represents a task that has a deadline.
 */
public class Deadline extends Task {
    protected String endDate;
    public Deadline(String name, int ID, String endDate) {
        super(name, ID);
        this.label.setLabel("D");
        this.endDate = endDate;
    }

    /** Prints out the label, checkBox, name followed by the deadline */
    @Override
    public void printTaskName() {
        LocalDateTime end = LocalDateTime.parse(this.endDate);

        System.out.print(this.serialNumber + ". ");
        this.label.printLabel();
        this.checkBox.printCheckBox();
        System.out.print(this.getTaskName());
        System.out.println(" (Due: "
                + end.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh mm a")) + ")");
    }
}
