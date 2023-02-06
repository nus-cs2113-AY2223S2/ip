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

    public static void listTask() {
        if (taskList[0] == null) {
            System.out.println("You have no tasks in your list yet!");
            return;
        }

        int existingTaskCount = 1;
        for (Task item : taskList) {
            if (item != null) {
                System.out.println(existingTaskCount + ". " + item.describeTask());
                existingTaskCount++;
            } else {
                continue;
            }
        }

        System.out.println("\nYou have " + (existingTaskCount - 1) + " tasks in the list.\n");
    }

    public static void markTask(Task[] list, int index) {
        list[index - 1].markAsDone();
        System.out.println("Nice! I've marked this task as done:\n" + list[index - 1].describeTask() + "\n");
    }

    public static void unmarkTask(Task[] list, int index) {
        list[index - 1].unmarkAsDone();
        System.out.println("OK, Ive marked this task as not done yet:\n" + list[index - 1].describeTask() + "\n");
    }

}
