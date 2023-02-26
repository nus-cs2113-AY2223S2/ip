package app.tasks;

import app.exceptions.DukeException;
import app.exceptions.InvalidTaskException;

import java.util.ArrayList;

/**
 * Class to store the current tasks and useful functions related to the Task-list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor to initialise a new Task-list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor to initialise a new Task-list given an existing
     * ArrayList with Tasks.
     * @param tasks An ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        setTasks(tasks);
    }

    private void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Getter to obtain all the existing Tasks.
     * @return An ArrayList containing all the existing Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns a specific Task using a given index.
     * @param index The index of the Task that needs to be obtained.
     * @return The specified Task
     * @throws DukeException If index is outside the range of the current valid indexes.
     */
    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException();
        }
    }

    /**
     * Method to add a Task to the existing Task-list.
     * @param task The Task to be added to the Task-list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Method to get the current number of Tasks.
     * @return The size of the current Task-list.
     */
    public int getTasksCount() {
        return tasks.size();
    }

    /**
     * Method to remove a Task from the existing Task-list.
     * @param index The index of the Task to be deleted from the Task-list.
     * @return The Task that was deleted from the Task-list.
     * @throws DukeException If index is outside the range of the current valid indexes.
     */
    public Task deleteTask(int index) throws DukeException{
        Task taskToDelete = getTask(index - 1);
        return tasks.remove(index - 1);
    }

    /**
     * Method to mark a Task status.
     * @param index The index of the Task to be marked as done.
     * @return The Task that was marked as done.
     * @throws DukeException If index is outside the range of the current valid indexes.
     */
    public Task markTask(int index) throws DukeException {
        Task markedTask = getTask(index - 1);
        markedTask.setDone(true);
        return markedTask;
    }

    /**
     * Method to unmark a Task status.
     * @param index The index of the Task to be marked as not done.
     * @return The Task that was marked as not done.
     * @throws DukeException If index is outside the range of the current valid indexes.
     */
    public Task unmarkTask(int index) throws DukeException {
        Task unmarkedTask = getTask(index - 1);
        unmarkedTask.setDone(false);
        return unmarkedTask;
    }

    /**
     * Method to get all the Tasks in the existing Task-list that contain a
     * user-specified keyword in their Task description.
     * @param tasks The Task-list containing all the Tasks from the most recent iteration of Duke.
     * @param keyword The user-specified keyword used for filtering.
     * @return An ArrayList containing all the Tasks whose task description contains the keyword.
     */
    public ArrayList<Task> getTasksWithKeyword(TaskList tasks, String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t.getTaskDescription().toLowerCase().contains(keyword.toLowerCase())) {
                tasksWithKeyword.add(t);
            }
        }
        return tasksWithKeyword;
    }
}
