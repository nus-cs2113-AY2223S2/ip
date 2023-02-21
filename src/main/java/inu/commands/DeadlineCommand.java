package inu.commands;

import inu.commons.Messages;
import inu.exceptionhandling.EmptyUserInputException;
import inu.exceptionhandling.ExceptionManager;
import inu.task.DeadLine;
import inu.task.TaskList;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private final String deadlineDescription;

    private final String deadlineBy;

    public DeadlineCommand(String deadlineDescription, String deadlineBy) {
        this.deadlineDescription = deadlineDescription;
        this.deadlineBy = deadlineBy;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
            DeadLine deadLineTask = new DeadLine(deadlineDescription, deadlineBy);
            taskList.addTask(deadLineTask);
            return new CommandResult("added: " + taskList.getLastTask().toString() + "\n"
                    + "Now you have " + taskList.getTaskListSize() + " tasks in your list.");
    }

}
