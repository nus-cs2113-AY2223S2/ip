package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DateCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ModifyCommand;
import duke.exception.EmptyKeywordException;
import duke.exception.EmptyTaskDescException;
import duke.exception.IllegalCommandException;
import duke.exception.InvalidDateTime;
import duke.exception.InvalidDeadline;
import duke.exception.InvalidEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser class that makes sense of user commands or text.
 */
public class Parser {

    // User command words understood by Duke
    public static final String COMMAND_EXIT_WORD = "bye";
    public static final String COMMAND_HELP_WORD = "help";
    public static final String COMMAND_LIST_WORD = "list";
    public static final String COMMAND_DATE_WORD = "date";
    public static final String COMMAND_FIND_WORD = "find";
    public static final String COMMAND_MARK_WORD = "mark";
    public static final String COMMAND_UNMARK_WORD = "unmark";
    public static final String COMMAND_DELETE_WORD = "delete";
    public static final String COMMAND_TODO_WORD = "todo";
    public static final String COMMAND_DEADLINE_WORD = "deadline";
    public static final String COMMAND_EVENT_WORD = "event";

    /**
     * Reads in user input and returns a different type of Command based on that.
     *
     * @param userCommand Command line input from user
     * @param ui Prints out error messages if command cannot be parsed
     * @param size Number of tasks saved in TaskList
     * @return Corresponding Command class to user input
     */
    public static Command getCommand(String userCommand, Ui ui, int size) {
        final String[] split = userCommand.trim().split("\\s+", 2);
        String command = split[0];
        try {
            return parseCommand(split, command, size);
        } catch (IllegalCommandException e) {
            ui.printInvalidCommand();
        } catch (EmptyTaskDescException e) {
            ui.printEmptyDescription();
        } catch (EmptyKeywordException e) {
            ui.printEmptyKeyword();
        } catch (NumberFormatException e) {
            ui.printErrorForIdx(size);
        } catch (InvalidDeadline e) {
            ui.printInvalidDeadline();
        } catch (InvalidEvent e) {
            ui.printInvalidEvent();
        } catch (InvalidDateTime e) {
            ui.printInvalidDateTime();
        }
        return null;
    }

    /**
     * Takes data from the user input and puts it into the relevant Command class that is returned.
     *
     * @param split Truncated part of user input containing other instructions
     * @param command Truncated part of user input containing command
     * @param size Number of tasks saved in TaskList
     * @return Command class with data input
     * @throws InvalidDateTime If the input format for a date and time is wrong
     * @throws EmptyKeywordException If keyword is left empty (for Find command)
     * @throws NumberFormatException If the index is left empty (for Mark, Delete commands)
     * @throws EmptyTaskDescException If task description is left empty (for Add command)
     * @throws InvalidDeadline If the input format for adding a deadline is wrong
     * @throws InvalidEvent If the input format for adding an event is wrong
     * @throws IllegalCommandException If an unknown command is input by the user
     */
    private static Command parseCommand(String[] split, String command, int size) throws InvalidDateTime, EmptyKeywordException,
            EmptyTaskDescException, InvalidDeadline, InvalidEvent, IllegalCommandException, NumberFormatException {
        switch (command) {
        case COMMAND_EXIT_WORD:
            return new ExitCommand();
        case COMMAND_HELP_WORD:
            return new HelpCommand();
        case COMMAND_LIST_WORD:
            return new ListCommand();
        case COMMAND_DATE_WORD:
            if (split.length != 2) {
                throw new InvalidDateTime();
            }
            return new DateCommand(split[1]);
        case COMMAND_FIND_WORD:
            if (split.length != 2) {
                throw new EmptyKeywordException();
            }
            return new FindCommand(split[1]);
        case COMMAND_MARK_WORD:
        case COMMAND_UNMARK_WORD:
        case COMMAND_DELETE_WORD:
            if (split.length != 2) {
                throw new NumberFormatException();
            }
            return new ModifyCommand(command, split[1], size);
        case COMMAND_TODO_WORD:
        case COMMAND_DEADLINE_WORD:
        case COMMAND_EVENT_WORD:
            if (split.length != 2) {
                throw new EmptyTaskDescException();
            }
            return new AddCommand(command, split[1]);
        default:
            throw new IllegalCommandException();
        }
    }

    /**
     * Parses the input date and time, returns it as a String according to the given pattern.
     *
     * @param date Input date and time in the form of LocalDateTime
     * @param dateString Input date and time in the form of a String
     * @param pattern Format that the input date and time should be parsed into
     * @return String with parsed date and time
     */
    public static String parseDateTime(LocalDateTime date, String dateString, DateTimeFormatter pattern) {
        if (date != null) {
            return date.format(pattern);
        }
        return dateString;
    }

    /**
     * Separates the user input for a Deadline into the Deadline's description and its due date.
     *
     * @param param CLI user input after "deadline"
     * @return String array consisting separated description and due date of the deadline
     * @throws InvalidDeadline If the user did not input the due date in the right format
     */
    public static String[] parseDeadline(String param) throws InvalidDeadline {
        String[] split = param.trim().split("\\s/by\\s", 2);
        if (split.length != 2) {
            throw new InvalidDeadline();
        }
        return split;
    }

    /**
     * Separates the user input for a Deadline into the Event's description and its start and end date.
     *
     * @param param CLI user input after "event"
     * @return String array consisting separated description, start date, and end date of the event
     * @throws InvalidEvent If the user did not input the start or end date in the right format
     */
    public static String[] parseEvent(String param) throws InvalidEvent {
        String[] split = param.trim().split("\\s/from\\s|\\s/to\\s", 3);
        if (split.length != 3) {
            throw new InvalidEvent();
        }
        return split;
    }

}
