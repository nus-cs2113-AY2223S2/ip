package duke.commands;


import duke.TaskList;
import duke.data.Task;

public abstract class Command {
    protected TaskList taskList;


    protected Task getTargetTask(int index) throws IndexOutOfBoundsException{
        return taskList.getTask(index);
    }

    abstract void execute();

}
