package duke.parser;

import duke.command.*;
import duke.exception.NoKeyException;

public class Parser {
    public static void parseCommand(String userInput) throws NoKeyException {
        String key;
        String item;
        Boolean hasKeyword = false;
        String[] keyWords = {"/list", "/bye", "/mark", "/unmark", "/todo", "/event", "/deadline", "/help" };
        for (String keyWord : keyWords) {
            if (userInput.contains(keyWord)) {
                hasKeyword = true;
            }
        }
        if (!hasKeyword) {
            throw new NoKeyException();
        }
        if ((userInput.contains("/list") | userInput.contains("/bye"))) {
            key = userInput;
            item = null;
        } else {
            String[] userInput2 = userInput.split(" ", 2);
            key = userInput2[0];
            item = userInput2[1];
        }
        Command command;
        switch (key) {
            case "/mark":
                command = new MarkCommand(item);
                command.execute();
                break;
            case "/unmark":
                command = new UnmarkCommand(item);
                command.execute();
                break;
            case "/todo":
                command = new ToDoCommand(item);
                command.execute();
                break;
            case "/event":
                command = new EventCommand(item);
                command.execute();
                break;
            case "/deadline":
                command = new DeadlineCommand(item);
                command.execute();
                break;
            case "/list":
                command = new ListCommand();
                command.execute();
                break;
            case "/delete":
                command = new DeleteCommand(item);
                command.execute();
                break;
            case "/bye":
                break;
            case "/help":
            default:
                command = new HelpCommand();
                command.execute();
        }
    }
}

