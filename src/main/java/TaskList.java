// This class is used to store the list of tasks
import java.util.ArrayList;

public class TaskList{
    protected ArrayList<Task> tasks;
    //constructor that takes in an arraylist of tasks
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    //if there is no file to read from
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }
    //method that adds a task to the arraylist
    public void add(Task task){
        tasks.add(task);
    }
    //method that deletes a task from the arraylist
    public void delete(int taskNumber){
        tasks.remove(taskNumber - 1);
    }
    //method that marks a task as done
    public void markTask(int taskNumber){
        tasks.get(taskNumber - 1).markAsDone();
    }
    //method that marks a task as undone
    public void unmarkTask(int taskNumber){
        tasks.get(taskNumber - 1).markAsNotDone();
    }
    //method that returns the arraylist of tasks
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    //method that returns the size of the arraylist
    public int size(){
        return tasks.size();
    }
    //method that returns a task from the arraylist
    public Task get(int taskNumber){
        return tasks.get(taskNumber - 1);
    }
    //method that returns matching tasks that contain the keyword
    public ArrayList<Task> find(String keyword){
        ArrayList<Task> matchingTasks = new ArrayList<Task>();
        for (Task task : tasks){
            if (task.getTaskname().contains(keyword)){
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    

}
