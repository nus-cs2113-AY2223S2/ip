package duke.command;

import duke.task.TaskList;
import duke.task.Todo;

public class ToDoCommand extends Command{
    String item;
    public ToDoCommand(String item) {
        this.item = item;
    }
    @Override
    public void execute() {
        TaskList.addToList(new Todo(item, false));
    }
}
