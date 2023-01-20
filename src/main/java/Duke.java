import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;


public class Duke {
    public static void main(String[] args) {
        String logo = """
                 ____        _       \s
                |  _ \\ _   _| | _____\s
                | | | | | | | |/ / _ \\
                | |_| | |_| |   <  __/
                |____/ \\__,_|_|\\_\\___|
                """;


        System.out.println("Hello from\n" + logo);

        //to greet user
        PrintHorizontalBar();
        System.out.println("Hello! I'm Duke! \n");
        PrintHorizontalBar();
        String command;

        Task[] tasks = new Task[100];
        boolean hasTask = true;
        int numberOfTasks = 0;
        String latestResponse = "";
        boolean hasLatestResponse = false;

        while (hasTask && numberOfTasks < 100) {
            command = latestResponse;
            if (!hasLatestResponse) {
                System.out.println("What can I do for your today?");
                command = ReadTask();
            }
            PrintHorizontalBar();

            if (Objects.equals(command, "list")) {
                printTaskList(tasks, numberOfTasks);
            } else if (Objects.equals(command, "bye")) {
                break;
            } else if (command.contains("unmark")) {
                String[] result = command.split(" ");
                int taskNum = Integer.parseInt(result[1]);
                if (taskNum > numberOfTasks || taskNum<=0) {
                    System.out.println("Error! No such task exists!");
                } else {
                    Task target = tasks[taskNum - 1];
                    target.isCompleted = false;
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(target.getStatusIcon() + "   " + target.taskName);
                }


            } else if (command.contains("mark")) {
                String[] result = command.split(" ");
                int taskNum = Integer.parseInt(result[1]);
                if (taskNum > numberOfTasks || taskNum<=0) {
                    System.out.println("Error! No such task exists!");
                } else {
                    Task target = tasks[taskNum - 1];
                    target.isCompleted = true;
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(target.getStatusIcon() + "   " + target.taskName);
                }

            } else {
                Task newTask = new Task(command);
                newTask.isCompleted = false;
                newTask.taskName = command;
                System.out.println("added:  " + newTask.taskName);
                tasks[numberOfTasks] = newTask;
                numberOfTasks++;
            }
            PrintHorizontalBar();
            String response = CheckTask();
            if (Objects.equals(response, "0")) {
                hasTask = false;
            } else {
                latestResponse = response;
                hasLatestResponse = true;
            }


        }

        //to say bye
        System.out.println("Bye! Hope to see you again soon!\n");
        PrintHorizontalBar();

    }

    private static void printTaskList(Task[] list, int len) {
        int i = 1;
        while (i <= len) {
            if (!list[i - 1].isCompleted) {
                System.out.print(i + ". [ ]");
            } else {
                System.out.print(i + ". [X]");
            }
            System.out.println(list[i - 1].taskName);
            i++;
        }
    }

    private static void PrintHorizontalBar() {
        String horizontalBar = "---------------------------------------------------\n";
        System.out.println(horizontalBar);
    }

    private static String ReadTask() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        return line;

    }

    private static String CheckTask() {

        System.out.println("Do you have any other task for me?  ");
        String response = ReadTask();
        if (Objects.equals(response, "no")) {
            return "0";
        } else {
            return response;
        }


    }


}
