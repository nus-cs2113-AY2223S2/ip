package king.ui;

public class Ui {
    public static final String LOGO = "\n" 
            + "\t██╗░░██╗██╗███╗░░██╗░██████╗░\n"
            + "\t██║░██╔╝██║████╗░██║██╔════╝░\n"
            + "\t█████═╝░██║██╔██╗██║██║░░██╗░\n"
            + "\t██╔═██╗░██║██║╚████║██║░░╚██╗\n"
            + "\t██║░╚██╗██║██║░╚███║╚██████╔╝\n"
            + "\t╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░╚═════╝░\n";

            

    public static void printDivider() {
        System.out.println("\t____________________________________");
    }

    public static void printWelcome() {
        printDivider();
        System.out.println(LOGO);
        System.out.println("\tWelcome your majesty!\n"
                + "\tYour humble servant is at thy command");
        printDivider();
    }

    public static void printExit() {
        printDivider();
        System.out.println("\tTasks hast been saveth... Fare thee well!");
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
