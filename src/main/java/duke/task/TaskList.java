package duke.task;

import duke.commands.UI;

public class TaskList {
    private Task[] tasks = new Task[100];

    public void addTask(int index, Task taskToAdd){
        String lineBreak = UI.getLineBreak();
        tasks[index] = taskToAdd;
        taskToAdd.printAdded();
        System.out.println(lineBreak);
    }

    public Task getTask(int index){
        return tasks[index];
    }
}
