package duke.command;

public class Message {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void logo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo + "\n");
    }

    public static void line() {
        System.out.println(HORIZONTAL_LINE);
    }

    public static void hello() {
        Message.logo();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void echo(String userInput) {
        Message.line();
        System.out.println(userInput);
        Message.line();
    }

    public static void unknownCommandHandler() {
        Message.line();
        System.out.println("You have entered an unknown command! Please try again!");
        Message.line();
    }
}
