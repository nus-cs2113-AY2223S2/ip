package duke;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    protected Date by;

    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        final DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + dateFormat.format(by) + ") ";
    }
    public String toStorage() {
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return "[D]" + super.toStorage() + " by: " + dateFormat.format(by);
    }
}
