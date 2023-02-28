package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;
import duke.task.Todo;

public class ToDoCommand extends Command{
    String item;
    public ToDoCommand(String item) {
        this.item = item;
    }
    @Override
    public void execute() {
        Todo newToDo = new Todo(item, false);
        TaskList.addToList(newToDo);
        Ui.displayAddTask(newToDo);
    }
}
