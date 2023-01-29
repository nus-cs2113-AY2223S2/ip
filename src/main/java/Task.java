public class Task {
    protected String description;
    protected boolean isDone;

    private static Task[] taskList = new Task[100];

    public static Task[] getTaskList() {
        return taskList;
    }

    public static void addTask(Task t) {
        taskList[taskCount] = t;
    }

    public static void removeTask(int index) {
        taskList[index] = null;
    }

    private static int taskCount = 0;

    public static void upTaskCount() {
        taskCount++;
    }

    public static void downTaskCount() {
        taskCount--;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String describeTask() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
