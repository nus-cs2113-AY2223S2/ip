package Handlers;

import java.util.Scanner;

public class Ui {

    public static void printHello() {
        System.out.println("Hello! I'm DukeRunner\nWhat can I do for you?\n");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printLineBreak() {
        System.out.println("==============================\n");
    }

    public static void takeUserInputs() {

        boolean isComplete = false;
        Scanner in = new Scanner(System.in);

        while (!isComplete) {


            String line = in.nextLine();
            String firstWord = Parser.getFirstWord(line);

            switch (firstWord) {
            case "mark":
                TaskManager.markTask(Parser.getTaskNumber(line));
                break;
            case "unmark":
                TaskManager.unmarkTask(Parser.getTaskNumber(line));
                break;
            case "delete":
                int taskNumberDelete = Parser.getTaskNumber(line);
                TaskManager.deleteTask(taskNumberDelete);
                break;
            case "list":
                TaskManager.listTask();
                break;
            case "todo":
                Parser.getTodoDetails(line);
                break;
            case "deadline":
                Parser.getDeadlineDetails(line);
                break;
            case "event":
                Parser.getEventDetails(line);
                break;
            case "find":
                TaskManager.findTask(Parser.getTaskDescription(line));
                break;
            case "bye":
                isComplete = true;
                break;
            default:
                System.out.println("Unrecognized command. Please try again.");
                break;
            }

            Ui.printLineBreak();
        }

        in.close();
    }
}
