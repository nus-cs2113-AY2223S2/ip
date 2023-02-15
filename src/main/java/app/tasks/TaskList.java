package app.tasks;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        setTasks(tasks);
    }

    private void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
