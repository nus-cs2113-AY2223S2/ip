package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaCommandException;
import io.github.haoyangw.rica.task.TaskManager;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Handles the reading of commands from the user, and the parsing of commands to
 *   determine which <code>Command</code> implementation the user wants us to execute.
 */
public class CommandManager {
    private static final String BYE_CMD = "bye";
    private static final String DEADLINE_CMD = "deadline";
    private static final String DELETE_CMD = "delete";
    private static final String EVENT_CMD = "event";
    private static final String FIND_CMD = "find";
    private static final String LIST_CMD = "list";
    private static final String MARK_CMD = "mark";
    private static final String TODO_CMD = "todo";
    private static final String UNKNOWN_CMD_ERROR = " ??? Sorry, I don't understand this command. Sent to the wrong bot? xD";
    private static final String UNMARK_CMD = "unmark";
    private final Scanner userInput;

    public CommandManager() {
        this.userInput = new Scanner(System.in);
    }

    public CommandManager(InputStream userInput) {
        this.userInput = new Scanner(userInput);
    }

    private Scanner getUserInput() {
        return this.userInput;
    }

    /**
     * Reads in the next command from the user as a String
     *
     * @return String object representing the full command issued by
     *   the user
     */
    public String getNextCommand() {
        return this.getUserInput().nextLine();
    }

    /**
     * Parses a command issued by the user to determine the corresponding Command
     *   subclass that can execute that command
     *
     * @param command String of the full command issued by the user
     * @param taskManager TaskManager instance currently used by Rica that will provide
     *   the corresponding Command subclass with access to the user's tasks
     * @return Corresponding implementation of Command that can execute the user's
     *   current command
     * @throws RicaCommandException If an unrecognised command is issued by the user
     */
    public static Command parse(String command, TaskManager taskManager)
            throws RicaCommandException {
        String[] params = command.split(" ");
        String keyword = params[0];
        switch (keyword) {
        case BYE_CMD:
            return new ByeCommand(command, taskManager);
        case DEADLINE_CMD:
            return new DeadlineCommand(command, taskManager);
        case DELETE_CMD:
            return new DeleteCommand(command, taskManager);
        case EVENT_CMD:
            return new EventCommand(command, taskManager);
        case FIND_CMD:
            return new FindCommand(command, taskManager);
        case LIST_CMD:
            return new ListCommand(command, taskManager);
        case MARK_CMD:
            return new MarkCommand(command, taskManager);
        case TODO_CMD:
            return new TodoCommand(command, taskManager);
        case UNMARK_CMD:
            return new UnmarkCommand(command, taskManager);
        default:
            throw new RicaCommandException(CommandManager.UNKNOWN_CMD_ERROR);
        }
    }
}
