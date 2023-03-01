package duke.commands;

import duke.commands.task.Task;

import java.util.List;

public class CommandResult {
    public final String outputToUser;
    private final List<Task> taskList;

    public CommandResult(String outputToUser) {
        this.outputToUser = outputToUser;
        taskList = null;
    }

    public CommandResult(String outputToUser, List<Task> taskList) {
        this.outputToUser = outputToUser;
        this.taskList = taskList;
    }


}
