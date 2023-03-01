package dude.commands;

import java.util.Scanner;
import dude.task.ListManager;

public abstract class Io {

    /**
     * Prints horizontal line.
     */
    public static final String LINE = "__________________________________";

    /**
     * Prints dude logo.
     */
    public static final String DUDE_LOGO = "██████╗░██╗░░░██╗██████╗░███████╗       ██████╗░░█████╗░████████╗\n"
            + "██╔══██╗██║░░░██║██╔══██╗██╔════╝      ██╔══██╗██╔══██╗╚══██╔══╝\n"
            + "██║░░██║██║░░░██║██║░░██║█████╗░░      ██████╦╝██║░░██║░░░██║░░░\n"
            + "██║░░██║██║░░░██║██║░░██║██╔══╝░░      ██╔══██╗██║░░██║░░░██║░░░\n"
            + "██████╔╝╚██████╔╝██████╔╝███████╗      ██████╦╝╚█████╔╝░░░██║░░░\n"
            + "╚═════╝░░╚═════╝░╚═════╝░╚══════╝      ╚═════╝░░╚════╝░░░░╚═╝░░░\n";

    /**
     * Prints chocolate art.
     */
    public static final String CHOCOLATE_ART = "  ___  ___  ___  ___  ___.---------------.\n" +
            ".'\\__\\'\\__\\'\\__\\'\\__\\'\\__,`   .  ____ ___ \\\n" +
            "|\\/ __\\/ __\\/ __\\/ __\\/ _:\\   |`.  \\  \\___ \\\n" +
            " \\\\'\\__\\'\\__\\'\\__\\'\\__\\'\\_`.__|\"\"`. \\  \\___ \\\n" +
            "  \\\\/ __\\/ __\\/ __\\/ __\\/ __:                \\\n" +
            "   \\\\'\\__\\'\\__\\'\\__\\ \\__\\'\\_;-----------------`\n" +
            "    \\\\/   \\/   \\/   \\/   \\/ :                 |\n" +
            "     \\|______________________;________________|";

    /**
     * Prints greeting message.
     */
    public static void printGreeting() {
        System.out.println("Hello from\n" + DUDE_LOGO  + "\n" + Io.LINE);
        System.out.println("Greetings! I am DUDE_BOT, how can i be of service to you?"  + "\n" + Io.LINE);
    }

    /**
     * Prints bye message.
     */
    public static void printBye() {
        System.out.println(Io.LINE + "\n" + "Goodbye, it was a pleasure to be of service to you. Here have a chocolate bar as my parting gift");
        System.out.println(CHOCOLATE_ART);
    }

    /**
     * Prints list message when list is full
     */
    public static void listFullMessage() {
        System.out.println(Io.LINE + "\n" + "Im terribly sorry but the list is full, I am unable to add your entry");
        System.out.println("Yours Sincerely, Dude_Bot" + "\n" + Io.LINE);
    }

    /**
     * Prints message when new task is added to list
     */
    public static void addedMessage(int index) {
        System.out.println(Io.LINE + "\n" + "added: " + ListManager.getTask(index));
        System.out.println("Now you have " + (index + 1) + " tasks in the list" + "\n" + Io.LINE);
    }

    /**
     * Prints message when task is marked as done
     */
    public static void markDoneMessage() {
        System.out.println(Io.LINE + "\n" +"Nice! I've marked this task as done:" + "\n" + Io.LINE);
    }

    /**
     * Prints message when task is marked as undone
     */
    public static void markUndoneMessage() {
        System.out.println(Io.LINE + "\n" + "I have Unmarked this task:" + "\n" + Io.LINE);
    }

    /**
     * Prints message when task is deleted
     */
    public static void deletedMessage(int index){
        System.out.println("Okay, I have removed this task from your list");
        System.out.println(ListManager.getTask(index) + "\n" + Io.LINE);
    }

    /**
     * reads user input and calls parser to parse input and execute commands
     */
    public static void readInput() {
        Scanner in = new Scanner(System.in);
        String userInput;
        Boolean isRunningprogram = true;
        while (isRunningprogram) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                isRunningprogram = false;
                break;
            }
            try {
                Parser.parseInput(userInput,false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
