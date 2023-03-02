import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public void addTask (Task newTask){
        System.out.println(Buddy.divider);
        this.add(newTask);                      // new task is added to taskList
        Buddy.taskCount++;                      // increments number of tasks
        newTask.printAfterAddingTask();         // print message once new task is added (From Task class)
        System.out.println(Buddy.divider);
    }

    public void deleteTask (int indexOfTaskToDelete){
        System.out.println(Buddy.divider);
        Task taskToBeDeleted = this.get(indexOfTaskToDelete);
        Buddy.taskCount--;                      // decrements number of tasks
        taskToBeDeleted.printAfterDeletingTask();
        this.remove(indexOfTaskToDelete);       // delete task from taskList
        System.out.println(Buddy.divider);

    }
}
