package duke;

import duke.exceptions.EmptyCommandException;

import java.util.Scanner;

public class Parser {

    protected Scanner input;
    private static final String LIST_COMMAND = "list";

    public Parser() {
        input = new Scanner(System.in);
    }

    public String getInput() {
        String line = input.nextLine();
        return line;
    }

    public String[] splitCommandsIntoWords(String line) throws EmptyCommandException {
        String[] words = line.split(" ", 2);
        checkIfCommandEmpty(words);
        return words;
    }

    private void checkIfCommandEmpty(String[] words) throws EmptyCommandException {
        if (words[0].equals(LIST_COMMAND)) {
            return;
        }
        if (words.length < 2 || words[1].equals("")) {
            throw new EmptyCommandException();
        }
    }

}
