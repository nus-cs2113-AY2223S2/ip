package app.commands;

import app.exceptions.DukeException;
import app.exceptions.IncompleteCommandException;
import app.save.Storage;
import app.tasks.Task;
import app.tasks.TaskList;
import app.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String commandDescriptor) throws DukeException {
        parseInput(commandDescriptor);
    }

    private void parseInput(String commandDescriptor) throws DukeException {
        if (commandDescriptor.length() == 0) {
            throw new IncompleteCommandException();
        }
        this.keyword = commandDescriptor;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasksWithKeyword = tasks.getTasksWithKeyword(tasks, keyword);
        ui.printTasksWithKeyword(tasksWithKeyword);
    }
}
