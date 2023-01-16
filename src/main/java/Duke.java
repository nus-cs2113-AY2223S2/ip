public class Duke {
    private static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke\n"
                         + "What can I do for you?");
        printLine();
    }

    private static void exit() {
        System.out.println("Bye. How to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        printLogo();
        greet();
        exit();
   }
}
