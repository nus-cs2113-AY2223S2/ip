package grandduke.command;

import java.util.Scanner;

import grandduke.exception.GrandException;

public abstract class Io {
    public static final String LOGO = "  _____                     _______       _        \n"
            + "|  __ \\                   | |  _  \\     | |       \n"
            + "| |  \\/_ __ __ _ _ __   __| | | | |_   _| | _____ \n"
            + "| | __| '__/ _` | '_ \\ / _` | | | | | | | |/ / _ \\ \n"
            + "| |_\\ \\ | | (_| | | | | (_| | |/ /| |_| |   <  __/\n"
            + " \\____/_|  \\__,_|_| |_|\\__,_|___/  \\__,_|_|\\_\\___|\n";

    public static final String EXIT_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";
    public static final String EMPTY_COMMAND = "";

    /**
     * Prints a horizontal line
     */
    public static void printLine() {
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    /**
     * Prints a custom message to the command line
     * @param message the message to be printed
     */
    public static void printOutput(String message) {
        System.out.print("        ");
        System.out.println(message);
    }

    /**
     * Prints introduction message to the user
     */
    public static void printIntro() {
        System.out.println("Hello from\n" + LOGO);
        printLine();
        printOutput("Hello! I'm GrandDuke\n" + "        What can I do for you?");
        printLine();
        System.out.print(System.lineSeparator());
    }

    /**
     * Prints exit message to the user
     */
    public static void printExit() {
        printLine();
        printOutput("Bye. Hope to see you again soon!");
        printLine();
        System.out.print(System.lineSeparator());
    }

    /**
     * Gets inputs that are sent by the user through the command line
     */
    public static void getInputs() {
        Scanner in = new Scanner(System.in);
        String input;
        Boolean isRunning = true;
        while (isRunning) {
            input = in.nextLine();

            if (input.equals(EXIT_COMMAND)) {
                isRunning = false;
                continue;
            }

            printLine();

            try {
                Parser.parseCommand(input);
            } catch (Exception e) {
                if (e instanceof GrandException) {
                    printOutput(e.getMessage());
                } else {
                    printOutput("Unknown error occurred. Please try again.");
                }
            }

            printLine();
            System.out.print(System.lineSeparator());

        }
        in.close();
    }

}
