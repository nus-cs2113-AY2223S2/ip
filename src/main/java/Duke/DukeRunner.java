
// import java.util.Arrays;
import java.util.Scanner;

import Handlers.TaskManager;
import Handlers.Parser;
import Tasks.Task;
import java.util.ArrayList;

public class DukeRunner {

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        System.out.println("==============================\n");
        Scanner in = new Scanner(System.in);
        boolean isComplete = false;

        while (!isComplete) {

            String line = in.nextLine();
            String firstWord = Parser.getFirstWord(line);
            ArrayList<Task> taskList = TaskManager.getTaskList();

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

            System.out.println("==============================\n");
        }

        in.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
