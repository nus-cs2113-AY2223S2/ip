import duke.command.TaskManager;

import java.util.Scanner;

public class Duke {

    public static void printSeparator(){
        System.out.println("_______________________________________________________________");
    }

    public static void printGreeting() {
        printSeparator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printSeparator();
    }

    public static void printGoodbye() {
        printSeparator();
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public static void printEcho(String input) {
        printSeparator();
        System.out.println("added: " + input);
        printSeparator();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        printGreeting();
        boolean byeFlag = false;
        Scanner line = new Scanner(System.in);
        TaskManager dukeManager = new TaskManager(100);
        //TaskItems.Task[] userList = new TaskItems.Task[100]; //no more than 100 tasks
        while (!byeFlag) {
            String userInput = line.nextLine();
            if (userInput.equals("bye")) {
                byeFlag = true;
                printGoodbye();
            } else {
                dukeManager.handleCommand(userInput);
                printSeparator();
            }
        }
    }
}
