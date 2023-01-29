public class Task {
    private String task;
    private boolean complete;

    public Task(String name) {
        this.task = name;
        this.complete = false;
    }

    public String getTask() {
        return this.task;
    }

    public char getComplete() {
        if(this.complete) {
            return 'X';
        }
        return ' ';
    }

    public void setComplete() {
        this.complete = true;
    }

    public void setIncomplete() {
        this.complete = false;
    }

    @Override
    public String toString() {
        return "[T][" + getComplete() + "] " + getTask();
    }
}

