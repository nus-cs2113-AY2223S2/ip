package Command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import Entities.Event;
import EntityUtils.DateParser;
import Exceptions.DukeException;
import Exceptions.EmptyArgumentException;
import Exceptions.InsufficientArgumentsException;
import Exceptions.InvalidDateException;
import Exceptions.InvalidDateSequenceException;
import Exceptions.NoDescriptionException;

public class AddEventCommand extends AddCommand {
    /**
     * Constructor of AddEventCommand
     * Calls parseInput method to create the task
     * @param command command string of todo. Default: "event"
     * @param input input of user
     * @throws DukeException
     */
    public AddEventCommand(String command, String input) throws DukeException {
        super(command, input);
    }

    /**
     * Helper function to parse input string
     * Creates a Deadline, Event or Todo task based on command string
     * @param command command string of todo. Default: "event"
     * @param input input of user
     * @throws DukeException
     */
    public void parseInput(String command, String input) throws DukeException {
        String taskDescription, startDateString, endDateString;
        int startDateIdx, endDateIdx;
        LocalDateTime startDate, endDate;

        
        if (input.length() == command.length()) {
            throw new NoDescriptionException(command);
        }

        startDateIdx = input.indexOf("/from ");
        endDateIdx = input.indexOf("/to ");
        if (startDateIdx == -1 || endDateIdx == -1) {
            throw new InsufficientArgumentsException(command, "event [task] /from [startDate] /to [endDate]");
        }

        taskDescription = input.substring(command.length() + 1, startDateIdx - 1);
        startDateString = input.substring(startDateIdx + 6, endDateIdx - 1);
        endDateString = input.substring(endDateIdx + 4);

        if (taskDescription.length() == 0 || startDateString.length() == 0 || endDateString.length() == 0) {
            String emptyArgument;
            if (taskDescription.length() == 0) {
                emptyArgument = "Task Name";
            } else if (startDateString.length() == 0) {
                emptyArgument = "Start Date";
            } else {
                emptyArgument = "End Date";
            }

            throw new EmptyArgumentException(emptyArgument);
        }

        try {
            startDate = DateParser.stringToDate(startDateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(startDateString);
        }
        try {
            endDate = DateParser.stringToDate(endDateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(endDateString);
        }

        // throws exception if start date is after end date
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateSequenceException(startDate, endDate);
        }

        // throws exception if end date already passed
        if (LocalDateTime.now().isAfter(endDate)) {
            throw new InvalidDateSequenceException(LocalDateTime.now(), endDate);
        }

        this.addedTask = new Event(taskDescription, false, startDate, endDate);

    }
}
