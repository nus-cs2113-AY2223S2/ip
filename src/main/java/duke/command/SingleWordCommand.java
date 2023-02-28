package duke.command;

import duke.exception.EmptyListError;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Command that has only one string input
 */
public class SingleWordCommand extends Command {

    public SingleWordCommand(String commandType){
        super(commandType);
    }


    /**
     * Executes the command according to command type
     *
     * @param tasks tasklist which contains all the tasks
     */
    @Override
    public void execute(TaskList tasks){
        switch (commandType){
            case "list":
                try {
                    tasks.printList();
                } catch (EmptyListError err){
                    System.out.println("There is nothing inside the list");
                }
                break;
            case "bye":
                UI.showByeMessage();
                break;
        }
    }
}