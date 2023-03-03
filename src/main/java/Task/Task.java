package Task;

public class Task {
    protected String description;
    protected boolean isDone;
    public static int numberOfTasks = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        addNumberOfTasks();
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        addNumberOfTasks();
    }

    public static void addNumberOfTasks() {
        numberOfTasks++;
    }

    public static void decrementNumberOfTasks() {
        numberOfTasks--;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "]" + "\t" + this.getDescription();
    }

    public String toFileString() {
        return "Task";
    }

}