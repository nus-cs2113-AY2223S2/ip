import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import tasks.*;

import static java.util.stream.Collectors.toList;

public class ThomasShelby {
    static ArrayList<Task> taskManager = new ArrayList<>();

    private static void listTasks(ArrayList<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". "
                    + taskList.get(i));
        }
    }

    private static void addToDo(String[] parsedCommand) {
        ToDo newToDo = new ToDo(parsedCommand[1]);
        taskManager.add(newToDo);
        System.out.println("Don't sleep on it.\n" + "added: " + newToDo);
    }

    private static void addDeadline(String[] parsedCommand) {
        String[] taskAndDeadline = parsedCommand[1].split("/", 2);
        Deadline newDeadline = new Deadline(taskAndDeadline[0], taskAndDeadline[1]);
        taskManager.add(newDeadline);
        System.out.println("Time is money, chop chop!\n" + "added: " + newDeadline);
    }

    private static void addEvent(String[] parsedCommand) {
        String[] taskAndDuration = parsedCommand[1].split("/");
        Event newEvent = new Event(taskAndDuration[0], taskAndDuration[1], taskAndDuration[2]);
        taskManager.add(newEvent);
        System.out.println("I'll see you there.\n" + "added: " + newEvent);
    }

    private static void markTask(String[] parsedCommand) {
        int whichTask = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(whichTask).setIsDone(true);
        System.out.println("That was long due, well done.\n" + taskManager.get(whichTask));
    }

    private static void unmarkTask(String[] parsedCommand) {
        int whichTask = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        taskManager.get(whichTask).setIsDone(false);
        System.out.println("You've gotten lazy.\n" + taskManager.get(whichTask));
    }

    private static void deleteTask(String[] parsedCommand) {
        int taskNumber = Integer.parseInt(parsedCommand[1]) - 1; // shift back index
        Task taskToDelete = taskManager.get(taskNumber); // store in temp var to print later
        taskManager.remove(taskNumber);
        System.out.println("That's off the list: \n" + taskToDelete);
        System.out.println("You're left with " + taskManager.size() + " tasks.");
    }

    private static ArrayList<Task> findTask(String[] parseCommand) {
        ArrayList<Task> filteredTaskList = (ArrayList<Task>) taskManager.stream()
                .filter(task -> task.getDescription().contains(parseCommand[1]))
                .collect(toList());
        return filteredTaskList;
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Good day, I'm Thomas Shelby.\nTo what do I owe the pleasure?\n");
        File data = new File("data/data.txt");
        Data.loadData(taskManager);
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String command = in.nextLine();
                String[] parsedCommand = Parser.parseCommand(command); // user input split into individual words
                switch (parsedCommand[0]) {
                case "bye":
                    Data.saveData(taskManager);
                    System.out.println("Cheers.");
                    return;
                case "list":
                    listTasks(taskManager);
                    break;
                case "todo":
                    addToDo(parsedCommand);
                    break;
                case "deadline":
                    addDeadline(parsedCommand);
                    break;
                case "event":
                    addEvent(parsedCommand);
                    break;
                case "mark":
                    markTask(parsedCommand);
                    break;
                case "unmark":
                    unmarkTask(parsedCommand);
                    break;
                case "delete":
                    deleteTask(parsedCommand);
                    break;
                case "find":
                    listTasks(findTask(parsedCommand));
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