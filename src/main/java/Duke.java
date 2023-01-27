import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner input = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        dukeAddList(input);
    }

    public static void dukeAddList(Scanner inputScanner) {
        ArrayList<Task> list = new ArrayList<>();
        while (true) {
            String echo = inputScanner.nextLine();
            if (echo.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (Task task : list) {
                    if (task.isDone()) {
                        System.out.println(task.getIndex() + ". [X] " + task.getTaskName());
                    } else {
                        System.out.println(task.getIndex() + ". [ ] " + task.getTaskName());
                    }
                }
            } else if (echo.split(" ", 0)[0].equals("mark")) {
                String[] inputArray = echo.split(" ", 0);
                if (inputArray.length != 2) {
                    continue;
                }
                int index;
                try {
                    index = Integer.parseInt(inputArray[1]);
                } catch (NumberFormatException e) {
                    continue;
                }
                if (index > list.size() - 1) {
                    continue;
                }
                Task task = list.get(index);
                if (!task.isDone()) {
                    task.doTask();
                }
            } else if (echo.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
            } else {
                Task newTask = new Task(echo, false, list.size());
                list.add(newTask);
            }
        }
    }

    public static void dukeEcho(Scanner inputScanner) {
        while (true) {
            String echo = inputScanner.nextLine();
            if (echo.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            System.out.println(echo);
        }
    }
}
