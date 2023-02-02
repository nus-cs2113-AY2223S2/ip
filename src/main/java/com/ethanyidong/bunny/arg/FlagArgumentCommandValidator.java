package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.ParsedCommand;

public class FlagArgumentCommandValidator implements CommandValidator {
    private String flag;
    private ArgumentValidator argumentValidator;

    public FlagArgumentCommandValidator(String flag, ArgumentValidator argumentValidator) {
        this.flag = flag;
        this.argumentValidator = argumentValidator;
    }

    public boolean isValidCommand(BunnySession bunny, ParsedCommand command) {
        return this.argumentValidator.isValidArgument(bunny, command.getFlagArgument(this.flag));
    }
}
