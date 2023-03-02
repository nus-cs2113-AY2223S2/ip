package duke.Commands;

import duke.Storage;
import dukeException.DukeException;
import tasks.Todo;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private String desc;
    private boolean isMark;
    public ToDoCommand(String desc, boolean isMark) {
        this.desc = desc;
        this.isMark = isMark;
    }
    public void cmd() throws DukeException {
        Todo todo = new Todo(this.desc, this.isMark);
        tasks.add(todo);
        addTaskPrint(tasks, todo);
        Storage.saveTasks(tasks);
    }
}
