package duke.command;

import java.util.Scanner;

import duke.task.Task;

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

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public static void showLine() {
        System.out.println(SPLITTER);
    }

    public static void showError(String errMsg) {
        showLine();
        System.out.println("     " + errMsg);
        showLine();
        System.out.println();
    }

    public static void showWelcome() {
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println(enterGreet);
        showLine();
        System.out.println();
    }

    public static void showTask(int num, Task task, int leadingSpaceNum) {
        for(int i = 0; i < leadingSpaceNum; i++) {
            System.out.print(" ");
        }
        System.out.println(num + ". " + task);
    }

    public static void showTask(Task task, int leadingSpaceNum) {
        for(int i = 0; i < leadingSpaceNum; i++) {
            System.out.print(" ");
        }
        System.out.println(task);
    }

    public static void showExit() {
        showLine();
        System.out.println(exitPrompt);
        showLine();
        System.out.println();
    }
}
