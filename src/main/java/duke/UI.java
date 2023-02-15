package duke;

import static javax.swing.text.html.CSS.Attribute.MARGIN;

public class UI {
    private static final String MARGIN = "*----------------------------*" ;

    // Begin Program
    public static void welcomeMessage(){
        String logo = "__________________ ##\n" +
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
                "________________.####*__*####";
        System.out.println(MARGIN);
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(MARGIN);
    }
    // Terminate program
    public static void endProgram(){
        System.out.println(MARGIN);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(MARGIN);
    }

    public static void addNewTodo(String taskName) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [T][ ] " + taskName);
    }

    public static void addNewDeadline(String taskName, String by) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [D][ ] " + taskName + " (by: " + by + ")");
    }

    public static void addNewEvent(String taskName, String startTime, String endTime) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  [E][ ] " + taskName + " (from: " + startTime + " to: " + endTime + ")");
    }

    public static void printListLength(int lengthOfList) {
        System.out.println("Now you have " + lengthOfList + " tasks in the list.");
    }
}
