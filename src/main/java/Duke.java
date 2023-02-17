import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String LINE = "____________________________________________________________";
    private static final String BLANK = "    ";

    public static void main(String[] args) {
        printWelcome();
        Scanner in = new Scanner(System.in);
        String inputString;
        ArrayList<Task> taskList = new ArrayList<>();
        int numberOfTasks = 0;

        while (true) {
            try {
                inputString = in.nextLine();
                String[] command = inputString.split(" ", 2);

                switch (command[0]) {
                case "list":
                    doList(taskList, numberOfTasks);
                    break;

                case "bye":
                    doExit();
                    return;

                case "delete":
                    deleteTask(taskList, Integer.parseInt(command[1]) - 1, numberOfTasks);
                    numberOfTasks -= 1;
                    break;

                //mark/unmark command
                case "mark":
                case "unmark":
                    doMarkOrUnmarked(taskList, numberOfTasks, command);
                    break;

                //add task to list
                case "todo":
                    addTodo(taskList, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                case "deadline":
                    addDeadline(taskList, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                case "event":
                    addEvent(taskList, numberOfTasks, command);
                    numberOfTasks += 1;
                    break;

                default:
                    System.out.println("Unknown command issued");
                }
            }
            catch (IndexOutOfBoundsException e) {
                System.out.println("Wrong index");
            }
            catch (NumberFormatException e) {
                System.out.println("Number only for argument");
            }
            catch (DukeException e) {
            }
        }
    }


    private static void addTodo(ArrayList<Task> tasks, int numberOfTasks, String[] command) {
            tasks.add(new Todo(command[1]));
            tasks.get(numberOfTasks).printAddTask(numberOfTasks);

    }

    private static void addDeadline (ArrayList<Task> tasks, int numberOfTasks, String[] command) throws DukeException {
            if (!command[1].contains("/by")) {
                System.out.println("Error: Use /by");
                throw new DukeException();
            }
            String[] deadLineInputs = command[1].split("/by", 2);
            tasks.add(new Deadline(deadLineInputs[0],deadLineInputs[1]));
            tasks.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    private static void addEvent(ArrayList<Task> tasks, int numberOfTasks, String[] command) throws DukeException {
        if (!(command[1].contains("/from") && command[1].contains("/to"))) {
            System.out.println("Error: Use /from and /to");
            throw new DukeException();
        }
            String[] eventInputs = command[1].split("/from|/to");
            tasks.add(new Event(eventInputs[0], eventInputs[1], eventInputs[2]));
            tasks.get(numberOfTasks).printAddTask(numberOfTasks);
    }

    private static void doMarkOrUnmarked (ArrayList<Task> tasks, int numberOfTasks, String[] command) throws DukeException{
        int indexToChange = Integer.parseInt(command[1]) - 1;
        //Check for error
        if (indexToChange >= numberOfTasks || indexToChange < 0) {
            System.out.println("Index not found");
            throw new DukeException();
        }
        if (command[0].equals("mark")) {
            tasks.get(indexToChange).setDone();
            tasks.get(indexToChange).printMarkedTask();
        } else {
            tasks.get(indexToChange).setNotDone();
            tasks.get(indexToChange).printUnmarkedTask();
        }
    }

    private static void printWelcome() {
        String logo = BLANK + LINE + "\n"
                + BLANK + "Hello! I'm Duke\n"
                + BLANK + "What can I do for you?\n"
                + BLANK + LINE;
        System.out.println("\n" + logo);
    }

    private static void doList(ArrayList<Task> tasks, int numberOfTasks) {
        System.out.println(BLANK + LINE);
        for (int i = 0; i < numberOfTasks; i += 1) {
            System.out.print(BLANK + (i + 1) + ".");
            tasks.get(i).printTask();
        }
        System.out.println(BLANK + LINE);
    }
    private static void doExit() {
        System.out.println(BLANK + LINE);
        System.out.println(BLANK + "Bye. Hope to see you again soon!");
        System.out.println(BLANK + LINE);
    }
    private static void deleteTask(ArrayList<Task> tasks, int indexToDelete, int numberOfTasks) {
        tasks.get(indexToDelete).printDeleteTask(numberOfTasks);
        tasks.remove(indexToDelete);
    }
}
