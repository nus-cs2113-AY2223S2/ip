import java.util.Scanner;
import java.util.Arrays;
public class Duke {

    private static Scanner in = new Scanner(System.in);
    private static final Task[] tasks = new Task[100];
    private static final Task[] foundTasks = new Task[100];

    public static void whatToDo(String line) {
        try {
            command(line);
        }
        catch (InvalidCommandException error) {
            System.out.println("Try using one of following commands: todo, deadline, event.");
            line = in.nextLine();
            whatToDo(line);
        }
        catch (IndexOutOfBoundsException error) {
            System.out.println("Oops! You need to provide a description/time for your task");
            line = in.nextLine();
            whatToDo(line);
        }
    }

    public static boolean isWordPresent(Task task, String keyword) {
        String[] words = (task.description).split(" ");

        for (String temp : words) {
            if (temp.compareToIgnoreCase(keyword) == 0) {
                return true;
            }
        }
        return false;
    }

    public static void command(String line) throws InvalidCommandException {
        String exitCommand = "bye";
        String taskList = "list";
        String checkAsDone = "mark";
        String del = "delete";
        String search = "find";
        int numberOfTasks = 0;

        String[] tokens = line.split(" ", 2);
        String command = tokens[0];

        while (!command.equals(exitCommand)) {
            if (tokens[0].equals(taskList)) {
                System.out.println("Here are your list of tasks to complete!");
                for (int i = 0; i < numberOfTasks; ++i) {
                    System.out.format("%d. ", (i + 1));
                    System.out.println(tasks[i]);
                }
                line = in.nextLine();
                tokens = line.split(" ", 2);
                command = tokens[0];
            } else if (command.equals(checkAsDone)){
                int finishedTask = Integer.parseInt(tokens[1]) - 1;
                tasks[finishedTask].markDone();
                System.out.println("Alright! I have marked the task as complete!");

                for (int i = 0; i < numberOfTasks; ++i) {
                    System.out.format("%d. ", (i + 1));
                    System.out.println(tasks[i]);
                }

                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            } else if (command.equals(del)) {
                int taskToDelete = Integer.parseInt(tokens[1]) - 1;
                for (int i = taskToDelete; i < (tasks.length - 1); ++i) {
                    tasks[i] = tasks[i + 1];
                }
                --numberOfTasks;
                System.out.println("Removed task number " + (taskToDelete + 1) + ". Here are your remaining tasks:");
                for (int i = 0; i < numberOfTasks; ++i) {
                    System.out.format("%d. ", (i + 1));
                    System.out.println(tasks[i]);
                }

                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            } else if (command.equals(search)) {
                String itemToFind = tokens[1];
                int searchNumber = 0;

                for (int i = 0; i < numberOfTasks; ++i) {
                    if (isWordPresent(tasks[i], itemToFind)) {
                        foundTasks[searchNumber] = tasks[i];
                        searchNumber++;
                    }
                }
                System.out.println("Tasks matching with " + itemToFind);
                for (int i = 0; i < searchNumber; ++i) {
                    System.out.format("%d. ", (i + 1));
                    System.out.println(foundTasks[i]);
                }

                line = in.nextLine();
                tokens = line.split(" ", 0);
                command = tokens[0];
            } else {
                    String task = tokens[1];
                    switch(command) {
                    case "todo":
                        Task t = new ToDo(task);
                        tasks[numberOfTasks] = t;
                        tasks[numberOfTasks].isDone = false;
                        ++numberOfTasks;
                        break;
                    case "deadline":
                        String[] taskDeadline = task.split("/", 2);
                        String taskDescription = taskDeadline[0];
                        String dueBy = taskDeadline[1];

                        Task d = new Deadline(taskDescription, dueBy);
                        tasks[numberOfTasks] = d;
                        tasks[numberOfTasks].isDone = false;
                        ++numberOfTasks;
                        break;
                    case "event":
                        String[] taskEvent = task.split("/", 3);
                        String eventDescription = taskEvent[0];
                        String start = taskEvent[1];
                        String end = taskEvent[2];

                        Task e = new Event(eventDescription, start, end);
                        tasks[numberOfTasks] = e;
                        tasks[numberOfTasks].isDone = false;
                        ++numberOfTasks;
                        break;
                    default:
                        System.out.println("Sorry! I couldn't understand your command:( Please try again.");
                        throw new InvalidCommandException();
                    }
                    System.out.println("added: " + line);
                    line = in.nextLine();
                    tokens = line.split(" ", 0);
                    command = tokens[0];
                }
            }
        System.out.println("Bye! Hope to see you again");
    }

