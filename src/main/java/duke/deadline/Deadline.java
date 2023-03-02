package duke.deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.item.Item;
import duke.utils.Constants;

public class Deadline extends Item {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.OUTPUT_DATE_TIME_FORMAT.toString());
    
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