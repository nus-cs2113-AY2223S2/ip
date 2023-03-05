package buddy.tasks;

import buddy.Buddy;
import buddy.messages.Messages;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    /**
     * Adds task to the task list
     *
     * @param newTask Task to add
     */
    public void addTask(Task newTask) {
        System.out.println(Messages.DIVIDER);
        this.add(newTask);                      // new task is added to taskList
        Buddy.taskCount++;                      // increments number of buddy.tasks
        newTask.printAfterAddingTask();         // print message once new task is added (From Task class)
        System.out.println(Messages.DIVIDER);
    }

    /**
     * Deletes task from the task list
     *
     * @param indexOfTaskToDelete The index of the task to be deleted in the list
     */
    public void deleteTask(int indexOfTaskToDelete) {
        System.out.println(Messages.DIVIDER);
        Task taskToBeDeleted = this.get(indexOfTaskToDelete);
        Buddy.taskCount--;                      // decrements number of buddy.tasks
        taskToBeDeleted.printAfterDeletingTask();
        this.remove(indexOfTaskToDelete);       // delete task from taskList
        System.out.println(Messages.DIVIDER);
    }
}
