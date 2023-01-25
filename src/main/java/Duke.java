import java.util.Scanner;

public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String BYE_TRIGGER = "bye";
    private static final String BYE_PHRASE = " Bye. Hope to see you again soon!";
    public static void main(String[] args) {
        // Define horizontal line in a variable since it's printed multiple times

        printlnWithIndent(Duke.LINE);
        printlnWithIndent(" Hello! I'm Duke");
        printlnWithIndent(" What can I do for you?");
        printlnWithIndent(Duke.LINE);
        Duke.runCommands();
    }

    private static void printlnWithIndent(String line) {
        System.out.print("    ");
        System.out.println(line);
    }

    private static void runCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";
        do {
            command = in.nextLine();
            printlnWithIndent(Duke.LINE);
            if (!command.equals(Duke.BYE_TRIGGER)) {
                printlnWithIndent(" " + command);
            } else {
                printlnWithIndent(Duke.BYE_PHRASE);
            }
            printlnWithIndent(Duke.LINE);
        } while (!command.equals(Duke.BYE_TRIGGER));
    }
}
