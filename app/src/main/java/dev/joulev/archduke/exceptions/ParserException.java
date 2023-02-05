package dev.joulev.archduke.exceptions;

public class ParserException extends ArchdukeException {
    public enum ParserExceptionCode {
        UNKNOWN_COMMAND, UNKNOWN_OPTION,
    }

    ParserExceptionCode code;
    String payload;

    public ParserException(ParserExceptionCode code, String payload) {
        super("Parser failed with code " + code + ".");
        this.code = code;
        this.payload = payload;
    }

    public ParserException(ParserExceptionCode code, String payload, String message) {
        super("Parser failed with code " + code + ": " + message);
        this.code = code;
        this.payload = payload;
    }

    public String getErrorString() throws UnknownException {
        switch (code) {
        case UNKNOWN_COMMAND:
            return String.format("Unknown command: \"%s\". Please try again.", payload);
        case UNKNOWN_OPTION:
            return String.format("Unknown option: \"%s\". Please try again.", payload);
        default:
            throw new UnknownException("ParserException; code = " + code);
        }
    }
}
