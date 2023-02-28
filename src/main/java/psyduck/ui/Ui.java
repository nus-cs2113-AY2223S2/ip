package psyduck.ui;

import psyduck.command.Command;
import psyduck.task.*;
import psyduck.tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private final Scanner in = new Scanner(System.in);
    public static void linePrint() {
        for (int i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public String readInput() {
        String input = in.nextLine();
        return input;
    }

    public void greet() {
        Ui.linePrint();
        System.out.println("Hi I am Psyduck! How can I help you?");
        Ui.linePrint();
    }


    public void sayBye() {
        System.out.println("Bye see you soon! :) ");
        Ui.linePrint();
    }

    public static void printTaskAdded(TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has added the task: " + TaskList.getNewestTask());
        System.out.print("You now have: " + TaskList.getTaskCount());
        if (TaskList.getTaskCount() == 1) {
            System.out.println(" task");
        } else {
            System.out.println(" tasks");
        }
        linePrint();
    }

    public static void printTaskRemoved(Task task, TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has removed the task: " + task);
        linePrint();
    }

    public static void printTaskMarked(Task task, TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has marked the task: " + task);
        linePrint();
    }

    public static void printTaskUnmarked(Task task, TaskList tasks) {
        linePrint();
        System.out.println("Psyduck has marked the task: " + task);
        linePrint();
    }
}
