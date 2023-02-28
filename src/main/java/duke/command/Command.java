package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.exception.EmptyListError;

public class Command {

    protected String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public void execute(TaskList tasks){
        if (commandType.equals("list")){
            try {
                tasks.printList();
            } catch (EmptyListError err) {
                UI.printMessage("There is nothing inside the list");
            }
        } else if (commandType.equals("bye")) {
            UI.showByeMessage();
        }
    }
}