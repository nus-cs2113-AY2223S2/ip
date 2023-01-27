import java.util.Arrays;

public class TaskList {
    private final Task[] tasks = new Task[100];
    private static int numberOfTasks = 0;

    /**
     * Print the contents of tasks
     */
    public void printContents() {
        System.out.println("Your tasks:");

        int number = 1;
        for (Task task : Arrays.copyOf(tasks, numberOfTasks)) {
            System.out.printf("%d. %s\n", number, task.getTaskListing());
            number++;
        }
    }

    /**
     * Adds a String task to the list of strings.
     * @param taskName: Name of task, String to be added
     */
    public void addTask(String taskName) {
        tasks[numberOfTasks] = new Task(taskName);
        numberOfTasks++;
    }

    // TODO: think about if you should combine both methods into one or not.
    // -1 to account for zero-indexing.
    public void markAsDone(int taskNumber) {
        tasks[taskNumber - 1].markAsDone();
        System.out.print(Message.taskDone);
        System.out.println(tasks[taskNumber-1].getTaskListing());
    }

    public void markAsUndone(int taskNumber) {
        tasks[taskNumber - 1].markAsUndone();
        System.out.print(Message.taskUndone);
        System.out.println(tasks[taskNumber-1].getTaskListing());
    }

    // Getter for number of task
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }
}
