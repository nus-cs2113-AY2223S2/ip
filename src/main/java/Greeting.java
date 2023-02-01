public class Greeting {
    
    public static void printSeperator() {
        System.out.println("____________________________________________________________\n");
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
    }

    public static void printWelcome() {
        printSeperator();
        System.out.println(
                "\tHello! I'm Duke\n" +
                        "\tWhat can I do for you?\n");
        printSeperator();
    }

    public static void printHelp() {
        printSeperator();
        System.out.println(
                "\tPlease Type a valid command!\n");
        printSeperator();
    }

    public static void printGoodbye() {
        printSeperator();
        System.out.println(
                "\tBye. Hope to see you again soon!\n"
        );
        printSeperator();
    }
}
