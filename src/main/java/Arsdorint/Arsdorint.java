package Arsdorint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Arsdorint.command.ArsdorintException;
import Arsdorint.task.Deadline;
import Arsdorint.task.Event;
import Arsdorint.task.Task;
import Arsdorint.task.Todo;

public class Arsdorint {
    private static final int MAX_NUM_OF_TASKS = 100;
    private static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!\n";
    private static final String HELLO_MESSAGE =
            " Hello! I'm Arsdorint, a member of Arsdorint Team.\n" +
            " Please Type The Command As Follow:\n";
    private static final String COMMAND_LIST_MESSAGE =
            "> Type \"list\" to list all the tasks. \n" +
            "> Type \"mark\" follow by a number x to mark x tasks in the list. \n" +
            "> Type \"unmark\" follow by a number y to unmark y tasks in the list. \n" +
            "> Type \"todo\" follow by a string x to add a work that need to be done. \n" +
            "> Type \"deadline /x y\" with x is the type of work, y is the time or date of the deadline. \n" +
            "> Type \"event x /y\" with x is the event, y is the time or date of that event. \n" +
            "> Type \"bye\" to exit. \n";
    private static final String QUESTION = " What can I do for you?";

    private static final String STORAGE_DIRECTORY = "./storage";
    private static final String STORAGE_FILE_NAME = "./storage/arsdorintTask.txt";
    private static final String MESSAGE_NEW_FILE = "File created";
    private static final String MESSAGE_OVERWRITE_FILE = "File overwritten";
    private static final String MESSAGE_LOAD_FILE = "File loaded";
    private static final String MESSAGE_NO_FILE = "There is no existing file";
    private static final String MESSAGE_WRONG_FILE = "The storage files entry is invalid. Please save to overwrite";
    private static final String MESSAGE_DIVIDER =
            "____________________________________________________________";
    private static final String MESSAGE_DIVIDER_LIST =
            "____________________________LIST____________________________";
    private static final String MESSAGE_DELETE = "Noted. I've removed this task:";

    private static final String ERROR_MESSAGE_BYE = " ";
    private static final String ERROR_MESSAGE_LIST = " ";
    private static final String ERROR_MESSAGE_MARK =
            "=( OOPS!!! The description of a mark cannot be empty.\n" + "Syntax for mark\n\t" +
                    ">>> mark <item index number> \n" + "Note: item index must exist in the current list";
    private static final String ERROR_MESSAGE_UNMARK =
            "=( OOPS!!! The description of an unmark cannot be empty.\n" + "Syntax for unmark\n\t" +
                    ">>> unmark <item index number> \n" + "Note: item index must exist in the current list";
    private static final String ERROR_MESSAGE_TODO =
            "=( OOPS!!! The description of a todo cannot be empty.\n" + "Syntax for todo\n\t>>> todo <task>";
    private static final String ERROR_MESSAGE_DEADLINE =
            "=( OOPS!!! The description of a deadline cannot be empty.\n" +
            "Syntax for deadline\n\t>>> deadline <task> /<date of deadline";
    private static final String ERROR_MESSAGE_EVENT =
            "=( OOPS!!! The description of an event cannot be empty.\n" +
            "Syntax for event\n\t>>> event <task> /<date of event";
    private static final String ERROR_MESSAGE_DELETE = "Syntax for delete item \n\t>>> delete <item index number> \n" +
            "Note: item index must exist in the current list";

    static Scanner input = new Scanner(System.in);
    static String logo = "    ___                         _                                 _\n"
            + "   / _ \\     _____   _____  ___| |   ___    _____   _   _____   _| |_\n"
            + "  / /_\\ \\   /  ___| /  __/ /  _  |  / _ \\  /  ___| | | |  _  \\ |_   _|\n"
            + " / _____ \\  | /    __\\ \\   | |_| | | |_| | | /     | | | | | |   | |\n"
            + "/_/     \\_\\ |_|   /____/   \\_____|  \\___/  |_|     |_| |_| |_|   |_|\n";

    static ArrayList<Task> toDoList = new ArrayList<>(MAX_NUM_OF_TASKS);
    static boolean isRunning = true;
    public static void exit() {
        isRunning = false;
        exitMessage();
    }

    private static void wrongCommandMessage() {
        System.out.println("=( OOPS!!! I'm sorry, but I don't know what that means :-(\n" + MESSAGE_DIVIDER);
    }

