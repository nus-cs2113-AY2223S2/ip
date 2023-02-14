package app.ui;

import static app.Duke.line;

public class Greetings {
    public static void printHelloMessage() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println(line);
    }

    public static void printByeMessage() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

}
