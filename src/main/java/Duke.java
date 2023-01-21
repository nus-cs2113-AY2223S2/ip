public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printIntro();
        printExit();

    }

    public static void printIntro() {
        printMessage("Hello! I'm Duke\nWhat can I do for you?");
    }
    public static void printExit() {
        printMessage("Bye. Hope to see you again soon!");
    }
    public static void printMessage(String message) {
        System.out.println(message);
        printHorizontalLine();
    }
    public static void printHorizontalLine() {
        System.out.println("------------------------------------------");
    }
}
