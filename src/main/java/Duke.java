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
                int indexToChange = Integer.parseInt(command[1]) - 1;
                //Check for error
                if (!(indexToChange < numberOfTasks)) {
                    System.out.println("Error");
                    continue;
                }
                if (command[0].equals("mark")) {
                    tasks[indexToChange].setDone();
                    tasks[indexToChange].printMarkedTask();
                } else {
                    tasks[indexToChange].setNotDone();
                    tasks[indexToChange].printUnmarkedTask();
                }
            break;
            //add task to list
            case "todo":
                tasks[numberOfTasks] = new Task(command[1], numberOfTasks + 1);
                tasks[numberOfTasks].printAddTask();
                numberOfTasks += 1;
                break;

            case "deadline":
                if (!command[1].contains("/by")) {
                    System.out.println("Error: Use /by");
                    break;
                }
                String[] deadLineInputs = command[1].split("/by", 2);
                tasks[numberOfTasks] = new Deadline(deadLineInputs[0], numberOfTasks + 1, deadLineInputs[1]);
                tasks[numberOfTasks].printAddTask();
                numberOfTasks += 1;
                break;

            case "event":
                if (!(command[1].contains("/from") || command[1].contains("/to")) ) {
                    System.out.println("Error: Use /from and /to");
                    break;
                }
                String eventInputs[] = command[1].split("/from|/to");
                tasks[numberOfTasks] = new Event(eventInputs[0], numberOfTasks + 1, eventInputs[1], eventInputs[2]);
                tasks[numberOfTasks].printAddTask();
                numberOfTasks += 1;
                break;
            }
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
