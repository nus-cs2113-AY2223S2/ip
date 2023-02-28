package ChatTPG;

import java.util.Scanner;

/**
 * Specifications of UI class which allows the user to interact with ChatTPG.
 */
public class UI {

    /**
     * Greets user and prompts user for command.
     */
    public static void greet() {
        System.out.println("________________________________");
        System.out.println("Hello! I'm ChatTPG");
        System.out.println("What can I do for you?");
        System.out.println("________________________________");
    }

    /**
     * Ends conversation with user upon command "bye" before program termination.
     */
    public static void bye() {
        System.out.println("________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________");
    }

    public static void main(String[] args) {
        greet();
        Storage.loadData();
        Scanner in = new Scanner(System.in);
        String input;
        input = in.nextLine();
        while ((input.compareTo("bye") != 0)) {
            System.out.println("________________________________");
            Parser.handleCommand(input);
            System.out.println("________________________________");
            input = in.nextLine();
        }
        bye();
    }
}
