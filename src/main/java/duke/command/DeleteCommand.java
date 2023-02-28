package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Tasks;

public class DeleteCommand extends Command{
    protected String item;
    public DeleteCommand(String item){
        this.item = item;
    }
    public void execute() {
        Tasks toDelete = TaskList.getTaskList().get(Integer.parseInt(item) - 1);
        Ui.displayDelete(toDelete);
        TaskList.deleteFromList(Integer.parseInt(item) - 1);
    }
}
