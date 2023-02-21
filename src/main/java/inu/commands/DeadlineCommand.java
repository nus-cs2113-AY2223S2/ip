package inu.commands;

import inu.task.DeadLine;
import inu.task.TaskList;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    public static final String COMMAND_WORD = "deadline";

    private final String deadlineDescription;

    private final LocalDateTime deadlineBy;

    public DeadlineCommand(String deadlineDescription, LocalDateTime deadlineBy) {
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
