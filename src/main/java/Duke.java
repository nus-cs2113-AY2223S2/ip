import java.util.Scanner;

public class Duke {
    public static Task[] tasks = new Task[100];
    public static int tasksLength = 0;

    public static void printSeperator() {
        System.out.println("____________________________________________________________\n");
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);
        printSeperator();
    }

    public static void printWelcome() {
        System.out.println(
                "\tHello! I'm Duke\n" +
                        "\tWhat can I do for you?\n");
        printSeperator();
    }

    public static void printGoodbye() {
        System.out.println(
                "\tBye. Hope to see you again soon!\n"
        );
        printSeperator();
    }

    public static void addToList(String userInput) {
        Task newTask = new Task(userInput);
        tasks[tasksLength] = newTask;
        tasksLength += 1;
        printSeperator();
        System.out.println("\tadded: " + userInput);
        printSeperator();
    }

    public static void printTasks() {
        printSeperator();
        for (int i = 0; i < tasksLength; i++) {
            System.out.println("\t" + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getTask());
        }
        printSeperator();
    }

    public static void mark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks[taskNumber - 1].MarkStatusDone();
        printSeperator();
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t\t[X] " + tasks[taskNumber - 1].getTask());
        printSeperator();
    }

    public static void unmark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks[taskNumber - 1].MarkStatusUndone();
        printSeperator();
        System.out.println("\tOK, I've marked this task as not done yet:\n" +
                "\t\t[ ] " + tasks[taskNumber - 1].getTask());
        printSeperator();
    }

    public static void ChatPolling() {
        String userInput;

        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                printSeperator();
                break;
            }

            if (userInput.equals("list")) {
                printTasks();
            } else if (userInput.contains("unmark")) {
                userInput = userInput.substring(7);
                unmark(userInput);
            } else if (userInput.contains("mark")) {
                userInput = userInput.substring(5);
                mark(userInput);
            } else {
                addToList(userInput);
            }
        }
    }
    public static void main (String[]args){
        printLogo();
        printWelcome();
        ChatPolling();
        printGoodbye();
    }
}