package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.cmd.TokenizedCommand;

/**
 * Represents a class which can determine the validity of a command in context
 */
public interface CommandValidator {
    /**
     * @param bunny the current Bunny session
     * @param command the tokenized command to validate
     * @throws InvalidCommandException if the command is invalid
     */
    void validateCommand(BunnySession bunny, TokenizedCommand command) throws InvalidCommandException;
}
