public class Duke {

    private static final String GREET_STR = "Hello! I'm Duke";
    private static final String PROMPT_STR = "What can I do for you?";
    private static final String EXIT_STR = "Bye. Hope to see you again soon!";

    // Overloaded default for printDivider
    private static void printDivider() {
        printDivider(60);
    }

    private static void printDivider(int width) {
        System.out.println("_".repeat(width));
    }

    public static void main(String[] args) {
        printDivider();
        System.out.println(GREET_STR);
        System.out.println(PROMPT_STR);
        printDivider(40);
        System.out.println(EXIT_STR);
    }
}
