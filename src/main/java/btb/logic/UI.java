package btb.logic;

import btb.constants.Constant;
import btb.tasks.TaskManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class UI {
    /**
     * prints the logo of the chatBot.
     */
    public static void printLogo() {
        System.out.println(Constant.LOGO);
    }

    /**
     * greets the user.
     */
    public static void greetUser() {
        System.out.println("\t Hello! I'm Bob the Bot, aka BtB.");
        System.out.println("\t Please enter some tasks in the todo list.");
    }

    /**
     * starts the application by printing both
     * the logo and greeting the user.
     */
    public static void start() {
        System.out.println(Constant.DOTTED_LINE);
        printLogo();
        greetUser();
        System.out.println(Constant.DOTTED_LINE);
    }

    /**
     * runs the CLI application continuously
     * by asking for user's input and executing
     * those commands.
     * Terminate the program when the command
     * "bye" is entered.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        TaskManager tasks = new TaskManager();
        String userInput;
        boolean isRepeat = true;

        do {
            try {
                userInput = scanner.nextLine().trim();

                if (userInput.equals("bye")) {
                    isRepeat = false;
                    continue;
                }

                System.out.println(Constant.DOTTED_LINE);
                Logic.runCommand(tasks, userInput);
                System.out.println(Constant.DOTTED_LINE);
            } catch (NoSuchElementException e) {
                System.out.println("\t Invalid input ┻ ︵ヽ(`Д´)ﾉ︵ ┻. Please try again!");
                break;
            }
        } while (isRepeat);
    }

    /**
     * prints a goodbye message
     * after the program has terminated.
     */
    public static void end() {
        System.out.println(Constant.DOTTED_LINE);
        System.out.println(Constant.BYE);
        System.out.println(Constant.DOTTED_LINE);
    }
}
