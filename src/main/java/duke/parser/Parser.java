package duke.parser;

import duke.command.*;
import duke.exception.NoKeyException;

public class Parser {
    /**
     * Parses the input from user
     * @param userInput Description of task entered by user
     * @throws NoKeyException If keywords are missing from user input
     */
    public static void parseCommand(String userInput) throws NoKeyException {
        String key;
        String item;
        Boolean hasKeyword = false;
        String[] keyWords = {"/find", "/list", "/bye", "/mark", "/unmark", "/todo", "/event", "/deadline", "/help" };
        for (String keyWord : keyWords) {
            if (userInput.contains(keyWord)) {
                hasKeyword = true;
                break;
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
            case "/find":
                command= new FindCommand(item);
                command.execute();
            case "/bye":
                break;
            case "/help":
            default:
                command = new HelpCommand();
                command.execute();
        }
    }
}

