package duke.parser.tasks;

import duke.constants.ParserConstants;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Task;
import duke.tasks.TaskEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface TaskParser {
    /**
     * Helper method, checks if input string matches Pattern.
     *
     * @param input    User input to be checked
     * @param taskType Task to be matched
     * @throws InvalidTaskFormatException If pattern does not match input
     */
    static void checkValidInput(String input, TaskEnum taskType)
            throws InvalidTaskFormatException {
        Pattern pattern = ParserConstants.PATTERN_TODO;
        switch (taskType) {
        case DEADLINE:
            pattern = ParserConstants.PATTERN_DEADLINE;
            break;
        case EVENT:
            pattern = ParserConstants.PATTERN_EVENT;
            break;
        }

        Matcher matcher = pattern.matcher(input.trim());
        boolean isEmptyString = input.trim().isEmpty();
        boolean isWrongFormat = !matcher.find();
        boolean isInvalidFormat = isEmptyString || isWrongFormat;
        if (isInvalidFormat) {
            throw new InvalidTaskFormatException(taskType);
        }
    }

    /**
     * Convert user input into a task object. Checks if the user input is valid. If so, calls the appropriate
     * parser method and returns the corresponding task.
     * @param input Details of task entered by the user
     * @return Task corresponding to user input
     * @throws InvalidTaskFormatException If user input does not match the required format
     *                                    Exception message will describe the required format
     * @throws InvalidDateTimeException If input date does not match the required format
     */
    Task parseInput(String input) throws InvalidTaskFormatException, InvalidDateTimeException;
}
