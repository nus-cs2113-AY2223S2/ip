package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

public interface CommandValidator {
    void validateCommand(BunnySession bunny, TokenizedCommand command) throws InvalidCommandException;
}
