import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

public class Ui {
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
}
