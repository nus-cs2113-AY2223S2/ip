package duke.deadline;

import duke.item.Item;

public class Deadline extends Item {
    private String by;
    
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        type = "Deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}