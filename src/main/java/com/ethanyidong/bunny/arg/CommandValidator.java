package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.ParsedCommand;

public interface CommandValidator {
    void validateCommand(BunnySession bunny, ParsedCommand command) throws InvalidCommandException;
}
