package Onandon.module;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<Task>(100);
    protected int tasksNum;

    public TaskList(ArrayList<Task> tasks, int tasksNum){
        this.tasks = tasks;
        this.tasksNum = tasksNum;
    }

    public Task get(int index){
        return this.tasks.get(index);
    }
    public void add(Task task){
        this.tasks.add(task);
    }
    public void remove(int index){
        this.tasks.remove(index);
    }
    public int getNum(){
        return this.tasksNum;
    }
    public int setNum(int num){
        return this.tasksNum = num;
    }
    public void addNum(){
        this.tasksNum += 1;
    }
    public void substractNum(){
        this.tasksNum -= 1;
    }

}
