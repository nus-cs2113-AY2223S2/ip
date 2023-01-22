package wilsonoh.sagyo.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wilsonoh.sagyo.tasks.DeadlineTask;
import wilsonoh.sagyo.tasks.EventTask;
import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.tasks.TodoTask;

public class TaskParser {

    private static final Pattern EVENT_REGEX =
        Pattern.compile("\\s*(?:event|e)\\s*(?<name>.+?)\\s*/from\\s*(?<from>.+?)\\s*/to\\s*(?<to>.+)\\s*");
    private static final Pattern DEADLINE_REGEX =
        Pattern.compile("\\s*(?:deadline|d)\\s*(?<name>.+?)\\s*/by\\s*(?<by>.+)\\s*");
    private static final Pattern TODO_REGEX = Pattern.compile("\\s*(?:todo|t)\\s*(?<name>.+)\\s*");

    public Optional<Task> parseInput(String input) {
        ArrayList<Pattern> patterns = new ArrayList<>(List.of(EVENT_REGEX, DEADLINE_REGEX, TODO_REGEX));
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                if (matcher.pattern().equals(EVENT_REGEX)) {
                    return Optional.of(
                        new EventTask(matcher.group("name"), matcher.group("from"), matcher.group("to")));
                } else if (matcher.pattern().equals(DEADLINE_REGEX)) {
                    return Optional.of(new DeadlineTask(matcher.group("name"), matcher.group("by")));
                } else {
                    return Optional.of(new TodoTask(matcher.group("name")));
                }
            }
        }
        return Optional.empty();
    }
}
