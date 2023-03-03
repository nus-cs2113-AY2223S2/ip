package duke.commands;

/**
 * Lists all tasks in the task list.
 */

public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST_SUCCESS = "all tasks listed!";

    /**
     * Prints all tasks in the task list
     * @return CommandResult object containing feedback of execution
     */

    public CommandResult execute() {
        for (int x = 0; x < taskList.getSizeOfList(); x++) {
            System.out.print(x + 1 + ". ");
            taskList.getTask(x).print();
        }
        return new CommandResult(MESSAGE_LIST_SUCCESS);
    }
}
