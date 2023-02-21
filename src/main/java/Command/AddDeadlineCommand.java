package Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import Entities.Deadline;
import EntityUtils.DateParser;
import Exceptions.DukeException;
import Exceptions.EmptyArgumentException;
import Exceptions.InsufficientArgumentsException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidDateSequenceException;
import Exceptions.NoDescriptionException;

public class AddDeadlineCommand extends AddCommand {
    /**
     * Constructor of AddDeadlineCommand
     * Calls parseInput method to create the task
     * @param command command string of todo. Default: "deadline"
     * @param input input of user
     * @throws DukeException
     */
    public AddDeadlineCommand(String command, String input) throws DukeException {
        super(command, input);
    }

    /**
     * Helper function to parse input string
     * Creates a Deadline, Event or Todo task based on command string
     * @param command command string of todo. Default: "deadline"
     * @param input input of user
     * @throws DukeException
     */
    public void parseInput(String command, String input) throws DukeException {
        String taskDescription, endDateString;
        int endDateIdx;
        LocalDateTime endDate;

        if (input.length() == command.length()) {
            throw new NoDescriptionException(command);
        }

        endDateIdx = input.indexOf("/by ");
        if (endDateIdx == -1) {
            throw new InsufficientArgumentsException(command, "deadline [task] /by [date]");
        }

        taskDescription = input.substring(command.length() + 1, endDateIdx - 1);
        endDateString = input.substring(endDateIdx + 4);

        if (taskDescription.length() == 0 || endDateString.length() == 0) {
            String emptyArgument;
            if (taskDescription.length() == 0) {
                emptyArgument = "Task Name";
            } else {
                emptyArgument = "Start Date";
            }
            throw new EmptyArgumentException(emptyArgument);
        }

        try {
            endDate = DateParser.stringToDate(endDateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(endDateString);
        }

        // throws exception if end date already passed
        if (LocalDateTime.now().isAfter(endDate)) {
            throw new InvalidDateSequenceException(LocalDateTime.now(), endDate);
        }

        this.addedTask = new Deadline(taskDescription, false, endDate);
    }
    
}
