package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Tasks;

public class MarkCommand extends Command{
    String item;
    public MarkCommand(String item){
        this.item = item;
    }
    @Override
    public void execute() {
        Tasks markTask = TaskList.getTaskList().get(Integer.parseInt(item) - 1);
        markTask.setMarked(true);
        Ui.displayMark(markTask);
    }
}
