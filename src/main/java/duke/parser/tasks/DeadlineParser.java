package duke.parser.tasks;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskFormatException;
import duke.parser.datetime.DateTimeParser;
import duke.tasks.Deadline;
import duke.tasks.TaskEnum;

import java.time.LocalDateTime;

public class DeadlineParser implements TaskParser {
    private static final String KEYWORD_BY = "/by";

    public Deadline parseInput(String input) throws InvalidTaskFormatException, InvalidDateTimeException {
        TaskParser.checkValidInput(input, TaskEnum.DEADLINE);
        int byStartIndex = input.indexOf(KEYWORD_BY);
        String description = input.substring(0, byStartIndex).trim();
        String byString = input.substring(byStartIndex + KEYWORD_BY.length()).trim();
        LocalDateTime byDateTime = DateTimeParser.parse(byString);
        return new Deadline(description, byDateTime);
    }
}
