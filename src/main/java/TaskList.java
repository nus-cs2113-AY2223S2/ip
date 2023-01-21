import java.util.Arrays;

public class TaskList {
    private final String[] tasks = new String[100];
    private int numberOfTasks = 0;

    /**
     * Print the contents of tasks
     */
    public void printContents() {
        System.out.println("Your tasks:");

        int number = 1;
        for (String task : Arrays.copyOf(tasks, numberOfTasks)) {
            System.out.printf("%d. %s\n", number, task);
            number++;
        }
    }

    /**
     * Adds a String task to the list of strings.
     * @param task input string to be added
     */
    public void addTask(String task) {
        tasks[numberOfTasks] = task;
        numberOfTasks++;
    }
}
