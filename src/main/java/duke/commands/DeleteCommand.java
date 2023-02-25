package duke.commands;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class DeleteCommand implements Command {
    public static final String COMMAND_WORD = "delete";
    private String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        Parser parser = new Parser();
        int indexTask = parser.parseIndex(arguments);
        try {
            if (indexTask != -1) {
                Task deletedTask = tasks.deleteTask(indexTask);
                ;
                ui.showDeleteTaskMessage(tasks, deletedTask);
            }
        } catch (IndexOutOfBoundsException e) {
            ui.showTaskIndexOutOfBoundsError();
        }
    }

    public boolean isExit() {
        return false;
    }
}
