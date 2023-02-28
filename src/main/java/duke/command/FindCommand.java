package duke.command;

import duke.exception.EmptyListError;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;


public class FindCommand extends Command {

    protected String keyword;
    public FindCommand(String commandType, String keyword) {
        super(commandType);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks) {
        try {
            tasks.findInList(keyword);
        } catch (EmptyListError err){
            UI.printMessage("There is nothing inside the list");
        }

    }

}