public class Task {
    private String content;
    private boolean completed;

    Task(String content) {
        this.content = content;
        this.completed = false;
    }

    void markAsComplete() {
        this.completed = true;
    }

    void markAsIncomplete() {
        this.completed = false;
    }

    public String toString() {
        return (this.completed ? "[X] " : "[ ] ") + this.content;
    }
}
