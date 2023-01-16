public class Duke {
    public static void printDuke() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n");
    }

    // Greets the users and exits
    public static void greet() {
        String breakLine = "------------------------------------------\n";
        System.out.println(breakLine +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                breakLine +
                "Bye. Hope to see you again soon!\n" +
                breakLine);
    }
    public static void main(String[] args) {
        printDuke();
        greet();
    }
}
