package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

/**
 * Implementation of <code>ArgumentValidator</code> which checks that an argument can be parsed as an integer
 */
public class IntegerArgumentValidator implements ArgumentValidator {
    /**
     * @param bunny    the current Bunny session
     * @param argument the value of the argument to validate
     * @throws InvalidArgumentException if the passed argument cannot be parsed as an integer
     */
    public void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException {
        if (argument == null) {
            throw new InvalidArgumentException(bunny.getUI().MISSING_ARGUMENT_ERROR);
        }

        try {
            Integer.parseInt(argument);
        } catch (NumberFormatException nfe) {
            throw new InvalidArgumentException(bunny.getUI().INTEGER_ARGUMENT_ERROR);
        }
    }
}
