import java.util.Scanner;

public class Duke {
    static String[] list = new String[100];
    static int listSize = 0;

    public static void printDivider() {
        String divider = "\t____________________________________";
        System.out.println(divider);
    }

    public static void printLogo() {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
    }

    public static void printWelcome() {
        String welcomeMessage = "\tHello! I'm Duke\n\tWhat can I do for you?";

        printDivider();
        printLogo();
        System.out.println(welcomeMessage);
        printDivider();
    }

    public static void printExit() {
        String exitMessage = "\tBye. Hope to see you again soon!";

        System.out.println(exitMessage);
        printDivider();
    }

    public static void addToList(String inputMessage) {
        list[listSize] = inputMessage;
        listSize++;
        String addedMessage = "\tadded: " + inputMessage;

        printDivider();
        System.out.println(addedMessage);
        printDivider();
    }

    public static void printList() {
        printDivider();
        for (int i = 0; i < listSize; i++) {
            System.out.println("\t" + (i + 1) + ". " + list[i]);
        }
        printDivider();
    }

    public static void main(String[] args) {

        printWelcome();
        Scanner input = new Scanner(System.in);

        while (input.hasNextLine()) {
            String inputString = input.next();
            switch (inputString) {
            case "bye":
                printExit();
                System.exit(0);
                break;

            case "list":
                printList();
                break;
                
            default:
                addToList(inputString);
                input.nextLine();
            }
        }

    }
}
