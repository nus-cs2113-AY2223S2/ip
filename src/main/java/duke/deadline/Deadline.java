package duke.deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.item.Item;

public class Deadline extends Item {
    private static String format = "dd MMM yyyy hh:mm a";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    
    private LocalDateTime datemark;
    
    public Deadline(String description, LocalDateTime datemark) {
        super(description);
        this.datemark = datemark;
    }

    public Deadline(String description, LocalDateTime datemark, boolean isDone) {
        super(description, isDone);
        this.datemark = datemark;
    }

    public String getDate() {
        return this.datemark.toString();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datemark.format(formatter) + ")";
    }
}