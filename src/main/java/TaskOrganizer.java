import java.util.ArrayList;
import java.util.HashMap;

public class TaskOrganizer {
    private int taskIndex;
    private HashMap<Integer, Task> tasks;

    public TaskOrganizer() {
        taskIndex = 1;
        tasks = new HashMap<Integer, Task>();
    }

    public boolean outOfBounds(int index) {
        if (index <= 0 || index >= taskIndex) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void addTask(String taskName) {
        Task newTask = new Task(taskName, taskIndex);
        tasks.put(taskIndex, newTask);
        taskIndex += 1;
    }

    public void markTask(int serialNumber) {
        tasks.get(serialNumber).markTask();
    }

    public void unmarkTask(int serialNumber) {
        tasks.get(serialNumber).unmarkTask();
    }

    public String getTaskbySerial(int serialNumber) {
        return tasks.get(serialNumber).getTaskName();
    }

    public ArrayList<Task> getTaskList() {
        ArrayList<Task> copy =  new ArrayList<Task>();
        for (int i = 1; i < taskIndex; i++) {
            copy.add(tasks.get(i));
        }
        return copy;
    }
}
