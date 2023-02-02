package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.ParsedCommand;

public class PositionalArgumentCommandValidator implements CommandValidator {
    private ArgumentValidator argumentValidator;

    public PositionalArgumentCommandValidator(ArgumentValidator argumentValidator) {
        this.argumentValidator = argumentValidator;
    }

    public boolean isValidCommand(BunnySession bunny, ParsedCommand command) {
        return this.argumentValidator.isValidArgument(bunny, command.getPositionalArgument());
    }
}
