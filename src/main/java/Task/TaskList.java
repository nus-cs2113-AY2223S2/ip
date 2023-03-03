package Task;

import java.io.IOException;
import java.util.ArrayList;
import Storage.DukeStorage;

public class TaskList {
    // array list of tasks
    private ArrayList<Task> tasks;

    /**
     * Constructor
     * 
     * @param storage the storage file
     * @throws IOException
     * 
     */
    public TaskList(DukeStorage storage) throws IOException {
        tasks = storage.loadTaskList();
    }

    /**
     * add task to the list
     *
     * @param task task to be added
     * 
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * remove task from the list
     *
     * @param task task to be removed
     * 
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * get the list of tasks
     *
     * @return the list of tasks
     * 
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * print the list of tasks
     *
     * @param tasks the list of tasks
     * @return the list of tasks in string format
     */
    public static String printTasksList(ArrayList<Task> tasks) {
        String tasksList = new String();
        int count = 1;
        for (Task i : tasks) {
            tasksList += count + ". " + i.toString();
            if (count < Task.numberOfTasks) {
                tasksList += System.lineSeparator();
            }
            count++;
        }
        return tasksList;
    }

    /**
     * find all tasks containing a given string
     *
     * @param tasks the list of tasks
     * @param input the string to search
     * @return the list of tasks containing the given string
     * 
     */
    public static String findList(ArrayList<Task> tasks, String input) {
        String findList = new String();
        int count = 1;
        for (Task i : tasks) {
            if (i.toString().contains(input)) {
                findList += count + ". " + i.toString();
                findList += System.lineSeparator();
                count++;
            }
        }
        return findList;
    }
}