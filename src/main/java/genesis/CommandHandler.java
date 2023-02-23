package genesis;

import exception.GenesisException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import utility.ConsolePrinter;
import utility.Formatter;

import java.util.ArrayList;

/**
 * The CommandHandler class handles the different user input commands and executes the necessary actions based on the
 * command.
 * The class provides static methods to handle list tasks, mark task as done, unmark task as undone, add todo,
 * add deadline, add event and delete tasks.
 */
public class CommandHandler {

    protected static void handleListTasks(ArrayList<Task> tasks) throws GenesisException {
        if (tasks.size() < 1) {
            throw new GenesisException("List is empty, nothing to show");
        }

        ConsolePrinter.listTasks(tasks);
    }

    protected static ArrayList<Task> handleMarkTask(String content, ArrayList<Task> tasks) {
        int index = Integer.parseInt(content) - 1;
        Task task = tasks.get(index);
        task.setIsDone(true);

        ConsolePrinter.onTaskMarked(task.getListDescription());

        return tasks;
    }

    protected static ArrayList<Task> handleUnmarkTask(String content, ArrayList<Task> tasks) {
        int index = Integer.parseInt(content) - 1;
        Task task = tasks.get(index);
        task.setIsDone(false);

        ConsolePrinter.onTaskUnmarked(task.getListDescription());

        return tasks;
    }

    protected static ArrayList<Task> handleTodo(String description, ArrayList<Task> tasks) throws GenesisException {
        Todo todo = new Todo(description);
        tasks.add(todo);

        ConsolePrinter.onTaskAdded(todo.getListDescription(), tasks.size());

        return tasks;
    }

    protected static ArrayList<Task> handleDeadline(String content, ArrayList<Task> tasks) throws GenesisException {
        String[] parts = Formatter.deadlineFromatter(content);

        String description = parts[0];
        String by = parts[1];

        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);

        ConsolePrinter.onTaskAdded(deadline.getListDescription(), tasks.size());

        return tasks;
    }

    protected static ArrayList<Task> handleEvent(String content, ArrayList<Task> tasks) throws GenesisException {
        String[] parts = Formatter.eventFormatter(content);

        String description = parts[0];
        String from = parts[1];
        String to = parts[2];

        Event event = new Event(description, from, to);
        tasks.add(event);

        ConsolePrinter.onTaskAdded(event.getListDescription(), tasks.size());

        return tasks;
    }

    protected static ArrayList<Task> handleDelete(String content, ArrayList<Task> tasks) {
        int index = Integer.parseInt(content) - 1;
        Task task = tasks.remove(index);

        ConsolePrinter.onTaskDelete(task.getListDescription(), tasks.size());

        return tasks;
    }
}
