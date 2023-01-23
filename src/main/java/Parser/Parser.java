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
    public String getMessage() {
        return message;
    }
    public boolean isList() {
        return (message.toLowerCase().contains("list"));
    }
    public boolean isExit() {
        return (message.toLowerCase().contains("bye"));
    }
    /**
     * Gets the a message from console that user inputs.
     * Checks if the input message is empty and raises Exception if empty.
     * @exception EmptyCommandException
     */
    private void getNextMessage() throws EmptyCommandException {
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
    public Command getCommand() throws EmptyCommandException {
        getNextMessage();
        if (message == null) {
            throw new EmptyCommandException("Empty command");
        }
        if (isExit()) {
            return Command.EXIT;
        }
        else if (isList()) {
            return Command.LIST;
        }
        else {
            return Command.TASK;
        }
    }
}
