package duke;

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

    public void printSimpleMessage (String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    public void printMessageWithTask (String message, Task task) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(task);
        System.out.println(LINE);
    }

    public void printList(TaskList tasks) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.length(); i++) {
            try {
                System.out.println(Integer.toString(i + 1) + "." + tasks.getTask(i));
            } catch (IncorrectIndexException e) {
                //Shouldn't happen since i is alway within valid limits due to for loop constraints
                System.out.println("Unexpected Error While Listing.");
            }
        }
        System.out.println(LINE);
    }
}
