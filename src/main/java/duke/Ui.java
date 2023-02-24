package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        System.out.println("Hello! I'm Duke  U ´ᴥ` U\n" + "What can I do for you?");
        printLine();
    }
    public static void showInitErrorMessage() {
        System.out.println("No previous file found, Duke will try to create a file to store your data.");
    }
    public static void showGoodByeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!ﾉ~");
        printLine();
    }
    public static String getUserCommand() {
        Scanner scan = new Scanner(System.in);
        String s;
        s = scan.nextLine();
        while (s.trim().isEmpty() || s.trim().charAt(0) == '#') {
            s = scan.nextLine();
        }
        return s;
    }
    public static void showResult(ArrayList<Task> tasks, int taskCount) {
        printLine();
        System.out.println("Got it. I've added this task: \n" + tasks.get(taskCount-1));
        System.out.println("Now you have " + taskCount + " tasks in your list.");
        printLine();
    }
    public static void showAddTaskMessage(ArrayList<Task> tasks) {
        printLine();
        System.out.println("Got it. I've added this task: \n" + tasks.get(tasks.size()-1));
        System.out.println("Now you have " + tasks.size() + " tasks in your list. ^ ^");
        printLine();
    }
}