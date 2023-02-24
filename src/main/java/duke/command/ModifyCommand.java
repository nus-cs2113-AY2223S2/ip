package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

import static duke.Parser.COMMAND_DELETE_WORD;
import static duke.Parser.COMMAND_MARK_WORD;
import static duke.Parser.COMMAND_UNMARK_WORD;

/**
 * Mark and Delete Command class that modifies an existing Task from the TaskList tasks.
 * Handles <code>mark</code>, <code>unmark</code>, and <code>delete</code> commands.
 */
public class ModifyCommand extends Command {

    protected String command;
    protected int idx;

    /**
     * Initialises the class with the type and description of the task given in the command.
     *
     * @param command Type of modification command being executed (mark, unmark, delete)
     * @param param Contains the index of the task to be modified
     */
    public ModifyCommand(String command, String param) {
        int idx = Integer.parseInt(param) - 1;
        this.command = command;
        this.idx = idx;
    }

    /**
     * Executes the modification of a Task in the TaskList tasks based on data in the class.
     *
     * @param tasks The TaskList of existing Tasks
     * @param ui Prints success or error message to user
     * @param storage Gets updated after the TaskList has been modified
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (idx < 0 || idx >= tasks.getSize()) {
            throw new NumberFormatException();
        }
        switch(command) {
        case COMMAND_MARK_WORD:
            tasks.markDone(idx);
            ui.printMarkDone(tasks.allTasks.get(idx));
            break;
        case COMMAND_UNMARK_WORD:
            tasks.markNotDone(idx);
            ui.printMarkNotDone(tasks.allTasks.get(idx));
            break;
        case COMMAND_DELETE_WORD:
            ui.printDeleted(tasks.allTasks.get(idx), tasks.getSize());
            tasks.deleteTask(idx);
            break;
        }
        try {
            storage.update(tasks);
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }

}
