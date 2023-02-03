public class TaskManager {
    private static Task[] taskList = new Task[100];
    private static int taskCount = 0;

    public static Task[] getTaskList() {
        return taskList;
    }

    public static void addTask(Task t) {
        taskList[taskCount] = t;
        upTaskCount();
    }

    public static void removeTask(int index) {
        taskList[index] = null;
        downTaskCount();
    }

    public static void upTaskCount() {
        taskCount++;
    }

    public static void downTaskCount() {
        taskCount--;
    }

    public static int getTaskCount() {
        return taskCount;
    }

}
