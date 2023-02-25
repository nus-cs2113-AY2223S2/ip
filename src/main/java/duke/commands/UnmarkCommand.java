package duke.commands;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class UnmarkCommand implements Command {
    public static final String COMMAND_WORD = "unmark";
    private String arguments;

    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Parser parser = new Parser();
        int indexTask = parser.parseIndex(arguments);
        try {
            if (indexTask != -1) {
                Task selectedTask = tasks.getTaskByIndex(indexTask);
                selectedTask.setStatus(false);
                ui.showTaskStatusMessage(selectedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskIndexOutOfBoundsError();
        }
    }

    public boolean isExit() {
        return false;
    }
}
