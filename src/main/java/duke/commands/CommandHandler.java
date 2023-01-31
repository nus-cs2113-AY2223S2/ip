package duke.commands;

import duke.commands.actionCommands.HelpCommand;
import duke.commands.actionCommands.ListCommand;
import duke.commands.actionCommands.MarkCommand;
import duke.commands.actionCommands.UnmarkCommand;
import duke.commands.taskCommands.DeadlineCommand;
import duke.commands.taskCommands.EventCommand;
import duke.commands.taskCommands.ToDoCommand;
import duke.exceptions.InvalidTaskException;
import duke.tasks.TaskList;

public class CommandHandler {

    private String line;

    /**
     * Constructor for CommandHandler
     *
     * @param line User input represented as a String
     */
    public void updateLine(String line){
        this.line = line;
    }

    /**
     * Parses the command from user input
     *
     * @return String containing the command
     */
    private String parseCommand(){
        return line.contains(" ") ? line.split(" ")[0] : line;
    }

    /**
     * Handles the command based on user input.
     *
     * @param taskList List containing the tasks input by user.
     */
    public void handleCommand(TaskList taskList) throws InvalidTaskException {
        String command = parseCommand();
        Command newCommand;
        switch (command) {

        case "todo":
            newCommand = new ToDoCommand();
            newCommand.handleCommand(line, taskList);
            break;

        case "deadline":
            newCommand = new DeadlineCommand();
            newCommand.handleCommand(line, taskList);
            break;

        case "event":
            newCommand = new EventCommand();
            newCommand.handleCommand(line, taskList);
            break;

        case "list":
            newCommand = new ListCommand();
            newCommand.handleCommand(line, taskList);
            break;

        case "mark":
            newCommand = new MarkCommand();
            newCommand.handleCommand(line, taskList);
            break;

        case "unmark":
            newCommand = new UnmarkCommand();
            newCommand.handleCommand(line, taskList);
            break;

        case "help":
            newCommand = new HelpCommand();
            newCommand.handleCommand(line, taskList);
            break;

        default:
            throw new InvalidTaskException();
        }
    }
}
