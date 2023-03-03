package duke.parser.tasks;

import duke.constants.ParserConstants;
import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskFormatException;
import duke.parser.datetime.DateTimeParser;
import duke.tasks.Event;
import duke.tasks.TaskEnum;

import java.time.LocalDateTime;

public class EventParser implements TaskParser {
    public Event parseInput(String input) throws InvalidTaskFormatException, InvalidDateTimeException {
        TaskParser.checkValidInput(input, TaskEnum.EVENT);
        int fromStartIndex = input.indexOf(ParserConstants.KEYWORD_FROM);
        int toStartIndex = input.indexOf(ParserConstants.KEYWORD_TO);
        String description = input.substring(0, fromStartIndex).trim();
        String fromString = input.substring(fromStartIndex + ParserConstants.KEYWORD_FROM.length(), toStartIndex).trim();
        String toString = input.substring(toStartIndex + ParserConstants.KEYWORD_TO.length()).trim();
        LocalDateTime fromDateTime = DateTimeParser.parse(fromString);
        LocalDateTime toDateTime = DateTimeParser.parse(toString);
        // check if end date and time is after start date and time
        if (fromDateTime.compareTo(toDateTime) >= 0) {
            throw new InvalidDateTimeException();
        }
        return new Event(description, fromDateTime, toDateTime);
    }
}
