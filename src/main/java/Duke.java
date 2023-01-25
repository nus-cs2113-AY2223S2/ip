import java.util.Objects;
import java.util.Scanner;
import java.util.StringJoiner;


public class Duke {

    private static Task[] tasks = new Task[100];
    private static int numberOfTasks = 0;

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
        printHorizontalBar();
        System.out.println("Hello! I'm Duke! \n");
        printHorizontalBar();
        String command;


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
            printHorizontalBar();

            if (Objects.equals(command, "list")) {
                printTaskList(tasks);
            } else if (Objects.equals(command, "bye")) {
                break;
            } else if (command.contains("unmark")) {
                String[] result = command.split(" ");
                int taskNum = Integer.parseInt(result[1]);
                if (taskNum > numberOfTasks || taskNum <= 0) {
                    System.out.println("Error! No such task exists!");
                } else {
                    Task target = tasks[taskNum - 1];
                    target.markAsNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(target.getStatusIcon() + "   " + target.taskName);
                }


            } else if (command.contains("mark")) {
                String[] result = command.split(" ");
                int taskNum = Integer.parseInt(result[1]);
                if (taskNum > numberOfTasks || taskNum <= 0) {
                    System.out.println("Error! No such task exists!");
                } else {
                    Task target = tasks[taskNum - 1];
                    target.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(target.getStatusIcon() + "   " + target.taskName);
                }

            } else if (command.contains("deadline")) {
                String content = command.substring(8);
                String[] contents = content.split("/");
                String title = contents[0];
                String dueDate = contents[1];
                Deadline newDeadline = new Deadline(title);
                newDeadline.setEndTime(dueDate);
                tasks[numberOfTasks] = newDeadline;
                numberOfTasks++;
                System.out.println("Got it. I've added this new task '" + newDeadline.taskName);
                System.out.println("with a deadline of: " + newDeadline.getEndTime());
                System.out.println("Now you have " + numberOfTasks + " tasks in the list");

            } else if (command.contains("event")) {
                String content = command.substring(5);
                String[] contents = content.split("/");
                String title = contents[0];
                String startTime = contents[1];
                String endTime = contents[2];
                Event newEvent = new Event(title);
                newEvent.setStartTime(startTime);
                newEvent.setEndTime(endTime);
                tasks[numberOfTasks] = newEvent;
                numberOfTasks++;
                System.out.println("Got it. I've added this new event '" + newEvent.taskName);
                System.out.println("with a start time of: " + newEvent.getStartTime());
                System.out.println("and an ending time of: " + newEvent.getEndTime());
                System.out.println("Now you have " + numberOfTasks + " tasks in the list");

            } else {
                Task task = new Task(command);
                task.isCompleted = false;
                task.taskName = command;
                System.out.println("added:  " + task.taskName);
                tasks[numberOfTasks] = task;
                numberOfTasks++;

            }
            printHorizontalBar();
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
        printHorizontalBar();

    }

    private static void printTaskList(Task[] list) {
        int i = 1;
        for (Task a : list) {
            if (a != null) {
                System.out.print(i);
                a.printTask();
                i++;
            }
        }

    }


    private static void printHorizontalBar() {
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
