package com.ethanyidong.bunny.arg;

public class InvalidCommandException extends Exception {
    public InvalidCommandException(String message, InvalidArgumentException cause) {
        super(message, cause);
    }

    @Override
    public String toString() {
        return "Invalid command: " + this.getMessage() + " " + this.getCause().getMessage();
    }
}
