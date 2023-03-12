package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

/**
 * An implementation of <code>CommandValidator</code> which validates a flag argument
 */
public class FlagArgumentCommandValidator implements CommandValidator {
    private final String flag;
    private final ArgumentValidator argumentValidator;

    /**
     * @param flag              the name of the flag argument to validate
     * @param argumentValidator the validator to run on the flag argument
     */
    public FlagArgumentCommandValidator(String flag, ArgumentValidator argumentValidator) {
        this.flag = flag;
        this.argumentValidator = argumentValidator;
    }

    /**
     * Validates the specified flag argument using the provided argument validator
     *
     * @param bunny   the current Bunny session
     * @param command the tokenized command to validate
     * @throws InvalidCommandException if the flag argument is invalid according to the argument validator
     */
    public void validateCommand(BunnySession bunny, TokenizedCommand command) throws InvalidCommandException {
        try {
            this.argumentValidator.validateArgument(bunny, command.getFlagArgument(this.flag));
        } catch (InvalidArgumentException iae) {
            throw new InvalidCommandException(String.format(bunny.getUI().FLAG_ARGUMENT_ERROR_FORMAT, flag), iae);
        }
    }
}
