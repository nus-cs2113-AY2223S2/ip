import java.util.Scanner;

public class Duke {
    public static void printSeparator() {//prints a dash line for separating text
        System.out.println("____________________________________________________________");
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

    public static void handleRequest(String input, TaskManager dukeManager) { //TO be implemented in own class
        if (input.equals("list")) {
            dukeManager.listTasks();
            printSeparator();
        } else if (input.contains("mark")) {
            dukeManager.markTask(input);
        } else {
            Task newTask = new Task(input);
            //taskList[Task.getTaskNumber()] = newTask;
            dukeManager.addTask(newTask);
            printEcho(input);
        }
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
        //Task[] userList = new Task[100]; //no more than 100 tasks
        while (!byeFlag) {
            String userInput = line.nextLine();
            if (userInput.equals("bye")) {
                byeFlag = true;
                printGoodbye();
            } else {
                handleRequest(userInput, dukeManager);
            }
        }
    }
}
