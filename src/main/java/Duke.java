import java.lang.reflect.Array;
import java.time.format.TextStyle;
import java.util.Scanner;

public class Duke {

    public static String HorizontalLine = "__________________________\n";
    private static Task[] taskList = new Task[101];

    public static void main(String[] args) {
        System.out.println(HorizontalLine + "Hello! I'm Duke\n" + "What can I do for you?\n"
                + HorizontalLine);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("bye")) {
            String[] inputWords = input.split(" ", 2);
            switch (inputWords[0]) {
            case "list":
                displayList();
                break;
            case "mark":
                markTask(inputWords);
                break;
            case "unmark":
                unmarkTask(inputWords);
                break;
            case "todo":
                createTodo(inputWords);
                break;
            case "deadline":
                createDeadline(inputWords);
                break;
            case "event":
                createEvent(inputWords);
                break;
            }
            input = in.nextLine();
        }
        System.out.println(HorizontalLine + "Goodbye!" + "\n" + HorizontalLine);
    }

    private static void createEvent(String[] inputWords) {
        String[] event = inputWords[1].split(" /from | /to ", 3);
        taskList[Task.maxTaskNumber] = new Event(event[0], event[1], event[2]);
        Task.maxTaskNumber++;
        System.out.printf(HorizontalLine + "Event added: %s (from: %s to: %s)\n" + HorizontalLine, event[0], event[1], event[2]);
    }

    private static void createDeadline(String[] inputWords) {
        String[] deadline = inputWords[1].split(" /by ", 2);
        taskList[Task.maxTaskNumber] = new Deadline(deadline[0], deadline[1]);
        Task.maxTaskNumber++;
        System.out.printf(HorizontalLine + "Deadline added: %s (by: %s)\n" + HorizontalLine, deadline[0], deadline[1]);
    }

    private static void createTodo(String[] inputWords) {
        taskList[Task.maxTaskNumber] = new ToDo(inputWords[1]);
        Task.maxTaskNumber++;
        System.out.println(HorizontalLine + "To do added: " + inputWords[1] + "\n" + HorizontalLine);
    }

    private static void unmarkTask(String[] inputWords) {
        int unmarkTaskNumber = Integer.valueOf(inputWords[1]);
        if (unmarkTaskNumber >= Task.maxTaskNumber) {
            System.out.println("No such task found\n" + HorizontalLine);
        } else {
            taskList[unmarkTaskNumber].unsetDone();
            System.out.println(HorizontalLine + "Task set as undone: " + taskList[unmarkTaskNumber].getTaskName() +
                    "\n" + HorizontalLine);
        }
    }

    private static void markTask(String[] inputWords) {
        int markTaskNumber = Integer.valueOf(inputWords[1]);
        if (markTaskNumber >= Task.maxTaskNumber) {
            System.out.println("No such task found\n" + HorizontalLine);
        } else {
            taskList[markTaskNumber].setDone();
            System.out.println(HorizontalLine + "Task set as done: " + taskList[markTaskNumber].getTaskName() + "\n"
                    + HorizontalLine);
        }
    }

    private static void displayList() {
        if (taskList[1] != null) {
            System.out.println(HorizontalLine + "List of Tasks: \n");
            for (int i = 1; i < Task.maxTaskNumber; i++) {
                System.out.printf("%d.", i);
                taskList[i].getTaskStatus();
            }
            System.out.println(HorizontalLine);
        } else {
            System.out.println(HorizontalLine + "no task added yet\n" + HorizontalLine);
        }
    }
}

