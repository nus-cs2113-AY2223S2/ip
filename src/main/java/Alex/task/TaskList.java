package Alex.task;

import Alex.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> allTasks = new ArrayList<>();
    private boolean isExit = false;


    public void setTask(Task t) {
        allTasks.add(t);
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public void setExit() {
        isExit = !isExit;
    }

    public boolean getExit() {
        return isExit;
    }

    public int getNumberOfTasks() {
        return allTasks.size();
    }

    public void deleteTask(int taskNo) {
        allTasks.remove(taskNo);
    }


}
