import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static Task[] tasks = new Task[100];

    public static int index = 0;

    public static int itemCount = 0;

    public static void main(String[] args) {

        String input;
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showWelcomeMessage();
        do {
            input = in.nextLine();
            command(input);
        } while (!input.equals("bye"));
    }

    private static void command(String input) {
        String[] commandInput = input.split(" ");
        switch(commandInput[0]) {
        case "list":
            System.out.println(HORIZONTAL_LINE);
            for (int i = 0; i < itemCount; i++) {
                System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
            };
            System.out.println(HORIZONTAL_LINE);
            break;
        case "mark":
            int taskIndex = Integer.parseInt(commandInput[1]) - 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Nice! I've marked this task as done:");
            tasks[taskIndex].markAsDone();
            System.out.println("  [" + tasks[taskIndex].getStatusIcon() + "]" + tasks[taskIndex].description);
            System.out.println(HORIZONTAL_LINE);
            break;
        case "unmark":
            taskIndex = Integer.parseInt(commandInput[1]) - 1;
            System.out.println(HORIZONTAL_LINE);
            System.out.println("OK, I've marked this task as not done yet:");
            tasks[taskIndex].markAsUndone();
            System.out.println("  [" + tasks[taskIndex].getStatusIcon() + "]" + tasks[taskIndex].description);
            System.out.println(HORIZONTAL_LINE);
            break;
        case "bye":
            System.out.println(HORIZONTAL_LINE);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(HORIZONTAL_LINE);
            break;
        default:
            System.out.println(HORIZONTAL_LINE);
            System.out.println("added: " + input);
            Task t = new Task(input);
            tasks[index] = t;
            index++;
            itemCount++;
            System.out.println(HORIZONTAL_LINE);

        }
    }

    private static void showWelcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }
}
