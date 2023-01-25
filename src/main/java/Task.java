public class Task {
    private String taskName;
    private boolean isDone;

    public String getTaskName() {
        return taskName;
    }

    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public void markTask() {
        this.isDone = true;
    }

    public void unmarkTask() {
        this.isDone = false;
    }

    public void printTask() {
        if (this.isDone) {
            System.out.println("[X] " + taskName);
        } else {
            System.out.println("[ ] " + taskName);
        }
    }

}
