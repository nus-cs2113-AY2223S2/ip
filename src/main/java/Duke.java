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

    public static void printTaskDetails(Task item) {
        String desc = item.getDescription();
        System.out.print("[" + item.getStatusIcon() + "]" + " " + desc+"\n");
    }


    public static void handleRequest(String input, Task[] taskList) {
        if (input.equals("list")) {
            for (int i = 0; i < Task.getTaskNumber() + 1; i++) {
                System.out.print((i + 1) + ".");
                printTaskDetails(taskList[i]);
            }
            printSeparator();
        } else if (input.contains("mark")) {
            markTask(input, taskList);
        } else {
            Task newTask = new Task(input);
            taskList[Task.getTaskNumber()] = newTask;
            printEcho(input);
        }
    }

    private static void markTask(String input, Task[] taskList) {
        int whitespaceIndex = input.indexOf(" ");
        int startingIndex = Integer.parseInt(input.substring(whitespaceIndex + 1));
        if (input.indexOf("m") == 0) {
            taskList[startingIndex - 1].markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
        } else {
            taskList[startingIndex - 1].markAsUndone();
            System.out.println("OK, I've marked this task as not done yet: ");
        }
        printTaskDetails(taskList[startingIndex - 1]);
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
        Task[] userList = new Task[100]; //no more than 100 tasks
        while (!byeFlag) {
            String userInput = line.nextLine();
            if (userInput.equals("bye")) {
                byeFlag = true;
                printGoodbye();
            } else {
                handleRequest(userInput, userList);
            }
        }
    }
}
