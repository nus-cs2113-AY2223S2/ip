package Tasks;

public class Task {

    private final String taskName;
    private boolean isDone;
    private int index;

    public Task(String taskName, int index) {
        this(taskName, false, index);
    }

    public Task(String taskName, boolean isDone, int index) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.index = index;
    }

    public boolean isDone() {
        return this.isDone;
    }
    public String getTaskName() {
        return this.taskName;
    }
    public int getIndex() {
        return this.index;
    }

    public void doTask() {
        if (this.isDone) {
            System.out.println("Tasks.Task is done already");
            return;
        }
        this.isDone = true;
    }

    public void undoTask() {
        if (!this.isDone) {
            System.out.println("Tasks.Task has not been started");
            return;
        }
        this.isDone = false;
    }

}
