// Java program to read data of various types using Scanner class.
import exceptions.EmptyInputException;
import exceptions.IllegalInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * @author : Steven A. O. Waskito
 * @mailto : e0851459@u.nus.edu
 * @created : 3 February 2023
 *
 * This is the main Java driver and has most of the important methods
 *
 **/
public class Duke {
    protected static final String FILE_PATH = "data/input.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    public static void printWelcomeMessage() {
        String welcomeMessage = "____________________________________________________________\n" +
                " Hello! I'm Elzi, your dog!\n" +
                " What can I do for my master?\n" +
                "____________________________________________________________\n";
        System.out.println(welcomeMessage);
    }
    public static void printLine() {
        System.out.print("___________________________******___________________________\n");
    }
    public static void printList(int taskCounter) {
        System.out.println("Your current tasks are as follows: ");
        for (int index = 0; index < tasks.size(); index += 1) {
            System.out.println((index + 1) + "." + tasks.get(index));
        }
    }
    public static void updateFile() {
        String list = "";
        for (int index = 0; index < tasks.size(); index += 1) {
            list += tasks.get(index).getUpdate() + "\n";
        }
        try {
            writeToFile(FILE_PATH, list);
        }
        catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
    public static void printAddTodo(Todo print) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + tasks.size() + " task in the list");
    }
    public static void printAddDeadline(Deadline print) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + tasks.size() + " task in the list");
    }
    public static void printAddEvent(Event print) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + tasks.size() + " task in the list");
    }
    /**
     * Parses Todo Input
     *
     * @param inputs The inputs from the user in string array
     */
    public static String parseInput(String[] inputs){
        String todoInput = "";
        for (int i = 1; i < inputs.length; i ++) {
            todoInput += inputs[i];
            todoInput += " ";
        }
        return todoInput;
    }
    public static void inputTodo(String[] inputs) {
        String todoInput = parseInput(inputs);
        Todo todo = new Todo(todoInput);
        tasks.add(todo);
        printAddTodo(todo);
    }
    /**
     * Executes the find command
     *
     * @param input The word that needs to be found
     */
    public static void findID(String input) {
        for (int index = 0; index < tasks.size(); index += 1) {
            Task currentTask = tasks.get(index);
            if (currentTask.getDescription().contains(input)) {
                System.out.println((index + 1) + "." + currentTask);
            }
        }
    }
    /**
     * Read all of the inputs typed in by users and the input.txt file
     * Commands are entered in each new line
     * Each new command starts from first character
     *
     * There are 9 types of commands: bye, list, find, mark, unmark, todo, deadline, event, delete
     *
     * @param s The line submitted by the user / input.txt
     * @throws EmptyInputException if there is no description after the input
     * @throws IllegalInputException if the command is not following the format
     */
    private static boolean readInput(String s) throws EmptyInputException, IllegalInputException {
        String input = "";
        if (s.equals("in")) {
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
        }
        else {
            input = s;
        }

        int taskCounter = 0;

        if (!input.equals("bye")) {

            String[] inputs = input.split(" ");
            String command = inputs[0];
            int taskIndex;

            printLine();

            switch (command) {
            case "list":
                printList(taskCounter);
                break;
            case "find":
                if (inputs.length == 1) {
                    throw new EmptyInputException();
                }
                String ID = "";
                for (int i = 1; i < inputs.length; i += 1) {
                    if (i != inputs.length-1) {
                        ID = ID + inputs[i] + " ";
                    }
                    else {
                        ID = ID + inputs[i];
                    }
                }
                findID(ID);
                break;
            case "mark":
                if (inputs.length == 1) {
                    throw new EmptyInputException();
                }
                taskIndex = Integer.parseInt(inputs[1]);
                if (taskIndex > tasks.size() || taskIndex <= 0) throw new IllegalInputException();
                tasks.get(taskIndex - 1).markAsDone();
                System.out.println("I have marked this task as done");
                printList(taskCounter);
                break;
            case "unmark":
                if (inputs.length == 1) {
                    throw new EmptyInputException();
                }
                taskIndex = Integer.parseInt(inputs[1]);
                if (taskIndex > tasks.size() || taskIndex <= 0) throw new IllegalInputException();
                tasks.get(taskIndex - 1).markAsNotDone();
                System.out.println("I have unmarked this task");
                printList(taskCounter);
                break;
            case "todo":
                if (inputs.length == 1) {
                    throw new EmptyInputException();
                }
                inputTodo(inputs);
                break;
            case "deadline":
                if (inputs.length == 1) {
                    throw new EmptyInputException();
                }
                String deadlineInput = "";
                String deadlineDeadline = "";
                boolean isDeadline = false;
                for (int i = 1; i < inputs.length; i++) {
                    if (inputs[i].contains("/")) {
                        isDeadline = true;
                        continue;
                    }
                    if (isDeadline) {
                        deadlineDeadline += inputs[i];
                        deadlineDeadline += " ";
                    } else {
                        deadlineInput += inputs[i];
                        deadlineInput += " ";
                    }
                }
                if (!isDeadline) throw new IllegalInputException();
                Deadline deadline = new Deadline(deadlineInput, deadlineDeadline);
                tasks.add(deadline);
                taskCounter += 1;
                printAddDeadline(deadline);
                break;
            case "event":
                if (inputs.length == 1) {
                    throw new EmptyInputException();
                }
                String eventInput = "";
                String eventFrom = "";
                String eventTo = "";
                boolean isFrom = false;
                boolean isTo = false;
                for (int i = 1; i < inputs.length; i++) {
                    if (inputs[i].contains("/from")) {
                        isFrom = true;
                        continue;
                    }
                    if (inputs[i].contains("/to")) {
                        isTo = true;
                        continue;
                    }
                    if (isTo) {
                        eventTo += inputs[i];
                        eventTo += " ";
                    } else if (isFrom) {
                        eventFrom += inputs[i];
                        eventFrom += " ";
                    } else {
                        eventInput += inputs[i];
                        eventInput += " ";
                    }
                }
                if (!(isTo && isFrom)) throw new IllegalInputException();
                Event event = new Event(eventInput, eventFrom, eventTo);
                tasks.add(event);
                printAddEvent(event);
                break;
            case "delete":
                if (inputs.length == 1) {
                    throw new EmptyInputException();
                }
                taskIndex = Integer.parseInt(inputs[1]);
                if (taskIndex > tasks.size() || taskIndex <= 0) throw new IllegalInputException();
                tasks.remove(taskIndex - 1);
                System.out.print("I have removed this item in index ");
                System.out.println(taskIndex);
                printList(taskCounter);
                break;
            default:
                throw new IllegalInputException();
            }
            updateFile();
            printLine();
            return false;
        }
        else
            return true;
    }
    /**
     * Scans all of the input.txt file and calls readInput to read it.
     * @param filePath The file path
     * @throws FileNotFoundException if there is no file input.txt in /data/
     */
    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            try {
                boolean yes = readInput(s.nextLine());
            }
            catch (EmptyInputException e) {
                System.out.println("Description Can Not Be Empty!");
            }
            catch (IllegalInputException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
        }
        printList(0);
        printLine();
    }
    /**
     * Writes to file input.txt to save the tasks.
     *
     * @param filePath The file path
     * @param textToAdd The tasks to be written to input.txt
     * @throws IOException if there is I/O error occured
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    /**
     * The main driver of Duke.java
     *
     * Allows user to input commands and duke will process it according to the commands given
     *
     * Handles file creation if no input.txt is found
     * Handles Empty Input Exception and Illegal Input Exception Error Messages
     */
    public static void main(String[] args) {
        printWelcomeMessage();
        File f = new File(FILE_PATH);
        File directory = new File("data");
        boolean isFileFound = true;
        //populate
        try {
            printFileContents(FILE_PATH);
        }
        catch (FileNotFoundException e) {
            isFileFound = false;
            System.out.println("File not found!\n");
        }
        if (!isFileFound) {
            System.out.printf("Creating a new File for you!\n");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            try {
                f.createNewFile();
            }
            catch (IOException ex) {
                System.out.println("Something went wrong: " + ex.getMessage());
            }
            System.out.printf("Created a new File!\n");
        }
        boolean isBye = false;
        while (!isBye) {
            try {
                String s = "in";
                isBye = readInput(s);
            }
            catch (EmptyInputException e) {
                System.out.println("Description Can Not Be Empty!");
            }
            catch (IllegalInputException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                printLine();
            }
        }

        // Print end text line
        printLine();
    }
}
