
// import java.util.Arrays;
import java.util.Scanner;

import Handlers.TaskManager;
import Handlers.Parser;
import Handlers.StorageManager;
public class DukeRunner {

    public static void main(String[] args) {
        printHello();
        printLineBreak();
        StorageManager.loadFileContents();
        Scanner in = new Scanner(System.in);

        boolean isComplete = false;

        while (!isComplete) {

            String line = in.nextLine();
            String firstWord = Parser.getFirstWord(line);

            switch (firstWord) {
            case "mark":
                int taskNumberMark = Parser.getTaskNumber(line);
                TaskManager.markTask(taskNumberMark);
                break;
            case "unmark":
                int taskNumberUnmark = Parser.getTaskNumber(line);
                TaskManager.unmarkTask(taskNumberUnmark);
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
            case "bye":
                isComplete = true;
                break;
            default:
                System.out.println("Unrecognized command. Please try again.");
                break;
            }

            printLineBreak();
        }

        in.close();
        StorageManager.saveFileContents();
        printBye();
    }

    private static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void printLineBreak() {
        System.out.println("==============================\n");
    }

    private static void printHello() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
    }
}
