import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import tasks.*;

public class ThomasShelby {
    static final int MAX_TASKS = 100;

    static Task[] taskManager = new Task[MAX_TASKS];
    static int taskCount = 0;

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". "
                    + taskManager[i]);
        }
    }

    private static void addToDo(String[] cmdSplit) {
        ToDo newToDo = new ToDo(cmdSplit[1]);
        taskManager[taskCount] = newToDo;
        taskCount++;
        System.out.println("Don't sleep on it.");
        System.out.println("added: " + newToDo);
    }

    private static void addDeadline(String[] cmdSplit) {
        String[] taskAndDeadline = cmdSplit[1].split("/", 2);
        Deadline newDeadline = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        taskManager[taskCount] = newDeadline;
        taskCount++;
        System.out.println("Time is money, chop chop!");
        System.out.println("added: " + newDeadline);
    }

    private static void addEvent(String[] cmdSplit) {
        String[] taskAndDuration = cmdSplit[1].split("/");
        Event newEvent = new Event(taskAndDuration[0], taskAndDuration[1], taskAndDuration[2]);
        taskManager[taskCount] = newEvent;
        taskCount++;
        System.out.println("I'll see you there.");
        System.out.println("added: " + newEvent);
    }

    private static void markTask(String[] cmdSplit) {
        int whichTask;
        whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        taskManager[whichTask].setIsDone(true);
        System.out.println("That was long due, well done.");
        System.out.println(taskManager[whichTask]);
    }

    private static void unmarkTask(String[] cmdSplit) {
        int whichTask;
        whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        taskManager[whichTask].setIsDone(false);
        System.out.println("You've gotten lazy.");
        System.out.println(taskManager[whichTask]);
    }

    private static void deleteTask(String[] cmdSplit) {
        int whichTask;
        whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        taskManager[whichTask] = null;
        System.out.println("Noted, deleted this task.");
        System.out.println(taskManager[whichTask]);
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Good day, I'm Thomas Shelby.\nTo what do I owe the pleasure?\n");
        File data = new File("/data/data.txt");
        Data.loadData(data, taskManager, taskCount);
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String cmd = in.nextLine();
                String[] cmdSplit = cmd.split(" ", 2); // user input split into individual words
                switch (cmdSplit[0]) {
                case "bye":
                    Data.saveData(taskManager, taskCount);
                    System.out.println("Cheers.");
                    return;
                case "list":
                    listTasks();
                    break;
                case "todo":
                    addToDo(cmdSplit);
                    break;
                case "deadline":
                    addDeadline(cmdSplit);
                    break;
                case "event":
                    addEvent(cmdSplit);
                    break;
                case "mark":
                    markTask(cmdSplit);
                    break;
                case "unmark":
                    unmarkTask(cmdSplit);
                    break;
                case "delete":
                    deleteTask(cmdSplit);
                    break;
                default:
                    throw new Exception();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Something's wrong: " + e);
                System.out.println("You probably didn't include the task or the timeframe.");
            } catch (Exception e) {
                System.out.println("Don't know what that means comrade.");
            }
        }
    }
}