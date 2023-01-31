package wilsonoh.sagyo.parser;

import java.util.regex.Matcher;

import wilsonoh.sagyo.commands.CommandType;
import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.tasks.DeadlineTask;
import wilsonoh.sagyo.tasks.EventTask;
import wilsonoh.sagyo.tasks.Task;
import wilsonoh.sagyo.tasks.TodoTask;

public class TaskParser {

    public static Task generateTaskFromMatcher(Matcher matcher, CommandType taskType) throws InvalidTaskException {
        Task task;
        String taskName = matcher.group("name");
        if (taskName == null) {
            throw new InvalidTaskException("The description of a task cannot be empty.");
        }
        switch (taskType) {
        case DEADLINE:
            String by = matcher.group("by");
            if (by == null) {
                throw new InvalidTaskException("Invalid syntax. deadline command must contain the /by keyword");
            }
            task = new DeadlineTask(taskName, matcher.group("by"));
            break;
        case EVENT:
            String from = matcher.group("from");
            String to = matcher.group("to");
            if (from == null || to == null) {
                throw new InvalidTaskException("Invalid syntax. event command must contain the /from and /to keywords");
            }
            task = new EventTask(taskName, matcher.group("from"), matcher.group("to"));
            break;
        case TODO:
            task = new TodoTask(taskName);
            break;
        default:
            throw new IllegalArgumentException("Should not reach here as the CommandType must be a Task type.");
        }
        return task;
    }
}
