package duke.tasks;

import static duke.constants.Constants.LINEBREAK;

public class TaskList {
    private Task[] tasks = new Task[100];

    /**
     * Adds a task to the task list.
     *
     * @param index Index of task to be added.
     * @param taskToAdd Task to be added.
     */
    public void addTask(int index, Task taskToAdd){
        tasks[index] = taskToAdd;
        taskToAdd.printAdded();
        System.out.println(LINEBREAK);
    }

    public void loadTask(int index, Task taskToAdd){
        tasks[index] = taskToAdd;
    }

    /**
     * Gets the task from the task list at the specified index.
     *
     * @param index Index of task to be retrieved.
     *
     * @return Task at the specified index.
     */
    public Task getTask(int index){
        return tasks[index];
    }
}
