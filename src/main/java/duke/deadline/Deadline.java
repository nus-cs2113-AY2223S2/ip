package duke.deadline;

import duke.item.Item;

public class Deadline extends Item {
    private String before;
    
    public Deadline(String description, String before) {
        super(description);
        this.before = before;
        type = "Deadline";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + before + ")";
    }
}