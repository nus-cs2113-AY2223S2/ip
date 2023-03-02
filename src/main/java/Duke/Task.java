package Duke;

public abstract class Task {
    private String content;
    private boolean isCompleted;

    Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    boolean isCompleted() {
        return isCompleted;
    }

    void markAsComplete() {
        this.isCompleted = true;
        TaskSaver.updateTask(this);
    }

    void markAsIncomplete() {
        this.isCompleted = false;
        TaskSaver.updateTask(this);
    }

    abstract String getType();

    public String toString() {
        return (this.isCompleted ? "[X] " : "[ ] ") + this.content;
    }
}
