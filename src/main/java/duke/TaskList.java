package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

/**
 * A list of Task objects representing the current list of tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets the task list.
     */
    public ArrayList<Task> getTasksList() {
        return this.tasks;
    }

    /**
     * Adds the given task to the task list.
     *
     * @param task The task to be added to the task list.
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    // customized task addition after parsing the input

    /**
     * Adds a new event task, with the task name, start time and end time provided by the user, to the task list.
     *
     * @param informationNeededForPerformingUserRequest An array of strings containing information extracted from
     *                                                  parsing the user's commands. The string at index 1 of this array
     *                                                  contains the task name provided by the user. The string at index
     *                                                  2 of this array contains the start time provided by the user.
     *                                                  The string at index 3 of this array contains the end time
     *                                                  provided by the user.
     */
    public static void addEventTask(String[] informationNeededForPerformingUserRequest) {
        tasks.add(new Event(informationNeededForPerformingUserRequest[1],
                informationNeededForPerformingUserRequest[2],
                informationNeededForPerformingUserRequest[3]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "event", tasks.size());
    }

    /**
     * Adds a new deadline task, with the task name and deadline provided by the user, to the task list.
     *
     * @param informationNeededForPerformingUserRequest An array of strings containing information extracted from
     *                                                  parsing the user's commands. The string at index 1 of this array
     *                                                  contains the task name provided by the user. The string at index
     *                                                  2 of this array contains the deadline provided by the user.
     */
    public static void addDeadlineTask(String[] informationNeededForPerformingUserRequest) {
        tasks.add(new Deadline(informationNeededForPerformingUserRequest[1],
                informationNeededForPerformingUserRequest[2]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "deadline", tasks.size());
    }

    /**
     * Adds a new todo task, with the task name provided by the user, to the task list.
     *
     * @param informationNeededForPerformingUserRequest An array of strings containing information extracted from
     *                                                  parsing the user's commands. The string at index 1 of this array
     *                                                  contains the task name provided by the user.
     */
    public static void addToDoTask(String[] informationNeededForPerformingUserRequest) {
        tasks.add(new ToDo(informationNeededForPerformingUserRequest[1]));
        Ui.printNotification(tasks.get(tasks.size() - 1), "todo", tasks.size());
    }

    /**
     * Marks the task, at the index provided by the user, in the task list as undone.
     *
     * @param informationNeededForPerformingUserRequest An array of strings containing information extracted from
     *                                                  parsing the user's commands. The string at index 1 of this array
     *                                                  contains the index provided by the user.
     */
    public static void unmarkTask(String[] informationNeededForPerformingUserRequest) {
        int indexToBeUnmarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        tasks.get(indexToBeUnmarked).setDone(false);
        Ui.printNotification(tasks.get(indexToBeUnmarked), "unmark", tasks.size());
    }

    /**
     * Marks the task, at the index provided by the user, in the task list as done.
     *
     * @param informationNeededForPerformingUserRequest An array of strings containing information extracted from
     *                                                  parsing the user's commands. The string at index 1 of this array
     *                                                  contains the index provided by the user.
     */
    public static void markTask(String[] informationNeededForPerformingUserRequest) {
        int indexToBeMarked = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        tasks.get(indexToBeMarked).setDone(true);
        Ui.printNotification(tasks.get(indexToBeMarked), "mark", tasks.size());
    }

    /**
     * Deletes the task, at the index provided by the user, from the task list.
     *
     * @param informationNeededForPerformingUserRequest An array of strings containing information extracted from
     *                                                  parsing the user's commands. The string at index 1 of this array
     *                                                  contains the index provided by the user.
     */
    public static void deleteTask(String[] informationNeededForPerformingUserRequest) {
        int indexToRemove = Integer.parseInt(informationNeededForPerformingUserRequest[1]) - 1; // 0-indexed
        Ui.printNotification(tasks.get(indexToRemove), "delete", tasks.size() - 1);
        tasks.remove(indexToRemove);
    }

    /**
     * Finds and lists all tasks in the task list that contains keywords provided by the user.
     *
     * @param informationNeededForPerformingUserRequest An array of strings containing information extracted from
     *                                                  parsing the user's commands. The string at index 1 of this array
     *                                                  contains the keywords provided by the user.
     */
    public static void findTask(String[] informationNeededForPerformingUserRequest) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.getTaskName().contains(informationNeededForPerformingUserRequest[1])) {
                tasksWithKeyword.add(task);
            }
        }
        if (tasksWithKeyword.isEmpty()) {
            System.out.println("There were no matching tasks in your list.");
            return;
        }
        Ui.listTasks(tasksWithKeyword, "find");
    }
}