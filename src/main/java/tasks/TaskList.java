package tasks;

import exceptions.TaskListEmptyError;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The <code>TaskList</code> class contains the task list
 * and also keeps track of the number of tasks currently
 * present in the list.
 * <p></p>
 * There are also methods that could
 * modify the task list present in the class.
 */
public class TaskList {

    private final ArrayList<Task> tasksList;
    private int currTaskNumber;

    /**
     * Class constructor with a <code>newTaskList</code>
     * as input to initialize the local <code>taskList</code>
     * and the <code>currTaskNumber</code>.
     *
     * @param newTaskList the task list to be tracked.
     */
    public TaskList(ArrayList<Task> newTaskList) {
        tasksList = newTaskList;
        currTaskNumber = newTaskList.size();
    }

    /**
     * Class constructor without inputs.
     */
    public TaskList(){
        tasksList = new ArrayList<>();
        currTaskNumber = 0;
    }

    /**
     * Returns the local variable <code>taskList</code>.
     *
     * @return the task list stored in the instance of the class.
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Returns the local variable <code>currTaskNumber</code>.
     *
     * @return the current number of tasks in task list.
     */
    public int getCurrTaskNumber() {
        return currTaskNumber;
    }

    /**
     * Adds a new task to the <code>taskList</code> and
     * increases the number of tasks for <code>currentTaskNumber</code>
     *
     * @param task task to be added to the list.
     */
    public void addNewTask(Task task) {
        tasksList.add(task);
        currTaskNumber++;
    }

    /**
     * Removes a new task from the <code>taskList</code> and
     * decreases the number of tasks for <code>currentTaskNumber</code>
     *
     * @param task task to be removed from  the list.
     * @throws TaskListEmptyError if <code>taskList</code> is empty.
     */
    public void deleteTask(int index) throws TaskListEmptyError {
        if (tasksList.isEmpty()) {
            throw new TaskListEmptyError();
        }
        tasksList.remove(index - 1);
        currTaskNumber--;
    }

    /**
     * Returns the latest added task from the <code>taskList</code> and
     *
     * @return the latest task added tp the <code>taskList</code>
     */
    public Task getNewestTask() {
        return tasksList.get(currTaskNumber - 1);
    }

    /**
     * Returns a particular task from <code>taskList</code> given
     * its <code>index</code> in the list as an input.
     *
     * @param index the index of the task in the list.
     * @return the task located at a particular index int the list.
     */
    public Task getTaskFromList(int index) {
        return tasksList.get(index - 1);
    }

    /**
     * Returns true if the particular task at the given index of the
     * <code>taskList</code> has been completed, false otherwise.
     *
     * @param index the index of the task in the list.
     * @return boolean value of whether the particular task has been marked as done or not.
     */
    public boolean checkTaskDone(int index) {
        return tasksList.get(index - 1).getIsDone();
    }

    /**
     * Sets a particular task at a given index in
     * the <code>taskList</code> as completed.
     *
     * @param index the index of the task in the list.
     */
    public void setTaskAsDone(int index) {
        tasksList.get(index - 1).markAsDone();
    }

    /**
     * Sets a particular task at a given index in
     * the <code>taskList</code> as not completed.
     *
     * @param index the index of the task in the list.
     */
    public void setTaskAsUndone(int index) {
        tasksList.get(index - 1).markAsUndone();
    }
}
