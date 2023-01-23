import java.util.Scanner;  // Import the Scanner class

public class IO {

    public static void println() {
        System.out.println("        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }         
    public static void output(String message) {
        println();
        System.out.print("        ");
        System.out.println(message);
        println();
    }                 
    public static void greet() {
        output("Hello! I'm GrandDuke\n" + "        What can I do for you?");
    }
    public static void exit() {
        output("Bye. Hope to see you again soon!");
    }

    public static void parseCommand(String input) {
        if (input.equals("bye")) {
            IO.exit();
            System.exit(0);
        } else if (input.equals("list")){
            Tasks.listTask();
        } else {
            Tasks.addTask(input);
        }
    }
    public static void startup() {
        IO.greet();
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            String command = input.nextLine();
            parseCommand(command);
        }
    }
}
