public class Task {
    protected String description;
    protected boolean isDone;
    protected static int taskNumber = -1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.taskNumber += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public static int getTaskNumber() {
        return Task.taskNumber;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void printTaskDetails() {
        String desc = this.getDescription();
        System.out.print("[" + this.getStatusIcon() + "]" + " " + desc+"\n");
    }
}
