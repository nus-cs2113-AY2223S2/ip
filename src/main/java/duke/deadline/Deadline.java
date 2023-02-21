package duke.deadline;

import duke.item.Item;

public class Deadline extends Item {
    private String datemark;
    
    public Deadline(String description, String datemark) {
        super(description);
        this.datemark = datemark;
        type = "Deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + datemark + ")";
    }
}