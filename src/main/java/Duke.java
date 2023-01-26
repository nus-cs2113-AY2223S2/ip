import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void horizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void greet() {
        horizontalLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        horizontalLine();
    }

    public static void exit() {
        horizontalLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        horizontalLine();
    }

    public static void echo(String userInput) {
        horizontalLine();
        System.out.println("added: " + userInput);
        horizontalLine();
    }

    public static String[] processLine(String userInput) {
        String[] words = userInput.split(" ");
        return words;
    }

    public static void processCommands(Task[] tasks, String userInput) {
        if (userInput.equals("list")) {
            horizontalLine();
            for (int i = 0; i < Task.getNumberOfTasks(); i += 1) {
                System.out.println(tasks[i].getTaskNumber() + ".[" + tasks[i].getStatusIcon() + "]" + tasks[i].getDescription());
            }
            horizontalLine();
        } else if (userInput.contains("mark")) {
            String[] words = processLine(userInput);
            Integer taskNumber = Integer.parseInt(words[1]) - 1;
            if (words[0].equals("mark")) {
                tasks[taskNumber].markDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                tasks[taskNumber].markUndone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            horizontalLine();
            System.out.println("[" + tasks[taskNumber].getStatusIcon() + "]" + tasks[taskNumber].getDescription());
            horizontalLine();
        } else {
            echo(userInput);
            tasks[Task.getNumberOfTasks()] = new Task(userInput);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Task[] tasks = new Task[100];
        boolean byeCommand = false;

        greet();
        while (byeCommand == false) {
            Scanner in = new Scanner(System.in);
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                byeCommand = true;
            } else {
                processCommands(tasks, userInput);
            }
        }
        exit();
    }
}
