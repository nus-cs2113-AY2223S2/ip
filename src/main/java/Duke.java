import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.Scanner;

public class Duke {

    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\____|_|\\_\\___|\n";
    public static final String HORIZONTAL_LINE = "============================================================";

    public static void main(String[] args) {
        greeting();
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[100];
        int counter = 1; //start from 1
        String userCommand = "";
        handleUserCommand(in, list, counter, userCommand);
    }

    private static void handleUserCommand(Scanner in, Task[] list, int counter, String userCommand) {
        while (!userCommand.contains("bye")) {
            userCommand = in.nextLine();
            if (userCommand.contains("list")) {
                if (counter == 1) {
                    System.out.println(HORIZONTAL_LINE + "\n");
                    System.out.println("Your day is clear!");
                    System.out.println(HORIZONTAL_LINE + "\n");
                } else {
                    printListOfTasks(list, counter);
                }
            } else if (userCommand.contains("bye")) {
                bye();
            } else if (userCommand.contains("unmark")) {
                unmarkTask(list, userCommand, counter);
            } else if (userCommand.contains("mark")) {
                markTask(list, userCommand, counter);
            } else if (userCommand.contains("todo")) {
                addTodoTask(list, counter, userCommand);
                counter++;
            } else if (userCommand.contains("deadline")) {//Task type D
                addDeadlineTask(list, counter, userCommand);
                counter++;
            } else if (userCommand.contains("event")) {
                addEventTask(list, counter, userCommand);
                counter++;
            }else{
                invalidEntry();
            }
        }
    }

    private static void invalidEntry() {
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("I cannot understand your instruction please re-enter");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void markTask(Task[] list, String ins, int counter) {
        if (counter == 1 ){
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("Your day is clear! there is no task");
            System.out.println(HORIZONTAL_LINE + "\n");
        }else {
            System.out.println(HORIZONTAL_LINE + "\n");
            if (ins.length() < 6){
                System.out.println("Please specify the task you want to mark :) ");
                System.out.println(HORIZONTAL_LINE + "\n");
                return;
            }
            int idx = 5;
            String subStr = ins.substring(idx);
            if (subStr.contains(" ")){
                System.out.println("Please specify the task you want to mark :) ");
            }else {
                int taskNum = Integer.parseInt(subStr);
                boolean isNumWithinCounter = (taskNum < counter) && (taskNum > 0);
                if (isNumWithinCounter) {
                    System.out.println("Nice! You have done Task " + taskNum);
                    list[taskNum - 1].setIsDone(true);
                }else {
                    System.out.println("Please enter a valid task No. :) ");
                }
            }
            System.out.println(HORIZONTAL_LINE + "\n");

        }
    }

    private static void unmarkTask(Task[] list, String ins, int counter) {
        if (counter == 1 ){
            System.out.println(HORIZONTAL_LINE + "\n");
            System.out.println("Your day is clear! there is no task");
            System.out.println(HORIZONTAL_LINE + "\n");
        }else {
            System.out.println(HORIZONTAL_LINE + "\n");
            if (ins.length() < 6){
                System.out.println("Please specify the task you want to mark :) ");
                System.out.println(HORIZONTAL_LINE + "\n");
                return;
            }
            int idx = 7;
            String subStr = ins.substring(idx);
            if (subStr.contains(" ")){
                System.out.println("Please specify the task you want to mark :) ");
            }else {
                int taskNum = Integer.parseInt(subStr);
                boolean isNumWithinCounter = (taskNum < counter) && (taskNum > 0);
                if (isNumWithinCounter) {
                    System.out.println("Okay, I have unmarked Task " + taskNum);
                    list[taskNum - 1].setIsDone(false);
                }else {
                    System.out.println("Please enter a valid task No. :) ");
                }
            }
            System.out.println(HORIZONTAL_LINE + "\n");

        }
    }

    private static void greeting() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void bye() {
        System.out.println(HORIZONTAL_LINE + "\n");
        System.out.println("Bye! See you next time!");
        System.out.println(HORIZONTAL_LINE + "\n");
    }


    private static void printListOfTasks(Task[] list, int counter) {
        System.out.println(HORIZONTAL_LINE + "\n");
        for (int i = 0; i < counter - 1; i++) {
            System.out.println(list[i].toString());
        }
        System.out.println("\n" + "Found " + (counter - 1) + " Task!");
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void addEventTask(Task[] list, int counter, String ins) {
        System.out.println(HORIZONTAL_LINE + "\n");
        String[] arrOfStr = ins.split("event", 2);
        arrOfStr = arrOfStr[1].split("/", 3);
        String name = arrOfStr[0];
        String from = arrOfStr[1].split("from")[1];
        String to = arrOfStr[2].split("to")[1];
        list[counter - 1] = new Event(name, counter, from, to);
        System.out.println("Added: " + ins);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void addDeadlineTask(Task[] list, int counter, String ins) {
        System.out.println(HORIZONTAL_LINE + "\n");
        String[] arrOfStr = ins.split("deadline");
        arrOfStr = arrOfStr[1].split("/by ", 2);
        String name = arrOfStr[0];
        String by = arrOfStr[1];
        list[counter - 1] = new Deadline(name, counter, by);
        System.out.println("Added: " + ins);
        System.out.println(HORIZONTAL_LINE + "\n");
    }

    private static void addTodoTask(Task[] list, int counter, String ins) {
        System.out.println(HORIZONTAL_LINE + "\n");
        String[] arrOfStr = ins.split("todo", 2);
        String name = arrOfStr[1];
        list[counter - 1] = new Todo(name, counter);
        System.out.println("Added: " + ins);
        System.out.println(HORIZONTAL_LINE + "\n");
    }
}