import java.util.Scanner;

public class Rica {
    private static final String LINE = "____________________________________________________________";
    private static final String BYE_TRIGGER = "bye";
    private static final String CONFIRM_PHRASE = " Did you say: ";
    private static final String BYE_PHRASE = " Leaving so soon? Come back anytime, I'll be happy to help!";
    public static void main(String[] args) {
        // Define horizontal line in a variable since it's printed multiple times

        printlnWithIndent(Rica.LINE);
        printlnWithIndent(" Hello! I'm R.I.C.A.");
        printlnWithIndent((" That's Really-Intelligent-Chat-Assistant for you!"));
        printlnWithIndent(" How may I be of assistance?");
        printlnWithIndent(Rica.LINE);
        Rica.runCommands();
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
            printlnWithIndent(Rica.LINE);
            if (!command.equals(Rica.BYE_TRIGGER)) {
                printlnWithIndent(Rica.CONFIRM_PHRASE + command + "?");
            } else {
                printlnWithIndent(Rica.BYE_PHRASE);
            }
            printlnWithIndent(Rica.LINE);
        } while (!command.equals(Rica.BYE_TRIGGER));
    }
}
