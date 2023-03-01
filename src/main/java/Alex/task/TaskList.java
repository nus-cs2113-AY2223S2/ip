package Alex.task;

import Alex.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> allTasks = new ArrayList<>();

    public void setTask(Task t) {
        allTasks.add(t);
    }

    /**
     * retrieve all stored tasks
     *
     * @return  ArrayList containing all task
     */
    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }
    /**
     * get total number of tasks
     *
     * @return int that shows total tasks
     */
    public int getNumberOfTasks() {
        return allTasks.size();
    }
    /**
     * Delete a task from taskList
     *
     * @param taskNo index position of task in taskList
     */
    public void deleteTask(int taskNo) {
        allTasks.remove(taskNo);
    }


}
