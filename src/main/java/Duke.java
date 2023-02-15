import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    private static Scanner in = new Scanner(System.in);
    private static final Task[] tasks = new Task[100];

    public static void command(String line) {
        String exitCommand = "bye";
        String toDo = "list";
        String checkAsDone = "mark";
        int numberOfTasks = 0;

        String[] tokens = line.split(" ", 2);
        String command = tokens[0];
        String task = tokens[1];

        while (!command.equals(exitCommand)) {
            if (tokens[0].equals(toDo)) {
                System.out.println("Here are your list of tasks to complete!");
                for (int i = 0; i < numberOfTasks; ++i) {
                    System.out.format("%d. ", (i + 1));
                    String status = tasks[i].getStatus();
                    System.out.println(status + " " + tasks[i]);
                }
                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            } else if (command.equals(checkAsDone)){
                int finishedTask = Integer.parseInt(tokens[1]) - 1;
                tasks[finishedTask].markAsDone();
                System.out.println("Alright! I have marked the task as complete!");

                for (int i = 0; i < numberOfTasks; ++i) {
                    System.out.format("%d. ", (i + 1));
                    String status = tasks[i].getStatus();
                    System.out.println(status + " " + tasks[i]);
                }

                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            } else {
                switch(command) {
                case "todo":
                    Task t = new ToDo(task);
                    tasks[numberOfTasks] = t;
                    tasks[numberOfTasks].isDone = false;
                    ++numberOfTasks;
                case "deadline":
                    String[] taskDeadline = task.split("/", 2);
                    String taskDescription = taskDeadline[0];
                    String dueBy = taskDeadline[1];

                    Task d = new Deadline(taskDescription, dueBy);
                    tasks[numberOfTasks] = d;
                    tasks[numberOfTasks].isDone = false;
                    ++numberOfTasks;
                case "event":
                    String[] taskEvent = task.split("/", 3);
                    String eventDescription = taskEvent[0];
                    String start = taskEvent[1];
                    String end = taskEvent[2];

                    Task e = new Event(eventDescription, start, end);
                    tasks[numberOfTasks] = e;
                    tasks[numberOfTasks].isDone = false;
                    ++numberOfTasks;
                default:
                    System.out.println("Sorry! There seems to be an error:( Please try again.");
                }

                Task t = new Task(line);
                tasks[numberOfTasks] = t;
                tasks[numberOfTasks].isDone = false;
                ++numberOfTasks;

                System.out.println("added: " + line);
                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            }
        }
        System.out.println("Bye! Hope to see you again");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! My name is Duke. \n Nice to meet you!");

        String line;
        line = in.nextLine();
        command(line);
    }
}
