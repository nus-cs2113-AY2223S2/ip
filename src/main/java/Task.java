public class Task {
    private String task;
    private boolean isComplete;

    public Task(String name) {
        this.task = name;
        this.isComplete = false;
    }

    public String getTask() {
        return this.task;
    }

    public char getComplete() {
        if (this.isComplete) {
            return 'X';
        }
        return ' ';
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public void setIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return "[T][" + getComplete() + "] " + getTask();
    }
}

