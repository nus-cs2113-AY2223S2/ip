package duke.data;

import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.task.Task;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) throws DukeException {
        this.taskList = tasks;
    }

    public ArrayList<Task> getReadableList() {
        return this.taskList;
    }


    public int size() {
        return taskList.size();
    }

    /**
     * Overloaded: Adds Deadline to the list to keep track
     *
     * @param task: user task to remember
     */

    public void add(Task task, Storage storage) throws DukeException {
        taskList.add(task);
        storage.writeTasks(taskList);
    }

    public Task markAsDone(int taskIndex, Storage storage) throws DukeException {
        taskList.get(taskIndex).setAsDone();
        storage.writeTasks(taskList);
        return taskList.get(taskIndex);
    }

    /**
     * Set the specified task at the given index to undone
     *
     * @param taskIndex index in which the task is stored in the array
     */
    public Task markAsUndone(int taskIndex, Storage storage) throws DukeException {
        taskList.get(taskIndex).setAsUndone();
        storage.writeTasks(taskList);
        return taskList.get(taskIndex);

    }

    /**
     * Deletes the specified index
     */
    public Task deleteTask(int taskIndex, Storage storage) throws DukeException {
        Task currentTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        storage.writeTasks(taskList);
        return currentTask;
    }


}
