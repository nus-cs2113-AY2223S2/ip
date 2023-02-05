package dev.joulev.archduke.exceptions;

public class OtherException extends ArchdukeException {
    public OtherException(String message) {
        super(message);
    }

    public String getErrorString() {
        return getMessage();
    }
}
