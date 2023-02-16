package parser;

import constants.Command;
import validator.error.InvalidTaskError;

import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

public class IoParser {

    protected final String COMMAND = "command";
    protected final String DESCRIPTION = "description";
    protected final String DEADLINE = "deadline";
    protected final String BY = " /by ";

    public HashMap<String, String> handleTodo(String text) {
        HashMap<String, String> dictionary = new HashMap<String, String>();
        dictionary.put(COMMAND, Command.TODO);
        dictionary.put(DESCRIPTION, text);
        return dictionary;
    }

    public HashMap<String, String> handleDeadline(String text) throws InvalidTaskError {
        try {
            HashMap<String, String> dictionary = new HashMap<String, String>();
            String[] words = text.split(BY);
            if (words.length == 1) {
                throw new InvalidTaskError("No description has been provided");
            }
            dictionary.put(COMMAND, Command.DEADLINE);
            String description = words[0].trim();
            String deadline = words[1].trim();
            dictionary.put(DESCRIPTION, description);
            dictionary.put(DEADLINE, deadline);
            return dictionary;
        } catch (PatternSyntaxException e) {
            System.out.println("No deadline provided");
            return null;
        }
    }
}
