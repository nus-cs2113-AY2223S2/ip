package wilsonoh.sagyo.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import wilsonoh.sagyo.commands.AddTaskCommand;
import wilsonoh.sagyo.commands.ByeCommand;
import wilsonoh.sagyo.commands.Command;
import wilsonoh.sagyo.commands.CommandType;
import wilsonoh.sagyo.commands.ListCommand;
import wilsonoh.sagyo.commands.MarkTaskCommand;
import wilsonoh.sagyo.commands.SaveTasksCommand;
import wilsonoh.sagyo.commands.UnMarkTaskCommand;
import wilsonoh.sagyo.exceptions.InvalidCommandException;
import wilsonoh.sagyo.exceptions.InvalidTaskException;
import wilsonoh.sagyo.storage.Storage;
import wilsonoh.sagyo.tasks.Task;

public class CommandParser {

    private ArrayList<Task> tasks;
    private Storage storage;

    public CommandParser(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    public Command parseCommand(String input) throws InvalidCommandException, InvalidTaskException {
        CommandType[] commands = CommandType.values();
        for (CommandType command : commands) {
            Pattern pattern = Pattern.compile(command.getRegexPattern(), Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                switch (command) {
                case MARK:
                case UNMARK:
                    boolean isMark = command.equals(CommandType.MARK);
                    String idxGroup = matcher.group("idx");
                    return isMark ? new MarkTaskCommand(tasks, idxGroup) : new UnMarkTaskCommand(tasks, idxGroup);
                case BYE:
                    return new ByeCommand();
                case LIST:
                    return new ListCommand(this.tasks);
                case WRITE:
                    return new SaveTasksCommand(this.tasks, this.storage);
                case DEADLINE:
                case EVENT:
                case TODO:
                    Task toAdd = TaskParser.generateTaskFromMatcher(matcher, command);
                    return new AddTaskCommand(tasks, toAdd);
                default:
                    throw new InvalidCommandException(
                        "Should not reach here because the matched regex pattern must belong to a valid command");
                }
            }
        }
        throw new InvalidCommandException();
    }
}
