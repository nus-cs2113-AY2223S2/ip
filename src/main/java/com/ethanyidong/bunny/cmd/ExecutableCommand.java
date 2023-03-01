package com.ethanyidong.bunny.cmd;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.arg.CommandValidator;
import com.ethanyidong.bunny.arg.InvalidArgumentException;
import com.ethanyidong.bunny.arg.InvalidCommandException;

/**
 * Represents a command and its parameters.
 * Implementors <code>ExecutableCommand</code> will be passed the <code>BunnySession</code> object to run on and mutate.
 */
public abstract class ExecutableCommand {
    /**
     * Maps the command name to a concrete <code>ExecutableCommand</code> class
     *
     * @param commandString the name of the command
     * @return a new instance of the <code>ExecutableCommand</code> for that command name
     */
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

    /**
     * Parses a <code>TokenizedCommand</code> into the correct <code>ExecutableCommand</code>.
     * Uses the specific implementation of <code>ExecutableCommand</code>'s
     * <code>validateCommand</code> and <code>parseArguments</code> methods
     * to validate the input and parse the arguments.
     *
     * @param bunny   the current Bunny session
     * @param command the <code>TokenizedCommand</code> representation of the input
     * @return a new <code>ExecutableCommand</code> which has been parsed from the passed <code>command</code>
     * @throws InvalidCommandException if the passed <code>command</code> does not represent a valid command.
     */
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

    /**
     * Provides a list of command validators.
     * Implementors of <code>ExecutableCommand</code> with arguments to be validated should override this method
     *
     * @return a list of command validators to be run by <code>validateCommand()</code>
     */
    protected CommandValidator[] getValidators() {
        return new CommandValidator[]{};
    }

    /**
     * Parses input from the <code>command</code>
     * and stores the arguments it into the current <code>ExecutableCommand</code>'s properties
     *
     * @param bunny   the current Bunny session
     * @param command the <code>TokenizedCommand</code> to parse
     */
    protected void parseArguments(BunnySession bunny, TokenizedCommand command) {

    }

    /**
     * Validates the <code>command</code> using validators provided by <code>validators()</code>
     *
     * @param bunny   the current Bunny session
     * @param command the <code>TokenizedCommand</code> to validate
     * @throws InvalidCommandException if the passed <code>command</code> does not represent a valid command
     */
    private void validateCommand(BunnySession bunny, TokenizedCommand command)
            throws InvalidCommandException {
        for (CommandValidator validator : this.getValidators()) {
            validator.validateCommand(bunny, command);
        }
    }

    /**
     * Executes the <code>ExecutableCommand</code> on the passed Bunny session
     *
     * @param bunny the current Bunny session
     */
    public abstract void execute(BunnySession bunny);
}

