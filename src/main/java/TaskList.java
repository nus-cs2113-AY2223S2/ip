import java.util.Arrays;

public class TaskList {
    private static final Task[] tasks = new Task[100];
    private static int numberOfTasks = 0;

    /**
     * Print the contents of Task List
     */
    public static String getTaskListString() {
        String output = "Your Tasks: \n";
        for (Task task : Arrays.copyOf(tasks, numberOfTasks)) {
            output += task.getTaskNumber() + task.toString() + '\n';
        }
        return output;
    }

    /**
     * Adds a String task to the list of strings.
     * @param taskName: Name of task, String to be added
     */
    public void addTask(Task task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
    }

    // TODO: think about if you should combine both methods into one or not.
    // -1 to account for zero-indexing.
    public void markAsDone(int taskNumber) {
        tasks[taskNumber - 1].markAsDone();
        System.out.print(Command.MESSAGE_TASK_DONE);
        System.out.println(tasks[taskNumber-1].toString());
    }

    public void markAsUndone(int taskNumber) {
        tasks[taskNumber - 1].markAsUndone();
        System.out.print(Command.MESSAGE_TASK_UNDONE);
        System.out.println(tasks[taskNumber-1].toString());
    }

    // Getter for number of task
    public static int getNumberOfTasks() {
        return numberOfTasks;
    }

    // A bit unoptimised, but this is to get the next number for numbering purposes.
    public static int getNextTaskNumber() {
        return numberOfTasks +1;
    }
}
