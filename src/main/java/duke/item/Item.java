package duke.item;

public abstract class Item {
    private String description; // item name
    private boolean isDone;
    public String type = "Item";

    public Item (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatus() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + description;
    }
}
