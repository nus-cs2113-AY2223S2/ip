package parser;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.IncorrectCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.TodoCommand;
import commands.UnmarkCommand;
import common.Messages;
import exceptions.DukeException;
import task.TaskParser;

public class Parser {
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
        default:
            return new IncorrectCommand();
        }
    }

    private Command prepareDeleteCommand(String userInputNoCommand) {
        int taskNumberInList;
        try{
            taskNumberInList = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if(!TaskParser.isValidTaskNumber(taskNumberInList)) {
                throw new DukeException(Messages.ERROR_COMMAND_DELETE);
            }
            return new DeleteCommand(taskNumberInList);
        }catch(DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }catch(NumberFormatException e) {
            Exception exception = new DukeException(Messages.ERROR_COMMAND_DELETE);
            System.out.println(exception);
            return new IncorrectCommand();
        }
    }
    private Command prepareUnmarkCommand(String userInputNoCommand) {
        int taskNumberInList;
        try{
            taskNumberInList = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if(!TaskParser.isValidTaskNumber(taskNumberInList)) {
                throw new DukeException(Messages.ERROR_COMMAND_UNMARK);
            }
            return new UnmarkCommand(taskNumberInList);
        }catch(DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }catch(NumberFormatException e) {
            Exception exception = new DukeException(Messages.ERROR_COMMAND_UNMARK);
            System.out.println(exception);
            return new IncorrectCommand();
        }
    }
    private Command prepareMarkCommand(String userInputNoCommand) {
        int taskNumberInList;
        try{
            taskNumberInList = Integer.parseInt(userInputNoCommand.trim()) - 1;
            if(!TaskParser.isValidTaskNumber(taskNumberInList)) {
                throw new DukeException(Messages.ERROR_COMMAND_MARK);
            }
            return new MarkCommand(taskNumberInList);
        }catch(DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }catch(NumberFormatException e) {
            Exception exception = new DukeException(Messages.ERROR_COMMAND_MARK);
            System.out.println(exception);
            return new IncorrectCommand();
        }
    }
    private Command prepareEventCommand(String userInputNoCommand) {
        String replaceSlash = userInputNoCommand.replace("/from", "--from:");
        replaceSlash = replaceSlash.replace("/to", "to:");
        String[] splitEvent = replaceSlash.split("--");
        try{
            if (userInputNoCommand.equals("") || splitEvent.length != 2) {
                throw new DukeException(Messages.ERROR_EVENT_COMMAND);
            }
        }catch (DukeException e) {
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new EventCommand(splitEvent[0], splitEvent[1]);
    }
    private Command prepareDeadlineCommand(String userInputNoCommand) {
        String[] splitDeadline = userInputNoCommand.split("/by");
        try{
            if (userInputNoCommand.equals("") || splitDeadline.length != 2) {
                throw new DukeException(Messages.ERROR_DEADLINE_COMMAND);
            }
        }catch (DukeException e){
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new DeadlineCommand(splitDeadline[0], splitDeadline[1]);

    }
    private Command prepareTodoCommand(String userInputNoCommand) {
        try{
            if (userInputNoCommand.trim().isEmpty()){
                throw new DukeException(Messages.ERROR_TODO_COMMAND);
            }
        }catch (DukeException e){
            System.out.println(e);
            return new IncorrectCommand();
        }
        return new TodoCommand(userInputNoCommand);
    }
    private Command prepareListCommand() {
        return new ListCommand();
    }
    private Command prepareExitCommand() {
        return new ExitCommand();
    }
}
