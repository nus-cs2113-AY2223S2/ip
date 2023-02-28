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

    public void processCommands(String input, TaskList tasks, Ui ui) {
        String command = parser.prepareCommand(input);
        Command commandType;
        String[] format;
        switch (command) {
        case "bye":
            //fallthrough
        case "exit":
            canExit = true;
            break;
        case "find":
            try {
                format = parser.prepareFind(input);
                commandType = new FindCommand();
                commandType.executeCommand(format, tasks, ui);
            } catch (EmptyFindException e) {
                ErrorMessage.printEmptyFindMessage();
            }
            break;
        case "list":
            commandType = new ListCommand();
            commandType.executeCommand(null, tasks, ui);
            break;
        case "mark":
            format = parser.prepareMark(input);
            commandType = new MarkTaskCommand();
            commandType.executeCommand(format, tasks, ui);
            break;
        case "unmark":
            format = parser.prepareUnmark(input);
            commandType = new UnmarkTaskCommand();
            commandType.executeCommand(format, tasks, ui);
            break;
        case "delete":
            //fallthrough
        case "remove":
            format = parser.prepareRemove(input);
            commandType = new RemoveTaskCommand();
            commandType.executeCommand(format, tasks, ui);
            break;
        case "todo":
            try {
                format = parser.prepareToDo(input);
                commandType = new AddToDoCommand();
                commandType.executeCommand(format, tasks, ui);
            } catch (TaskEmptyException e) {
                ErrorMessage.printTaskEmptyMessage();
            }
            break;
        case "deadline":
            try {
                format = parser.prepareDeadline(input);
                commandType = new AddDeadlineCommand();
                commandType.executeCommand(format, tasks, ui);
            } catch (TaskEmptyException e) {
                ErrorMessage.printTaskEmptyMessage();
            } catch (InvalidDeadlineFormatException e) {
                ErrorMessage.printInvalidDeadlineFormatMessage();
            }

            break;
        case "event":
            try {
                format = parser.prepareEvent(input);
                commandType = new AddEventCommand();
                commandType.executeCommand(format, tasks, ui);
            } catch (TaskEmptyException e) {
                ErrorMessage.printTaskEmptyMessage();
            } catch (InvalidEventFormatException e) {
                ErrorMessage.printInvalidEventFormatMessage();
            }
            break;
        default:
            ErrorMessage.printInvalidCommandMessage();
        }

    }
}
