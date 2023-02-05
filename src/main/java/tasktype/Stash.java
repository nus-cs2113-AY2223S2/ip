package tasktype;

import tasktype.Task;

import java.util.Arrays;
//this class stores all the tasks
public class Stash {
    private Task[] tasks;

    public Stash() {
        this.tasks = new Task[0];
    }

    public void addNewTask(Task task) {
        //instead of a fixed sized array, use an array that 'dynamically' increases in size for every task added
        int listLength = this.tasks.length;
        this.tasks = Arrays.copyOf(this.tasks, listLength + 1);
        this.tasks[listLength] = task;
    }

    public Task getTask(int index) {
        return this.tasks[index];
    }

    public int ObtainTaskCount() {
        int curr = this.tasks.length;
        return curr;
    }

    public void printTasks() {
        for (Task task : tasks) {
            System.out.printf(" %s", task.toString());
            System.out.print("\n");
        }
        System.out.printf("Currently, you have %d tasks(s) in your ToDo list.", tasks.length);
        System.out.print("\n");

    }

}
