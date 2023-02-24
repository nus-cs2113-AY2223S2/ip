package duke;

import java.util.Scanner;

public class Ui {

    public static final String LINE_BREAK = "    ____________________________________________________________";
    public static final String INDENTATION = "    ";
    public static final String HELP_PAGE = "    todo: add a new task to Duke\n" +
            "    deadline: add a new task and '/by' date to add a task with deadline\n" +
            "    event: add a new event with '/from' and '/to' duration\n" +
            "    list: list out all tasks stored\n" +
            "    help: no :D\n    bye: end the program\n    Please enter command:\n";
    public static Scanner in = new Scanner(System.in);

    public static void greet() {
        System.out.println(LINE_BREAK);
        printlnWithIndentation("Hello! I'm Duke");
        printlnWithIndentation("What can I do for you?");
        System.out.println(LINE_BREAK);
    }

    public String getCommand() {
        return in.nextLine().trim();
    }

    public static void farewell() {
        System.out.println(LINE_BREAK);
        printlnWithIndentation("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    public static void printException(String string) {
        System.out.println(LINE_BREAK);
        printlnWithIndentation(string);
        System.out.println(HELP_PAGE + LINE_BREAK);
    }

    public static void printlnWithIndentation(String string) {
        System.out.println(INDENTATION + string);
    }
}
