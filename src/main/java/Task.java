public class Task {
    protected String taskName;
    protected boolean isDone;
    protected static int totalTasks = 0;

    public Task() {
    }

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public static void incrementTotalTasks() {
        totalTasks += 1;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public String getFullTaskDetail() {
        String taskDetail;
        taskDetail = "[" + getStatusIcon() + "] " + taskName;
        return taskDetail;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
}
