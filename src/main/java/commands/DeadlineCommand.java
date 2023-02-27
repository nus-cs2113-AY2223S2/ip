package commands;

import storage.Storage;
import task.Deadline;
import task.TaskParser;
import ui.TextUi;

/**
 * Handles the deadline command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String description;
    private String by;

    /**
     * A constructor that accepts the description and /by deadline specified by the user.
     *
     * @param description The description of the deadline.
     * @param by          The date of the deadline.
     */
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command the user provided.
     *
     * @param taskParser The task parser that Duke use to determine what task to execute.
     * @param ui         The Ui instance. Use to display messages to users.
     * @param storage    The storage instance. Use to write data into the text file.
     */
    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        Deadline deadline = taskParser.createDeadlineTask(description, by);
        taskParser.addAndPrintTask(deadline, ui, storage);
    }

    /**
     * Check if the program is exiting.
     *
     * @return the exit value.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
