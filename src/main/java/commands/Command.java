package commands;

import io.DukeNUSPrinter;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;

import static java.util.stream.Collectors.toCollection;

public class Command {
    /**
     * @param tasks The master ArrayList of all tasks.
     * @param searchPrompt The string to be searched within the description of each task. This is case-sensitive.
     * @return A filtered ArrayList of tasks with the searchPrompt as a substring in its description.
     */
    public static ArrayList<Task> getFoundTasks(ArrayList<Task> tasks, String searchPrompt) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(searchPrompt))
                .collect(toCollection(ArrayList::new));
    }
    /**
     * @param todo A newly constructed todo object as a child of Task that has a user-defined description.
     */
    public static void addTodo(ArrayList<Task> tasks, Todo todo) {
        tasks.add(todo);
        DukeNUSPrinter.printAddedTask(todo.getTaskString(), tasks.size());
    }
    /**
     * @param deadline A newly constructed deadline object as a child of Task that has a user-defined description
     *                 and due date.
     */
    public static void addDeadline(ArrayList<Task> tasks, Deadline deadline) {
        tasks.add(deadline);
        DukeNUSPrinter.printAddedTask(deadline.getTaskString(), tasks.size());
    }
    /**
     * @param event A newly constructed event object as a child of Task that has a user-defined description,
     *              from date, and to date.
     */
    public static void addEvent(ArrayList<Task> tasks, Event event) {
        tasks.add(event);
        DukeNUSPrinter.printAddedTask(event.getTaskString(), tasks.size());
    }

    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will remove the task from the array.
     */
    public static void deleteTask(ArrayList<Task> tasks, int taskIndex) {
        DukeNUSPrinter.printDeletedTask(tasks.get(taskIndex - 1).getTaskString());
        tasks.remove(taskIndex - 1);
    }
    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will cause the isDone property of the
     *                  task to be true.
     */
    public static void markTask(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex - 1).setDone(true);
        DukeNUSPrinter.printMarkedTask(tasks.get(taskIndex - 1).getTaskString());
    }

    /**
     * @param taskIndex The 1-based index of the task the user is referring to. Will cause the isDone property of the
     *                  task to be false.
     */
    public static void unmarkTask(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex - 1).setDone(false);
        DukeNUSPrinter.printUnmarkedTask(tasks.get(taskIndex - 1).getTaskString());
    }
}
