package io.github.haoyangw.rica.input;

import io.github.haoyangw.rica.exception.RicaCommandException;
import io.github.haoyangw.rica.task.TaskManager;

import java.io.InputStream;
import java.util.Scanner;

public class CommandManager {
    private static final String BYE_CMD = "bye";
    private static final String DEADLINE_CMD = "deadline";
    private static final String DELETE_CMD = "delete";
    private static final String EVENT_CMD = "event";
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

    public String getNextCommand() {
        return this.getUserInput().nextLine();
    }

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
