package bro;

import bro.tasks.Task;
import static bro.Messages.HORIZONTAL_LINE;
import static bro.Messages.GREETING;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Returns the input in the form of an array of Strings
     **/
    public String[] getUserCommand() {
        return in.nextLine().split(" ");
    }

    public static void printGreeting() {
        System.out.println(HORIZONTAL_LINE + GREETING + HORIZONTAL_LINE);
    }

    public static void printReply(String reply) {
        System.out.println(HORIZONTAL_LINE + reply + HORIZONTAL_LINE);
    }

    public static void printException(Exception e) {
        System.out.println(HORIZONTAL_LINE + e.toString() + HORIZONTAL_LINE);
    }
    public static void printException(String message) {
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    public static void printTaskList(TaskList taskListObject) {
        ArrayList<Task> taskList = taskListObject.getTaskList();
        StringBuilder reply = new StringBuilder(" Your tasks:\n");
        for (int i = 0; i < taskList.size(); ++i) {
            Task currentTask = taskList.get(i);
            String mark = currentTask.mark();
            reply.append(" ").append(i + 1).append(".[").append(currentTask.getType()).append("][").append(mark).append("] ").append(currentTask).append("\n");
        }
        printReply(reply.toString());
    }
}