    private static void list() {
        showToUser(MESSAGE_DIVIDER_LIST);
        for (int i = 0; i < Task.numOfTasks; i++) {
            System.out.print((i + 1) + ".");
            toDoList.get(i).printTask();
        }
        showToUser(MESSAGE_DIVIDER);
    }

    public static void add(String command) {
        String newTaskType = command.split(" ")[0];
        String[] taskDescription =
                command.replace(newTaskType, "").trim().split("/");
        switch (newTaskType.toLowerCase()) {
            case "todo":
                addToDo(taskDescription);
                break;
            case "deadline":
                addDeadline(taskDescription);
                break;
            case "event":
                addEvent(taskDescription);
                break;
            default:
                break;
        }
    }
    private static void commandErrorHandler(String errorMessage) {
        switch (errorMessage) {
            case "bye":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_BYE, MESSAGE_DIVIDER);
                break;
            case "list":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_LIST, MESSAGE_DIVIDER);
                break;
            case "mark":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_MARK, MESSAGE_DIVIDER);
                break;
            case "unmark":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_UNMARK, MESSAGE_DIVIDER);
                break;
            case "todo":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_TODO, MESSAGE_DIVIDER);
                break;
            case "deadline":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_DEADLINE, MESSAGE_DIVIDER);
                break;
            case "event":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_EVENT, MESSAGE_DIVIDER);
                break;
            case "delete":
                showToUser(MESSAGE_DIVIDER, ERROR_MESSAGE_DELETE, MESSAGE_DIVIDER);
                break;
            default:
                showToUser(MESSAGE_DIVIDER, errorMessage, MESSAGE_DIVIDER);
                break;
        }
    }

    private static void addTaskMessage() {
        showToUser(MESSAGE_DIVIDER);
        System.out.println("\nGot it. I've added this task:\n" + "\t");
        toDoList.get(toDoList.size() - 1).printTask();
        System.out.println("\t" + "Now you have " + Integer.toString(Task.numOfTasks) + " tasks in the list.");
        showToUser(MESSAGE_DIVIDER);
    }

    public static void mark(String[] command) {
        try {
            int idx = Integer.parseInt(command[1]) - 1;
            if (command[0].equalsIgnoreCase("mark")) {
                toDoList.get(idx).isDone = true;
                System.out.println("Nice! I've marked this task as done: \n");
            } else toDoList.get(idx).isDone = false;
            list();
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException err) {
            commandErrorHandler("mark");
        }
    }

    public static void unmark(String[] command) {
        try {
            int idx = Integer.parseInt(command[1]) - 1;
            if (command[0].equalsIgnoreCase("unmark")) {
                System.out.println("OK, I've marked this task as not done yet: \n");
                toDoList.get(idx).isDone = false;
            }
            else {
                toDoList.get(idx).isDone = true;
            }
            list();
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException err) {
            commandErrorHandler("unmark");
        }
    }


    public static void removeTaskMessage(int idx) {
        //showToUser(MESSAGE_DIVIDER);
        System.out.println("\t");
        toDoList.get(idx).printTask();
        System.out.println("\t" + "Now you have " + --Task.numOfTasks + " tasks in the list.");
        showToUser(MESSAGE_DIVIDER);
    }

    public static void delete(String[] command) {
        try {
            int idx = Integer.parseInt(command[1]) - 1;
            if (command[0].equalsIgnoreCase("delete")) {
                toDoList.get(idx).isDone = true;
                System.out.println(MESSAGE_DELETE);
            } else toDoList.get(idx).isDone = false;
            removeTaskMessage(idx);
            toDoList.remove(idx);
        }
        catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException err) {
            commandErrorHandler("delete");
        }
    }

    public static void addToDo(String[] taskDescription) {
        try {
            if (taskDescription[0].trim().isEmpty()) {
                throw new ArsdorintException("Error: Empty Todo Description.");
            }
            toDoList.add(new Todo(taskDescription[0]));
            addTaskMessage();
        } catch (ArrayIndexOutOfBoundsException | ArsdorintException err) {
            commandErrorHandler("todo");
        }
    }

    public static void addDeadline(String[] taskDescription) {
        try {
            if (taskDescription[0].trim().isEmpty()) {
                throw new ArsdorintException("Error: Empty Deadline Description.");
            }
            toDoList.add(new Deadline(taskDescription[0], taskDescription[1].trim()));
            addTaskMessage();
        } catch (ArrayIndexOutOfBoundsException | ArsdorintException err) {
            commandErrorHandler("deadline");
        }
    }

    public static void addEvent(String[] taskDescription) {
        try {
            if (taskDescription[0].trim().isEmpty()) {
                throw new ArsdorintException("Error: Empty Event Description.");
            }
            toDoList.add(new Event(taskDescription[0], taskDescription[1].trim()));
            addTaskMessage();
        } catch (ArrayIndexOutOfBoundsException | ArsdorintException err){
            commandErrorHandler("event");
        }
    }
    private static void save() {
        try {
            FileWriter fileWriter = new FileWriter(STORAGE_FILE_NAME);
            for (int i = 0; i < Task.numOfTasks; i++) {
                fileWriter.write(toDoList.get(i).toSave());
            }
            fileWriter.close();
        } catch (IOException err) {
            File newFile = new File(STORAGE_DIRECTORY);
            newFile.mkdir();
            showToUser(MESSAGE_NEW_FILE);
            save();
        } finally {
            showToUser(MESSAGE_OVERWRITE_FILE);
        }
    }
    private static void load() {
        try {
            File file = new File(STORAGE_FILE_NAME);
            Scanner fileRead = new Scanner(file);
            showToUser(MESSAGE_LOAD_FILE);
            while (fileRead.hasNext()) {
                fileParser(fileRead.nextLine());
            }
            fileRead.close();
        } catch (FileNotFoundException err) {
            showToUser(MESSAGE_NO_FILE);
        } catch (ArrayIndexOutOfBoundsException err) {
            showToUser(MESSAGE_WRONG_FILE);
        }
    }

    private static void fileParser(String newLine) {
        String[] parsedLine = newLine.split("");
        switch (parsedLine[0]) {
            case Todo.typeToDo:
                toDoList.add(new Todo(parsedLine[2]));
                toDoList.get(Task.numOfTasks - 1).isDone = Boolean.valueOf(parsedLine[1]);
                addTaskMessage();
                break;
            case Deadline.typeDeadline:
                toDoList.add(new Deadline(parsedLine[2], parsedLine[3]));
                toDoList.get(Task.numOfTasks - 1).isDone = Boolean.valueOf(parsedLine[1]);
                addTaskMessage();
                break;
            case Event.typeEvent:
                toDoList.add(new Event(parsedLine[2], parsedLine[3]));
                toDoList.get(Task.numOfTasks - 1).isDone = Boolean.valueOf(parsedLine[1]);
                addTaskMessage();
                break;
        }
    }

    private static void exitMessage() {
        showToUser(MESSAGE_DIVIDER, EXIT_MESSAGE, MESSAGE_DIVIDER);
    }

    private static void showToUser(String... message) {
        for (String i : message) System.out.println(i);
    }

    private static void helloMessage() {
        System.out.println("Hello from\n" + logo);
        showToUser(MESSAGE_DIVIDER, HELLO_MESSAGE, COMMAND_LIST_MESSAGE, QUESTION, MESSAGE_DIVIDER);
    }
    private static void command() {
        String command = input.nextLine();
        String[] splitLine = command.split(" ");
        String lowerCaseLine = splitLine[0].toLowerCase();
        if (lowerCaseLine.equals("bye")) exit();
        else if (lowerCaseLine.contains("list")) {
            System.out.println("Here are the tasks in your list:\n");
            list();
        }
        else if (lowerCaseLine.equalsIgnoreCase("mark")) {
            mark(splitLine);
        }
        else if (lowerCaseLine.equalsIgnoreCase("unmark")) {
            unmark(splitLine);
        }
        else if (lowerCaseLine.equalsIgnoreCase("todo") ||
                lowerCaseLine.equalsIgnoreCase("deadline") ||
                lowerCaseLine.equalsIgnoreCase("event")) add(command);
            //add command when user don't type the instruction's TaskList.command
        else if (lowerCaseLine.equalsIgnoreCase("delete")) {
            delete(splitLine);
        }
        else if (lowerCaseLine.equalsIgnoreCase("save")) {
            save();
        }
        else wrongCommandMessage();
    }
    public static void main(String[] args) {
        helloMessage();
        load();
        while (isRunning) {
            command();
        }
    }
}
