package duke;

import duke.addable.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public int getLength() {
        return tasks.size();
    }
    public void remove(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }
    public Task get(int index) {
        return tasks.get(index);
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
