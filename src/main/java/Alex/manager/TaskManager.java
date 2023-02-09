package Alex.manager;

import Alex.task.Task;

public class TaskManager {
    private Task[] allTasks = new Task[100];
    private boolean isExit = false;
    private int numberOfTasks = 0;


    public void setTask(Task t) {
        allTasks[numberOfTasks] = t;
        numberOfTasks += 1;
    }

    public Task[] getAllTasks() {
        return allTasks;
    }
    public void setExit() {
        isExit = !isExit;
    }
    public boolean getExit() {
        return isExit;
    }
    public int getNumberOfTasks() {
        return numberOfTasks;
    }
}
