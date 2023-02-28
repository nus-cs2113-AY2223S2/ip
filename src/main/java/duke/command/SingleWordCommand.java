package duke.command;

import duke.exception.EmptyListError;
import duke.tasklist.TaskList;
import duke.ui.UI;

public class SingleWordCommand extends Command {

    public SingleWordCommand(String commandType){
        super(commandType);
    }

    @Override
    public void execute(TaskList tasks){
        switch (commandType){
            case "list":
                try {
                    tasks.printList();
                } catch (EmptyListError err){
                    UI.printMessage("There is nothing inside the list");
                }
                break;
            case "bye":
                UI.showByeMessage();
                break;
        }
    }
}