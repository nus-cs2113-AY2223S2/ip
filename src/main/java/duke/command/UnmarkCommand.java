package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Tasks;

public class UnmarkCommand extends Command{

    String item;
    public UnmarkCommand(String item){
        this.item = item;
    }
    @Override
    public void execute() {
        Tasks markTask = TaskList.getTaskList().get(Integer.parseInt(item) - 1);
        markTask.setMarked(false);
        Ui.displayUnmark(markTask);
    }
}
