package duke.ui;

public class Ui {
    public static final String LOGO = "\n"
            + "\t██████╗░██╗░░░██╗██╗░░██╗███████╗\n"
            + "\t██╔══██╗██║░░░██║██║░██╔╝██╔════╝\n"
            + "\t██║░░██║██║░░░██║█████═╝░█████╗░░\n"
            + "\t██║░░██║██║░░░██║██╔═██╗░██╔══╝░░\n"
            + "\t██████╔╝╚██████╔╝██║░╚██╗███████╗\n"
            + "\t╚═════╝░░╚═════╝░╚═╝░░╚═╝╚══════╝\n";

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
        printDivider();
        System.out.println("\tSaving tasks... Hope to see you again soon!");
        printDivider();
    }

    /**
     * Prints the message passed as an array onto the terminal line by line
     *
     * @input messages string array
     */
    public static void printMessage(String[] messages) {
        printDivider();
        for (String message : messages) {
            System.out.println(message);
        }
        printDivider();
    }
}
