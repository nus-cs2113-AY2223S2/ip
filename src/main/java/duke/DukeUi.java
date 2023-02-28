package duke;

import java.util.Scanner;

/**
 * Handles input and output for Duke. Mostly contains printing methods.
 */

public class DukeUi {
    
    private final String LINE = "____________________________________________________________";

    private Scanner scanner;

    /**
     * Instantiates DukeUi with or without a scanner for reading.
     * 
     * @param withScanner A boolean that instantiates DukeUi with a scanner if true.
     */

    public DukeUi(boolean withScanner) {
        if (withScanner) {
            this.scanner = new Scanner(System.in);
        }
    }

    // Input

    /**
     * Returns the next line entered in the terminal.
     * 
     * @return line entered in terminal.
     */

    public String getNextLine() {
        return scanner.nextLine();
    }

    /**
     * Closes the scanner to prevent errors.
     */

    public void closeScanner() {
        this.scanner.close();
    }

    // Output

    /**
     * Prints the standard LINE string on the terminal.
     */

    public void printLine() {
        System.out.println(LINE);
    }

    // Come to think about it, printing functions seems to be pretty trivial, let's 
    // not javadoc those.

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
        System.out.println("save: Saves all the tasks to the savefile");

        // Planned feature
        // System.out.println("autosave: Enables file to be saved after every command. " + 
        //         "May slow down the program.");
        System.out.println("bye: Exits the program.");
    }

    public void printDirCreated() {
        System.out.println("data directory successfully created.");
    }

    public void printDirNotFound() {
        System.out.println("data directory not found, creating directory.");
    }

    public void printSavefileNotFound() {
        System.out.println("savefile not found, creating new savefile.");
    }

    public void printSavefileCreated() {
        System.out.println("savefile successfully created.");
    }

    public void printSavefileFound() {
        System.out.println("savefile found. Parsing savefile.");
    }

    public void printSavingToFile() {
        System.out.println("Saving tasks to savefile, please do not close the application.");
    }

    public void printInvalidCommand() {
        System.out.println("Invalid command entered, please enter 'help' to see " +
                "the list of commands.");
    }
}