    public static void main(String[] args) {
        String logo = " ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠋⠈⠙⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠤⢤⡀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⠈⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠞⠀⠀⢠⡜⣦⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡃⠀⠀⠀⠀⠈⢷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⠊⣠⠀⠀⠀⠀⢻⡘⡇\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠃⠀⠀⠀⠀⠀⠀⠙⢶⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠚⢀⡼⠃⠀⠀⠀⠀⠸⣇⢳\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⣀⠖⠀⠀⠀⠀⠉⠀⠀⠈⠉⠛⠛⡛⢛⠛⢳⡶⠖⠋⠀⢠⡞⠀⠀⠀⠐⠆⠀⠀⣿⢸\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣦⣀⣴⡟⠀⠀⢶⣶⣾⡿⠀⠀⣿⢸\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⡠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠻⣏⠀⠀⠀⣶⣿⣿⡇⠀⠀⢏⡞\n" +
                "⠀⠀⠀⠀⠀⠀⢀⡴⠛⠀⠀⠀⠀⠀⠀⠀⠀⢀⢀⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢦⣤⣾⣿⣿⠋⠀⠀⡀⣾⠁\n" +
                "⠀⠀⠀⠀⠀⣠⠟⠁⠀⠀⠀⣀⠀⠀⠀⠀⢀⡟⠈⢀⣤⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠙⣏⡁⠀⠐⠚⠃⣿⠀\n" +
                "⠀⠀⠀⠀⣴⠋⠀⠀⠀⡴⣿⣿⡟⣷⠀⠀⠊⠀⠴⠛⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠀⠀⠀⠀⢹⡆\n" +
                "⠀⠀⠀⣴⠃⠀⠀⠀⠀⣇⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⡶⢶⣶⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                "⠀⠀⣸⠃⠀⠀⠀⢠⠀⠊⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⢲⣾⣿⡏⣾⣿⣿⣿⣿⠖⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢧\n" +
                "⠀⢠⡇⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠈⠛⠿⣽⣿⡿⠏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜\n" +
                "⢀⡿⠀⠀⠀⠀⢀⣤⣶⣟⣶⣦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                "⢸⠇⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                "⣼⠀⢀⡀⠀⠀⢷⣿⣿⣿⣿⣿⣿⡿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇\n" +
                "⡇⠀⠈⠀⠀⠀⣬⠻⣿⣿⣿⡿⠙⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁\n" +
                "⢹⡀⠀⠀⠀⠈⣿⣶⣿⣿⣝⡛⢳⠭⠍⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀\n" +
                "⠸⡇⠀⠀⠀⠀⠙⣿⣿⣿⣿⣿⣿⣷⣦⣀⣀⣀⣤⣤⣴⡶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠇⠀⠀\n" +
                "⠀⢿⡄⠀⠀⠀⠀⠀⠙⣇⠉⠉⠙⠛⠻⠟⠛⠛⠉⠙⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠋⠀⠀⠀\n" +
                "⠀⠈⢧⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀\n" +
                "⠀⠀⠘⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠱⢆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠛⢦⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⠴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⠲⠤⣤⣤⣤⣄⠀⠀⠀⠀⠀⠀⠀⢠⣤⣤⠤⠴⠒⠛⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hi! My name is Doge. \n Nice to meet you!");

        String line;
        line = in.nextLine();
        whatToDo(line);
    }
}
