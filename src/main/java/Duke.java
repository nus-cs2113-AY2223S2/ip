// Java program to read data of various types using Scanner class.
import exceptions.EmptyInputException;
import exceptions.IllegalInputException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
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
    public static void printAddTodo(Todo print, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + tasks.size() + " task in the list");
    }
    public static void printAddDeadline(Deadline print, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + tasks.size() + " task in the list");
    }
    public static void printAddEvent(Event print, int taskCounter) {
        System.out.println("Got it. I've added this task:");
        System.out.print("   "); System.out.println(print);
        System.out.println("Now you have " + tasks.size() + " task in the list");
    }
    public static String parseInput(String[] inputs){
        String todoInput = "";
        for (int i = 1; i < inputs.length; i ++) {
            todoInput += inputs[i];
            todoInput += " ";
        }
        return todoInput;
    }
    public static void inputTodo(String[] inputs, int taskCounter) {
        String todoInput = parseInput(inputs);
        Todo todo = new Todo(todoInput);
        tasks.add(todo);
        taskCounter += 1;
        printAddTodo(todo, taskCounter);
    }
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
                inputTodo(inputs, taskCounter);
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
                printAddDeadline(deadline, taskCounter);
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
                taskCounter += 1;
                printAddEvent(event, taskCounter);
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
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
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
