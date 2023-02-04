package duke.ui;
import java.util.Scanner;
import duke.commands.Command;
import duke.exceptions.InvalidCommandException;
import duke.exceptions.InvalidFormatException;
import duke.exceptions.InvalidTaskException;

public class Ui {
    public static final String LOGO = "\t ____        _        \n"
            + "\t|  _ \\ _   _| | _____ \n"
            + "\t| | | | | | | |/ / _ \\\n"
            + "\t| |_| | |_| |   <  __/\n"
            + "\t|____/ \\__,_|_|\\_\\___|\n";

    public static void printDivider() {
        System.out.println("\t____________________________________");
    }

    public static void printWelcome() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("\tHello! I'm Duke\n"
                + "\tWhat can I do for you?");
        printDivider();
    }

    public static void printExit() {
        System.out.println("\tBye. Hope to see you again soon!");
        printDivider();
    }

    public static void printMessage(String[] messages) {
        printDivider();
        for (String message : messages) {
            System.out.println(message);
        }
        printDivider();
    }

    public static void getInput() throws InvalidCommandException, InvalidTaskException, InvalidFormatException {
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            String inputString = input.nextLine();
            String[] inputArray = inputString.split(" ", 2);

            Command.handleCommand(inputArray);
        }
        input.close();
    }
}
