package Onandon.module;

import java.util.ArrayList;

/**
 * Representation of the list of the tasks.
 * Manage the list of the tasks, e.g, adding the task, removing the task, etc.
 */
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<Task>(100);
    protected int tasksNum;

    /**
     * Create new list of the tasks by ArrayList class.
     *
     * @param tasks ArrayList of the Task class.
     * @param tasksNum The number of the tasks contain in the ArrayList.
     */
    public TaskList(ArrayList<Task> tasks, int tasksNum){
        this.tasks = tasks;
        this.tasksNum = tasksNum;
    }

    /**
     * Get specified task by index to process it with command instruction.
     *
     * @param index Index of the task that user want to get.
     */
    public Task get(int index){
        return this.tasks.get(index);
    }

    /**
     * Add single task into ArrayList.
     *
     * @param task Task user want to add in this list.
     */
    public void add(Task task){
        this.tasks.add(task);
    }

    /**
     * Remove single task into ArrayList.
     *
     * @param index Index of the task that user want to remove.
     */
    public void remove(int index){
        this.tasks.remove(index);
    }

    /**
     * Get the number of the tasks included in the ArrayList.
     * Necessary for executing list command or storing the current status of the ArrayList
     * in the checkpoint.txt file.
     */
    public int getNum(){
        return this.tasksNum;
    }

    /**
     * Set the number of the tasks included in the ArrayList.
     *
     * @param num Number user want to set in 'tasksNum' variable.
     */
    public int setNum(int num){
        return this.tasksNum = num;
    }

    /**
     * Add 1 to the number of the tasks included in the ArrayList.
     * Usually add() method is followed by this method.
     */
    public void addNum(){
        this.tasksNum += 1;
    }

    /**
     * Subtract 1 to the number of the tasks included in the ArrayList.
     * Usually remove() method is followed by this method.
     */
    public void subtractNum(){
        this.tasksNum -= 1;
    }

}
