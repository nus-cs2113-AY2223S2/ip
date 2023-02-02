package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;
import com.ethanyidong.bunny.ParsedCommand;

public interface CommandValidator {
    boolean isValidCommand(BunnySession bunny, ParsedCommand command);
}
