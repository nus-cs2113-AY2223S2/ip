package duke.commands;

import duke.commands.actionCommands.*;
import duke.commands.taskCommands.DeadlineCommand;
import duke.commands.taskCommands.EventCommand;
import duke.commands.taskCommands.ToDoCommand;
import duke.exceptions.InvalidTaskException;
import duke.save.Storage;
import duke.tasks.TaskList;

public class Parser {

    private String line;

    /**
     * Constructor for CommandHandler.
     *
     * @param line User input represented as a String.
     */
    public void updateLine(String line) {
        this.line = line;
    }

    /**
     * Parses the command from user input.
     *
     * @return String containing the command.
     */
    private String parseCommand() {
        return line.contains(" ") ? line.split(" ")[0] : line;
    }

    /**
     * Checks if the user input is "bye". Signals whether to exit the loop.
     *
     * @return true if the user input is "bye", false otherwise.
     */
    public boolean isExit() {
        return line.equals("bye");
    }

    /**
     * Handles the command based on user input.
     *
     * @param taskList List containing the tasks input by user.
     * @param storage Storage object to save the task list.
     * @throws InvalidTaskException If the command is invalid.
     */
    public void handleCommand(TaskList taskList, Storage storage) throws InvalidTaskException {
        String command = parseCommand();
        Command newCommand;
        switch (command) {

        case "todo":
            newCommand = new ToDoCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "deadline":
            newCommand = new DeadlineCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "event":
            newCommand = new EventCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "delete":
            newCommand = new DeleteCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "list":
            newCommand = new ListCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "mark":
            newCommand = new MarkCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "unmark":
            newCommand = new UnmarkCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "find":
            newCommand = new FindCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        case "help":
            newCommand = new HelpCommand();
            newCommand.handleCommand(line, taskList, storage);
            break;

        default:
            throw new InvalidTaskException();
        }
    }
}

