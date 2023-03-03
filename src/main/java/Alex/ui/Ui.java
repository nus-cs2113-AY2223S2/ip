package Alex.ui;

public class Ui {


    /**
     * Commonly used printing outputs
     */
    private static final String DIVIDER = "===================================================";
    /**
     * Print welcome message for user
     */
    public static void showWelcomeMessage() {
        printLineDivider();
        String Alex = "\n" +
                " $$$$$$\\  $$\\       $$$$$$$$\\ $$\\   $$\\ \n" +
                "$$  __$$\\ $$ |      $$  _____|$$ |  $$ |\n" +
                "$$ /  $$ |$$ |      $$ |      \\$$\\ $$  |\n" +
                "$$$$$$$$ |$$ |      $$$$$\\     \\$$$$  / \n" +
                "$$  __$$ |$$ |      $$  __|    $$  $$<  \n" +
                "$$ |  $$ |$$ |      $$ |      $$  /\\$$\\ \n" +
                "$$ |  $$ |$$$$$$$$\\ $$$$$$$$\\ $$ /  $$ |\n" +
                "\\__|  \\__|\\________|\\________|\\__|  \\__|\n";
        String greet = "Hello! I'm Alex the less famous brother of Amazon's Alexa\nWhat can I do for you today?";
        System.out.print(Alex);
        System.out.println(greet);
        printLineDivider();
    }

    /**
     * Print output for user encased in line dividers
     */
    public static void showOutput (String output) {
        printLineDivider();
        System.out.println(output);
        printLineDivider();

    }

    private static void printLineDivider() {
        System.out.println(DIVIDER);
    }
}
