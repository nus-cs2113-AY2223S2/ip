package dev.joulev.archduke.exceptions;

/**
 * This class represents any exceptions that are thrown in the parser caused by
 * malformed or invalid user input. See {@link ParserExceptionCode} for the list
 * of possible error codes.
 */
public class ParserException extends ArchdukeException {
    public enum ParserExceptionCode {
        UNKNOWN_COMMAND, UNKNOWN_OPTION,
    }

    ParserExceptionCode code;
    String payload;

    /**
     * Constructs a new {@link ParserException} exception.
     * 
     * @param code    The code of the exception (see {@link ParserExceptionCode}).
     * @param payload The payload of the exception which might be used for the error
     *                message string. For {@link #UNKNOWN_COMMAND}, the payload is
     *                the unknown command in question. For {@link #UNKNOWN_OPTION},
     *                the payload is the unknown option.
     */
    public ParserException(ParserExceptionCode code, String payload) {
        super("Parser failed with code " + code + ".");
        this.code = code;
        this.payload = payload;
    }

    /**
     * Constructs a new {@link ParserException} exception.
     * 
     * @param code    The code of the exception (see {@link ParserExceptionCode}).
     * @param payload The payload of the exception which might be used for the error
     *                message string. For {@link #UNKNOWN_COMMAND}, the payload is
     *                the unknown command in question. For {@link #UNKNOWN_OPTION},
     *                the payload is the unknown option.
     * @param message The error message (if any).
     */
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
