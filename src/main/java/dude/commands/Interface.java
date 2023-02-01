package dude.commands;

import dude.task.ListManager;

import java.util.List;
import java.util.Scanner;

public abstract class Interface {
    public static final String LINE = "__________________________________";
    public static final String DUDE_LOGO = "██████╗░██╗░░░██╗██████╗░███████╗       ██████╗░░█████╗░████████╗\n"
            + "██╔══██╗██║░░░██║██╔══██╗██╔════╝      ██╔══██╗██╔══██╗╚══██╔══╝\n"
            + "██║░░██║██║░░░██║██║░░██║█████╗░░      ██████╦╝██║░░██║░░░██║░░░\n"
            + "██║░░██║██║░░░██║██║░░██║██╔══╝░░      ██╔══██╗██║░░██║░░░██║░░░\n"
            + "██████╔╝╚██████╔╝██████╔╝███████╗      ██████╦╝╚█████╔╝░░░██║░░░\n"
            + "╚═════╝░░╚═════╝░╚═════╝░╚══════╝      ╚═════╝░░╚════╝░░░░╚═╝░░░\n";

    public static void printGreeting() {
        System.out.println("Hello from\n" + DUDE_LOGO);
        System.out.println(LINE);
        System.out.println("Greetings! I am DUDE_BOT, how can i be of service to you?");
        System.out.println(LINE);
        System.out.println();
    }

    public static void printBye() {
        System.out.println(LINE);
        System.out.println("Goodbye, it was a pleasure to be of service to you");
        System.out.println(LINE);
        System.out.println();
    }

    public static void listFullMessage() {
        System.out.println(Interface.LINE);
        System.out.println("Im terribly sorry but the list is full, I am unable to add your entry");
        System.out.println("Yours Sincerely, Dude_Bot");
        System.out.println(Interface.LINE);
    }

    public static void addedMessage(String input) {
        System.out.println(Interface.LINE);
        System.out.println("added: " + input);
        System.out.println(Interface.LINE);
    }

    public static void markDoneMessage() {
        System.out.println(Interface.LINE);
        System.out.println("Nice! I've marked this task as done:");
    }

    public static void markUndoneMessage() {
        System.out.println(Interface.LINE);
        System.out.println("I have Unmarked this task:");
    }

    public static void parseInput(String input) {
        String[] commands = input.split(" ");
        switch (commands[0]) {

        case "bye":
            printBye();
            System.exit(0);
        case "list":
            ListManager.printList();
            break;
        case "mark":
            int index = Integer.parseInt(commands[1]) - 1;
            if (index <= ListManager.getSize()) {
                ListManager.markDone(index);
            } else {
                System.out.println("That task does not exist!");
            }
            break;
        case "unmark":
            int id = Integer.parseInt(commands[1]) - 1;
            if (id <= ListManager.getSize()) {
                ListManager.markUndone(id);
            } else {
                System.out.println("That task does not exist!");
            }
            break;
        case "":
            System.out.println("Input a task");
            break;
        default:
            ListManager.addToList(input);
        }
    }

    public static void readInput() {
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = in.nextLine();
            parseInput(userInput);
        }
    }
}
