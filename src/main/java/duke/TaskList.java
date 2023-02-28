package duke;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> storedTasks;
    private int taskNum;
    public TaskList() {
        storedTasks = new ArrayList<>();
        taskNum = 0;
    }
    public void incrementTaskNum() {
        taskNum++;
    }
    public void decrementTaskNum() {
        taskNum--;
    }
    public int getTaskNum() {
        return taskNum;
    }
    public ArrayList<Task> getStoredTasks() {
        return storedTasks;
    }
    public void setStoredTasks(ArrayList<Task> newTasks) {
        storedTasks = newTasks;
    }
    public void removeItem(int index) {
        storedTasks.remove(index);
    }
    public void addItem(Task task) {
        storedTasks.add(task);
    }
}
