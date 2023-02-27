package Interface;

import java.util.Scanner;

public class Ui {
    static Scanner in = new Scanner(System.in);

    /**
     * Outputs intro message to terminal
     */
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

    /**
     * Outputs farewell message to terminal
     */
    public static void farewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads user's input
     * @return string of user input
     */
    public static String readCommand() {
        return in.nextLine();
    }

    /**
     * Outputs a copy of a string to terminal
     * @param line string to be output at terminal
     */
    public static void copy(String line) {
        System.out.println(line);
    }

    /**
     * Outputs create directory message to terminal
     */
    public static void makeDirectory() {
        System.out.println("New directory created...");
    }

    /**
     * Outputs create file message to terminal
     */
    public static void makeFile() {
        System.out.println("New file created...");
    }

    /**
     * Outputs load file message to terminal
     */
    public static void loadFile() {
        System.out.println("Save file has been successfully loaded.");
    }
}