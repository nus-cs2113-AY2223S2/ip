import java.util.Scanner;
public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";

    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        String inputString;
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;

        while (true) {
            try {
                inputString = in.nextLine();
                String[] command = inputString.split(" ", 2);
                //list command
                switch (command[0]) {
                case "list":
                    doList(tasks, numberOfTasks);
                    break;
                //bye command
                case "bye":
                    doExit();
                    return;
                //mark/unmark command
                case "mark":
                case "unmark":
                    doMarkOrUnmarked(tasks, numberOfTasks, command);
                    break;
                //add task to list
                case "todo":
                    addTodo(tasks, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                case "deadline":
                    addDeadline(tasks, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                case "event":
                    addEvent(tasks, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                default:
                    System.out.println("Unknown command issued");
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Missing arguments");
            }
            catch (DukeException e) {
            }
        }
    }


    private static void addTodo(Task[] tasks, int numberOfTasks, String[] command) {
            tasks[numberOfTasks] = new Todo(command[1], numberOfTasks + 1);
            tasks[numberOfTasks].printAddTask();

    }

    private static void addDeadline (Task[] tasks, int numberOfTasks, String[] command) throws DukeException {
            if (!command[1].contains("/by")) {
                System.out.println("Error: Use /by");
                throw new DukeException();
            }
            String[] deadLineInputs = command[1].split("/by", 2);
            tasks[numberOfTasks] = new Deadline(deadLineInputs[0], numberOfTasks + 1, deadLineInputs[1]);
            tasks[numberOfTasks].printAddTask();
    }

    private static void addEvent(Task[] tasks, int numberOfTasks, String[] command) throws DukeException {
        if (!(command[1].contains("/from") && command[1].contains("/to"))) {
            System.out.println("Error: Use /from and /to");
            throw new DukeException();
        }
            String[] eventInputs = command[1].split("/from|/to");
            tasks[numberOfTasks] = new Event(eventInputs[0], numberOfTasks + 1, eventInputs[1], eventInputs[2]);
            tasks[numberOfTasks].printAddTask();
    }

    private static void doMarkOrUnmarked (Task[] tasks, int numberOfTasks, String[] command) throws DukeException{
        int indexToChange = Integer.parseInt(command[1]) - 1;
        //Check for error
        if (indexToChange >= numberOfTasks || indexToChange < 0) {
            System.out.println("Index not found");
            throw new DukeException();
        }
        if (command[0].equals("mark")) {
            tasks[indexToChange].setDone();
            tasks[indexToChange].printMarkedTask();
        } else {
            tasks[indexToChange].setNotDone();
            tasks[indexToChange].printUnmarkedTask();
        }
    }

    private static void printWelcome() {
        String logo = BLANK + LINE + "\n"
                + BLANK + "Hello! I'm Duke\n"
                + BLANK + "What can I do for you?\n"
                + BLANK + LINE;
        System.out.println("\n" + logo);
    }

    private static void doList(Task[] tasks, int numberOfTasks) {
        System.out.println(BLANK + LINE);
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print(BLANK + (i + 1) + ".");
            tasks[i].printTask();
        }
        System.out.println(BLANK + LINE);
    }
    private static void doExit() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Bye. Hope to see you again soon!");
        System.out.println(BLANK + LINE);
    }
}
