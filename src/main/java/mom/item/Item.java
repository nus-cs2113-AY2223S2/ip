package mom.item;

public abstract class Item {
    private String description; // item name
    private boolean isDone;

    public Item (String description) {
        this.description = description;
        this.isDone = false;
    }

    public Item (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
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
