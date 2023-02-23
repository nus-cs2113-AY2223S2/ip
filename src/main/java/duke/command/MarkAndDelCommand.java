package duke.command;

import duke.task.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;

import static duke.Parser.COMMAND_DELETE_WORD;
import static duke.Parser.COMMAND_MARK_WORD;
import static duke.Parser.COMMAND_UNMARK_WORD;

public class MarkAndDelCommand extends Command {

    protected String command;
    protected int idx;
    public MarkAndDelCommand(String command, String param) {
        int idx = Integer.parseInt(param) - 1;
        this.command = command;
        this.idx = idx;
    }
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
            Storage.update();
        } catch (IOException e) {
            ui.printErrorForIO();
        }
    }
}
