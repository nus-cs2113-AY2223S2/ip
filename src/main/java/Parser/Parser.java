package Parser;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser implements IParser {
    private Scanner sc;
    private String message;
    public Parser(Scanner sc) {
        this.sc = sc;
    }
    @Override
    public void getNextMessage() throws EmptyCommandException {
        try {
            message = sc.nextLine();
        } catch (NoSuchElementException e) {
            throw new EmptyCommandException("Empty command");
        }
        if (message.isBlank()){
            throw new EmptyCommandException("Empty command");
        }
    }
    @Override
    public boolean isExit() {
        if (message == null) {
            // Null message should never happen because getNextMessage would
            // filter such cases.
            // In the event that message becomes null,
            // then this command should return true by definition.
            return true;
        }
        return (message.toLowerCase().contains("bye"));
    }
    @Override
    public String getMessage() {
        return message;
    }
}
