package duke;

/**
 * Class used to handle output to the user. Contains a collection of methods used to print to standard output.
 */
public class Ui {

    static final String LINE = "_".repeat(60);

    /**
     * Encapsulates print statements for whenever a task is added
     * (as either a todo, deadline, or event).
     * 
     * @param task     Reference to task that is added.
     * @param numTasks Total number of tasks in the list.
     *
     */
    public void printAddTaskConfirmation(Task task, int numTasks) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:\n" + task + "\nNow you have " + numTasks + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints any message, correctly separated by horizontal lines.
     *
     * @param message Message to be printed.
     */
    public void printSimpleMessage (String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    /**
     * Prints any message followed by a corresponding task, correctly separated by horizontal lines.
     *
     * @param message Message to be printed.
     * @param task Task to be printed.
     */
    public void printMessageWithTask (String message, Task task) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(task);
        System.out.println(LINE);
    }

    /**
     * Prints a list of all tasks currently in the list.
     *
     * @param tasks TaskList to print list for.
     */
    public void printList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.length(); i++) {
            try {
                System.out.println(Integer.toString(i + 1) + "." + tasks.getTask(i));
            } catch (IncorrectIndexException e) {
                //Shouldn't happen since i is alway within valid limits due to for loop constraints
                throw new RuntimeException ("Unexpected Error While Listing.");
            }
        }
        System.out.println(LINE);
    }

    /**
     * Searches through TaskList for any Tasks that match with keyword and prints only those Tasks.
     *
     * @param tasks TaskList to search through.
     * @param keyword Keyword to search for.
     */
    public void printMatches(TaskList tasks, String keyword) {
        System.out.println(LINE);
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.length(); i++) {
            Task task;
            
            try {
                task = tasks.getTask(i);
            } catch (IncorrectIndexException e) {
                //Shouldn't happen since i is alway within valid limits due to for loop constraints
                throw new RuntimeException ("Unexpected Error While Listing.");
            }

            if(task.matchesKeyword(keyword)) {
                System.out.println(Integer.toString(i + 1) + "." + task);
            }
        }
        System.out.println(LINE);
    }
}
