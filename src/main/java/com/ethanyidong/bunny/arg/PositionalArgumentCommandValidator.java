package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.ParsedCommand;

public class PositionalArgumentCommandValidator implements CommandValidator {
    private ArgumentValidator argumentValidator;

    public PositionalArgumentCommandValidator(ArgumentValidator argumentValidator) {
        this.argumentValidator = argumentValidator;
    }

    public void validateCommand(BunnySession bunny, ParsedCommand command) throws InvalidCommandException {
        try {
            this.argumentValidator.validateArgument(bunny, command.getPositionalArgument());
        } catch(InvalidArgumentException iae) {
            throw new InvalidCommandException("positional argument", iae);
        }
    }
}
