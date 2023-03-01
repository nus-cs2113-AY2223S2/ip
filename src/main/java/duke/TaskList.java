package duke;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskArray;

    public TaskList() {
        taskArray = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskArray.add(task);
    }

    public ArrayList<Task> getTaskArray() {
        return taskArray;
    }

    public int getIndex() {
        return taskArray.size();
    }


}
