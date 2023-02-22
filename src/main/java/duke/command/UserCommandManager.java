package duke.command;

import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.ui.Ui;

public class UserCommandManager {

    /**
     * Handles the command (i.e. type of task) by user
     * and parse the arguments to add to task list
     *
     * @param userCommands parsed userInput containing command, and arguments
     * @param storage      handler for writing to json file
     * @param tasks        taskList of tasks
     * @param ui           handler for printing text to the screen
     */
    public void handleCommands(String[] userCommands, Storage storage, TaskList tasks, Ui ui) throws DukeException {

        Command command;
        switch (userCommands[0]) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
        case "ls":
            command = new ListTasks();
            break;
        case "list-sorted":
        case "ls -s":
            command = new PrintSortedTasks();
            break;
        case "find":
            command = new FindTask(userCommands[1]);
            break;
        case "sort":
            command = new SortTasks();
            break;
        case "mark":
            command = new MarkCommand(userCommands[1]);
            break;
        case "unmark":
            command = new UnmarkCommand(userCommands[1]);
            break;
        case "todo":
            command = new AddTodoToList(userCommands[1]);
            break;
        case "deadline":
            command = new AddDeadlineToList(userCommands[1]);
            break;
        case "event":
            command = new AddEventToList(userCommands[1]);
            break;
        case "delete":
            command = new DeleteFromList(userCommands[1]);
            break;
        default:
            throw new DukeException("Unknown input");
        }
        command.executeCommand(tasks, storage, ui);
    }
}
