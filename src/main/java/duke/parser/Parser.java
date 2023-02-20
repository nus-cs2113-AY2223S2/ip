package duke.parser;

import duke.command.*;
import duke.error.DukeException;
import duke.error.ErrorTypes;

public class Parser { // deals with making sense of the user command
    public Command parseCommand(String input) {
        String[] arrayOfInput = input.split(" ");
        arrayOfInput[0] = arrayOfInput[0].trim();
        switch (arrayOfInput[0]) {
        case (ListCommand.COMMAND_WORD):
            return new ListCommand();
        case (HelpCommand.COMMAND_WORD):
            return new HelpCommand();
        case (MarkCommand.COMMAND_WORD):
            return prepareMarkUnmarkDelete(arrayOfInput, MarkCommand.COMMAND_WORD);
        case (UnmarkCommand.COMMAND_WORD):
            return prepareMarkUnmarkDelete(arrayOfInput, UnmarkCommand.COMMAND_WORD);
        case (DeleteCommand.COMMAND_WORD):
            return prepareMarkUnmarkDelete(arrayOfInput, DeleteCommand.COMMAND_WORD);
        case (AddCommand.COMMAND_TODO):
            return prepareTodoCommand(input);
        case (AddCommand.COMMAND_DEADLINE):
            return prepareDeadlineCommand(input);
        case (AddCommand.COMMAND_EVENT):
            return prepareEventCommand(input);
        case (FindCommand.COMMAND_WORD):
            return prepareFindCOmmand(input);
        default:
            return new InvalidCommand(ErrorTypes.INVALID_INPUT);
        }
    }

    private Command prepareMarkUnmarkDelete(String[] input, String command) throws IndexOutOfBoundsException {
        try {
            int taskNumber;
            switch (command) {
            case MarkCommand.COMMAND_WORD:
                taskNumber = Integer.parseInt(parseMarkUnmarkDelete(input, MarkCommand.COMMAND_WORD)) - 1;
                return new MarkCommand(taskNumber);
            case UnmarkCommand.COMMAND_WORD:
                taskNumber = Integer.parseInt(parseMarkUnmarkDelete(input, UnmarkCommand.COMMAND_WORD)) - 1;
                return new UnmarkCommand(taskNumber);
            case DeleteCommand.COMMAND_WORD:
                taskNumber = Integer.parseInt(parseMarkUnmarkDelete(input, DeleteCommand.COMMAND_WORD)) - 1;
                return new DeleteCommand(taskNumber);
            default:
                return new InvalidCommand(ErrorTypes.INVALID_COMMAND);
            }
        } catch (Exception e) {
            return new InvalidCommand(ErrorTypes.INVALID_COMMAND);
        }
    }

    private Command prepareTodoCommand(String input) {
        try {
            InputValidity.checkTodo(input);
            String taskName = input.replace(AddCommand.COMMAND_TODO, "").trim();
            return new TodoCommand(taskName);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_TODO);
        }
    }

    private Command prepareDeadlineCommand(String input) {
        try {
            InputValidity.checkDeadline(input);
            input = input.replace(AddCommand.COMMAND_DEADLINE, "").trim();
            String taskName = input.split(InputValidity.DEADLINE_DELIMITER, 2)[0].trim();
            String deadline = input.split(InputValidity.DEADLINE_DELIMITER, 2)[1].trim();
            return new DeadlineCommand(taskName, deadline);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_DEADLINE_COMMAND);
        }
    }

    private Command prepareEventCommand(String input) {
        try {
            InputValidity.checkValidEvent(input);
            input = input.replace(AddCommand.COMMAND_EVENT, "").trim();
            String taskName = input.split(InputValidity.EVENT_FROM_DELIMITER, 2)[0].trim();
            input = input.replace(taskName + InputValidity.EVENT_FROM_DELIMITER, "").trim();
            String startDate = input.split(InputValidity.EVENT_TO_DELIMITER, 2)[0].trim();
            String endDate = input.split(InputValidity.EVENT_TO_DELIMITER, 2)[1].trim();
            return new EventCommand(taskName, startDate, endDate);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_EVENT_COMMAND);
        }
    }

    private String parseMarkUnmarkDelete(String[] userInput, String command) throws DukeException {
        InputValidity.checkValid(userInput, command);
        return userInput[1].trim();
    }

    private Command prepareFindCOmmand(String input) {
        try {
            InputValidity.checkValidFind(input);
            String keyword = input.replace("find ", "").trim();
            return new FindCommand(keyword);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_FIND_COMMAND);
        }
    }
}
