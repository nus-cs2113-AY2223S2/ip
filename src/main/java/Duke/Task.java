package Duke;

public abstract class Task {
    protected String content;
    private boolean isCompleted;

    Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    boolean isCompleted() {
        return isCompleted;
    }

    Task markAsComplete() {
        this.isCompleted = true;
        return this;
    }

    Task markAsIncomplete() {
        this.isCompleted = false;
        return this;
    }

    abstract boolean contains(String keyword);

    abstract String getType();

    public String toString() {
        return (this.isCompleted ? "[X] " : "[ ] ") + this.content;
    }
}
