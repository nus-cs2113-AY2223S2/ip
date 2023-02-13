package duke.remind;

import duke.item.Item;

public class Remind extends Item {
    public Remind(String description) {
        super(description);
        type = "Remind";
    }

    @Override
    public String toString() {
        return "[R]" + super.toString();
    }
}