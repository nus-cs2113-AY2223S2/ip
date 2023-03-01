package duke.commands;

import duke.TaskList;

public class Command {

    protected TaskList taskList;
    public int targetIndex = -1;
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }
    protected Command() {
    }
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
    public CommandResult execute() {
        throw new UnsupportedOperationException("method to be implemented by child classes");
    };

}
