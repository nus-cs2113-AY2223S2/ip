package parser;

import constants.Command;
import constants.ErrorMessage;
import validator.error.InvalidTaskError;

import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

public class IoParser {

    protected final String COMMAND = "command";
    protected final String DESCRIPTION = "description";
    protected final String DEADLINE = "deadline";
    protected final String BY = " /by ";

    protected HashMap<String, String> handleTodo(String text) {
        HashMap<String, String> dictionary = new HashMap<String, String>();
        dictionary.put(COMMAND, Command.TODO);
        dictionary.put(DESCRIPTION, text.trim());
        return dictionary;
    }

    protected HashMap<String, String> handleDeadline(String text) throws InvalidTaskError {
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

    protected HashMap<String, String> handleMark(String text, String command) {
        HashMap<String, String> dictionary = new HashMap<String, String>();
        dictionary.put(COMMAND, command);
        dictionary.put("index", text);
        return dictionary;
    }

    protected HashMap<String, String> handleOthers(String command) {
        HashMap<String, String> dictionary = new HashMap<String, String>();
        dictionary.put(COMMAND, command);
        return dictionary;
    }

    protected HashMap<String, String> handleEvent(String text) throws InvalidTaskError {
        HashMap<String, String> dictionary = new HashMap<String, String>();
        dictionary.put(COMMAND, Command.EVENT);
        String[] words = text.split("/");
        if (words.length != 3) {
            throw new InvalidTaskError("Invalid input");
        }

        String start = "";
        String end = "";
        String description = "";

        int numOfStart = 0;
        int numOfEnd = 0;
        for (String word: words) {
            if (word.startsWith("to ")) {
                numOfEnd++;
            } else if (word.startsWith("from ")) {
                numOfStart++;
            }
        }

        if (numOfEnd == 0 && numOfStart == 0) {
            throw new InvalidTaskError("No start or end provided");
        }

        if (numOfEnd > 1 || numOfStart > 1) {
            throw new InvalidTaskError("You did not provide a description");
        }

        for (int i = 0; i < 3; ++i) {
            if (words[i].startsWith("to ")) {
                words[i] = words[i].substring("to ".length(), words[i].length()).trim();
                end = words[i];
            } else if (words[i].startsWith("from ")) {
                words[i] = words[i].substring("from ".length(), words[i].length()).trim();
                start = words[i];
            } else {
                description = words[i].trim();
            }
        }

        dictionary.put("start", start);
        dictionary.put("end", end);
        dictionary.put("description", description);
        return dictionary;
    }

    public HashMap<String, String> parse(String input) {
        input = input.trim();
        String[] words = input.split(" ", 2);
        String command = words[0];
        HashMap<String, String> dictionary = new HashMap<String, String>();

        try {
            switch (command) {
            case Command.LIST:
            case Command.BYE:
                return handleOthers(command);
            case Command.TODO:
                return handleTodo(words[1]);
            case Command.MARK:
            case Command.UNMARK:
                return handleMark(words[1], command);
            case Command.DEADLINE:
                return handleDeadline(words[1]);
            case Command.EVENT:
                return handleEvent(words[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(ErrorMessage.NO_DESCRIPTION.message);
        } catch (NumberFormatException e) {
            System.out.println(ErrorMessage.INVALID_NUMBER.message);
        } catch (Exception e) {
            System.out.printf("Server error %s\n", e.getMessage());
        }

        return dictionary;
    }
}
