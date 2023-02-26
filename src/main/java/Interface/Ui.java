package Interface;

import java.util.Scanner;

public class Ui {
    static Scanner in = new Scanner(System.in);
    public static void intro() {
        String logo = "\n" +
                "██████╗░██╗░░░██╗██████╗░███████╗\n" +
                "██╔══██╗██║░░░██║██╔══██╗██╔════╝\n" +
                "██║░░██║██║░░░██║██║░░██║█████╗░░\n" +
                "██║░░██║██║░░░██║██║░░██║██╔══╝░░\n" +
                "██████╔╝╚██████╔╝██████╔╝███████╗\n" +
                "╚═════╝░░╚═════╝░╚═════╝░╚══════╝\n";
        System.out.println("Hello from:\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello there! My name is Dude");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }
    public static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static String readCommand() {
        return in.nextLine();
    }
    public static void copy(String line) {
        System.out.println(line);
    }
    public static void makeDirectory() {
        System.out.println("New directory created...");
    }
    public static void makeFile() {
        System.out.println("New file created...");
    }
    public static void loadFile() {
        System.out.println("Save file has been successfully loaded.");
    }
}