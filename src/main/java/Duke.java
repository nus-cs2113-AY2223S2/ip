import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        final String DOTTED_LINE = "    --------------------------------------------------";
        final String TAB_SIZE = "     ";

        // greets user
        System.out.println(DOTTED_LINE);
        printLogo();
        greetUser();
        System.out.println(DOTTED_LINE + '\n');

        String userCommand;

        // ask for user inputs and echo those inputs
        do {
            Scanner scanner = new Scanner(System.in);
            userCommand = scanner.nextLine();

            // top dotted line
            System.out.println(DOTTED_LINE);

            // body
            System.out.print(TAB_SIZE);
            echoCommand(userCommand);

            // bottom dotted line
            System.out.println(DOTTED_LINE + '\n');

        } while (!userCommand.equals("bye"));

    }

    /** prints the logo of the chatBot */
    public static void printLogo() {

        String logo = "      ____     _    ____\n"
                + "     |  _ \\ __| |__|  _ \\\n"
                + "     | | //|__   __| | //\n"
                + "     | |_\\\\   |  |_| |_\\\\\n"
                + "     |____/   |___/|____/\n";

        System.out.println(logo);
    }

    /** greets the user */
    public static void greetUser() {

        System.out.println("     Hello! I'm Bob the Bot, aka BtB."); // five spaces
        System.out.println("     What can I do for you?");
    }

    /** Say bye to the user */
    public static void sayBye() {

        System.out.println("Bye. Hope to see you again sooooooon (┬┬﹏┬┬)!");
    }

    /**
     * Echos out the user's command onto the terminal
     *
     * @param userCommand the CLI input that the user key in
     */
    public static void echoCommand(String userCommand) {

        if (userCommand.equals("bye")) {
            sayBye();
        } else {
            System.out.println(userCommand);
        }
    }
}