import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void printList(Task[] l1, int currListIndex) {
        int index;
        if (l1[0] == null) {
            System.out.println("List is empty!");
            return;
        }
        for (int i = 0; i < currListIndex; i += 1) {
            index = i + 1;
            System.out.println(index + ". " + l1[i].toString());
        }
        System.out.println();
    }

    public static void printMarked(boolean isChanged, String icon, String description) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as done." + '\n');
        } else {
            System.out.println('\n' + "Nice! I've marked this task as done:");
            System.out.println("  [" + icon + "] " + description + '\n');
        }
    }
    public static void printUnmarked(boolean isChanged, String icon, String description) {
        if (!isChanged) {
            System.out.println('\n' + "Task is originally marked as not done." + '\n');
        } else {
            System.out.println('\n' + "OK, I've marked this task as not done yet:");
            System.out.println("  [" + icon + "] " + description + '\n');
        }
    }

    public static void printAdded(Task task, int taskSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + taskSize + " tasks in the list.");
    }

    public static String[] parseCommands(String line) {
        String[] lineSpaced = line.split(" ");
        for (int i = 2; i < lineSpaced.length; i++) {
            lineSpaced[1] = lineSpaced[1].concat(" " + lineSpaced[i]);
        }
        return lineSpaced;
    }

    public static void printLineSpaced(String [] line) {
        for (String a : line) {
            System.out.print(a + " ");
        }
    }

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                      + "|  _ \\ _   _| | _____ \n"
                      + "| | | | | | | |/ / _ \\\n"
                      + "| |_| | |_| |   <  __/\n"
                      + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm a Robot");
        System.out.println("What can I do for you?\n");
        Task[] tasksList = new Task[100];
        Scanner in = new Scanner(System.in);
        String line, commandWord, description;
        int taskListIndex, currTaskNumber;
        String[] lineSpaced;
        boolean isChanged = false;

        line = in.nextLine();
        lineSpaced = parseCommands(line);
        currTaskNumber = 0;
        while(!lineSpaced[0].equals("bye")) {
            commandWord = lineSpaced[0];
            switch (commandWord) {
            case "list":
                printList(tasksList, currTaskNumber);
                break;
            case "mark":
                description = lineSpaced[1];
                taskListIndex = Integer.parseInt(description) - 1;
                isChanged = false;
                if (!tasksList[taskListIndex].getIsDone()) {
                    tasksList[taskListIndex].markAsDone();
                    isChanged = true;
                }
                printMarked(isChanged, tasksList[taskListIndex].getStatusIcon(), tasksList[taskListIndex].getDescription());
                break;
            case "unmark":
                description = lineSpaced[1];
                taskListIndex = Integer.parseInt(description) - 1;
                isChanged = false;
                if (tasksList[taskListIndex].getIsDone()) {
                    tasksList[taskListIndex].markAsUndone();
                    isChanged = true;
                }
                printUnmarked(isChanged, tasksList[taskListIndex].getStatusIcon(), tasksList[taskListIndex].getDescription());
                break;
            case "Todo":
                description = lineSpaced[1];
                tasksList[currTaskNumber] = new ToDo (description);
                printAdded(tasksList[currTaskNumber], currTaskNumber+1);
                currTaskNumber++;
                break;
            default:
                /*printAdded(line);
                Task temp_task = new Task(line);
                 */
            }
            line = in.nextLine();
            lineSpaced = parseCommands(line);
        }
        System.out.println('\n' + "Bye. Hope to see you again soon!");
    }
}
