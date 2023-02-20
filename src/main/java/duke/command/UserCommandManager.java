package duke.command;

import duke.data.TaskData;
import duke.exceptions.DukeException;
import duke.filemanager.Storage;
import duke.main.Duke;

import java.util.Scanner;

public class UserCommandManager {

    /**
     * Handles the command (i.e. type of task) by user
     * and parse the arguments to add to task list
     *
     * @param rawUserInput raw user input
     * @param storage      handler for writing to json file
     * @param taskData     taskList of tasks
     */
    public void handleCommands(String[] userCommand, Storage storage, TaskData taskData) throws DukeException {

        Command command;
        switch (userCommand[0]) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListTasks();
            break;
        case "mark":
            command = new MarkCommand(userCommand[1]);
            break;
        case "unmark":
            command = new UnmarkCommand(userCommand[1]);
            break;
        case "todo":
            command = new AddTodoToList(userCommand[1]);
            break;
        case "deadline":
            command = new AddDeadlineToList(userCommand[1]);
            break;
        case "event":
            command = new AddEventToList(userCommand[1]);
            break;
        case "delete":
            command = new DeleteFromList(userCommand[1]);
            break;
        default:
            throw new DukeException("Unknown input");
        }
        command.executeCommand(taskData, storage);
    }
}
