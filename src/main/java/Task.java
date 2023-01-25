public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String getTaskName() {
        return taskName;
    }

    public void printTask() {
        if (this.isDone) {
            System.out.println("[X] " + taskName);
        } else {
            System.out.println("[ ] " + taskName);
        }
    }

}
