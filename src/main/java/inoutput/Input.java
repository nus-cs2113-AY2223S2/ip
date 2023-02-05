package inoutput;

import java.util.Scanner;

/**
 * The IO class is responsible for handling user inputs for the program.
 * <p>
 * It uses a Scanner object to read input from the user, and provides a
 * <p>
 * readInput() method to retrieve the input as a string.
 * <p>
 * ">>" is used as a prompt for the user to enter their next command.
 */
public class Input {
    private Scanner toRead;

    /**
     * The constructor IO creates a new instance of the class and initializes
     * a Scanner object to read input from the user.
     */
    public Input() {
        this.toRead = new Scanner(System.in);
    }

    /**
     * The readInput() method retrieves a line of input from the user and returns it as a string.
     *
     * @return A string containing the input entered by the user.
     */
    public String readInput() {
        System.out.print(">> ");
        return toRead.nextLine();
    }
}