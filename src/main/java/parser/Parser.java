package parser;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.IncorrectCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import common.Messages;
import exceptions.DukeException;
import task.TaskParser;

/**
 * The Parser class will parse the user commands into segments understood by the program.
 */
public class Parser {

    /**
     * It will parse the user inputted command and prepare to execute the appropriate commands.
     *
     * @param userInput The raw input of what the user typed.
     * @return Command object in order for the program to know what command to execute.
     */
    public Command parseCommand(String userInput) {
        String[] userInputSplit = userInput.split(" ");
        String userInputNoCommand = userInput.replace(userInputSplit[0], "");
        String commandWord = userInputSplit[0];

        switch (commandWord) {
        case ExitCommand.COMMAND_WORD:
            return prepareExitCommand();
        case ListCommand.COMMAND_WORD:
            return prepareListCommand();
        case TodoCommand.COMMAND_WORD:
            return prepareTodoCommand(userInputNoCommand);
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadlineCommand(userInputNoCommand);
        case EventCommand.COMMAND_WORD:
            return prepareEventCommand(userInputNoCommand);
        case MarkCommand.COMMAND_WORD:
            return prepareMarkCommand(userInputNoCommand);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmarkCommand(userInputNoCommand);
        case DeleteCommand.COMMAND_WORD:
            return prepareDeleteCommand(userInputNoCommand);
        case FindCommand.COMMAND_WORD:
            return prepareFindCommand(userInputNoCommand);
        default:
            return new IncorrectCommand();
        }
    }

    /**
     * It will prepare the find command appropriately so that the command is ready to be executed.
     *
     * @param userInputNoCommand The raw input of what the user typed. Without the command portion.
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareFindCommand(String userInputNoCommand) {
        String keyword = userInputNoCommand.trim();
        return new FindCommand(keyword);
    }
    /**
     * It will prepare the delete command appropriately so that the command is ready to be executed.
     *
     * @param userInputNoCommand The raw input of what the user typed. Without the command portion.
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareDeleteCommand(String userInputNoCommand) {
        int taskNumberInList;
        try {
            taskNumberInList = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if (!TaskParser.isValidTaskNumber(taskNumberInList)) {
                throw new DukeException(Messages.ERROR_COMMAND_DELETE);
            }
            return new DeleteCommand(taskNumberInList);
        } catch (DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        } catch (NumberFormatException e) {
            Exception exception = new DukeException(Messages.ERROR_COMMAND_DELETE);
            System.out.println(exception);
            return new IncorrectCommand();
        }
    }

    /**
     * It will prepare the unmark command appropriately so that the command is ready to be executed.
     *
     * @param userInputNoCommand The raw input of what the user typed. Without the command portion.
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareUnmarkCommand(String userInputNoCommand) {
        int taskNumberInList;
        try {
            taskNumberInList = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if (!TaskParser.isValidTaskNumber(taskNumberInList)) {
                throw new DukeException(Messages.ERROR_COMMAND_UNMARK);
            }
            return new UnmarkCommand(taskNumberInList);
        } catch (DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        } catch (NumberFormatException e) {
            Exception exception = new DukeException(Messages.ERROR_COMMAND_UNMARK);
            System.out.println(exception);
            return new IncorrectCommand();
        }
    }

    /**
     * It will prepare the mark command appropriately so that the command is ready to be executed.
     *
     * @param userInputNoCommand The raw input of what the user typed. Without the command portion.
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareMarkCommand(String userInputNoCommand) {
        int taskNumberInList;
        try {
            taskNumberInList = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if (!TaskParser.isValidTaskNumber(taskNumberInList)) {
                throw new DukeException(Messages.ERROR_COMMAND_MARK);
            }
            return new MarkCommand(taskNumberInList);
        } catch (DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        } catch (NumberFormatException e) {
            Exception exception = new DukeException(Messages.ERROR_COMMAND_MARK);
            System.out.println(exception);
            return new IncorrectCommand();
        }
    }

    /**
     * It will prepare the event command appropriately so that the command is ready to be executed.
     *
     * @param userInputNoCommand The raw input of what the user typed. Without the command portion.
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareEventCommand(String userInputNoCommand) {
        String replaceSlash = userInputNoCommand.replace("/from", "--from:");
        replaceSlash = replaceSlash.replace("/to", "to:");
        String[] splitEvent = replaceSlash.split("--");
        try {
            if (userInputNoCommand.equals("") || splitEvent.length != 2) {
                throw new DukeException(Messages.ERROR_EVENT_COMMAND);
            }
        } catch (DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new EventCommand(splitEvent[0], splitEvent[1]);
    }

    /**
     * It will prepare the deadline command appropriately so that the command is ready to be executed.
     *
     * @param userInputNoCommand The raw input of what the user typed. Without the command portion.
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareDeadlineCommand(String userInputNoCommand) {
        String[] splitDeadline = userInputNoCommand.split("/by");
        try {
            if (userInputNoCommand.equals("") || splitDeadline.length != 2) {
                throw new DukeException(Messages.ERROR_DEADLINE_COMMAND);
            }
        } catch (DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new DeadlineCommand(splitDeadline[0], splitDeadline[1]);

    }

    /**
     * It will prepare the todo command appropriately so that the command is ready to be executed.
     *
     * @param userInputNoCommand The raw input of what the user typed. Without the command portion.
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareTodoCommand(String userInputNoCommand) {
        try {
            if (userInputNoCommand.trim().isEmpty()) {
                throw new DukeException(Messages.ERROR_TODO_COMMAND);
            }
        } catch (DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new TodoCommand(userInputNoCommand);
    }

    /**
     * It will prepare the list command appropriately so that the command is ready to be executed.
     *
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareListCommand() {
        return new ListCommand();
    }

    /**
     * It will prepare the exit command appropriately so that the command is ready to be executed.
     *
     * @return Command object in order for the program to know what command to execute.
     */
    private Command prepareExitCommand() {
        return new ExitCommand();
    }
}
