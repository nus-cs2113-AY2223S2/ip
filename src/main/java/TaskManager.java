import java.util.ArrayList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import static java.util.stream.Collectors.toList;

/**
 * This <code>TaskManager</code> class is used to manage a list of <code>Task</code> objects.
 * It provides methods to list, add, mark, unmark, delete, and find tasks by keyword.
 *
 * @version v0.2
 * @since 2023-02-24
 * @see ThomasShelby
 * @see Ui
 */
public class TaskManager {
    static ArrayList<Task> taskManager = new ArrayList<>();

    /**
     * Prints a list of tasks to the console.
     *
     * @param tasksToPrint the list of tasks to print
     */
    public static void listTasks(ArrayList<Task> tasksToPrint) {
        Ui.printTasksMessage();
        for (int i = 0; i < tasksToPrint.size(); i++) {
            System.out.println((i + 1) + ". "
                    + tasksToPrint.get(i));
        }
    }

    /**
     * Adds a new <code>todo</code> task to the task manager.
     *
     * @param parsedCommand an array of strings containing the user's command (<code>todo</code>) and task description
     */
    public static void addToDo(String[] parsedCommand) {
        ToDo newToDo = new ToDo(parsedCommand[1]);
        taskManager.add(newToDo);
        Ui.printToDoMessage(newToDo);
    }

    /**
     * Adds a new <code>deadline</code> task to the task manager.
     *
     * @param parsedCommand an array of strings containing the user's command (<code>deadline</code>) and task
     *                      description with deadline
     */
    public static void addDeadline(String[] parsedCommand) {
        String[] taskAndDeadline = parsedCommand[1].split("/", 2);
        Deadline newDeadline = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        taskManager.add(newDeadline);
        Ui.printDeadlineMessage(newDeadline);
    }

    /**
     * Adds a new <code>event</code> task to the task manager.
     *
     * @param parsedCommand an array of strings containing the user's command (<code>event</code>) and task description
     *                      with event timing
     */
    public static void addEvent(String[] parsedCommand) {
        String[] taskAndDuration = parsedCommand[1].split("/");
        Event newEvent = new Event(taskAndDuration[0], taskAndDuration[1], taskAndDuration[2]);
        taskManager.add(newEvent);
        Ui.printEventMessage(newEvent);
    }

    /**
     * <code>mark</code> a task in the task manager as done.
     *
     * @param parsedCommand an array of strings containing the user's command (<code>mark</code>) and task number
     */
    public static void markTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(taskNumber).setIsDone(true);
        Ui.printMarkTaskMessage(taskNumber);
    }

    /**
     * <code>unmark</code> a task in the task manager as done.
     *
     * @param parsedCommand an array of strings containing the user's command (<code>unmark</code>) and task number
     */
    public static void unmarkTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(taskNumber).setIsDone(false);
        Ui.printUnmarkTaskMessage(taskNumber);
    }

    /**
     * <code>delete</code> a task in the task manager as done.
     *
     * @param parsedCommand an array of strings containing the user's command (<code>delete</code>) and task number
     */
    public static void deleteTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        Task taskToDelete = taskManager.get(taskNumber); // store in temp var to print later
        taskManager.remove(taskNumber);
        Ui.printDeleteTaskMessage(taskToDelete);
    }

    /**
     * <code>find</code> a task in the task manager as done.
     *
     * @param parsedCommand an array of strings containing the user's command (<code>find</code>) and task description
     */
    public static ArrayList<Task> findTask(String[] parsedCommand) {
        return (ArrayList<Task>) taskManager.stream()
                .filter(task -> task.getDescription().contains(parsedCommand[1]))
                .collect(toList());
    }
}
