package duke;

import duke.exceptions.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;


import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

/**
 * Contains the list of <code>Task</code>s which consists of <code>Todo</code>, <code>Deadline</code> and
 * <code>Event</code> <code>Task</code>s.
 * Also contains the operations to manage the list of <code>Task</code>s.
 */
public class TaskList implements java.io.Serializable {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public Task getLatestTask() {
        return tasks.get(tasks.size() - 1);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }


    /**
     * Adds a <code>Todo</code> <code>Task</code> to the <code>TaskList</code>.
     *
     * @param taskName The name of the <code>Todo</code> <code>Task</code>.
     */
    public void addToDo(String taskName) {
        tasks.add(new Todo(taskName));
    }

    /**
     * Adds a <code>Deadline</code> <code>Task</code> to the <code>TaskList</code>.
     *
     * @param taskDetails The details of the <code>Deadline</code> <code>Task</code>.
     * @throws InvalidCommandException If the deadline is of an invalid format.
     */
    public void addDeadline(String taskDetails) throws InvalidCommandException {
        int byIndex = taskDetails.toLowerCase().indexOf(" /by ");
        if (byIndex == -1) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, byIndex);
        String dueDate = taskDetails.substring(byIndex + 5); // rest of string after " /by "
        tasks.add(new Deadline(taskName, dueDate));
    }

    /**
     * Adds a <code>Event</code> <code>Task</code> to the <code>TaskList</code>.
     *
     * @param taskDetails The details of the <code>Event</code> <code>Task</code>.
     * @throws InvalidCommandException If the event is of an invalid format.
     */
    public void addEvent(String taskDetails) throws InvalidCommandException {
        int fromIndex = taskDetails.toLowerCase().indexOf(" /from ");
        int toIndex = taskDetails.toLowerCase().indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new InvalidCommandException();
        }
        String taskName = taskDetails.substring(0, fromIndex);
        String startTime = taskDetails.substring(fromIndex + 7, toIndex);
        String endTime = taskDetails.substring(toIndex + 5);
        tasks.add(new Event(taskName, startTime, endTime));
    }

    /**
     * Returns the <code>Task</code> which is to be deleted.
     *
     * @param index The index of the <code>Task</code> which is to be deleted.
     * @return The deleted <code>Task</code>.
     */
    public Task deleteTask(int index) {
        Task deletedTask = tasks.get(index);
        tasks.remove(index);
        return deletedTask;
    }

    /**
     * Returns the filtered list of tasks matching the filterString.
     * Uses stream to filter the list of <code>Task</code>s to just the tasks matching the filterString.
     *
     * @param filterString The string to filter the <code>Task</code> names by.
     * @return The filtered list.
     */
    public ArrayList<Task> filterTaskList(String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter(t -> t.getTaskName().contains(filterString))
                .collect(toList());

        return filteredList;

    }


}
