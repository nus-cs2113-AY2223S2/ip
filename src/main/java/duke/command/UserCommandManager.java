package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.Ui;

public class UserCommandManager {

    /**
     * Handles the command (i.e. type of task) by user and parse the arguments to add to task list.
     *
     * @param userCommands Parsed userInput containing command, and arguments
     * @param storage      Handler for writing to json file
     * @param tasks        TaskList of tasks
     * @param ui           Handler for printing text to the screen
     */
    public void handleCommands(String[] userCommands, Storage storage, TaskList tasks, Ui ui) throws DukeException {

        Command command;
        switch (userCommands[0]) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
        case "ls":
            command = new ListTasksCommand();
            break;
        case "list-sorted":
        case "ls-s":
            command = new PrintSortedTasksCommand();
            break;
        case "find":
            command = new FindTaskCommand(userCommands[1]);
            break;
        case "sort":
            command = new SortTasksCommand();
            break;
        case "mark":
            command = new MarkCommand(userCommands[1]);
            break;
        case "unmark":
            command = new UnmarkCommand(userCommands[1]);
            break;
        case "todo":
            command = new AddTodoToListCommand(userCommands[1]);
            break;
        case "deadline":
            command = new AddDeadlineToListCommand(userCommands[1]);
            break;
        case "event":
            command = new AddEventToListCommand(userCommands[1]);
            break;
        case "delete":
            command = new DeleteFromListCommand(userCommands[1]);
            break;
        default:
            throw new DukeException("Unknown input");
        }
        command.executeCommand(tasks, storage, ui);
    }
}
