package duke.commands;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.TextUi;

public class AddTodoCommand implements Command {
    private String arguments;
    public static final String COMMAND_WORD = "todo";

    public AddTodoCommand(String arguments) {
        this.arguments = arguments;
    }


    public void execute(TaskList tasks, TextUi ui, Storage storage) {
        if (!arguments.isBlank()) {
            Task task = new Todo(arguments);
            tasks.addTask(task);
            ui.showNewTaskMessage(tasks, task);
        } else {
            ui.showPromptEmptyErrorMessage(COMMAND_WORD);
        }
    }

    public boolean isExit() {
        return false;
    }
}
