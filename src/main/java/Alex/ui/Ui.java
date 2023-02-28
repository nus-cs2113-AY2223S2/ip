package Alex.ui;

public class Ui {

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();
    private static final String DIVIDER = "===================================================";

    public static void showWelcomeMessage() {
        printLineDivider();
        String greet = "Hello! I'm Alex\nWhat can I do for you today?";
        System.out.println(greet);
        printLineDivider();
    }

    public static void showExitMessage () {
        String exit = "Bye. Hope to chat with you again soon!";
        System.out.println(exit);
        printLineDivider();
    }

    public static void showOutput (String output) {
        printLineDivider();
        System.out.println(output);
        printLineDivider();

    }

    private static void printNewLine() {
        System.out.println();
    }
    private static void printLineDivider() {
        System.out.println(DIVIDER);
    }
}
