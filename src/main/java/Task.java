public class Task {
    protected String description;
    protected int num;
    protected boolean isDone;

    public Task(String description, int num) {
        this.description = description;
        this.isDone = false;
        this.num = num;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

}
