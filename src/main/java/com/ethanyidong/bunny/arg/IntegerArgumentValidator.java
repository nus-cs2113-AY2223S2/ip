package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

public class IntegerArgumentValidator implements ArgumentValidator {
    public void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException("is missing");
        }

        try {
            Integer.parseInt(argument);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentException("is not a number");
        }
    }
}
