package inu.commands;

import inu.exceptionhandling.ExceptionManager;
import inu.exceptionhandling.InvalidDate;
import inu.storage.StorageFile;
import inu.task.DeadLine;
import inu.task.TaskList;

import java.time.LocalDateTime;

/**
 * Adds a new deadline to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String deadlineDescription;
    private final LocalDateTime deadlineBy;

    /**
     * Constructor.
     *
     * @param deadlineDescription description of the new deadline.
     * @param deadlineBy date and time the deadline is to be completed by.
     */
    public DeadlineCommand(String deadlineDescription, LocalDateTime deadlineBy) throws InvalidDate {
        this.deadlineDescription = deadlineDescription;
        this.deadlineBy = deadlineBy;
        ExceptionManager.checkCorrectDate(deadlineBy);
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        DeadLine deadLineTask = new DeadLine(deadlineDescription, deadlineBy);
        taskList.addTask(deadLineTask);
        StorageFile.saveTaskListToFile(taskList);
        return new CommandResult("added: " + taskList.getLastTask().toString() + "\n"
                + "Now you have " + taskList.getTaskListSize() + " tasks in your list.");
    }
}
