package UI;

import java.util.Scanner;

public class Conversation {
    static Scanner in = new Scanner(System.in);
    public static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");        
    }

    public static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello there! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static String readCommand() {
        return in.nextLine();
    }
    public static void copy(String line) {
        System.out.println(line);

    }
}