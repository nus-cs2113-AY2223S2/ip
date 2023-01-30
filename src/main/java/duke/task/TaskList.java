package duke.task;

import duke.commands.UI;

public class TaskList {
    private Task[] tasks = new Task[100];

    /**
     * Adds a task to the task list
     *
     * @param index Index of task to be added
     * @param taskToAdd Task to be added
     */
    public void addTask(int index, Task taskToAdd){
        String lineBreak = UI.getLineBreak();
        tasks[index] = taskToAdd;
        taskToAdd.printAdded();
        System.out.println(lineBreak);
    }

    /**
     * Gets the task at the specified index
     *
     * @param index Index of task to be retrieved
     *
     * @return Task at the specified index
     */
    public Task getTask(int index){
        return tasks[index];
    }
}
