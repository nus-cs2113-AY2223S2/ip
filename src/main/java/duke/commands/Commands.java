package duke.commands;

import duke.exception.TodoException;
import duke.task.Task;
import duke.taskTypes.Deadline;
import duke.taskTypes.Event;
import duke.taskTypes.Todo;

import java.util.ArrayList;

import static duke.print.Print.*;

/**
 * Collection of Commands to be used in the Duke programme.
 * These include:
 * --> Adding todo, deadline and event task types.
 * --> Deleting a task from the list of tasks.
 * --> Marking / Unmarking a task as done.
 * --> Finding tasks that contain a keyword from the user.
 */
public class Commands {
    /**
     * Method to find tasks in the task list that contain a particular keyword.
     *
     * @param taskList The list to find tasks that contain the keyword.
     * @param keyword  The keyword to search for.
     */
    public static void findTasks(ArrayList<Task> taskList, String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }

        printFoundTasks(foundTasks);
    }

    /**
     * Method to delete one task from the tasks list.
     *
     * @param taskList   The list to delete one task from.
     * @param taskNumber The task to delete
     */
    public static void deleteOneTask(ArrayList<Task> taskList, String taskNumber) {
        int taskNumberToDelete = Integer.parseInt(taskNumber);
        taskNumberToDelete -= 1;

        Task selectedTask = taskList.get(taskNumberToDelete);
        taskList.remove(taskNumberToDelete);

        printDeletingOneTask(taskList, selectedTask);
    }

    /**
     * Creates a new task, classified as a todo task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    public static void addTodo(ArrayList<Task> taskList, String[] userInput) throws TodoException {
        Todo new_todo = new Todo(userInput[1]);
        taskList.add(new_todo);

        printAddingOneTask(new_todo, taskList);
    }

    /**
     * Creates a new task, classified as an event task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    public static void addEvent(ArrayList<Task> taskList, String[] userInput) {
        String[] eventDetails = userInput[1].split(" /from | /to ");

        Event new_event = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);

        taskList.add(new_event);

        printAddingOneTask(new_event, taskList);
    }

    /**
     * Creates a new task, classified as a deadline task
     *
     * @param taskList  The list to insert the task into
     * @param userInput The details of the task to be added
     */
    public static void addDeadline(ArrayList<Task> taskList, String[] userInput) {
        String[] taskDetails = userInput[1].split(" /by ", 2);

        String taskName = taskDetails[0];
        String taskDueDate = taskDetails[1];

        Deadline new_deadline = new Deadline(taskName, taskDueDate);

        taskList.add(new_deadline);

        printAddingOneTask(new_deadline, taskList);
    }

    /**
     * Sets a specified task as not done
     *
     * @param taskList           The list of tasks that contains the task that needs to be marked as not done
     * @param taskNumberToUnmark The number of the task to mark as not done
     */
    public static void unmarkSelectedTask(ArrayList<Task> taskList, int taskNumberToUnmark) {
        taskNumberToUnmark -= 1;
        Task selectedTask = taskList.get(taskNumberToUnmark);
        selectedTask.markNotDone();

        printOneLine();
        println("     OK, I've marked this task as not done yet:");

        printMarkingOrUnmarkingOneTask(selectedTask);
    }

    /**
     * Sets a specified task as done
     *
     * @param taskList         The list of tasks that contains the task that needs to be marked as done
     * @param taskNumberToMark The number of the task to mark as done
     */
    public static void markSelectedTask(ArrayList<Task> taskList, int taskNumberToMark) {
        taskNumberToMark -= 1;
        Task selectedTask = taskList.get(taskNumberToMark);
        selectedTask.markDone();

        printOneLine();
        println("     Nice! I've marked this task as done:");

        printMarkingOrUnmarkingOneTask(selectedTask);
    }
}
