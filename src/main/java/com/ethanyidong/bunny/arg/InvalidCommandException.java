package com.ethanyidong.bunny.arg;

import com.ethanyidong.bunny.BunnySession;

/**
 * Represents a custom exception for when an invalid command is detected
 * May be caused by an <code>InvalidArgumentException</code>
 */
public class InvalidCommandException extends Exception {
    /**
     * @param message the message to print when displayed to the user
     * @param cause   the <code>InvalidArgumentException</code> which caused this <code>InvalidCommandException</code>
     */
    public InvalidCommandException(String message, InvalidArgumentException cause) {
        super(message, cause);
    }

    public String getFriendlyErrorMessage(BunnySession bunny) {
        return String.format(String.format(bunny.getUI().INVALID_COMMAND_ERROR_FORMAT,
                this.getMessage(), this.getCause().getMessage()));
    }
}
