package duke.ui;
import duke.outputs.Messages;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UI {
    private static final String MARGIN = "*----------------------------*";

    private final Scanner in;
    private final PrintStream out;

    private UI(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }
    public UI(){
        this(System.in , System.out);
    }



    public static void showWelcomeMessage() {
        String logo = " __________________ ##\n" +
                "_________________###*\n" +
                "______________.*#####\n" +
                "_____________*######\n" +
                "___________*#######\n" +
                "__________*########.\n" +
                "_________*#########.\n" +
                "_________*#######*##*\n" +
                "________*#########*###\n" +
                "_______*##########*__*##\n" +
                "_____*###########_____*\n" +
                "____############\n" +
                "___*##*#########\n" +
                "___*_____########\n" +
                "__________#######\n" +
                "___________*######\n" +
                "____________*#####*\n" +
                "______________*####*\n" +
                "________________*####\n" +
                "__________________*##*\n" +
                "____________________*##\n" +
                "_____________________*##.\n" +
                "____________________.#####.\n" +
                "_________________.##########\n" +
                "________________.####*__*####\n" +
                "\n";

        System.out.println(MARGIN);
        System.out.println(logo);
        System.out.println(Messages.WELCOME_MESSAGE_1);
        System.out.println(Messages.WELCOME_MESSAGE_2);
        System.out.println(MARGIN);
    }

    // Terminate program
    public static void endProgram() {
        System.out.println(MARGIN);
        System.out.println(Messages.EXIT_MESSAGE);
        System.out.println(MARGIN);
    }

}





//    public static void addNewTodo(String taskName) {
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  [T][ ] " + taskName);
//    }
//
//    public static void addNewDeadline(String taskName, String by) {
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  [D][ ] " + taskName + " (by: " + by + ")");
//    }
//
//    public static void addNewEvent(String taskName, String startTime, String endTime) {
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  [E][ ] " + taskName + " (from: " + startTime + " to: " + endTime + ")");
//    }
//
//    public static void printListLength(int lengthOfList) {
//        System.out.println("Now you have " + lengthOfList + " tasks in the list.");
//    }
//
//
//
//    // Executes when error is encountered
//    public static void errorReport(String errorDescription) {
//        System.out.println("Error: " + errorDescription);
//    }
//}
