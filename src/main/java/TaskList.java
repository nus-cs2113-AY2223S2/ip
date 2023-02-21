/**
 * This class represents the list of tasks.
 * It contains an arraylist of tasks.
 * It contains methods to add, delete, mark and unmark tasks.
 * @param tasks the arraylist of tasks
 * @param add method to add a task to the arraylist
 * @param delete method to delete a task from the arraylist
 * @param markTask method to mark a task as done
 * @param unmarkTask method to mark a task as undone
 * @param get method to get a task from the arraylist
 * @param size method to get the size of the arraylist
 * @param getTasks method to get the arraylist of tasks
 * @throws DukeException if the deadline is not in the correct format
 * @throws DukeException if the task number is not in the correct format
 */
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
    

}
