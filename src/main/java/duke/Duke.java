package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import parser.EmptyCommandException;
import parser.IParser;
import parser.Parser;
import task.ITaskController;
import task.TaskController;

public class Duke {
    static String LINEBREAK = "________________________________________\n";
    static String LINE_TAB_STRING = "    ";
    static String COMMAND_TAB_STRING = "     ";
    private static Scanner sc = new Scanner(System.in);
    private static IParser parser = new Parser(sc);
    private static ITaskController taskController = new TaskController();
    /**
     * Main program that runs the Duke program.
     * Greets users and exits.
     * @param args
     */
    public static void main(String[] args) {
        greet();
        ArrayList<?> list;
        boolean isExit = false;
        do {
            try {
                switch (parser.getCommand()) {
                case EXIT:
                    isExit = true;
                    break;
                case LIST:
                    list = taskController.getTasks();
                    printSystemMessage(list);
                    break;
                case TASK:
                    printSystemMessage("Got it. I've added this task:\n " +
                    taskController.addTask(parser.getTask()).toString() +
                    String.format("\nNow you have %d tasks in the list.", taskController.getCount()));
                    break;
                case UNMARK:
                    printSystemMessage(taskController.unmarkTask(parser.getTaskIndex()));
                    break;
                case MARK:
                    printSystemMessage(taskController.markTask(parser.getTaskIndex()));
                    break;
                default:
                    break;
                }
            } catch (DukeException e) {
                printSystemMessage(e.getMessage());
            } 
        } while(!isExit);
        bye();
    }
    /**
     * Streams message and prints each line of message based
     * on new line feed.
     * @param message Output message to print
     */
    private static void printSystemMessage(String message) {
        System.out.println(LINE_TAB_STRING + LINEBREAK.substring(0, LINEBREAK.length()-1));
        Arrays.stream(message.split("\n"))
                .map(item -> COMMAND_TAB_STRING + item + '\n')
                .forEach(System.out::print);
        System.out.println(LINE_TAB_STRING + LINEBREAK);
    }
    private static <T> void printSystemMessage(ArrayList<T> list) {
        System.out.println(LINE_TAB_STRING + LINEBREAK.substring(0, LINEBREAK.length()-1));
        IntStream.range(0, list.size())
            .mapToObj(index -> String.format("%s%d. %s", COMMAND_TAB_STRING, index+1, list.get(index)))
            .forEach(System.out::println);
        System.out.println(LINE_TAB_STRING + LINEBREAK);
    }
    /**
     * Prints greet message to user
     */
    private static void greet() {
        String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo);
        printSystemMessage("Hello! I'm Duke\nWhat can I do for you?");
    }
    /**
     * Prints bye messgae to user
     */
    private static void bye() {
        printSystemMessage("Bye. Hope to see you again soon!");
    }
}