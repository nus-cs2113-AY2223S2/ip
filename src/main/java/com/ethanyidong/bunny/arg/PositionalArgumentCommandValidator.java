package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

/**
 * An implementation of <code>CommandValidator</code> which validates the positional argument
 */
public class PositionalArgumentCommandValidator implements CommandValidator {
    private final ArgumentValidator argumentValidator;

    /**
     * @param argumentValidator the validator to run on the positional argument
     */
    public PositionalArgumentCommandValidator(ArgumentValidator argumentValidator) {
        this.argumentValidator = argumentValidator;
    }

    /**
     * Validates the positional argument using the provided argument validator
     *
     * @param bunny   the current Bunny session
     * @param command the tokenized command to validate
     * @throws InvalidCommandException if the positional argument is invalid according to the argument validator
     */
    public void validateCommand(BunnySession bunny, TokenizedCommand command) throws InvalidCommandException {
        try {
            this.argumentValidator.validateArgument(bunny, command.getPositionalArgument());
        } catch (InvalidArgumentException iae) {
            throw new InvalidCommandException(bunny.getUI().POSITIONAL_ARGUMENT_ERROR, iae);
        }
    }
}
