package psyduck.command;

import psyduck.exceptions.EmptyFindException;
import psyduck.exceptions.InvalidDeadlineFormatException;
import psyduck.exceptions.InvalidEventFormatException;
import psyduck.exceptions.TaskEmptyException;
import psyduck.parser.Parser;
import psyduck.task.Deadline;
import psyduck.tasklist.TaskList;
import psyduck.ui.ErrorMessage;
import psyduck.ui.Ui;

public class CommandHandler {
    protected Parser parser = new Parser();
    public boolean canExit = false;

    /**
     * Handles the command inputs from the user and executes the target command.
     *
     * @param input the string input from the user.
     * @param tasks the list containing the tasks.
     * @param ui    the user interface that interacts with the user.
     */
    public void processCommands(String input, TaskList tasks, Ui ui) {
        String command = parser.prepareCommand(input);
        Command commandType;
        switch (command) {
        case "bye":
            //fallthrough
        case "exit":
            canExit = true;
            break;
        case "find":
            commandType = new FindCommand();
            commandType.executeCommand(input, tasks, ui);
            break;
        case "list":
            commandType = new ListCommand();
            commandType.executeCommand(null, tasks, ui);
            break;
        case "mark":
            commandType = new MarkTaskCommand();
            commandType.executeCommand(input, tasks, ui);
            break;
        case "unmark":
            commandType = new UnmarkTaskCommand();
            commandType.executeCommand(input, tasks, ui);
            break;
        case "delete":
            //fallthrough
        case "remove":
            commandType = new RemoveTaskCommand();
            commandType.executeCommand(input, tasks, ui);
            break;
        case "todo":
            commandType = new AddToDoCommand();
            commandType.executeCommand(input, tasks, ui);
            break;
        case "deadline":
            commandType = new AddDeadlineCommand();
            commandType.executeCommand(input, tasks, ui);
            break;
        case "event":
            commandType = new AddEventCommand();
            commandType.executeCommand(input, tasks, ui);
            break;
        default:
            ErrorMessage.printInvalidCommandMessage();
        }

    }
}
