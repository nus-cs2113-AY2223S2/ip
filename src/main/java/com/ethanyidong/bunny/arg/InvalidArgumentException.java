package com.ethanyidong.bunny.arg;

/**
 * Represents a custom exception for when an invalid argument is detected
 */
public class InvalidArgumentException extends Exception {
    /**
     * @param message the message to print when the error is displayed to the user
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
