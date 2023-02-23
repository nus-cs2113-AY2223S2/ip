package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList class contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    /**
     * ArrayList of tasks where all the tasks will be stored by Duke
     */
    public ArrayList<Task> tasks;

    /**
     * Constructor of the TaskList where it connects the ArrayList of tasks to database's ArrayList of tasks
     * that have been loaded beforehand when initialisation occurs
     *
     * @param database
     */
    public TaskList(ArrayList<Task> database) {
        tasks = database;
    }

    /**
     * Deletes task from the TaskList
     *
     * @param deleteIndex the index of the Task to be deleted
     */
    public void deleteTaskFromTaskList(int deleteIndex) {
        tasks.remove(deleteIndex);
    }

    /**
     * Adds a new task to the TaskList
     *
     * @param task the Task object to be newly added to the back of the TaskList
     */
    public void addTaskToTaskList(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the Task from the given index of the TaskList
     *
     * @param index the index of the TaskList that contains the Task the user wants
     * @return the Task object of said index
     */
    public Task getTaskFromIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the total size of the ArrayList of tasks
     *
     * @return the total number of tasks stored in the ArrayList
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Prints all the tasks that description contains the keyword and their index
     *
     * @param keyword the search word the user inputs
     */
    public void printTasksByKeyword(String keyword) {
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                System.out.println((i+1) + "." + tasks.get(i));
            }
        }
    }

}
