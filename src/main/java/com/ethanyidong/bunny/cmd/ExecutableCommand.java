package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.InvalidArgumentException;
import com.ethanyidong.bunny.arg.InvalidCommandException;

public abstract class ExecutableCommand {
    private static ExecutableCommand fromCommandString(String commandString) {
        switch (commandString) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand();
        case "mark":
            return new MarkCommand(true);
        case "unmark":
            return new MarkCommand(false);
        case "todo":
            return new AddTodoCommand();
        case "deadline":
            return new AddDeadlineCommand();
        case "event":
            return new AddEventCommand();
        case "delete":
            return new DeleteTaskCommand();
        default:
            return null;
        }
    }

    public static ExecutableCommand validateAndParse(BunnySession bunny, TokenizedCommand command)
            throws InvalidCommandException {
        ExecutableCommand newExecutableCommand = ExecutableCommand.fromCommandString(command.getCommand());

        if (newExecutableCommand == null) {
            throw new InvalidCommandException("command", new InvalidArgumentException("is unrecognized"));
        }
        newExecutableCommand.validateCommand(bunny, command);
        newExecutableCommand.parseArguments(bunny, command);
        return newExecutableCommand;
    }

    protected CommandValidator[] validators() {
        return new CommandValidator[]{};
    }

    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {

    }

    private void validateCommand(BunnySession bunny, TokenizedCommand command)
            throws InvalidCommandException {
        for (CommandValidator validator : this.validators()) {
            validator.validateCommand(bunny, command);
        }
    }

    public abstract void execute(BunnySession bunny);
}

