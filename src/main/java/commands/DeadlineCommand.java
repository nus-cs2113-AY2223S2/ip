package commands;

import storage.Storage;
import task.Deadline;
import task.TaskParser;
import ui.TextUi;

public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";

    private String description;
    private String by;

    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        Deadline deadline = taskParser.createDeadlineTask(description, by);
        taskParser.addAndPrintTask(deadline, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
