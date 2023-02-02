package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

public class FlagArgumentCommandValidator implements CommandValidator {
    private String flag;
    private ArgumentValidator argumentValidator;

    public FlagArgumentCommandValidator(String flag, ArgumentValidator argumentValidator) {
        this.flag = flag;
        this.argumentValidator = argumentValidator;
    }

    public void validateCommand(BunnySession bunny, TokenizedCommand command) throws InvalidCommandException {
        try {
            this.argumentValidator.validateArgument(bunny, command.getFlagArgument(this.flag));
        } catch(InvalidArgumentException iae) {
            throw new InvalidCommandException("/" + flag + " argument", iae);
        }
    }
}
