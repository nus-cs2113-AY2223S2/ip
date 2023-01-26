import java.lang.reflect.Array;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String line = "____________________________________________________________\n";
        String greet = line +
                "Hello! I'm Duke.\n" +
                "What do you wish to add to the Task List?\n" +
                line;
        System.out.println(greet);

        // Level 3 Mark as Done
        Scanner in = new Scanner(System.in);
        int countTask = 0;
        Task[] tasks = new Task[100];
        while (true) {
            String userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Thank you for using Duke. Hope to see you soon!");
                break;
            } else if (userInput.equals("list")) {
                System.out.println(line + "Here are the tasks in your list:");
                for (int i = 0; i < countTask; i++) {
                    int taskIndex = i + 1;
                    System.out.println(taskIndex +
                            ". [" + tasks[i].getStatusIcon() + "] "
                            + tasks[i].description);
                }
                System.out.println(line);
            } else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.substring(4).trim());
                tasks[taskIndex - 1].markAsDone();
                System.out.println(line + "Task " + taskIndex + " marked as done: \n"
                        + "  [" + tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].description + '\n'
                        + line);
            } else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.substring(6).trim());
                tasks[taskIndex - 1].markAsUndone();
                System.out.println(line + "Task " + taskIndex + " marked as not done yet: \n"
                        + "  [" + tasks[taskIndex - 1].getStatusIcon() + "] " + tasks[taskIndex - 1].description + '\n'
                        + line);
            } else {
                Task t = new Task(userInput);
                tasks[countTask] = t;
                countTask++;
                System.out.println(line + "added: " + t.description + '\n' + line);
            }
        }
    }
}
