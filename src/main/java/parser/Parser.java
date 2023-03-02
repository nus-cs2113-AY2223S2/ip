package parser;

import constants.Command;
import constants.ErrorMessage;
import constants.Keyword;
import exception.DukeException;
import ui.Ui;

import java.util.HashMap;
import java.util.regex.PatternSyntaxException;

public class Parser {
    protected final Ui ui = Ui.getInstance();

    protected HashMap<String, String> handleTodo(String text) {
        HashMap<String, String> dictionary = new HashMap<>();
        putTodoPromptInDictionary(text, dictionary);
        return dictionary;
    }

    private static void putTodoPromptInDictionary(String text, HashMap<String, String> dictionary) {
        dictionary.put(Keyword.COMMAND, Command.TODO);
        dictionary.put(Keyword.DESCRIPTION, text.trim());
    }

    protected HashMap<String, String> handleDeadline(String text) throws DukeException {
        try {
            HashMap<String, String> dictionary = new HashMap<>();
            String[] words = text.split(Keyword.BY);
            if (words.length == 1) {
                throw new DukeException(ErrorMessage.INVALID_INPUT);
            }
            String description = words[0].trim();
            String deadline = words[1].trim();
            putDeadlinePromptsInDictionary(dictionary, description, deadline);
            return dictionary;
        } catch (PatternSyntaxException e) {
            ui.printMessage(ErrorMessage.NO_DEADLINE_PROVIDED);
            return null;
        }
    }

    private static void putDeadlinePromptsInDictionary(
            HashMap<String, String> dictionary,
            String description,
            String deadline
    ) {
        dictionary.put(Keyword.COMMAND, Command.DEADLINE);
        dictionary.put(Keyword.DESCRIPTION, description);
        dictionary.put(Keyword.DEADLINE, deadline);
    }

    protected HashMap<String, String> handleMarkAndDelete(String text, String command) {
        HashMap<String, String> dictionary = new HashMap<>();
        dictionary.put(Keyword.COMMAND, command);
        dictionary.put(Keyword.INDEX, text);
        return dictionary;
    }

    protected HashMap<String, String> handleOthers(String command) {
        HashMap<String, String> dictionary = new HashMap<>();
        dictionary.put(Keyword.COMMAND, command);
        return dictionary;
    }

    protected HashMap<String, String> handleEvent(String text) throws DukeException {
        HashMap<String, String> dictionary = new HashMap<>();
        String[] words = splitInput(text);
        validateStartAndEnd(words);

        String start = "";
        String end = "";
        String description = "";
        for (int i = 0; i < 3; ++i) {
            if (words[i].startsWith(Keyword.TO)) {
                words[i] = words[i].substring(Keyword.TO.length()).trim();
                end = words[i];
            } else if (words[i].startsWith(Keyword.FROM)) {
                words[i] = words[i].substring(Keyword.FROM.length()).trim();
                start = words[i];
            } else {
                description = words[i].trim();
            }
        }

        putEventPromptInDictionary(dictionary, start, end, description);
        return dictionary;
    }

    private static String[] splitInput(String text) throws DukeException {
        String[] words = text.split(Keyword.SLASH);
        if (words.length != 3) {
            throw new DukeException(ErrorMessage.INVALID_INPUT);
        }
        return words;
    }

    private static void putEventPromptInDictionary(
            HashMap<String, String> dictionary,
            String start,
            String end,
            String description
    ) {
        dictionary.put(Keyword.COMMAND, Command.EVENT);
        dictionary.put(Keyword.START, start);
        dictionary.put(Keyword.END, end);
        dictionary.put(Keyword.DESCRIPTION, description);
    }

    private static void validateStartAndEnd(String[] words) throws DukeException {
        int numOfStart = 0;
        int numOfEnd = 0;
        for (String word : words) {
            if (word.startsWith(Keyword.TO)) {
                numOfEnd++;
            } else if (word.startsWith(Keyword.FROM)) {
                numOfStart++;
            }
        }

        if (numOfEnd == 0 && numOfStart == 0) {
            throw new DukeException(ErrorMessage.NO_START_OR_END);
        }

        if (numOfEnd > 1 || numOfStart > 1) {
            throw new DukeException(ErrorMessage.NO_DESCRIPTION_PROVIDED);
        }
    }


    protected HashMap<String, String> handleFind(String keyword) {
        HashMap<String, String> dictionary = new HashMap<>();
        keyword = keyword.trim();
        dictionary.put(Keyword.COMMAND, Command.FIND);
        dictionary.put(Keyword.KEYWORD, keyword);
        return dictionary;
    }

    public HashMap<String, String> parse(String input) throws DukeException {
        input = input.trim();
        String[] words = input.split(Keyword.SPACE, 2);
        String command = words[0];

        switch (command) {
        case Command.FIND:
            return handleFind(words[1]);
        case Command.LIST:
        case Command.BYE:
            return handleOthers(command);
        case Command.TODO:
            return handleTodo(words[1]);
        case Command.MARK:
        case Command.UNMARK:
        case Command.DELETE:
            if (words.length == 1) {
                throw new DukeException(ErrorMessage.INVALID_INDEX);
            }
            return handleMarkAndDelete(words[1], command);
        case Command.DEADLINE:
            return handleDeadline(words[1]);
        case Command.EVENT:
            return handleEvent(words[1]);
        default:
            throw new DukeException(ErrorMessage.UNKNOWN_COMMAND);
        }

    }
}
