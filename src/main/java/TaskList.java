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

    /**
     * This is the constructor for the TaskList class.
     * It takes in an arraylist of tasks.
     * If the arraylist is empty, it creates a new arraylist.
     * @param tasks the arraylist of tasks
     */
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList(){
        this.tasks = new ArrayList<Task>();
    }

    /**
     * This method adds a task to the arraylist.
     * @param task the task to be added
     */
    public void add(Task task){
        tasks.add(task);
    }

    /**
     * This method deletes a task from the arraylist.
     * @param taskNumber the number of the task to be deleted
     */
    public void delete(int taskNumber){
        tasks.remove(taskNumber - 1);
    }

    /**
     * This method marks a task as done.
     * @param taskNumber the number of the task to be marked as done
     */
    public void markTask(int taskNumber){
        tasks.get(taskNumber - 1).markAsDone();
    }

    /**
     * This method marks a task as not done.
     * @param taskNumber the number of the task to be marked as not done
     */
    public void unmarkTask(int taskNumber){
        tasks.get(taskNumber - 1).markAsNotDone();
    }

    /**
     * This method returns the arraylist of tasks.
     * @return the arraylist of tasks
     */
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    /**
     * This method returns the size of the arraylist.
     * @return the size of the arraylist
     */
    public int size(){
        return tasks.size();
    }

    /**
     * This method returns a task from the arraylist.
     * @param taskNumber the number of the task to be returned
     * @return the task
     */
    public Task get(int taskNumber){
        return tasks.get(taskNumber - 1);
    }
    

}
