package duke.command;

import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.exception.EmptyListError;

/**
 * Abstract class to manage all commands
 */
public class Command {

    protected String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    /**
     * Executes the command according to command type
     *
     * @param tasks tasklist which contains all the tasks
     */
    public void execute(TaskList tasks){
        if (commandType.equals("list")){
            try {
                tasks.printList();
            } catch (EmptyListError err) {
                System.out.println("There is nothing inside the list");
            }
        } else if (commandType.equals("bye")) {
            UI.showByeMessage();
        }
    }
}