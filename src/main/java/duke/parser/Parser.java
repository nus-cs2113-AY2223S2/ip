package duke.parser;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

public class Parser {
    public  Parser() {}

    public Command parseCommand(String userInputCommand) throws IllegalArgumentException {
        String[] args = userInputCommand.split(" ");

        switch (args[0]) {
            case TodoCommand.COMMAND_WORD:
                return parseTodo(args);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();
            case ListCommand.COMMAND_WORD:
                return new ListCommand();
            case HelpCommand.COMMAND_WORD: //fall through
            default:
                return new HelpCommand();
        }
    }

    private Command parseTodo(String[] args) throws  IllegalArgumentException {
        assert args[0].equals("todo");
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]).append(" ");
        }
        if (sb.toString().isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String content = sb.toString().trim();
        return new TodoCommand(content);
    }
}
