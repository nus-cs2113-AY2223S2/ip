import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import tasks.*;

public class ThomasShelby {
    static ArrayList<Task> taskManager = new ArrayList<>();
    static int taskCount = 0;

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". "
                    + taskManager.get(i));
        }
    }

    private static void addToDo(String[] cmdSplit) {
        ToDo newToDo = new ToDo(cmdSplit[1]);
        taskManager.add(newToDo);
        taskCount++;
        System.out.println("Don't sleep on it.\n" + "added: " + newToDo);
    }

    private static void addDeadline(String[] cmdSplit) {
        String[] taskAndDeadline = cmdSplit[1].split("/", 2);
        Deadline newDeadline = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        taskManager.add(newDeadline);
        taskCount++;
        System.out.println("Time is money, chop chop!\n" + "added: " + newDeadline);
    }

    private static void addEvent(String[] cmdSplit) {
        String[] taskAndDuration = cmdSplit[1].split("/");
        Event newEvent = new Event(taskAndDuration[0], taskAndDuration[1], taskAndDuration[2]);
        taskManager.add(newEvent);
        taskCount++;
        System.out.println("I'll see you there.\n" + "added: " + newEvent);
    }

    private static void markTask(String[] cmdSplit) {
        int whichTask;
        whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        taskManager.get(whichTask).setIsDone(true);
        System.out.println("That was long due, well done.\n" + taskManager.get(whichTask));
    }

    private static void unmarkTask(String[] cmdSplit) {
        int whichTask;
        whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        taskManager.get(whichTask).setIsDone(false);
        System.out.println("You've gotten lazy.");
        System.out.println(taskManager.get(whichTask));
    }

    private static void deleteTask(String[] cmdSplit) {
        int whichTask = Integer.parseInt(cmdSplit[1]) - 1; // shift back index
        Task taskToDelete = taskManager.get(whichTask);
        taskManager.remove(whichTask);
        System.out.println("That's off the list: \n" + taskToDelete);
        System.out.println("You're left with " + taskManager.size() + " tasks.");
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