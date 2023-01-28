import java.util.Scanner;

public class Duke {
    public static Task[] tasks = new Task[100];
    public static int tasksLength = 0;


    public static void addToList(String userInput) {
        Task newTask = new Task(userInput);
        tasks[tasksLength] = newTask;
        tasksLength += 1;
        Greeting.printSeperator();
        System.out.println("\tadded: " + userInput);
        Greeting.printSeperator();
    }

    public static void printTasks() {
        Greeting.printSeperator();
        for (int i = 0; i < tasksLength; i++) {
            System.out.println("\t" + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getTask());
        }
        Greeting.printSeperator();
    }

    public static void mark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks[taskNumber - 1].MarkStatusDone();
        Greeting.printSeperator();
        System.out.println("\tNice! I've marked this task as done:\n" +
                "\t\t[X] " + tasks[taskNumber - 1].getTask());
        Greeting.printSeperator();
    }

    public static void unmark(String userInput) {
        int taskNumber = Integer.parseInt(userInput);
        tasks[taskNumber - 1].MarkStatusUndone();
        Greeting.printSeperator();
        System.out.println("\tOK, I've marked this task as not done yet:\n" +
                "\t\t[ ] " + tasks[taskNumber - 1].getTask());
        Greeting.printSeperator();
    }

    public static void ChatPolling() {
        String userInput;

        while (true) {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();

            if (userInput.equals("bye")) {
                Greeting.printSeperator();
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
        Greeting.printLogo();
        Greeting.printWelcome();
        ChatPolling();
        Greeting.printGoodbye();
    }
}