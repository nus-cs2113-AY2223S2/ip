package ui;


import exception.IncompleteInputException;
import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static final int LINE_LENGTH = 50;
    private  final Scanner scanner;
    public UI() {
        scanner = new Scanner(System.in);
    }

    public void printLine() {
        System.out.println("\t " + "_".repeat(LINE_LENGTH));
    }

    public void printGreeting() {
        printLine();
        System.out.println("\t Hello I'm duke.Duke, your personal chatbot.");
        System.out.println("\t Is there anything I can do for you");
        printLine();
    }

    public void printAddTaskSuccess(Task task, int taskCount) {
        printLine();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\t Now you have " + taskCount + " tasks in your list.");
        printLine();
    }

    public void printRemoveTaskSuccess(Task task, int taskCount) {
        printLine();
        System.out.println("\t Noted. I've removed this task: ");
        System.out.println("\t " + task);
        System.out.println("\t Now you have " + taskCount + " tasks in your list");
        printLine();
    }

    public void printList(ArrayList<Task> tasks) {
        printLine();
        if (tasks.size() ==0) {
            System.out.println("\t Seems like you do not have any task at the moment...");
        } else {
            System.out.println("\t Here are the tasks for your list: ");
            int i =1;
            for (Task t: tasks) {
                System.out.println("\t " + i++ + "." + t.toString());
            }
        }
        printLine();
    }

    public void printMarkSuccess (Task task) {
//        if (taskIndex < 0 || taskList.get(taskIndex) == null) {
//            throw new DukeException("Please ensure that you enter the correct task number");
//        }
        //only gets executed if no exception is thrown
//        taskList.get(taskIndex).isDone = true;
        printLine();
        System.out.println("\t Nice, I have marked this task as done: ");
        System.out.println("\t " + task);
        printLine();
    }

    public void printUnmarkSuccess (Task task)  {
//        if (taskIndex < 0 || taskList.get(taskIndex) == null) {
//            throw new DukeException("Please ensure that you enter the correct task number");
//        }
//        taskList.get(taskIndex).isDone = false;
        printLine();
        System.out.println("\t Ouch, I have unmarked this task: ");
        System.out.println("\t " + task);
        printLine();
    }

    public void printBye() {
        printLine();
        System.out.println("\t Bye! Do let me know if you need any further assistance");
        printLine();
    }

    public void printSavingError(java.io.IOException ex) {
        printLine();
        System.out.println("Exception Occured: " + ex);
        System.out.println("Failed to save your file");
        printLine();
    }

    public void printError(Exception ex) {
        printLine();
        System.out.println("Exception Occured: " + ex);
        printLine();
    }

    public String getCommand() {
        return scanner.nextLine();
    }

}
