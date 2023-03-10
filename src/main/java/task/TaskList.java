package task;

import java.util.ArrayList;

/**
 * Object of a tasklist, contains all types of tasks.
 */
public class TaskList extends ArrayList<Task>{
    protected ArrayList<Task> tasks = new ArrayList<>();
    public TaskList(){
        return;
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int i){

        return tasks.get(i);
    }


    public boolean add(Task task){
        tasks.add(task);
        return true;
    }

    public Task remove(int index){
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return removedTask;
    }
}
