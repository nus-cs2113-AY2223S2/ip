package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

/**
 * Implementation of <code>ArgumentValidator</code> which checks that an argument exists and is not empty
 */
public class NotEmptyArgumentValidator implements ArgumentValidator {
    /**
     * @param bunny    the current Bunny session
     * @param argument the value of the argument to validate
     * @throws InvalidArgumentException if the argument does not exist or is empty
     */
    public void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException(bunny.getUI().MISSING_ARGUMENT_ERROR);
        }

        if (argument.isEmpty()) {
            throw new InvalidArgumentException(bunny.getUI().EMPTY_ARGUMENT_ERROR);
        }
    }
}
