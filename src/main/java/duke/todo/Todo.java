package duke.todo;

import duke.item.Item;

public class Todo extends Item {
    public Todo(String description) {
        super(description);
        type = "Todo";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}