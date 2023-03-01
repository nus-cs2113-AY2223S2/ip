package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

/**
 * Represents a class which can determine the validity of an argument in context
 */
public interface ArgumentValidator {
    /**
     * Validates an argument
     * @param bunny the current Bunny session
     * @param argument the value of the argument to validate
     * @throws InvalidArgumentException if the argument is invalid
     */
    void validateArgument(BunnySession bunny, String argument) throws InvalidArgumentException;
}
