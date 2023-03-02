package duke.command;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the UI interface interacting with users.
 */
public class Ui {
    
    private static final String SPLITTER = "    ____________________________________________________________";
    private static final String logo = 
          " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String enterGreet = "     Hello! I'm Duke\n" + "     What can I do for you?";
    private static final String exitPrompt = "     Bye. Hope to see you again soon!";
    
    private Scanner in;

    /**
     * Constructor for <code>Ui</code> class. It will create a 
     * <code>Ui</code> object accepting standard input as its input stream.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Read one line of command.
     * @return The command line read.
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Show the <code>SPLITTER</code>, i.e. "    ____________________________________________________________".
     */
    public static void showLine() {
        System.out.println(SPLITTER);
    }

    /**
     * Show the error message.
     * @param errMsg Error message provided.
     */
    public static void showError(String errMsg) {
        showLine();
        System.out.println("     " + errMsg);
        showLine();
        System.out.println();
    }

    /**
     * Show welcome message.
     */
    public static void showWelcome() {
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println(enterGreet);
        showLine();
        System.out.println();
    }

    /**
     * Show information of a task.
     * @param num Number of the task.
     * @param task Task provided.
     * @param leadingSpaceNum Number of leading spaces for better appearance.
     */
    public static void showTask(int num, Task task, int leadingSpaceNum) {
        for(int i = 0; i < leadingSpaceNum; i++) {
            System.out.print(" ");
        }
        System.out.println(num + ". " + task);
    }

    /**
     * Show information of a task.
     * @param task Task provided.
     * @param leadingSpaceNum Number of leading spaces for better appearance.
     */
    public static void showTask(Task task, int leadingSpaceNum) {
        for(int i = 0; i < leadingSpaceNum; i++) {
            System.out.print(" ");
        }
        System.out.println(task);
    }

    /**
     * Show exit message.
     */
    public static void showExit() {
        showLine();
        System.out.println(exitPrompt);
        showLine();
        System.out.println();
    }
}
