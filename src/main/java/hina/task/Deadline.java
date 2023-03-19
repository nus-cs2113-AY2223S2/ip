package hina.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a <code>Task</code> on a to-do list with a deadline.
 */
public class Deadline extends Task {
    private LocalDateTime by;
    String byString;

    /**
     * Class constructor specifying this <code>Deadline</code>'s <code>description</code> and
     * due date.
     *
     * @param description description of this <code>Deadline</code>.
     * @param by <code>LocalDateTime</code> object specifying the due date.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
            this.by = by;
            this.byString = this.by.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
    }

    @Override
    public String toString() {
        String mark;
        if (super.isDone()) {
            mark = "X";
        } else {
            mark = " ";
        }
        return String.format("[D][%s] %s (by: %s)", mark, super.getDescription(), byString);
    }

    @Override
    public String toSave() {
        return String.format("D / %s / %s / %s", isDone? "1" : "0", description, byString);
    }
}
