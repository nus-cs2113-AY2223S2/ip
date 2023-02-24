import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

/**
 * The <code>Ui</code> class contains methods to print various messages to the console for user interaction
 *It provides messages for the welcome screen, listing, adding, marking, unmarking, deletion,
 * and error handling for ArrayIndexOutOfBoundsException and IncompleteTaskException
 * @version v0.2
 * @since 2023-02-24
 * @see TaskManager
 * @see Task
 * @see ToDo
 * @see Deadline
 * @see Event
 */
public class Ui {
    /**
    * Prints a welcome message to the console when the program is started
    */
    public static void printWelcomeMessage() {
        System.out.println("Good day, I'm Thomas Shelby.");
        System.out.println("To what do I owe the pleasure?");
    }

    /**
     * Prints a message to the console before all the tasks in the <code>TaskManager</code> ArrayList are listed
     * @see TaskManager
     */
    public static void printTasksMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    /**
     * Prints a message to the console when a new <code>ToDo</code> task is added to the <code>TaskManager</code>
     *
     * @param newToDo the newly added <code>ToDo</code> task
     */
    public static void printToDoMessage(ToDo newToDo) {
        System.out.println("Don't sleep on it.\n" + "added: " + newToDo);
    }

    /**
     * Prints a message to the console when a new <code>Deadline</code> task is added to the <code>TaskManager</code>
     *
     * @param newDeadline the newly added <code>Deadline</code> task
     */
    public static void printDeadlineMessage(Deadline newDeadline) {
        System.out.println("Time is money, chop chop!\n" + "added: " + newDeadline);
    }

    /**
     * Prints a message to the console when a new <code>Event</code> task is added to the <code>TaskManager</code>
     *
     * @param newEvent the newly added <code>Event</code> task
     */
    public static void printEventMessage(Event newEvent) {
        System.out.println("I'll see you there.\n" + "added: " + newEvent);
    }

    /**
     * Prints a message and the marked task to the console when a task is marked as done
     *
     * @param taskNumber the index of the task in the <code>TaskManager</code>
     */
    public static void printMarkTaskMessage(int taskNumber) {
        System.out.println("That was long due, well done.\n" + TaskManager.taskManager.get(taskNumber));
    }

    /**
     * Prints a message and the unmarked task to the console when a task is marked as undone
     *
     * @param taskNumber the index of the task in the <code>TaskManager</code>
     */
    public static void printUnmarkTaskMessage(int taskNumber) {
        System.out.println("You've gotten lazy.\n" + TaskManager.taskManager.get(taskNumber));
    }

    /**
     * Prints a message, the task to delete, and the number of remaining tasks to the console when a task is deleted
     * from the <code>TaskManager</code>
     *
     * @param taskToDelete the task that was deleted
     */
    public static void printDeleteTaskMessage(Task taskToDelete) {
        System.out.println("That's off the list: \n" + taskToDelete);
        System.out.println("You're left with " + TaskManager.taskManager.size() + " task(s).");
    }

    /**
     * Prints an error message to the console when an ArrayIndexOutOfBoundsException occurs
     *
     * @param e the exception thrown
     */
    public static void printArrayIndexOutOfBoundsExceptionErrorMessage(ArrayIndexOutOfBoundsException e) {
        System.out.println("Something's wrong: " + e);
        System.out.println("You probably didn't include the task or the timeframe.");
    }

    /**
     * Prints an error message to the console when an IncompleteTaskException occurs
     */
    public static void printIncompleteTaskExceptionErrorMessage() {
        System.out.println("Don't know what that means comrade.");
        System.out.println("Refer to the `Usage` section in the 'README.md' file for valid commands.");
    }
}
