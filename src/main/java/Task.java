public class Task {
    protected static Task[] tasks = new Task[100];
    protected static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String icon = getStatusIcon();
        String output;
        output = "[" + icon + "] " + description;
        return output;
    }

}
