package tasktype;

import tasktype.Task;

import java.util.ArrayList;

//this class stores all the tasks
public class Stash {
//    private Task[] tasks;
    private ArrayList<Task> tasks;
    public Stash() {
        this.tasks = new ArrayList<Task>();;
    }

    public void addNewTask(Task task) {
        this.tasks.add(task);
        //instead of a fixed sized array, use an array that 'dynamically' increases in size for every task added
//        int listLength = this.tasks.length;
//        this.tasks = Arrays.copyOf(this.tasks, listLength + 1);
//        this.tasks[listLength] = task;
    }

    public void generateTask(int count, Task task){
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }
    public void deleteTask(int index){
        this.tasks.remove(index);
    }

    public int ObtainTaskCount() {
        int curr = this.tasks.size();
        return curr;
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.printf(" %s", task.toString());
            System.out.print("\n");
        }
        System.out.printf("Currently, you have %d tasks(s) in your ToDo list.", ObtainTaskCount());
        System.out.print("\n");

    }

}
