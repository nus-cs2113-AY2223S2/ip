package buddy.tasks;

import buddy.Buddy;
import buddy.messages.Messages;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public void addTask (Task newTask){
        System.out.println(Messages.DIVIDER);
        this.add(newTask);                      // new task is added to taskList
        Buddy.taskCount++;                      // increments number of buddy.tasks
        newTask.printAfterAddingTask();         // print message once new task is added (From Task class)
        System.out.println(Messages.DIVIDER);
    }

    public void deleteTask (int indexOfTaskToDelete){
        System.out.println(Messages.DIVIDER);
        Task taskToBeDeleted = this.get(indexOfTaskToDelete);
        Buddy.taskCount--;                      // decrements number of buddy.tasks
        taskToBeDeleted.printAfterDeletingTask();
        this.remove(indexOfTaskToDelete);       // delete task from taskList
        System.out.println(Messages.DIVIDER);

    }
}
