
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import duke.TaskManager;

public class Duke {

    public static void printInstructions() {
        System.out.println("LIST OF ALL COMMANDS:");
        System.out.println("todo + \"task name\" to add a task");
        System.out.println("deadline + \"task name\" + /by \"task deadline\" to add a task with a deadline");
        System.out.println(
                "event + \"event name\" + /from \"event start time\" + /to \"event finish time\" to add an event with a stant and finish time");
        System.out.println("list to list all events and tasks available");
        System.out.println("mark + \"task number\" to mark an item");
        System.out.println("unmark + \"task number\" to unmark an item");
        System.out.println("delete + \"task number\" to remove an item");
        System.out.println("clear to clear all data in the hard drive");
    }

    public static void printHorizontalLine() {
        for (int i = 0; i <= 30; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void printfalseInput() {
        System.out.println("Sorry Duke could not understand your input :> please follow the instructions");
        printInstructions();
    }

    public static String firstWord(String s) {
        String a[] = s.split(" ");
        return a[0];
    }

    public static void executeAddTodo(String s, TaskManager listofItems) {
        try {
            s = s.substring("todo ".length(), s.length());
            listofItems.addTask(s, false);
            System.out.println("Roger. The following todo has been added:");
            System.out.println("[T][ ] " + s);
            System.out.println("You now have " + listofItems.getSize() + " item in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Missing todo item. Please read instructions again");
            printInstructions();
        }
    }

    public static void executeAddDeadline(String s, TaskManager listofItems) {
        try {
            s = s.substring("deadline ".length(), s.length());
            String[] cmd = s.split(" /by ");
            if (cmd.length > 2) {
                System.out.println("Extra deadline found! Please try again!");
                printInstructions();
                return;
            }
            listofItems.addDeadline(cmd[0], cmd[1], false);
            System.out.println("Roger. The following deadline has been added:");
            System.out.println("[D][ ] " + cmd[0] + " (by: " + cmd[1] + ")");
            System.out.println("You now have " + listofItems.getSize() + " item in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Your task name is missing please try again!");
            printInstructions();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Your deadline is missing please try again!");
            printInstructions();
        }

    }

    public static void executeAddEvent(String s, TaskManager listofItems) {
        try {
            s = s.substring("event ".length(), s.length());
            String[] cmd = s.split(" /from ");
            String startTime = cmd[1].split(" /to ")[0];
            String endTime = cmd[1].split(" /to ")[1];
            listofItems.addEvent(cmd[0], startTime, endTime, false);
            System.out.println("Roger. The following event has been added:");
            System.out.println("[E][ ] " + cmd[0] + " (from: " + startTime + " to: " + endTime + ")");
            System.out.println("You now have " + listofItems.getSize() + " item in the list");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Your task name is missing please try again!");
            printInstructions();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Something is wrong with your event's start time or finish time please try again!");
            printInstructions();
        }
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you ?");
        printHorizontalLine();
        printInstructions();
        printHorizontalLine();
    }

    public static void main(String[] args) throws IOException {
        printGreeting();
        Scanner scanObj = new Scanner(System.in);
        TaskManager listofItems = new TaskManager();
        loadFile(listofItems);
        String userCmd = scanObj.nextLine();
        while (!userCmd.equals("bye")) {
            switch (firstWord(userCmd)) {
                case "todo":
                    executeAddTodo(userCmd, listofItems);
                    printHorizontalLine();
                    listofItems.saveFile();
                    break;
                case "deadline":
                    executeAddDeadline(userCmd, listofItems);
                    printHorizontalLine();
                    listofItems.saveFile();
                    break;
                case "event":
                    executeAddEvent(userCmd, listofItems);
                    printHorizontalLine();
                    listofItems.saveFile();
                    break;
                case "list":
                    listofItems.listTask();
                    printHorizontalLine();
                    listofItems.saveFile();
                    break;
                case "mark":
                    String markId[] = userCmd.split(" ");
                    listofItems.markTask(Integer.parseInt(markId[1]) - 1);
                    printHorizontalLine();
                    listofItems.saveFile();
                    break;
                case "unmark":
                    String unmarkId[] = userCmd.split(" ");
                    listofItems.unmarkTask(Integer.parseInt(unmarkId[1]) - 1);
                    printHorizontalLine();
                    listofItems.saveFile();
                    break;
                case "delete":
                    String deleteId[] = userCmd.split(" ");
                    listofItems.deleteTask(Integer.parseInt(deleteId[1]) - 1);
                    printHorizontalLine();
                    listofItems.saveFile();
                    break;
                case "clear":
                    listofItems.clearData();
                    listofItems.saveFile();
                    printHorizontalLine();
                    break;
                default:
                    printfalseInput();
                    printHorizontalLine();
            }
            userCmd = scanObj.nextLine();
        }
        scanObj.close();
        printGoodbye();
    }

    public static void loadFile(TaskManager listOfItems) throws FileNotFoundException {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String l = s.nextLine();
            if (l.startsWith(" [T]")) {
                loadTodo(listOfItems, l);
            } else if (l.startsWith(" [D]")) {
                loadDeadline(listOfItems, l);
            } else if (l.startsWith(" [E]")) {
                loadEvent(listOfItems, l);
            }
        }
        s.close();
    }

    public static void loadTodo(TaskManager listOfItems, String l) {
        String name = l.substring(7).trim();
        if (l.contains("[ ]")) {
            listOfItems.addTask(name, false);
        } else {
            listOfItems.addTask(name, true);
        }
    }

    public static void loadEvent(TaskManager listOfItems, String l) {
        int fromIndex = l.indexOf("(from: ");
        int toIndex = l.indexOf(" to: ", fromIndex);
        String name = l.substring(8, fromIndex).trim();
        String startTime = l.substring(fromIndex + 7, toIndex);
        String finishTime = l.substring(toIndex + 5, l.length() - 1);
        if (l.contains("[ ]")) {
            listOfItems.addEvent(name, startTime, finishTime, false);
        } else {
            listOfItems.addEvent(name, startTime, finishTime, true);
        }
    }

    public static void loadDeadline(TaskManager listOfItems, String l) {
        int byIndex = l.indexOf("(by: ");
        String name = l.substring(8, byIndex - 1);
        int endIndex = l.indexOf(")");
        String deadline = l.substring(byIndex + 5, endIndex);
        if (l.contains("[ ]")) {
            listOfItems.addDeadline(name, deadline, false);
        } else {
            listOfItems.addDeadline(name, deadline, true);
        }
    }

    private static void printGoodbye() {
        System.out.println("Thanks for using Duke! See ya!");
        System.out.println(" /\\_/\\  ");
        System.out.println("( o.o ) ");
        System.out.println(" > ^ <  ");
    }
}
