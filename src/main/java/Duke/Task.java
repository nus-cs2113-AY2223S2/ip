package Duke;

public class Task {
    private String content;
    private boolean isCompleted;

    Task(String content) {
        this.content = content;
        this.isCompleted = false;
    }

    void markAsComplete() {
        this.isCompleted = true;
    }

    void markAsIncomplete() {
        this.isCompleted = false;
    }

    public String toString() {
        return (this.isCompleted ? "[X] " : "[ ] ") + this.content;
    }
}
