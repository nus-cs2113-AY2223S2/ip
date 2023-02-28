package ChatTPG;

import java.util.ArrayList;

/**
 * Class TaskList maintains an ArrayList of tasks and allows for
 * addition, deletion and searching of tasks.
 */
public class TaskList {

    /** List of tasks maintained by ChatTPG */
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /** Total number of tasks currently stored by ChatTPG */
    private static int taskCount = 0;

    /**
     * Creates and returns a ToDo task.
     *
     * @param command User command.
     * @param isDone True if task is done and false otherwise.
     * @return ToDo task created.
     */
    public static ToDo createToDo(String command, boolean isDone) {
        ToDo todo = new ToDo(command.substring(5), isDone);
        return todo;
    }

    /**
     * Creates and returns a Deadline task.
     *
     * @param command User command.
     * @param isDone True if task is done and false otherwise.
     * @param processed True if task is read from saved file and false otherwise.
     * @return Deadline task created.
     * @throws InvalidDateFormat if date is not in the format "YYYY-MM-DD".
     */
    public static Deadline createDeadline(String command, boolean isDone,
                                          boolean processed) throws InvalidDateFormat {
        int separatorIndex = command.indexOf("/by");
        String description = command.substring(9, separatorIndex - 1);
        String by = command.substring(separatorIndex + 5);
        String end = DateTimeParser.processDateTime(by, processed);
        Deadline deadline = new Deadline(description, isDone, end);
        return deadline;
    }

    /**
     Creates and returns an Event task.
     *
     * @param command User command.
     * @param isDone True if task is done and false otherwise.
     * @param processed True if task is read from saved file and false otherwise.
     * @return Event task created.
     * @throws InvalidDateFormat if date is not in the format "YYYY-MM-DD".
     * @throws InvalidStartEnd if start date occurs after end date.
     */
    public static Event createEvent(String command, boolean isDone, boolean processed)
            throws InvalidDateFormat, InvalidStartEnd {
        int fromIndex = command.indexOf("/from");
        int toIndex = command.indexOf("/to");
        String description = command.substring(6, fromIndex - 1);
        String from = command.substring(fromIndex + 7, toIndex - 1);
        String to = command.substring(toIndex + 5);
        String begin = DateTimeParser.processDateTime(from, processed);
        String end = DateTimeParser.processDateTime(to, processed);
        if (!processed) {
            DateTimeParser.verifyStartEnd(from, to);
        }
        Event event = new Event(description, isDone, begin, end);
        return event;
    }

    /**
     * Adds specified task to the list of tasks and updates taskCount and data file accordingly.
     *
     * @param task Task to be added to the list.
     */
    public static void addToList(Task task) {
        tasks.add(task);
        taskCount++;
        Storage.saveData(tasks);
    }

    /**
     * Informs the user of a successful task addition to the list.
     *
     * @param task Task added to list.
     */
    public static void notifyTaskAdded(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        displayNumberOfTasks();
    }

    /**
     * Informs the user of the current number of tasks in the list.
     */
    public static void displayNumberOfTasks() {
        if (taskCount == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.printf("Now you have %d tasks in the list.\n", taskCount);
        }
    }

    /**
     * Marks the specified task as done in the list and updates the data file accordingly.
     *
     * @param taskNumber Index number of task to be marked as done.
     */
    public static void markTask(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber).toString());
        Storage.saveData(tasks);
    }

    /**
     * Marks the specified task as not yet done in the list and updates the data file accordingly.
     *
     * @param taskNumber Index number of task to be marked as not yet done.
     */
    public static void unmarkTask(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber).toString());
        Storage.saveData(tasks);
    }

    /**
     * Removes the specified task from the list and updates the data file accordingly.
     *
     * @param taskNumber Index number of task to be removed from the list.
     */
    public static void deleteTask(int taskNumber) {
        String description = tasks.get(taskNumber).toString();
        tasks.remove(taskNumber);
        System.out.println("Noted. I've removed this task:");
        System.out.println(description);
        taskCount--;
        displayNumberOfTasks();
        Storage.saveData(tasks);
    }

    /**
     * Lists the current tasks in the list.
     */
    public static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int idx = 0; idx < taskCount; idx++) {
            System.out.println(tasks.get(idx).toString());
        }
    }

    /**
     * Filters out tasks in the list with descriptions containing the specified keyword.
     *
     * @param keyword Keyword specified to be used for filtering tasks in the list.
     */
    public static void findTasks(String keyword) {
        int match_count = 0;

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                match_count++;
                if (match_count == 1) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println(match_count + "." + task.toString());
            }
        }

        if (match_count == 0) {
            System.out.println("Here are no matching tasks in your list!");
        }
    }
}
