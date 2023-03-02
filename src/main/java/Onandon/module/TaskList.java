package Onandon.module;

import java.util.ArrayList;

// Class for the list of the tasks.
// Maintains list of the tasks with adding, deleting, editing, etc.
public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<Task>(100);
    protected int tasksNum;

    public TaskList(ArrayList<Task> tasks, int tasksNum){
        this.tasks = tasks;
        this.tasksNum = tasksNum;
    }

    // Gets the task of the corresponding index.
    public Task get(int index){
        return this.tasks.get(index);
    }

    // Inserts the new task into the array list.
    public void add(Task task){
        this.tasks.add(task);
    }

    // Removes the task of the corresponding index.
    public void remove(int index){
        this.tasks.remove(index);
    }

    // Gets the number of the tasks.
    public int getNum(){
        return this.tasksNum;
    }

    // Sets the number of the tasks.
    public int setNum(int num){
        return this.tasksNum = num;
    }

    // Increases the number of the tasks by 1 count.
    public void addNum(){
        this.tasksNum += 1;
    }

    // Decreases the number of the tasks by 1 count.
    public void substractNum(){
        this.tasksNum -= 1;
    }

}
