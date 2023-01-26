public class Duke {
    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printDivider();
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printDivider();
        System.out.println(" Bye. Hope to see you again soon!");
        printDivider();
    }
}
