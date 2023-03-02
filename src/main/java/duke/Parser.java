package duke;
import duke.addable.Deadline;
import duke.exception.UnknownCommandException;

import java.util.Scanner;
import java.util.Arrays;

/**
 * Class to parse user input and return parsed input
 */
public class Parser {
    Scanner in;
    public Parser() {
        in = new Scanner(System.in);
    }

    /**
     * Returns command being run based on words input by user
     *
     * @param input Words input by user
     * @return enum representing command that user is trying to run
     * @throws UnknownCommandException if user inputs an unknown command
     */
    public Command getCommand(String input) throws UnknownCommandException {
        String firstWord = input.split(" ")[0];
        switch (firstWord) {
        case "deadline":
            return Command.DEADLINE;
        case "todo":
            return Command.TODO;
        case "event":
            return Command.EVENT;
        case "delete":
            return Command.DELETE;
        case "list":
            return Command.LIST;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "find":
            return Command.FIND;
        case "bye":
            return Command.BYE;
        default:
            throw new UnknownCommandException(firstWord);
        }
    }

    /**
     * Returns parameters of command being run by user
     *
     * @param input Words input by user
     * @return ArrayList of parameters given by user
     */
    public String[] getParameters(String input) {
        String[] words = input.split(" ");
        return Arrays.copyOfRange(words, 1, words.length);
    }
}
