package UI;

import java.util.Scanner;

public class Conversation {
    static Scanner in = new Scanner(System.in);
    public static void greet() {
        System.out.println("Hello there! I'm Duke");
    }
    public static void question() {
        System.out.println("What can I do for you?");
    }
    public static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");        
    }
    public static String readCommand() {
        return in.nextLine();
    }
    public static void line() {
        System.out.println("____________________________________________________________");
    }
    public static void gap() {
        System.out.println("");
    }
    public static void copy(String line) {
        System.out.println(line);

    }
}