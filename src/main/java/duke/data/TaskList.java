package duke.data;

import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Task;

import java.util.ArrayList;
import java.util.Comparator;


/**
 * TaskList that contains list of user tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor to create and set a new empty taskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor to set the taskList according to populated json file.
     *
     * @param tasks tasks in the json file.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    /**
     * Gives a redundant copy of the taskList.
     *
     * @return the current taskList.
     */
    public ArrayList<Task> getReadableList() {
        return this.taskList;
    }

    /**
     * Gives the current size of the taskList.
     *
     * @return Size of the taskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds the task to the list to keep track.
     *
     * @param task:   User task to remember.
     * @param storage Handler to read write to json file.
     */
    public void add(Task task, Storage storage) throws DukeException {
        taskList.add(task);
        storage.writeTasks(taskList);
    }

    /**
     * Marks the task corresponding to task index as done.
     *
     * @param taskIndex Task to be marked as done.
     * @param storage   Handler to read write to json file.
     * @return The task that has just been marked as done.
     * @throws DukeException Occurs when there is a write error.
     */
    public Task markAsDone(int taskIndex, Storage storage) throws DukeException {
        taskList.get(taskIndex).setAsDone();
        storage.writeTasks(taskList);
        return taskList.get(taskIndex);
    }

    /**
     * Sets the specified task at the given index to undone.
     *
     * @param taskIndex Index in which the task is stored in the array.
     * @param storage   Handler to read write to json file.
     */
    public Task markAsUndone(int taskIndex, Storage storage) throws DukeException {
        taskList.get(taskIndex).setAsUndone();
        storage.writeTasks(taskList);
        return taskList.get(taskIndex);

    }

    /**
     * Deletes the specified index.
     *
     * @param taskIndex Task index corresponding to task to delete.
     * @param storage   Handler to read write to json file.
     */
    public Task deleteTask(int taskIndex, Storage storage) throws DukeException {
        Task currentTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        storage.writeTasks(taskList);
        return currentTask;
    }

    /**
     * Compares tasks based on datetime.
     */
    public static class CustomComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            return task1.getEndTime().compareTo(task2.getEndTime());
        }
    }

    /**
     * Sorts the current task list according to datetime.
     */
    public void sortTaskList() {
        taskList.sort(new CustomComparator());
    }

    /**
     * Saves the current taskList to storage.
     *
     * @param storage Handler to save to json file.
     * @throws DukeException Occurs when there is a write error.
     */
    public void saveList(Storage storage) throws DukeException {
        storage.writeTasks(taskList);
    }

}
