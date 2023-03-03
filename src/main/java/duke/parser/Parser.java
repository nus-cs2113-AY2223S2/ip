package duke.parser;

import duke.command.*;
import duke.error.DukeException;
import duke.error.ErrorTypes;
import duke.task.DateTime;

import java.time.LocalDate;

/** Parses user input  */
public class Parser {

    /**
     * Parses user input into command for execution
     *
     * @param input input given by user
     * @return the command based on the user input
     */
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
        case (DateCommand.COMMAND_WORD):
            return prepareDateCommand(input);
        default:
            return new InvalidCommand(ErrorTypes.INVALID_INPUT);
        }
    }

    /**
     * Checks and prepares the parameters required to initiate a new mark/unmark/delete command and creates the
     * relevant command
     *
     * @param input an array of the user input, separated by " "
     * @param command command given by user: mark/unmark/delete
     * @return the command based on the input provided by user
     * @throws IndexOutOfBoundsException when task number provided by user > total task count
     */
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

    /**
     * Checks and prepares the parameters required to initiate a todo command and creates the relevant command
     *
     * @param input input given by the user
     * @return the command based on the input provided by user
     */
    private Command prepareTodoCommand(String input) {
        try {
            InputValidity.checkTodo(input);
            String taskName = input.replaceFirst(AddCommand.COMMAND_TODO, "").trim();
            return new TodoCommand(taskName);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_TODO);
        }
    }

    /**
     * Checks and prepares the parameters required to initiate a deadline command and creates the relevant command
     *
     * @param input input given by the user
     * @return the command based on the input provided by user
     */
    private Command prepareDeadlineCommand(String input) {
        try {
            InputValidity.checkDeadline(input);
            input = input.replaceFirst(AddCommand.COMMAND_DEADLINE, "").trim();
            InputValidity.checkTaskName(input, AddCommand.COMMAND_DEADLINE);
            String taskName = input.split(InputValidity.DEADLINE_DELIMITER, 2)[0].trim();
            String deadline = input.split(InputValidity.DEADLINE_DELIMITER, 2)[1].trim();
            return new DeadlineCommand(taskName, deadline);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_DEADLINE_COMMAND);
        }
    }

    /**
     * Checks and prepares the parameters required to initiate an event command and creates the relevant command
     *
     * @param input input given by the user
     * @return the command based on the input provided by user
     */
    private Command prepareEventCommand(String input) {
        try {
            InputValidity.checkValidEvent(input);
            input = input.replaceFirst(AddCommand.COMMAND_EVENT, "").trim();
            InputValidity.checkTaskName(input, AddCommand.COMMAND_EVENT);
            String taskName = input.split(InputValidity.EVENT_FROM_DELIMITER, 2)[0].trim();
            input = input.replaceFirst(taskName + InputValidity.EVENT_FROM_DELIMITER, "").trim();
            String startDate = input.split(InputValidity.EVENT_TO_DELIMITER, 2)[0].trim();
            String endDate = input.split(InputValidity.EVENT_TO_DELIMITER, 2)[1].trim();
            return new EventCommand(taskName, startDate, endDate);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_EVENT_COMMAND);
        }
    }

    /**
     * Checks whether the command provided by user is valid for mark, unmark and delete
     *
     * @param userInput an array of the user input, separated by " "
     * @param command command word provided by user: mark/unmark/delete
     * @return string representing the task number
     * @throws DukeException when command provided by user is invalid
     */
    private String parseMarkUnmarkDelete(String[] userInput, String command) throws DukeException {
        InputValidity.checkValid(userInput, command);
        return userInput[1].trim();
    }

    /**
     * Checks and prepares the parameters required to initiate a date command and create the relevant command
     *
     * @param input input given by the user
     * @return the command based on the input provided by user
     */
    private Command prepareFindCOmmand(String input) {
        try {
            InputValidity.checkValidFind(input);
            String keyword = input.replaceFirst("find ", "").trim();
            return new FindCommand(keyword);
        } catch (DukeException e) {
            return new InvalidCommand(ErrorTypes.INVALID_FIND_COMMAND);
        }
    }

    /**
    * Checks and prepares the parameters required to initiate a date command and creates the relevant command
    *
     * @param input input given by the user
     * @return the command based on the input provided by user
    */
    private Command prepareDateCommand(String input) {
        try {
            input = input.replaceFirst(DateCommand.COMMAND_WORD, "");
            LocalDate date = LocalDate.parse(input.trim(), DateTime.inputDateFormat);
            return new DateCommand(date);
        } catch (Exception e) {
            return new InvalidCommand(ErrorTypes.INVALID_DATE);
        }
    }
}
