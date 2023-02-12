public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public boolean getStatus() {
        return isCompleted;
    }

    public String getName() {
        return name;
    }

    public void setStatus(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.name;
        }
        return "[ ] " + this.name;
    }
}
