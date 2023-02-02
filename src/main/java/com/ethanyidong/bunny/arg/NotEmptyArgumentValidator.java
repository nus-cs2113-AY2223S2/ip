package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public class NotEmptyArgumentValidator implements ArgumentValidator {
    public void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException("is missing");
        }

        if (argument.isEmpty()) {
            throw new InvalidArgumentException("is empty");
        }
    }
}
