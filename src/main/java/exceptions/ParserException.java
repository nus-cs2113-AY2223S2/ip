package exceptions;

import io.Out;

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

    public void printError() {
        switch (code) {
        case UNKNOWN_COMMAND:
            Out.printError("Unknown command: \"%s\". Please try again.", payload);
            break;
        case UNKNOWN_OPTION:
            Out.printError("Unknown option: \"%s\". Please try again.", payload);
            break;
        }
    }
}
