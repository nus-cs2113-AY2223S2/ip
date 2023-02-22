package duke.tasks;

import java.util.ArrayList;

import static duke.constants.Constants.LINEBREAK;

public class TaskList extends ArrayList<Task> {

    /**
     * Adds a task to the task list.
     *
     * @param taskToAdd Task to be added.
     */
    public void addTask(Task taskToAdd) {
        this.add(taskToAdd);
        taskToAdd.printAdded();
        System.out.println(LINEBREAK);
    }

    public void loadTask(int i, Task taskToAdd) {
        this.add(taskToAdd);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param indexToDelete The index of the Task to be deleted.
     */
    public void deleteTask(int indexToDelete) {
        Task taskToDelete = this.get(indexToDelete);
        this.remove(indexToDelete);
        Task.decrementIndexCount();
        taskToDelete.printDeleted();
        System.out.println(LINEBREAK);
    }

}
