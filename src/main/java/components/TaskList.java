package components;

import task.Task;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
}
