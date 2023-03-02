package duke;

import tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int size = 0;
    public TaskList() {
        
    }
    public Task getTask(int idx) {
        return tasks.get(idx);
    }
    public void add(Task task) {
        tasks.add(task);
        size++;
    }
    public void delete(int idx) {
        tasks.remove(idx);
        size--;
    }
    public int getSize() {
        return size;
    }


}
