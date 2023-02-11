// This class is used to store the list of tasks
import java.util.ArrayList;

public class TaskList extends Task{
    protected ArrayList<Task> tasks;
    protected int taskCount;
    //constructor for the tasklist class
    public TaskList(){
        tasks = new ArrayList<Task>();
        taskCount = 0;
    }
    //method that adds a task to the tasklist
    public void addTask(Task task){
        tasks.add(task);
        taskCount++;
    }
    //method that deletes a task from the tasklist
    public void deleteTask(int taskNumber){
        tasks.remove(taskNumber-1);
        taskCount--;
    }
    //method that marks a task as done
    public void markDone(int taskNumber){
        tasks.get(taskNumber-1).isDone = true;
    }
    //method that returns the tasklist
    public ArrayList<Task> getTaskList(){
        return tasks;
    }
    //method that returns the number of tasks in the tasklist
    public int getTaskCount(){
        return taskCount;
    }
}
