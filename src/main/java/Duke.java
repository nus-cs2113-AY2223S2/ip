public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro = "Hello! I'm Duke\n"
                + "What can I do for you?";

        String outro = "Bye. Hope to see you again soon!";

        // Greet message
        printHLine();
        System.out.println(intro);
        printHLine();

        // Exit message
        System.out.println(outro);
        printHLine();

    }

    /**
     * Prints a horizontal line.
     */
    public static void printHLine() {
        System.out.println("================================");
    }
}
