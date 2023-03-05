package duke.entity;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Contains the task list with operations to manipulate tasks in the list
 */
public class TaskList {
    private ArrayList<Task> taskArrayList;

    public TaskList() {}

    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    public Task getTask(int index) {
        return this.taskArrayList.get(index);
    }

    public void addTask(Task task) {
        taskArrayList.add(task);
    }

    public int taskSize() {
        return taskArrayList.size();
    }

    public void removeTask(int index) {
        this.taskArrayList.remove(index);
    }

    /**
     * Searches for tasks in task list that matches a given keyword
     *
     * @param keyword string to search for match
     * @return contains the found/matched tasks
     */
    public ArrayList<Task> findTaskArrayList(String keyword) {
        ArrayList<Task> tempTaskArrayList = new ArrayList<Task>();
        for (Task task : taskArrayList) {
            if (task.getDescription().matches("(.*)" + keyword + "(.*)")) {
                tempTaskArrayList.add(task);
            }
        }
        return tempTaskArrayList;
    }
}
