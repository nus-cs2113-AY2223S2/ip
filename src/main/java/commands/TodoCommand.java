package commands;

import storage.Storage;
import task.TaskParser;
import task.Todo;
import ui.TextUi;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskParser taskParser, TextUi ui, Storage storage) {
        Todo todo = taskParser.createTodoTask(description);
        taskParser.addAndPrintTask(todo, ui, storage);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
