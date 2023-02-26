package duke.parser;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskFormatException;
import duke.tasks.Event;
import duke.tasks.TaskEnum;

import java.time.LocalDateTime;

public class EventParser implements TaskParser {
    private static final String KEYWORD_FROM = "/from";
    private static final String KEYWORD_TO = "/to";

    public Event parseInput(String input) throws InvalidTaskFormatException, InvalidDateTimeException {
        TaskParser.checkValidInput(input, TaskEnum.EVENT);
        int fromStartIndex = input.indexOf(KEYWORD_FROM);
        int toStartIndex = input.indexOf(KEYWORD_TO);
        String description = input.substring(0, fromStartIndex).trim();
        String fromString = input.substring(fromStartIndex + KEYWORD_FROM.length(), toStartIndex).trim();
        String toString = input.substring(toStartIndex + KEYWORD_TO.length()).trim();
        LocalDateTime fromDateTime = DateTimeParser.parse(fromString);
        LocalDateTime toDateTime = DateTimeParser.parse(toString);
        return new Event(description, fromDateTime, toDateTime);
    }
}
