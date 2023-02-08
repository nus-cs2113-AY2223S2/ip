public class Task {
    protected String description;
    protected boolean isDone;
    protected static int numberOfTasks;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        numberOfTasks += 1;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void printTask() {
        System.out.println("task");
    }
}