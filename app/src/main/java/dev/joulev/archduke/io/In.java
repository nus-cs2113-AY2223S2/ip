package dev.joulev.archduke.io;

import java.util.Scanner;

/**
 * This class handles all input from the user.
 */
public class In {
    private Scanner scanner;

    /**
     * Initializes the input handler.
     */
    public In() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the user input until the end of the line and returns the input as
     * string. However this function also prints a prompt character to make it looks
     * hackerish, in non-testing environment (if {@code TESTING} environment
     * variable is not present).
     * 
     * @return The user input as a string, not including the newline character.
     */
    public String readUserInput() {
        if (System.getenv("TESTING") == null) {
            System.out.print("> ");
        }
        return scanner.nextLine();
    }
}
