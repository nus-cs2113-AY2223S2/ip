package io;

import java.util.Scanner;

public class In {
    private Scanner scanner;

    public In() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * As the name suggests, this reads the user input until the end of the line and
     * returns the input as string. However this function also prints a prompt
     * character to make it looks hackerish. This prompt character is hidden during
     * testing.
     * 
     * @return The user input as a string.
     */
    public String readUserInput() {
        if (System.getenv("TESTING") == null) {
            System.out.print("> ");
        }
        return scanner.nextLine();
    }
}
