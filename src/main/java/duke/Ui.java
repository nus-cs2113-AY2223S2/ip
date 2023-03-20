package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    private static final Scanner in = new Scanner(System.in);
    public static void printWelcomeMessage() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Elzi, your personal pet dog!\n" +
                " type help to see list of features!\n" +
                "____________________________________________________________";
        System.out.println(welcomeMessage);
    }
    public static void printHelp() {
        System.out.println("My features are as follows:\n" +
                "*BASIC*\n" +
                "1. todo [DESCRIPTION]\n" +
                "2. deadline [DESCRIPTION] /by [DEADLINE]\n" +
                "3. event [DESCRIPTION] /from [START] /to [END]\n" +
                "4. list\n" +
                "5. mark [INDEX]\n" +
                "6. unmark [INDEX]\n" +
                "7. find [KEYWORD]\n" +
                "*** ADVANCED : These are additional features! ***\n" +
                "8. list_todo\n" +
                "9. list_deadline\n" +
                "10. list_event\n" +
                "12. help");
        printLine();
    }
    public static String getCommand() {
        System.out.println("What is my task, master?");
        return in.nextLine();
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println("Oops! I encountered this error! "+ errorMessage);
    }

    public static void printLine() {
        System.out.println("-------------------------------------------------");
    }

    public static void printByeMessage() {
        System.out.println("Good bye! See you soon!");
        System.out.println("______________________******_____________________");
    }

    public static void printAddTask(Task task) {
        System.out.println("Got it. I've added this task:\n     " + task.toString());
    }
    public static void printDeleteTask(Task task) {
        System.out.println("I have removed this item\n      " + task.toString());
    }
    public static void printSetAsDone(Task task) {
        System.out.println("I have marked this task as done\n" + task.toString());
        printLine();
    }
    public static void printSetAsNotDone(Task task) {
        System.out.println("I have unmarked this task\n" + task.toString());
        printLine();
    }
    public static void printTaskLeft(TaskList taskList) {
        System.out.println("Now you have " + taskList.getSize() + " task in the list");
        printLine();
    }
    public static void printList(TaskList taskList, String type) {
        String taskType = type.equals("N") ? "tasks" : type.equals("T") ?
                "todo tasks" : type.equals("D") ? "deadlines" : "events";
        System.out.println("Your current " + taskType + " are as follows:");
        String list = taskList.listMessages(type).stripTrailing();
        System.out.println(list);
        printLine();
    }
}
