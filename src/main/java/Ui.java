import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

public class Ui {
    public static void printWelcomeMessage() {
        System.out.println("Good day, I'm Thomas Shelby.");
        System.out.println("To what do I owe the pleasure?");
    }

    public static void printTasksMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public static void printToDoMessage(ToDo newToDo) {
        System.out.println("Don't sleep on it.\n" + "added: " + newToDo);
    }

    public static void printDeadlineMessage(Deadline newDeadline) {
        System.out.println("Time is money, chop chop!\n" + "added: " + newDeadline);
    }

    public static void printEventMessage(Event newEvent) {
        System.out.println("I'll see you there.\n" + "added: " + newEvent);
    }

    public static void printMarkTaskMessage(int taskNumber) {
        System.out.println("That was long due, well done.\n" + TaskManager.taskManager.get(taskNumber));
    }

    public static void printUnmarkTaskMessage(int taskNumber) {
        System.out.println("You've gotten lazy.\n" + TaskManager.taskManager.get(taskNumber));
    }

    public static void printDeleteTaskMessage(Task taskToDelete) {
        System.out.println("That's off the list: \n" + taskToDelete);
        System.out.println("You're left with " + TaskManager.taskManager.size() + " task(s).");
    }

    public static void printArrayIndexOutOfBoundsExceptionErrorMessage(ArrayIndexOutOfBoundsException e) {
        System.out.println("Something's wrong: " + e);
        System.out.println("You probably didn't include the task or the timeframe.");
    }

    public static void printIncompleteTaskExceptionErrorMessage(IncompleteTaskException e) {
        System.out.println("Don't know what that means comrade.");
        System.out.println("Refer to the `Usage` section in the 'README.md' file for valid commands.");
    }
}
