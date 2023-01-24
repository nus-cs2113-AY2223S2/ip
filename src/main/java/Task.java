public class Task {
    private String task;
    private char complete;
    public Task(String name) {
        this.task = name;
        this.complete = ' ';
    }
    public String getTask() {
        return this.task;
    }
    public char getComplete() {
        return this.complete;
    }
    public void setComplete() {
        this.complete = 'X';
    }
    public void setIncomplete() {
        this.complete = ' ';
    }
}

