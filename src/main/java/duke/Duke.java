package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static boolean hasEnteredBye = false;
    
    public static void greetUser() {
        printLine();
        System.out.println("Hello, I'm Duke");
        System.out.println("Loading savefile...");
        printLine();
    }
    
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    
    public static void help() {
        System.out.println("List of commands:");
        System.out.println("list: Lists all tasks.");
        System.out.println("todo <name>: Adds a ToDo task.");
        System.out.println("deadline <name> /by <by when>: Adds a deadline task" +
        " with specified deadline.");
        System.out.println("event <name> /from <from when> /to <to when>:" +
        " Adds an event with specified time period.");
        System.out.println("mark <task number>: Marks the task specified as done.");
        System.out.println("unmark <task number>: Marks the task specified as not done.");
        System.out.println("delete <task number>: Deletes the task from the list.");
        System.out.println("bye: Exits the program.");
    }

    public static void save() {
        System.out.println("Saving tasks to savefile, please do not close the application.");
        try {
            FileWriter fileWriter = new FileWriter(SAVE_FILE, false);
            fileWriter.write("");
            taskList.saveToFile(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Saving failed for some reason.");
            e.printStackTrace();
        }
    }

    public static void exit() {
        printLine();
        save();
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    public static void parseUserInput(String input) {
        String[] split = input.trim().split("\\s+", 2);
        String command = split[0];
        String args = split.length == 2 ? split[1] : "";
        switch (command) {
        case "bye":
            hasEnteredBye = true;
            exit();
            break;
        case "list":
            taskList.listTasks();
            break;
        case "help":
            help();
            break;
        case "mark":
            try {
                taskList.markDone(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after mark.");
            }
            break;
        case "unmark":
            try {
                taskList.unmarkDone(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after unmark.");
            }
            break;
        case "todo":
            taskList.addToDo(args);
            break;
        case "deadline":
            taskList.addDeadline(args);
            break;
        case "event":
            taskList.addEvent(args);
            break;
        case "delete":
            try {
                taskList.deleteTask(Integer.parseInt(args));
            } catch (Exception e) {
                System.out.println("Please input an integer after delete.");
            }
            break;
        case "find":
            taskList.findTask(args);
            break;
        default:
            System.out.println("Invalid command entered, please enter 'help' to see " +
            "the list of commands.");
        }
    }

    public static void parseSavefile() throws IOException {
        Scanner s = new Scanner(SAVE_FILE);
        taskList = new TaskList(s);
    }

    public static void savefileChecker() throws FileNotFoundException, IOException {
        if (!SAVE_DIR.exists()) {
            System.out.println("data directory not found, creating directory.");
            if(SAVE_DIR.mkdir()) {
                System.out.println("data directory successfully created.");
            } else {
                System.out.println("data directory creation unsuccessful, exiting program.");
                throw new FileNotFoundException();
            }
        }
        if (!SAVE_FILE.exists()) {
            System.out.println("savefile not found, creating new savefile.");
            try {
                if (SAVE_FILE.createNewFile()) {
                    System.out.println("savefile successfully created.");
                    taskList = new TaskList();
                } else {
                    System.out.println("savefile creation unsuccessful, exiting program.");
                    throw new FileNotFoundException();
                }
            } catch (Exception e) {
                System.out.println("savefile creation unsuccessful, exiting program.");
                throw e;
            }
        } else {
            System.out.println("savefile found. Parsing savefile.");
            try {
                parseSavefile();  
            } catch (Exception e) {
                System.out.println("Savefile cannot be found, please delete the savefile yourself");
                System.out.println("cos I am too lazy to delete it for you for now.");
                throw e;
            }
        }
        printLine();
    }
    
    public static TaskList taskList;
    public static final File SAVE_DIR = new File("data");
    public static final File SAVE_FILE = new File("data/savefile.txt");
    
    public static void main(String[] args) {
        greetUser();
        try {
            savefileChecker();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        while (!hasEnteredBye) {
            String line = scanner.nextLine();
            parseUserInput(line);
            printLine();
        }
        scanner.close();
    }
}
