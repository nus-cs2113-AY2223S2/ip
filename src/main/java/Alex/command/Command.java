package Alex.command;

import Alex.task.TaskList;

public abstract class Command {

    public abstract CommandResult execute(TaskList taskList);
}
