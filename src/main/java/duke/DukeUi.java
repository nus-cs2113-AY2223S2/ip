package duke;

import java.util.Scanner;

public class DukeUi {
    
    private final String LINE = "____________________________________________________________";

    private Scanner scanner;

    public DukeUi(boolean withScanner) {
        if (withScanner) {
            this.scanner = new Scanner(System.in);
        }
    }

    // Input

    public String getNextLine() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        this.scanner.close();
    }

    // Output

    public void printLine() {
        System.out.println(LINE);
    }

    public void greetUser() {
        printLine();
        System.out.println("Hello, I'm Duke");
        System.out.println("Loading savefile...");
        printLine();
    }

    public void printHelp() {
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
        System.out.println("find <keyword>: Finds and lists the tasks that contains the keyword " + 
                "in the name of the task.");
        System.out.println("bye: Exits the program.");
    }
}
