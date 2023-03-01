package duke;

import duke.exceptions.EmptyCommandException;

import java.util.Scanner;

public class Parser {

    protected Scanner input;
    private static final String LIST_COMMAND = "list";

    public Parser() {
        input = new Scanner(System.in);
    }

    /**
     * Retrieves the next line of user input
     *
     * @return Input from user
     */
    public String getInput() {
        String line = input.nextLine();
        return line;
    }

    /**
     * @param line original line that the user input
     * @return String array of each word that is separated by the space char
     * @throws EmptyCommandException When the command is empty (e.g. mark without index)
     */
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
