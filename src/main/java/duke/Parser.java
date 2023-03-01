package duke;

import duke.commands.*;
import duke.commands.task.*;

import static duke.Ui.DISPLAYED_INDEX_OFFSET;

public class Parser {

    public Command parseCommand(String userInput) {
        String[] intermediateInput = userInput.split(" ", 2);
        String commandWord = intermediateInput[0];
        if (intermediateInput.length == 0) {
            return new IncorrectCommand(0);
        }
        if ((intermediateInput.length == 1) && (!commandWord.equals("list"))) {
            return new IncorrectCommand(2);
        }
        switch (commandWord) {
        case DeleteCommand.COMMAND_WORD: case UnmarkCommand.COMMAND_WORD: case MarkCommand.COMMAND_WORD:
            return prepareCommand(commandWord, intermediateInput[1]);
        case ToDo.COMMAND_WORD:
            return new ToDo(intermediateInput[1]);
        case Deadline.COMMAND_WORD:
            return prepareDeadline(intermediateInput[1]);
        case Event.COMMAND_WORD:
            return prepareEvent(intermediateInput[1]);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommand(intermediateInput[1]);
        default:
            return new IncorrectCommand(0);
        }
    }

    private int getIndexFromCommand(String arguments) {
        int taskIndex;
        try {
            return taskIndex = Integer.parseInt(arguments);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Command prepareCommand(String command, String arguments) {
        int taskIndex = getIndexFromCommand(arguments) - DISPLAYED_INDEX_OFFSET;
        switch (command) {
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(taskIndex);
            case UnmarkCommand.COMMAND_WORD:
                return new UnmarkCommand(taskIndex);
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(taskIndex);
            default:
                return null;
        }
    }

    private Command prepareEvent(String arguments) {
        int fromIndex = arguments.indexOf("/from");
        int toIndex = arguments.indexOf("/to");
        try {
            String taskDescriptor = arguments.substring(0, fromIndex - 1);
            String taskFrom = arguments.substring(fromIndex + 6, toIndex - 1);
            String taskTo = arguments.substring(toIndex + 4);
            return new Event(taskDescriptor, taskFrom, taskTo);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand(1);
        }
    }

    private Command prepareDeadline(String arguments) {
        int byIndex = arguments.indexOf("/by");
        try {
            String taskDescriptor = arguments.substring(0, byIndex - 1);
            String taskBy = arguments.substring(byIndex + 4);
            return new Deadline(taskDescriptor, taskBy);
        } catch (IndexOutOfBoundsException e) {
            return new IncorrectCommand(1);
        }
    }

}
